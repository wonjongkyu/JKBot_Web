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
import jk.framework.rest.binance.entity.BinanceAskBidResultEntity;
import jk.framework.rest.binance.entity.BinanceAskResultEntity;
import jk.framework.rest.upbit.entity.UpbitAskBidResultEntity;
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
	
	
	public List<UpbitAskBidResultEntity> getBidAskPrice(String apiUrl, HashSet<String> coinList, String symbolType, double exchangeRate){
		
		List<UpbitAskBidResultEntity> entity = null;
		List<UpbitAskBidResultEntity> resultList = new ArrayList<UpbitAskBidResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		// 현재 Binance BTC 가격 가져와서 500만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = 4000000;	// 400만원 (추후 실제 구매 가능 금액으로 변경 필요함)
		
		for(String str : coinList) {
			try {
					double purchasableAmount = BtcPrice;	// 구매 가능 금액
					// 결과 리스트 저장할 VO
					UpbitAskBidResultEntity resultEntity = new UpbitAskBidResultEntity();
					Thread.sleep(10);	// 1000이 1초
					String param = "?markets=KRW-" + str;
					String result = api.callUpbitApi("/v1/orderbook"+param, null);
				    Gson gson = new Gson();
				    entity = gson.fromJson(result, new TypeToken<List<UpbitAskBidResultEntity>>(){}.getType()); 
				    	
				    /*// API 리턴값 저장
				    Object[] bids = entity.get
				    Double coinAmount = 0.0;		// 구매 가능 코인수
				    
				    for (Object object : bids) {
				    	// ,로 자르고, 공백 [ 제거
				    	String row = object.toString().replaceAll("\\[", "").replaceAll("\\]", "");
				    	String[] priceArray = row.split(",");
				    	// array1에서 0이 아닌 숫자가 나올떄까지 진행
				    	String priceArray1 = priceArray[0].trim();
				    	// 총 10자리 맞추기 0.00000001 사토시 
				    	while(priceArray1.length() < 10) {
				    		priceArray1 += "0";
				    	}
				    	String arrayStr = "";
				    	char[] price0_char = priceArray1.toCharArray();
				    	for (int i=2; i<price0_char.length; i++) {
				    		if(price0_char[i] != '0' ) {
				    			arrayStr = priceArray1.substring(i,priceArray1.length());
				    			break;
				    		}
						}
				    	
				    	Double array1 = Double.parseDouble(arrayStr)* (BTCKRW/100000000);				// 사토시 (정수형으로 변환)
				    	Double array2 = Double.parseDouble(priceArray[1].trim());	// 구매 가능 수량
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
				    		System.out.println(BtcPrice);
				    		System.out.println(exchangeRate);
				    		System.out.println( BtcPrice*exchangeRate*6300 );
				    		System.out.println(str + ":::coinAmount:::" + coinAmount + ":::purchasableAmount::" + ((BtcPrice*exchangeRate*6300)/coinAmount));
				    		
				    		if(coinAmount > 0) {
				    			resultEntity.setCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		
				    		resultEntity.setCoinSymbolName(str);
				    		resultEntity.setCoinAveragePrice( ((BtcPrice)/coinAmount)+"");
				    		resultList.add(resultEntity);
				    		break;
				    	}
				    }*/
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		
		return resultList;
	}
}
