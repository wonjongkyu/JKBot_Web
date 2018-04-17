package jk.framework.rest.binance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;

@Service
public class BinanacePublicRestService {
	
	public List<BinanceTickerResultEntity> getTicker(String apiUrl){
		return getTicker(apiUrl, null, "USDT");
	}
	
	public List<BinanceTickerResultEntity> getTicker(String apiUrl, HashSet<String> coinList, String symbolType){
		
		List<BinanceTickerResultEntity> entity = null;
		List<BinanceTickerResultEntity> returnEntity = new ArrayList<BinanceTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		try {
		    String result = api.callBinanceApi("/ticker/24hr", null);
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
