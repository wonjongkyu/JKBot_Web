package jk.framework.web.admin.controller;

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
import jk.framework.web.admin.entity.ExchangeRateEntity;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.entity.PriceExchangeInfoEntity;
import jk.framework.web.admin.service.AdminService;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
 
    @Value("${binance.apiUrl}")
    private String binanceApiUrl ;
    @Value("${upbit.apiUrl}")
    private String upbitApiUrl ;
	
    @Autowired
    BinanacePublicRestService binancePublicService;

    @Autowired
    UpbitPublicRestService upbitPublicService;
    
    @Autowired
    AdminService adminService;
    
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
		mav.setViewName("/admin/priceCompare");
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
    	// 최종 리턴되는 결과
    	List<PriceCompareEntity> result = new ArrayList<PriceCompareEntity>();
    	
    	// 환율 가져오기
 		Double exchangeRate = Double.parseDouble(sessionService.getAttributeStr("exchangeRate"));
 		
 		// USDT / BTC 전용 코인 symbol 리스트
 		HashSet<String> coinList = new HashSet<String>();
 	 	
 		// 거래소 최근 거래 가격 가져오기
 		Map<String, PriceCompareEntity> resultEntity = new HashMap<String, PriceCompareEntity>();
 		PriceExchangeInfoEntity param = new PriceExchangeInfoEntity();
 		param.setCoinExchangeType(symbolType);
 		List<PriceExchangeInfoEntity> entityList = adminService.getAllExchangeInfo(param);
 		
 		// 가져올 코인 코드
 	 	for (PriceExchangeInfoEntity e : entityList) {
 	 		boolean listPut = false;
 	 		if("USDT".equals(symbolType)) {
 	 			if("USDT".equals(e.getCoinExchangeType())) {
 	 				listPut = true;
 	 			}
 	 		}else if("BTC".equals(symbolType)) {
 	 			if("BTC".equals(e.getCoinExchangeType()) || "KRW".equals(e.getCoinExchangeType())){
 	 				listPut = true;
 	 			}
 	 		}
 	 		
 	 		if(listPut) {
	 	 		logger.info("{}------coin::::{}", symbolType, e.getCoinSymbolName());
	 	 		coinList.add( e.getCoinSymbolName());
	
	 	 		/* 기본 데이터 세팅*/
	 	 		PriceCompareEntity entity = new PriceCompareEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
				entity.setTransferFeeA(e.getCoinTransFeeKrw());
				resultEntity.put(entity.getCoinSymbol(), entity);
 	 		}
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
 					double btckrw = Double.parseDouble(sessionService.getAttributeStr("BTCKRW"));
 					String lastPrice = JKStringUtil.nvl(entity.getLastPrice(), "-");
 					resultEntity.get(entity.getTradeType()).setPriceBtcB(String.valueOf(lastPrice));
 					
 					// 수수료 Get
 					resultEntity.get(entity.getTradeType()).setTransferFeeB(sessionService.getAttributeStr("binance_" + entity.getTradeType()));
 					
 					if(!("-").equals(lastPrice)) {
 						// 소수 셋째자리에서 반올림
 						double priceKrw = JKStringUtil.parseDouble(lastPrice) * btckrw;
 						resultEntity.get(entity.getTradeType()).setPriceKrwB(String.valueOf(JKStringUtil.mathRound(priceKrw,2)) );
 						// 차액도 계산 (최근 10분)
 						
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
 				String priceKrwB = JKStringUtil.nvl(resultEntity.get(entity.getTradeType()).getPriceKrwB(), "-");
 				// 업비트 원화 가격을 받아왔을때.
 				if(!("-").equals(priceKrwA)) {
 					resultEntity.get(entity.getTradeType()).setPriceKrwA(String.valueOf(JKStringUtil.mathRound(priceKrwA,2)));
 					// 원화 차액을 계산하기 위해 바이낸스 가격을 가져왔을때. 원화차액 및 김프까지 계산
 					if(!("-").equals(priceKrwB)) {
 						// 원화 차액 계산
 						double krwGap = Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
 						
 						// 수수료 Get
 						resultEntity.get(entity.getTradeType()).setTransferFeeA(sessionService.getAttributeStr("upbi" + entity.getTradeType()));
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
 	        // logger.info("키 : {}, 값 : {}", key, resultEntity.get(key));
 	    }
 		
 		// 상승률 내림차순 정렬
 		Collections.sort(result, new GapPercentDescCompare());
 		
 		if(symbolType.equals("BTC")) {
	 		// 10분마다 시세 Update
	 		if(sessionService.getAttributeInt("priceCompare") < 600) {
	 			// DB에 가격 저장 Update
	 			sessionService.setAttributeInt("priceCompare",sessionService.getAttributeInt("priceCompare") + 15);
	 		}else {
	 			adminService.updateCoinPriceInfo(result);
	 			sessionService.setAttributeInt("priceCompare", 15);
	 		}
	 		logger.info("compareTime:::{}", sessionService.getAttributeInt("priceCompare"));
	 	}
 		
 		
 		// DB에 데이터 저장하기
 		// List<PriceCompareEntity> result
 		if(symbolType.equals("BTC")) {
 			adminService.updateBtcCoinPrice(result);
 		}else if(symbolType.equals("USDT")) {
 			adminService.updateUsdtCoinPrice(result);
 		}
 		
 		
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
    @ResponseBody
    @RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET)
	public List<ExchangeRateEntity> getExchangeRate(Model model) {
		// 환율
    	List<ExchangeRateEntity>  result = adminService.getExchangeRate();
    	
		if(result != null) {
            Double exchangeRate = 0D;	// 환율
    	    for (ExchangeRateEntity exchangeRateEntity : result) {
    	    	exchangeRate = Double.parseDouble(exchangeRateEntity.getRate());
    		}
    	    
			sessionService.setAttribute("exchangeRate", String.valueOf(exchangeRate) );
		}
		return result;
    }
    
    /**
     * <pre>
     * 1. 개요 : DB에 저장되어 있는 거래소별 코인 정보 가져오기
     * 2. 처리내용 : 거래 수수료를 session에 저장하기 위한 용도
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
    	List<PriceExchangeInfoEntity> entityList = adminService.getAllExchangeInfo(entity);
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
