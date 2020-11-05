package jk.framework.rest.huobi.service;

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
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;

@Service
public class HuobiPublicRestService {
	
    @Autowired
    SessionService sessionService;
    
	public List<BinanceTickerResultEntity> getTicker(String apiUrl){
		return getAllTicker(apiUrl, null, "USDT");
	}
	
	public List<BinanceTickerResultEntity> getTicker(String apiUrl, HashSet<String> coinList, String symbolType){
		
		BinanceTickerResultEntity entity = null;
		List<BinanceTickerResultEntity> returnEntity = new ArrayList<BinanceTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		for(String str : coinList) {
			if(!str.equals(symbolType)) {
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
		}
		
		return returnEntity;
	}
	
	public List<BinanceAskResultEntity> getBidAskPrice(String apiUrl){
		return getBidAskPrice(apiUrl, null, "USDT", 0, 0);
	}
	
	public List<BinanceAskResultEntity> getBidAskPrice(String apiUrl, HashSet<String> coinList, String symbolType, double exchangeRate, double buyPrice){
		
		BinanceAskBidResultEntity entity = null;
		List<BinanceAskResultEntity> resultList = new ArrayList<BinanceAskResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		// BTC-KRW 가격 (해외)
    	Double BTCKRW = 7474676D;
    	if(sessionService.getAttributeStr("BTCKRW") != null) {
    		BTCKRW = Double.parseDouble(sessionService.getAttribute("BTCKRW"));
    		System.out.println("[DEBUG]___BTCKRW=" + sessionService.getAttribute("BTCKRW") );
    	}
    	
    	String USDT_LIST = "|MFT|STMX|BTT|ANKR|TFUEL|NPXS|MBL|VET|IOST|SC|JST|";
    	
		// 현재 Binance BTC 가격 가져와서 500만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = buyPrice;			// 400만원 (추후 실제 구매 가능 금액으로 변경 필요함)
		int c = 0;
		for(String str : coinList) {
			try {
					if(c == 30) {
						Thread.sleep(2000);	// 1000이 1초
					}
					c++;
					double purchasableAmount = BtcPrice;	// 구매 가능 금액
					// 결과 리스트 저장할 VO
					BinanceAskResultEntity resultEntity = new BinanceAskResultEntity();
					Thread.sleep(50);	// 1000이 1초
					String param = "?limit=50&symbol=" + str;
					if(USDT_LIST.indexOf("|" + str + "|") <= -1) {
						param += symbolType;
					}else {
						param += "USDT";
					}
					String result = api.callUpbitApi("/v3/depth"+param, null);
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
				    
				    int num = 0;
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

				    	Double array1 = 0D;
				    	if(USDT_LIST.indexOf("|" + str + "|") <= -1) {
				    		array1 = Double.parseDouble(arrayStr)* (BTCKRW/100000000);				// 사토시 (정수형으로 변환)
				    	}else {
				    		array1 = Double.parseDouble(arrayStr)* (  Double.parseDouble(sessionService.getAttributeStr("exchangeRate"))/100000000);	
				    	}
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
				    	
				    	if(num == 0) {
				    		resultEntity.setAskCoinSatosiPrice( priceArray[0].trim() + "");
				    		resultEntity.setAskCoinAveragePrice( array1+"");
				    	}
				    	
				    	if(purchasableAmount <= 0.0) {
				    		/*System.out.println(BtcPrice);
				    		System.out.println(exchangeRate);
				    		System.out.println( BtcPrice*exchangeRate*6300 );
				    		System.out.println(str + ":::coinAmount:::" + coinAmount + ":::purchasableAmount::" + ((BtcPrice*exchangeRate*6300)/coinAmount));
				    		*/
				    		if(coinAmount > 0) {
				    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		
				    		resultEntity.setCoinSymbolName(str);
				    		resultEntity.setBidCoinSatosiPrice(priceArray[0].trim() + "");
				    		resultEntity.setBidCoinAveragePrice( ((BtcPrice)/coinAmount)+"");
				    		resultList.add(resultEntity);
				    		break;
				    	}
				    	num++;
				    }
				    
				    
				    /*// API 리턴값 저장
				    Object[] asks = entity.getAsks().toArray();
				    coinAmount = 0.0;		// 구매 가능 코인수
				    purchasableAmount = BtcPrice;
				    for (Object object : asks) {
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
				    			resultEntity.setAskCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
				    		}else {
				    			resultEntity.setAskCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
				    		}
				    		
				    		resultEntity.setAskCoinAveragePrice( ((BtcPrice)/coinAmount)+"");
				    		resultList.add(resultEntity);
				    		break;
				    	}
				    }*/
			} catch (Exception e) {
				System.out.println("====================================error");
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
		    String result = api.callBinanceApi("/v3/ticker/24hr", null);
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
