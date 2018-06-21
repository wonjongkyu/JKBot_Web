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
		List<BinanceAskResultEntity> returnEntity = new ArrayList<BinanceAskResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);

		// 현재 Binance BTC 가격 가져와서 500만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = 0.66;		// 500만원
		double resultPrice = 0.0;
		for(String str : coinList) {
			try {
				if(str.equals("XLM")) {
					Thread.sleep(10);	// 1000이 1초
					String param = "?symbol=" + str + symbolType;
					String result = api.callUpbitApi("/v1/depth"+param, null);
				    Gson gson = new Gson();
				    entity = gson.fromJson(result, BinanceAskBidResultEntity.class ); 
				    
				    // System.out.println("test:::" + returnEntity.getBids());
				    Object[] bids = entity.getBids().toArray();
				    Double coinAmount = 0.0;
				    for (Object object : bids) {
				    	// ,로 자르고, 공백 [ 제거
				    	String btc = object.toString().replaceAll("\\[", "").replaceAll("\\]", "");
				    	String[] priceArray = btc.split(",");
				    	Double array1 = Double.parseDouble(priceArray[0].trim());
				    	Double array2 = Double.parseDouble(priceArray[1].trim());
				    	coinAmount += array2;
				    	Double result22 = array1 * array2;
				    	resultPrice += JKStringUtil.mathRound(result22,9);
				    	if(BtcPrice < resultPrice) {
				    		/*
				    		 * 총 (BTC 가격 / 구매 원하는 BTC 가격) * 코인 수
				    		 */
				    		Double realCoinAmount = 0.0;
				    		realCoinAmount = ( BtcPrice / JKStringUtil.mathRound(resultPrice, 3)) * coinAmount;
				    		
				    		System.out.println(str + ":::" + JKStringUtil.mathRound(resultPrice, 8));
				    		System.out.println("코인수 : " + realCoinAmount );
				    		System.out.println("코인 평균가 : " + (BtcPrice / realCoinAmount)*7414443);
				    		break;
				    	}
				    }
				}
			} catch (Exception e) {
			    // e.printStackTrace();
			}
		}
		
		return returnEntity;
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
