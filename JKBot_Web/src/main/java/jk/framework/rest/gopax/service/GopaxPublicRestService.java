package jk.framework.rest.gopax.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;

@Service
public class GopaxPublicRestService {
	
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
