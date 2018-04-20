package jk.framework.web.price.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.etc.SessionService;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.price.entity.PriceCompareEntity;
import jk.framework.web.price.entity.PriceExchangeInfoEntity;
import jk.framework.web.price.service.PriceService;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/price")
@Controller
public class PriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	// bithumb apiKey
    @Value("${bithumb.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${bithumb.apiSecretKey}")
    private String apiSecretKey;
    
    @Value("${binance.apiUrl}")
    private String binanceApiUrl ;
    @Value("${upbit.apiUrl}")
    private String upbitApiUrl ;
	
    @Autowired
    BinanacePublicRestService binancePublicService;

    @Autowired
    UpbitPublicRestService upbitPublicService;
    
    @Autowired
    PriceService priceService;
    
    @Autowired
    SessionService sessionService;
	
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : compare
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     * @return
     */ 	
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
	public ModelAndView compare(Model model) {
		ModelAndView mav = new ModelAndView();
		// 환율 가져오기
		getExchangeRate(model);		
		// getPriceExchangeRate(model);
		mav.setViewName("/price/priceCompare");
		return mav;
    }
    
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 컨트롤러
     * 2. 처리내용 : 
     * 	2.1 업비트의 코인들을 가져온다. (KRW)
     * 	2.2 바이낸스의 코인들을 가져온다. (USDT 먼저..)
     * 	2.3 두 개를 merge 하는 작업
     * </pre>
     * @Method Name : priceCompare
     * @date : 2018. 4. 16.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 16.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     * @return
     */ 	
    @ResponseBody
    @RequestMapping(value = "/priceCompare/{symbolType}", method = RequestMethod.GET)
   	public List<PriceCompareEntity> priceCompare(Model model, @PathVariable String symbolType) {
    	List<PriceCompareEntity> result = new ArrayList<PriceCompareEntity>();
    	
    	// 환율
    	// priceService.getExchangeRate();
 		Double exchangeRate = Double.parseDouble(sessionService.getAttribute("exchangeRate"));
 		logger.info("exchangeRate:::{}", exchangeRate);
 		// 가져올 코인 코드
 		HashSet<String> coinList = new HashSet<String>();
 		coinList.add("BTC");
 		coinList.add("ETH");
 		coinList.add("BCC");
 		coinList.add("NEO");
 		coinList.add("LTC");
 		coinList.add("QTUM");
 		coinList.add("ADA");
 		
 		HashSet<String> coinList2 = new HashSet<String>();
 		coinList2.add("ADA");
 		coinList2.add("ARK");
 		coinList2.add("BCC");
 		coinList2.add("DASH");
 		coinList2.add("EOS");
 		coinList2.add("ETC");
 		// coinList2.add("ETH");
 		coinList2.add("GRS");
 		coinList2.add("ICX");
 		// coinList2.add("KMD");
 		coinList2.add("LSK");
 		coinList2.add("LTC");
 		coinList2.add("MCO");		// 모나코인 추가
 		coinList2.add("MTL");
 		coinList2.add("NEO");
 		coinList2.add("OMG");
 		coinList2.add("PIVX");
 		coinList2.add("POWR");
 		coinList2.add("QTUM");
 		coinList2.add("SNT");
 		coinList2.add("STEEM");
 		coinList2.add("STORJ");
 		coinList2.add("STORM");
 		coinList2.add("STRAT");
 		coinList2.add("TRX");
 		coinList2.add("WAVES");
 		coinList2.add("XEM");
 		coinList2.add("XLM");
 		// coinList2.add("XMR");
 		coinList2.add("XRP");
 		coinList2.add("ZEC");
 		
 		/*
 		 * 1.resultEntity 세팅
 		 */
 		Map<String, PriceCompareEntity> resultEntity = new HashMap<String, PriceCompareEntity>();
 		if(symbolType.equals("USDT")) {
	 		for (String coinSymbol : coinList) {
	 			PriceCompareEntity entity = new PriceCompareEntity();
	 			entity.setCoinSymbol(coinSymbol);
	 			resultEntity.put(coinSymbol, entity);
	 		}
 		}else if(symbolType.equals("BTC")) {
 			for (String coinSymbol : coinList2) {
	 			PriceCompareEntity entity = new PriceCompareEntity();
	 			entity.setCoinSymbol(coinSymbol);
	 			resultEntity.put(coinSymbol, entity);
	 		}
 			coinList = coinList2;
 		}
 		
 		List<BinanceTickerResultEntity> binanceResultEntity = binancePublicService.getTicker(binanceApiUrl,coinList, symbolType);
 		for (BinanceTickerResultEntity entity : binanceResultEntity) {
 			if("USDT".equals(symbolType)){
 				if(resultEntity.containsKey(entity.getTradeType())){
 					// USDT or BTC
 					String lastPrice = JKStringUtil.nvl(entity.getLastPrice(), "-");
 					resultEntity.get(entity.getTradeType()).setPriceUsdtB(String.valueOf(JKStringUtil.mathRound(lastPrice,2)));
 					if(!("-").equals(lastPrice)) {
 						// 소수 셋째자리에서 반올림
 						double priceKrw = JKStringUtil.parseDouble(lastPrice) * exchangeRate;
 						resultEntity.get(entity.getTradeType()).setPriceKrwB(JKStringUtil.mathKrwRound(priceKrw) );
 					}
 				}
 				// BTC - KRW는 Session에 저장해 둔다.
 	 			if("BTCUSDT".equals(entity.getSymbol())){
 	 				sessionService.setAttribute("BTCKRW", resultEntity.get("BTC").getPriceKrwB());
 	 				logger.info("BTCKRW:::{}", sessionService.getAttribute("BTCKRW"));
 	 			}
 			}else if("BTC".equals(symbolType)) {
 				if(resultEntity.containsKey(entity.getTradeType())){
 					double btckrw = Double.parseDouble(sessionService.getAttribute("BTCKRW"));
 					String lastPrice = JKStringUtil.nvl(entity.getLastPrice(), "-");
 					resultEntity.get(entity.getTradeType()).setPriceBtcB(String.valueOf(lastPrice));
 					
 					// 수수료 Get
 					resultEntity.get(entity.getTradeType()).setTransferFeeB(sessionService.getAttribute("binance_" + entity.getTradeType()));
 					
 					if(!("-").equals(lastPrice)) {
 						// 소수 셋째자리에서 반올림
 						double priceKrw = JKStringUtil.parseDouble(lastPrice) * btckrw;
 						resultEntity.get(entity.getTradeType()).setPriceKrwB(String.valueOf(JKStringUtil.mathRound(priceKrw,2)) );
 					}
 				}
 			}else {
 				return null;		// 예외처리 필요함
 			}
 		}

 		List<UpbitTickerResultEntity> upBitResultEntity = upbitPublicService.getTicker(upbitApiUrl, coinList);
 		for (UpbitTickerResultEntity entity : upBitResultEntity) {
 			if(resultEntity.containsKey(entity.getTradeType())){
 				String priceKrwA = JKStringUtil.nvl(entity.getTradePrice(), "-");
 				String priceKrwB = resultEntity.get(entity.getTradeType()).getPriceKrwB();
 				// 업비트 원화 가격을 받아왔을때.
 				if(!("-").equals(priceKrwA)) {
 					resultEntity.get(entity.getTradeType()).setPriceKrwA(String.valueOf(JKStringUtil.mathRound(priceKrwA,2)));
 					// 원화 차액을 계산하기 위해 바이낸스 가격을 가져왔을때. 원화차액 및 김프까지 계산
 					if(!("-").equals(priceKrwB)) {
 						// 원화 차액 계산
 						double krwGap = Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
 						
 						// 수수료 Get
 						resultEntity.get(entity.getTradeType()).setTransferFeeA(sessionService.getAttribute("upbit_" + entity.getTradeType()));
 						/* 김프 : ((업비트 - 바이낸스) x 100) / 바이낸스 (%)
 				         * 즉, 바이낸스 가격을 기준으로 김프를 산출합니다.
 						 */
 						double priceGapPercent = ((Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB)) * 100) / Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapPercent(JKStringUtil.mathRound(priceGapPercent,2));
 					}
 				}
 			}
 		}
 		
 		// 값 정상 세팅 확인
 		for( String key : resultEntity.keySet() ){
 			result.add(resultEntity.get(key));
 	        logger.info("키 : {}, 값 : {}", key, resultEntity.get(key));
 	    }
 		
 		// 상승률 내림차순 정렬
 		Collections.sort(result, new GapPercentDescCompare());
		
 		return result;
	}
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : compare
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     * @return
     */ 	
    @RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET)
	public void getExchangeRate(Model model) {
		// 환율
		Double exchangeRate = priceService.getExchangeRate();
		sessionService.setAttribute("exchangeRate", String.valueOf(exchangeRate) );
    }
    
    /**
     * <pre>
     * 1. 개요 : DB에 저장되어 있는 거래소별 코인 리스트 가져오기
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : getPriceExchangeRate
     * @date : 2018. 4. 20.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 20.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param model
     */ 	
    @ResponseBody
    @RequestMapping(value = "/priceExchangeInfo", method = RequestMethod.GET)
   	public List<PriceExchangeInfoEntity> priceExchangeInfo(Model model) {
    	List<PriceExchangeInfoEntity> listEntity = new ArrayList<PriceExchangeInfoEntity>();
    	PriceExchangeInfoEntity entity = new PriceExchangeInfoEntity();
   		// 환율
    	List<PriceExchangeInfoEntity> entityList = priceService.getAllExchangeInfo(entity);
    	for (PriceExchangeInfoEntity resultEntity : entityList) {
    		sessionService.setAttribute(resultEntity.getExchangeName() + "_" + resultEntity.getCoinSymbolName(), resultEntity.getCoinTransFeeKrw());
    		if(resultEntity.getExchangeName().equals("upbit")) {
    			listEntity.add(resultEntity);
    		}
		}
    	return listEntity;
    }
    
   
	/**
	 * <pre> 상승률 ASC
	 * jk.framework.web.price.controller 
	 *    |_ PriceController.java
	 * 
	 * </pre>
	 * @date : 2018. 4. 17. 오전 9:45:45
	 * @version : 
	 * @author : Hyundai
	 */
	static class GapPercentAscCompare implements Comparator<PriceCompareEntity> {
		/**
		 * 오름차순(ASC)
		 */
		@Override
		public int compare(PriceCompareEntity arg0, PriceCompareEntity arg1) {
			double d1 = arg0.getPriceGapPercent();
			double d2 = arg1.getPriceGapPercent();
			return d1 < d2 ? -1 : d1 > d2 ? 1:0;
		}
	}

 
	/**
	 * <pre> 상승률 DESC
	 * jk.framework.web.price.controller 
	 *    |_ PriceController.java
	 * 
	 * </pre>
	 * @date : 2018. 4. 17. 오전 9:46:32
	 * @version : 
	 * @author : Hyundai
	 */
	static class GapPercentDescCompare implements Comparator<PriceCompareEntity> {
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(PriceCompareEntity arg0, PriceCompareEntity arg1) {
			double d1 = arg0.getPriceGapPercent();
			double d2 = arg1.getPriceGapPercent();
			return d1 > d2 ? -1 : d1 < d2 ? 1:0;
		}
	}
}
