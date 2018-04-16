package jk.framework.web.price.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.price.entity.PriceCompareEntity;
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
    @RequestMapping(value = "/priceCompare", method = RequestMethod.GET)
   	public List<PriceCompareEntity> priceCompare(Model model) {
    	List<PriceCompareEntity> result = new ArrayList<PriceCompareEntity>();
    	
    	// 환율
 		Double exchangeRate = priceService.getExchangeRate();
 		logger.info("exchangeRate:::{}", exchangeRate);
 		// 가져올 코인 코드
 		HashSet<String> coinList = new HashSet<String>();
 		coinList.add("BTC");
 		coinList.add("ETH");
 		coinList.add("BCC");
 		coinList.add("NEO");
 		coinList.add("LTC");
 		coinList.add("QTUM");
 		
 		/*
 		 * 1.resultEntity 세팅
 		 */
 		Map<String, PriceCompareEntity> resultEntity = new HashMap<String, PriceCompareEntity>();
 		for (String coinSymbol : coinList) {
 			PriceCompareEntity entity = new PriceCompareEntity();
 			entity.setCoinSymbol(coinSymbol);
 			resultEntity.put(coinSymbol, entity);
 		}
 		
 		List<BinanceTickerResultEntity> binanceResultEntity = binancePublicService.getTicker(binanceApiUrl,coinList);
 		for (BinanceTickerResultEntity entity : binanceResultEntity) {
 			if(resultEntity.containsKey(entity.getTradeType())){
 				String lastPrice = JKStringUtil.nvl(entity.getLastPrice(), "-");
 				resultEntity.get(entity.getTradeType()).setPriceUsdtB(String.valueOf(JKStringUtil.mathRound(lastPrice,2)));
 				if(!("-").equals(lastPrice)) {
 					// 소수 셋째자리에서 반올림
 					double priceKrw = JKStringUtil.parseDouble(lastPrice) * exchangeRate;
 					resultEntity.get(entity.getTradeType()).setPriceKrwB(JKStringUtil.mathKrwRound(priceKrw) );
 				}
 			}
 		}

 		List<UpbitTickerResultEntity> upBitResultEntity = upbitPublicService.getTicker(upbitApiUrl, coinList);
 		for (UpbitTickerResultEntity entity : upBitResultEntity) {
 			if(resultEntity.containsKey(entity.getTradeType())){
 				String priceKrwA = JKStringUtil.nvl(entity.getTradePrice(), "-");
 				String priceKrwB = resultEntity.get(entity.getTradeType()).getPriceKrwB();
 				// 업비트 원화 가격을 받아왔을때.
 				if(!("-").equals(priceKrwA)) {
 					resultEntity.get(entity.getTradeType()).setPriceKrwA(JKStringUtil.mathKrwRound(priceKrwA));
 					// 원화 차액을 계산하기 위해 바이낸스 가격을 가져왔을때. 원화차액 및 김프까지 계산
 					if(!("-").equals(priceKrwB)) {
 						// 원화 차액 계산
 						double krwGap = Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapKrw(JKStringUtil.mathKrwRound(krwGap));
 						
 						/* 김프 : ((업비트 - 바이낸스) x 100) / 바이낸스 (%)
 				         * 즉, 바이낸스 가격을 기준으로 김프를 산출합니다.
 						 */
 						double priceGapPercent = ((Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB)) * 100) / Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapPercent((String.valueOf(JKStringUtil.mathRound(priceGapPercent,2))) );
 					}
 				}
 			}
 		}
 		
 		// 값 정상 세팅 확인
 		for( String key : resultEntity.keySet() ){
 			result.add(resultEntity.get(key));
 	        logger.info("키 : {}, 값 : {}", key, resultEntity.get(key));
 	    }
 		return result;
	}
}
