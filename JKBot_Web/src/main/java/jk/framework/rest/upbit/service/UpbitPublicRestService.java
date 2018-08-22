package jk.framework.rest.upbit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.etc.SessionService;
import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.upbit.entity.UpbitAskBidResultEntity;
import jk.framework.rest.upbit.entity.UpbitAskBidResultEntity.ORDERBOOK;
import jk.framework.rest.upbit.entity.UpbitResultEntity;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;

@Service
public class UpbitPublicRestService {
	
    @Autowired
    SessionService sessionService;
	
	public List<UpbitTickerResultEntity> getTicker(String apiUrl){
		HashSet<String> coinList = new HashSet<String>();
		coinList.add("BTC");
		return getTicker(apiUrl, null);
	}
	
	public List<UpbitTickerResultEntity> getTicker(String apiUrl, HashSet<String> coinList) {
		List<UpbitTickerResultEntity> entity = null;
		List<UpbitTickerResultEntity> resultEntity = new ArrayList<UpbitTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		for(String str : coinList) {
			try {
				Thread.sleep(50);	// 1000이 1초
				String param = "/minutes/1?code=CRIX.UPBIT.KRW-" + str;
				String result = api.callUpbitApi("/candles/"+param, null);
			    Gson gson = new Gson();
			    entity = gson.fromJson(result, new TypeToken<List<UpbitTickerResultEntity>>(){}.getType()); 
			    for (UpbitTickerResultEntity upbitTickerResultEntity : entity) {
			    	upbitTickerResultEntity.setTradeType(str);
			    	resultEntity.add(upbitTickerResultEntity);
				}
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		 
		return resultEntity;
	}
	
	
	public List<UpbitResultEntity> getBidAskPrice(String apiUrl, HashSet<String> coinList, String symbolType, double exchangeRate, double buyPrice){
		
		List<UpbitAskBidResultEntity> entity = null;
		List<UpbitResultEntity> resultList = new ArrayList<UpbitResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		// 현재 Upbit BTC 가격 가져와서 400만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = buyPrice;	// 400만원 (추후 실제 구매 가능 금액으로 변경 필요함)
		
		for(String str : coinList) {
			try {
					double purchasableAmount = BtcPrice;	// 구매 가능 금액
					// 결과 리스트 저장할 VO
					UpbitResultEntity resultEntity = new UpbitResultEntity();
					Thread.sleep(10);	// 1000이 1초
					String param = "?markets=KRW-" + str;
					String result = api.callUpbitApi("/v1/orderbook"+param, null);
				    Gson gson = new Gson();
				    entity = gson.fromJson(result, new TypeToken<List<UpbitAskBidResultEntity>>(){}.getType()); 
				    
	 
				    // 업비트 매도물량 체크
				    List<ORDERBOOK> bids = entity.get(0).getOrderbook_units();
				    Double coinAmount = 0.0;		// 구매 가능 코인수
				    for (ORDERBOOK object : bids) {
				    	Double array1 = Double.parseDouble(object.getBid_price());
				    	Double array2 = Double.parseDouble(object.getBid_size());
				    	Double temp = JKStringUtil.mathRound(array1*array2,9);
				    	
				    	// 로직 추가
				    	if(purchasableAmount >= temp) {
				    		coinAmount += array2;
				    		purchasableAmount = purchasableAmount - (array1*array2);
				    	}else if(purchasableAmount < temp){
				    		coinAmount += (purchasableAmount / array1);
			    			purchasableAmount = 0;
				    	}
				    	
				    	if(purchasableAmount <= 0.0) {
				    		if(coinAmount > 0) {
				    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		
				    		resultEntity.setCoinSymbolName(str);
				    		resultEntity.setBidCoinAveragePrice(((BtcPrice)/coinAmount)+"");
				    		break;
				    	}
				    }
				    
				    // API 리턴값 저장
				    purchasableAmount = BtcPrice;	// 구매 가능 금액
				    List<ORDERBOOK> asks = entity.get(0).getOrderbook_units();
				    coinAmount = 0.0;		// 구매 가능 코인수
				    for (ORDERBOOK object : asks) {
				    	Double array1 = Double.parseDouble(object.getAsk_price());
				    	Double array2 = Double.parseDouble(object.getAsk_size());
				    	Double temp = JKStringUtil.mathRound(array1*array2,9);
				    	
				    	// 로직 추가
				    	if(purchasableAmount >= temp) {
				    		coinAmount += array2;
				    		purchasableAmount = purchasableAmount - (array1*array2);
				    	}else if(purchasableAmount < temp){
				    		coinAmount += (purchasableAmount / array1);
			    			purchasableAmount = 0;
				    	}
				    	
				    	if(purchasableAmount <= 0.0) {
				    		if(coinAmount > 0) {
				    			resultEntity.setAskCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setAskCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		resultEntity.setAskCoinAveragePrice(((BtcPrice)/coinAmount)+"");
				    		resultList.add(resultEntity);
				    		break;
				    	}
				    }
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		
		return resultList;
	}
}
