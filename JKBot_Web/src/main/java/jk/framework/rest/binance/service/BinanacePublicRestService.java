package jk.framework.rest.binance.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceAskBidResultEntity;
import jk.framework.rest.binance.entity.BinanceAskResultEntity;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;

@Service
public class BinanacePublicRestService {
	
	public List<BinanceTickerResultEntity> getTicker(String apiUrl){
		return getAllTicker(apiUrl, null, "USDT");
	}
	
	public List<BinanceTickerResultEntity> getTicker(String apiUrl, HashSet<String> coinList, String symbolType){
		
		BinanceTickerResultEntity entity = null;
		List<BinanceTickerResultEntity> returnEntity = new ArrayList<BinanceTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		for(String str : coinList) {
			try {
				Thread.sleep(10);	// 1000이 1초
				String param = "?symbol=" + str + symbolType;
				String result = api.callUpbitApi("/v3/ticker/price"+param, null);
			    Gson gson = new Gson();
			    entity = gson.fromJson(result, BinanceTickerResultEntity.class ); 
			    
			    String symbol = entity.getSymbol();
			    if("BTC".equals(symbolType)) {
	    			if(symbol.substring(symbol.length()-3).indexOf("BTC") > -1){
	    				symbol = symbol.replaceAll(symbolType, "");
	    			}		    			
	    		}else if("USDT".equals(symbolType)) {
	    			if(symbol.substring(symbol.length()-4).indexOf("USDT") > -1){
	    				symbol = symbol.replaceAll(symbolType, "");
	    			}
	    		}
				entity.setLastPrice(entity.getPrice());
				entity.setTradeType(symbol);
			    returnEntity.add(entity);
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		
		return returnEntity;
	}
	
	public List<BinanceAskResultEntity> getBidAskPrice(String apiUrl){
		return getBidAskPrice(apiUrl, null, "USDT");
	}
	
	public List<BinanceAskResultEntity> getBidAskPrice(String apiUrl, HashSet<String> coinList, String symbolType){
		
		BinanceAskBidResultEntity entity = null;
		List<BinanceAskResultEntity> resultList = new ArrayList<BinanceAskResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);

		// 현재 Binance BTC 가격 가져와서 500만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = 0.66;					// 500만원 (추후 실제 구매 가능 금액으로 변경 필요함)
		double totalBuyPrice = 0.0;	// 구매 금액 누적치
		for(String str : coinList) {
			try {
					double purchasableAmount = BtcPrice;	// 구매 가능 금액
					// 결과 리스트 저장할 VO
					BinanceAskResultEntity resultEntity = new BinanceAskResultEntity();
					Thread.sleep(10);	// 1000이 1초
					String param = "?symbol=" + str + symbolType;
					String result = api.callUpbitApi("/v1/depth"+param, null);
				    Gson gson = new Gson();
				    entity = gson.fromJson(result, BinanceAskBidResultEntity.class ); 
				    
				    // API 리턴값 저장
				    Object[] bids = entity.getBids().toArray();
				    Double coinAmount = 0.0;		// 구매 가능 코인수
				    /*
				     * 구매 수량 계산 : 
					 *	CASE 1
					 *		구매 가능한 코인 수 > 현재 잔고/코인가격
					 *		모두 매수 (구매 수량에 추가 및 현재 잔고 마이너스)
					 *		
					 *	CASE 2
					 *		구매 가능한 코인 수 < 현재 잔고/코인가격
					 *		일부 매수 (코인 수:코인 가격 / 현재 잔고)
				     * 평균 단가 계산 : 구매한 코인 수 / 현재 잔고
				     */
				    for (Object object : bids) {
				    	// ,로 자르고, 공백 [ 제거
				    	String row = object.toString().replaceAll("\\[", "").replaceAll("\\]", "");
				    	String[] priceArray = row.split(",");
				    	Double array1 = Double.parseDouble(priceArray[0].trim());	// 사토시
				    	Double array2 = Double.parseDouble(priceArray[1].trim());	// 구매 가능 수량
				    	
				    	Double temp = JKStringUtil.mathRound(array1*array2,9);
				    	// 로직 추가
				    	if(purchasableAmount >= temp) {
				    		coinAmount += array2;
				    		purchasableAmount = purchasableAmount - (array1*array2);
				    	}else if(purchasableAmount < temp){
				    		coinAmount += (purchasableAmount / array1);
			    			purchasableAmount = purchasableAmount - (array1*array2);	
				    	}
				    	
				    	// System.out.println(str + ":::temp:::" + temp + ":::purchasableAmount::" + purchasableAmount);
				    	if(purchasableAmount < 0.0) {
				    		if(coinAmount > 0) {
				    			resultEntity.setCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		resultEntity.setCoinSymbolName(str);
				    		resultEntity.setCoinAveragePrice(((BtcPrice*7300000)/coinAmount)+"");
				    		resultList.add(resultEntity);
				    		// System.out.println(str + ":::coinAmount::" + coinAmount);
				    		// System.out.println("purchasableAmount22::" + ((BtcPrice*7300000)/coinAmount));
				    		break;
				    	}
				    	
				    	/*
				    	Double result22 = array1 * array2;
				    	totalBuyPrice += JKStringUtil.mathRound(result22,9);
				    	if(BtcPrice < totalBuyPrice) {
				    		
				    		 * 총 (BTC 가격 / 구매 원하는 BTC 가격) * 코인 수
				    		 
				    		Double realCoinAmount = 0.0;
				    		realCoinAmount = ( BtcPrice / JKStringUtil.mathRound(totalBuyPrice, 3)) * coinAmount;
				    		
				    		System.out.println(str + ":::" + JKStringUtil.mathRound(totalBuyPrice, 8));
				    		System.out.println("코인수 : " + realCoinAmount );
				    		System.out.println("코인 평균가 : " + (BtcPrice / realCoinAmount)*7414443);
				    		resultEntity.setCoinAmout(realCoinAmount.toString());
				    		resultEntity.setCoinSymbolName(str);
				    		resultEntity.setCoinAveragePrice(( ((BtcPrice / realCoinAmount)*7414443))+"");
				    		resultList.add(resultEntity);
				    		break;
				    	}*/
				    }
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		
		return resultList;
	}
	
	public List<BinanceTickerResultEntity> getAllTicker(String apiUrl){
		return getAllTicker(apiUrl, null, "USDT");
	}
	
	public List<BinanceTickerResultEntity> getAllTicker(String apiUrl, HashSet<String> coinList, String symbolType){
		
		List<BinanceTickerResultEntity> entity = null;
		List<BinanceTickerResultEntity> returnEntity = new ArrayList<BinanceTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		try {
		    String result = api.callBinanceApi("/v1/ticker/24hr", null);
		    Gson gson = new Gson();
		    
		    entity = gson.fromJson(result, new TypeToken<List<BinanceTickerResultEntity>>(){}.getType()); 
		    for (BinanceTickerResultEntity binanceTickerResultEntity : entity) {
		    	if(coinList != null) {
		    		
		    		/*// 지정한 코인만 찾아서 리턴
		    		if(symbol.substring(symbol.length()-4).indexOf("USDT") > -1){
		    			symbol = symbol.replaceAll("USDT", "");
		    			addEntity = true;
		    		}else if(symbol.substring(symbol.length()-3).indexOf("BTC") > -1) {
		    			symbol = symbol.replaceAll("BTC", "");
		    			addEntity = true;
		    		}*/
		    		String symbol = binanceTickerResultEntity.getSymbol();
		    		boolean addYN = false;
		    		if("BTC".equals(symbolType)) {
		    			if(symbol.substring(symbol.length()-3).indexOf("BTC") > -1){
		    				symbol = symbol.replaceAll(symbolType, "");
		    				addYN = true;
		    			}		    			
		    		}else if("USDT".equals(symbolType)) {
		    			if(symbol.substring(symbol.length()-4).indexOf("USDT") > -1){
		    				symbol = symbol.replaceAll(symbolType, "");
		    				addYN = true;
		    			}
		    		}
		    		
		    		if(addYN && coinList.contains(symbol)) {
		    			if( binanceTickerResultEntity.getSymbol().contains(symbolType) ) {
		    				binanceTickerResultEntity.setTradeType(symbol);
		    				returnEntity.add(binanceTickerResultEntity);
		    			}
		    		}
		    	}else {
		    		// 모든 결과 리턴
		    		returnEntity = entity;
		    	}
			}
		    // DB에 시세 저장
		    // publicService.save(entity);
		   /* List<BinanceTickerData> test = entity.getList();
		    for (BinanceTickerData binanceTickerData : test) {
				System.out.println("bbbbbbbbbbbb:"+binanceTickerData.getSymbol());
			}*/
			
		} catch (Exception e) {
		   // e.printStackTrace();
		}
		return returnEntity;
	}
}
