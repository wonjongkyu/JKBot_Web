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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.etc.SessionService;
import jk.framework.rest.binance.entity.BinanceAskResultEntity;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.bithumb.entity.BithumbResultEntity;
import jk.framework.rest.bithumb.service.BithumbPublicRestService;
import jk.framework.rest.upbit.entity.UpbitResultEntity;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.admin.entity.CommonInfoEntity;
import jk.framework.web.admin.entity.ExchangeRateEntity;
import jk.framework.web.admin.entity.KimpEntity;
import jk.framework.web.admin.entity.PriceCompareAskBidEntity;
import jk.framework.web.admin.entity.PriceCompareCommonAskBidEntity;
import jk.framework.web.admin.entity.PriceCompareCommonAskBidEntity2;
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
    @Value("${upbit.apiUrl2}")
    private String upbitApiUrl2 ;
    @Value("${huobi.apiUrl}")
    private String huobiApiUrl ;
    @Value("${bithumb.apiUrl}")
    private String bithumbApiUrl ;
    @Value("${buyPrice}")
    private String buyPrice ;
	
    @Autowired
    BinanacePublicRestService binancePublicService;

    @Autowired
    UpbitPublicRestService upbitPublicService;
    
    @Autowired
    BithumbPublicRestService bithumbPublicService;
    
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
		mav.setViewName("/admin/priceCompare");
		return mav;
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
    @RequestMapping(value = "/compare2", method = RequestMethod.GET)
	public ModelAndView compare2(Model model) {
		ModelAndView mav = new ModelAndView();
		// 환율 가져오기
		getExchangeRate(model);		
		mav.setViewName("/admin/priceCompare2");
		return mav;
    }
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결 (업비트-gateIO)
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
    @RequestMapping(value = "/compare3", method = RequestMethod.GET)
	public ModelAndView compare3(Model model) {
		ModelAndView mav = new ModelAndView();
		// 환율 가져오기
		getExchangeRate(model);		
		mav.setViewName("/admin/priceCompare3");
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
    	Double exchangeRate = 1190D;
    	if(sessionService.getAttributeStr("exchangeRate") != null) {
    		exchangeRate = Double.parseDouble(sessionService.getAttributeStr("exchangeRate"));
    	}
 		
 		// USDT / BTC 전용 코인 symbol 리스트
 		HashSet<String> coinList = new HashSet<String>();
 	 	
 		// 거래소 최근 거래 가격 가져오기
 		Map<String, PriceCompareEntity> resultEntity = new HashMap<String, PriceCompareEntity>();
 		PriceExchangeInfoEntity param = new PriceExchangeInfoEntity();
 		param.setCoinExchangeType(symbolType);
 		// 거래소 정보 가져오기 (전송 수수료)
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
	 	 		// logger.info("{}------coin::::{}", symbolType, e.getCoinSymbolName());
	 	 		coinList.add( e.getCoinSymbolName());
	
	 	 		/* 
	 	 		 * 	기본 데이터 세팅
	 	 		 *	- 수수료 사토시 출력
	 	 		 */
	 	 		PriceCompareEntity entity = new PriceCompareEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
				if("upbit".equals(e.getExchangeName())) {
					if(resultEntity.containsKey(entity.getCoinSymbol())){
						resultEntity.get(entity.getCoinSymbol()).setTransferFeeA(e.getCoinTransFeeKrw());
					}else {
						entity.setTransferFeeA(e.getCoinTransFeeKrw());
					}
				}else if("binance".equals(e.getExchangeName())) {
					if(resultEntity.containsKey(entity.getCoinSymbol())){
						resultEntity.get(entity.getCoinSymbol()).setTransferFeeB(e.getCoinTransFeeKrw());
					}else {
						entity.setTransferFeeB(e.getCoinTransFeeKrw());
					}
				}
				
				if(!resultEntity.containsKey(entity.getCoinSymbol())){ 
					resultEntity.put(entity.getCoinSymbol(), entity);
				}
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
 	 				sessionService.setAttribute("BTCKRW_UPDATE_DT", JKStringUtil.getNowTime());
 	 				logger.info("BTCKRW:::{}", sessionService.getAttribute("BTCKRW"));
 	 			}
 			}else if("BTC".equals(symbolType)) {
 				if(resultEntity.containsKey(entity.getTradeType())){
 					double btckrw = Double.parseDouble(sessionService.getAttributeStr("BTCKRW"));
 					String lastPrice = JKStringUtil.nvl(entity.getLastPrice(), "-");
 					resultEntity.get(entity.getTradeType()).setPriceBtcB(String.valueOf(lastPrice));
 					
 					// 차액도 계산 (최근 10분)
 					if(!("-").equals(lastPrice)) {
 						// 소수 셋째자리에서 반올림
 						double priceKrw = JKStringUtil.parseDouble(lastPrice) * btckrw;
 						resultEntity.get(entity.getTradeType()).setPriceKrwB(String.valueOf(JKStringUtil.mathRound(priceKrw,2)) );
 						
 						// 수수료 사토시 -> 원화 계산
 	 					String transferFee = resultEntity.get(entity.getTradeType()).getTransferFeeB();
 	 					String transferFeeSum = JKStringUtil.mathKrwRound(priceKrw * JKStringUtil.parseDouble(transferFee));
 	 					resultEntity.get(entity.getTradeType()).setTransferFeeB(transferFeeSum);
 	 					
 	 					/*
 	 					double priceKrw2 = JKStringUtil.parseDouble(resultEntity.get(entity.getTradeType()).getPriceBtcB2()) * btckrw;
 	 					resultEntity.get(entity.getTradeType()).setPriceKrwB2(String.valueOf(JKStringUtil.mathRound(priceKrw2,2)) );
 	 					*/
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
 						double krwGap = Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
 						
 						/* 김프 : ((업비트 - 바이낸스) x 100) / 바이낸스 (%)
 				         * 즉, 바이낸스 가격을 기준으로 김프를 산출합니다.
 						 */
 						// double priceGapPercent = ((Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB)) * 100) / Double.parseDouble(priceKrwB);
 						double priceGapPercent = ((Double.parseDouble(priceKrwA) - Double.parseDouble(priceKrwB)) * 100) / Double.parseDouble(priceKrwB);
 						resultEntity.get(entity.getTradeType()).setPriceGapPercent(JKStringUtil.mathRound(priceGapPercent,2));
 						
 						// 수수료 사토시 -> 원화 계산
 	 					String transferFee = resultEntity.get(entity.getTradeType()).getTransferFeeA();
 	 					String transferFeeSum = JKStringUtil.mathKrwRound(JKStringUtil.parseDouble(priceKrwA) * JKStringUtil.parseDouble(transferFee));
 	 					resultEntity.get(entity.getTradeType()).setTransferFeeA(transferFeeSum);
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
	 		
 			// 1. 10분마다 시세 Update
	 		if(sessionService.getAttributeInt("priceCompare") < 600) {
	 			sessionService.setAttributeInt("priceCompare",sessionService.getAttributeInt("priceCompare") + 15);
	 		}else {
	 			adminService.updateCoinPriceInfo(result);
	 			sessionService.setAttributeInt("priceCompare", 15);
	 		}
	 		
	 		// 2. 2시간 마다 히스토리 삭제
	 		if(sessionService.getAttributeInt("historyDeleteTime") < 7200) {
	 			sessionService.setAttributeInt("historyDeleteTime",sessionService.getAttributeInt("historyDeleteTime") + 15);
	 			// 30초마다 DB에 저장
	 			if( sessionService.getAttributeInt("historyDeleteTime") % 30 == 0) {
	 				adminService.insertPriceHistory(result);
	 			}
	 		}else {
	 			adminService.deletePriceHistory(coinList);
	 			sessionService.setAttributeInt("historyDeleteTime", 15);
	 		}
	 		result = adminService.getPriceHistory(result);
	 		
	 		
	 		// logger.debug("compareTime:::{}", sessionService.getAttributeInt("priceCompare"));
	 	}
 		
 		
 		if(symbolType.equals("BTC")) {
 			adminService.updateBtcCoinPrice(result);
 		}else if(symbolType.equals("USDT")) {
 			// adminService.updateUsdtCoinPrice(result);
 		}
 		
 		
 		return result;
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
    @RequestMapping(value = "/priceCompare2/{symbolType}", method = RequestMethod.GET)
   	public List<PriceCompareAskBidEntity> priceCompare2(Model model, @PathVariable String symbolType) {
    	// 최종 리턴되는 결과
    	List<PriceCompareAskBidEntity> result = new ArrayList<PriceCompareAskBidEntity>();
    	
    	// 환율 가져오기
    	Double exchangeRate = 1190D;
    	if(sessionService.getAttributeStr("exchangeRate") != null) {
    		exchangeRate = Double.parseDouble(sessionService.getAttributeStr("exchangeRate"));
    	}
    	
    	// BTC 전용 코인 symbol 리스트
 		HashSet<String> coinList = new HashSet<String>();
 		// USDT 전용 코인 symbol 리스트
 		HashSet<String> coinList2 = new HashSet<String>();
 	 	coinList2.add("BTC");
 		
 		// 거래소 최근 거래 가격 가져오기
 		Map<String, PriceCompareAskBidEntity> resultEntity = new HashMap<String, PriceCompareAskBidEntity>();
 		PriceExchangeInfoEntity param = new PriceExchangeInfoEntity();
 		param.setCoinExchangeType(symbolType);
 		// 거래소 정보 가져오기 (전송 수수료)
 		List<PriceExchangeInfoEntity> entityList = adminService.getAllExchangeInfo(param);
 		
 		// 가져올 코인 코드
 	 	for (PriceExchangeInfoEntity e : entityList) {
 	 		boolean listPut = false;
 	 		if("binance".equals(e.getExchangeName()) || "upbit".equals(e.getExchangeName()) ) {
	 	 		if("USDT".equals(symbolType)) {
	 	 			if("USDT".equals(e.getCoinExchangeType())) {
	 	 				listPut = true;
	 	 			}
	 	 		}else if("BTC".equals(symbolType)) {
	 	 			if("BTC".equals(e.getCoinExchangeType()) || "KRW".equals(e.getCoinExchangeType())){
	 	 				listPut = true;
	 	 			}
	 	 		}
 	 		}
 	 		
 	 		if(listPut) {
	 	 		coinList.add( e.getCoinSymbolName());
	 	 		PriceCompareAskBidEntity entity = new PriceCompareAskBidEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
							
				if(!resultEntity.containsKey(entity.getCoinSymbol())){ 
					resultEntity.put(entity.getCoinSymbol(), entity);
				}
 	 		}
 		}
 	 	
 	 	// 바이낸스 USDT-BTC 가져오는 기능 개발 필요함
 	 	List<BinanceTickerResultEntity> binanceResultEntity = binancePublicService.getTicker(binanceApiUrl,coinList2, symbolType);
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
 	 			if("BTCUSDT".equals(entity.getSymbol())){
 	 				sessionService.setAttribute("BTCKRW", resultEntity.get("BTC").getPriceKrwB());
 	 				sessionService.setAttribute("BTCKRW_UPDATE_DT", JKStringUtil.getNowTime());
 	 				logger.info("BTCKRW:::{}", sessionService.getAttribute("BTCKRW"));
 	 			}
 			}
 		}
 	 	
 		Double buyPriceDouble = Double.valueOf(buyPrice);
 	 	// 해당 값으로 김프 계산하도록 변경 (옵셔널 하게.. 바꾸자)
 	 	// 해당 결과값을 아래 binanceResultEntity에 merge
 	 	List<BinanceAskResultEntity> askEntityList = binancePublicService.getBidAskPrice(binanceApiUrl,coinList, symbolType, exchangeRate, buyPriceDouble);
 	 	for (BinanceAskResultEntity e : askEntityList) {
 			if(resultEntity.containsKey(e.getCoinSymbolName())){
 				resultEntity.get(e.getCoinSymbolName()).setBinanceBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceBuySatosiPrice(String.valueOf(e.getBidCoinSatosiPrice()));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceSellSatosiPrice(String.valueOf(e.getAskCoinSatosiPrice()));
			}
		}
 	 	
 	 	List<UpbitResultEntity> askEntityList2 = upbitPublicService.getBidAskPrice(upbitApiUrl2,coinList, symbolType, exchangeRate, buyPriceDouble);
 		for (UpbitResultEntity e : askEntityList2) {
 			if(resultEntity.containsKey(e.getCoinSymbolName())){
 				resultEntity.get(e.getCoinSymbolName()).setUpbitBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setUpbitSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
			}
		}
		
 	
 		for (String str : coinList) {
 			if(resultEntity.containsKey(str)){
 				String binanceBuyPrice = JKStringUtil.nvl(resultEntity.get(str).getBinanceBuyPrice(), "-");
 				String binanceSellPrice = JKStringUtil.nvl(resultEntity.get(str).getBinanceSellPrice(), "-");
 				String upbitBuyPrice = JKStringUtil.nvl(resultEntity.get(str).getUpbitBuyPrice(), "-");
 				String upbitSellPrice = JKStringUtil.nvl(resultEntity.get(str).getUpbitSellPrice(), "-");
 				
 				// 원화 차액 / 김프 계산
 				if(!("-").equals(binanceBuyPrice) && (!("-").equals(upbitSellPrice))) {
 					double krwGap = Double.parseDouble(upbitSellPrice) - Double.parseDouble(binanceBuyPrice);
					resultEntity.get(str).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(upbitSellPrice) - Double.parseDouble(binanceBuyPrice)) * 100) / Double.parseDouble(binanceBuyPrice);
					resultEntity.get(str).setPriceGapPercent(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 				if(!("-").equals(binanceSellPrice) && (!("-").equals(upbitBuyPrice))) {
					double krwGap = Double.parseDouble(upbitBuyPrice) - Double.parseDouble(binanceSellPrice);
					resultEntity.get(str).setPriceGapKrw2(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(upbitBuyPrice) - Double.parseDouble(binanceSellPrice)) * 100) / Double.parseDouble(binanceSellPrice);
					resultEntity.get(str).setPriceGapPercent2(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 			}
		}

    	List<PriceCompareAskBidEntity> result1 = new ArrayList<PriceCompareAskBidEntity>();
    	List<PriceCompareAskBidEntity> result2 = new ArrayList<PriceCompareAskBidEntity>();
    	
 		// 값 정상 세팅 확인
 		for( String key : resultEntity.keySet() ){
 			result1.add(resultEntity.get(key));
 	        // logger.info("키 : {}, 값 : {}", key, resultEntity.get(key));
 	    }
 		
 		// 깊은 복사 (deep copy)
 		for(int i=0; i<result1.size(); i++) {
 			result2.add(new PriceCompareAskBidEntity(result1.get(i)));
 		}
 		
 		// 상승률 내림차순 정렬 (앞에꺼)
 		Collections.sort(result1, new GapPercentAscCompare2());
 		// 상승률 오름 정렬 (뒤에꺼)
 		Collections.sort(result2, new GapPercent2DescCompare2());
 		
 		for(int i=0; i<result1.size(); i++) {
			result1.get(i).setCoinSymbol2(result2.get(i).getCoinSymbol());
			result1.get(i).setBinanceSellSatosiPrice(result2.get(i).getBinanceSellSatosiPrice());
			result1.get(i).setUpbitBuyPrice(result2.get(i).getUpbitBuyPrice());
			result1.get(i).setBinanceSellPrice(result2.get(i).getBinanceSellPrice());
			result1.get(i).setPriceGapKrw2(result2.get(i).getPriceGapKrw2());
			result1.get(i).setPriceGapPercent2(result2.get(i).getPriceGapPercent2());
		}
 		
 		return result1;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @RequestMapping(value = "/priceCompare3/{symbolType}", method = RequestMethod.GET)
   	public Map<String, Object> priceCompare3(Model model, @PathVariable String symbolType) {
    	System.out.println("콜콜");
    	
    	if(symbolType.equals("BTC")) {
    		return adminService.getPriceCompare3ForBTC();
    	}
    	else if(symbolType.equals("USDT")) {
    		return adminService.getPriceCompare3ForUSDT();
    	}
    	
    	return null;
	}
    
    /**
     * <pre>
     * 1. 개요 : 김프 계산 페이지 연결
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : compare
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 환율 정보 DB에 Update 처리
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
    	    
    	    // jk_common_infomation에 업데이트 작업 수행
    	    logger.info("환율::::{}", String.valueOf(exchangeRate) );
			sessionService.setAttribute("exchangeRate", String.valueOf(exchangeRate) );
		}
		return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/getBtcKrwPrice", method = RequestMethod.GET)
	public CommonInfoEntity getBtcKrwPrice(Model model) {
    	CommonInfoEntity entity = new CommonInfoEntity();
    	//entity.setBinanceBtcKrwPrice(sessionService.getAttribute("BTCKRW"));
    	//entity.setBtcUpdateDt(sessionService.getAttribute("BTCKRW_UPDATE_DT"));
    	
    	if(!adminService.getSessionMap().isEmpty()) {    		
    		entity.setBinanceBtcKrwPrice(adminService.getSessionMap().get("BTCKRW").toString());
    		entity.setBtcUpdateDt(adminService.getSessionMap().get("BTCKRW_UPDATE_DT").toString());
    	}
    	return entity;
    }
    
    /**
     * 김프 알람용
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getKimp", method = RequestMethod.GET)
	public KimpEntity getKimp(Model model) {
    	
    	// 환율
    	KimpEntity result = adminService.getKimp();
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
    	Map<String, PriceExchangeInfoEntity> templistEntity = new HashMap<String, PriceExchangeInfoEntity>();
    	List<PriceExchangeInfoEntity> listEntity = new ArrayList<PriceExchangeInfoEntity>();
    	PriceExchangeInfoEntity entity = new PriceExchangeInfoEntity();
    	List<PriceExchangeInfoEntity> entityList = adminService.getAllExchangeInfo(entity);
    	for (PriceExchangeInfoEntity resultEntity : entityList) {
    		sessionService.setAttribute(resultEntity.getExchangeName() + "_" + resultEntity.getCoinSymbolName(), resultEntity.getCoinTransFeeKrw());
 
			if(!templistEntity.containsKey(resultEntity.getCoinSymbolName())){ 
				templistEntity.put(resultEntity.getCoinSymbolName(), resultEntity);
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
	static class GapPercentAscCompare2 implements Comparator<PriceCompareAskBidEntity> {
		/**
		 * 오름차순(ASC)
		 */
		@Override
		public int compare(PriceCompareAskBidEntity arg0, PriceCompareAskBidEntity arg1) {
			double d1 = arg0.getPriceGapPercent();
			double d2 = arg1.getPriceGapPercent();
			return d1 < d2 ? -1 : d1 > d2 ? 1:0;
		}
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
	static class GapPercentAscCompare3 implements Comparator<PriceCompareCommonAskBidEntity> {
		/**
		 * 오름차순(ASC)
		 */
		@Override
		public int compare(PriceCompareCommonAskBidEntity arg0, PriceCompareCommonAskBidEntity arg1) {
			double d1 = arg0.getPriceGapPercent();
			double d2 = arg1.getPriceGapPercent();
			return d1 < d2 ? -1 : d1 > d2 ? 1:0;
		}
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
	static class GapPercentAscCompare4 implements Comparator<PriceCompareCommonAskBidEntity2> {
		/**
		 * 오름차순(ASC)
		 */
		@Override
		public int compare(PriceCompareCommonAskBidEntity2 arg0, PriceCompareCommonAskBidEntity2 arg1) {
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
	static class GapPercentDescCompare2 implements Comparator<PriceCompareAskBidEntity> {
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(PriceCompareAskBidEntity arg0, PriceCompareAskBidEntity arg1) {
			double d1 = arg0.getPriceGapPercent();
			double d2 = arg1.getPriceGapPercent();
			return d1 > d2 ? -1 : d1 < d2 ? 1:0;
		}
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
	static class GapPercent2DescCompare2 implements Comparator<PriceCompareAskBidEntity> {
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(PriceCompareAskBidEntity arg0, PriceCompareAskBidEntity arg1) {
			double d1 = arg0.getPriceGapPercent2();
			double d2 = arg1.getPriceGapPercent2();
			return d1 > d2 ? -1 : d1 < d2 ? 1:0;
		}
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
	static class GapPercent2DescCompare3 implements Comparator<PriceCompareCommonAskBidEntity> {
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(PriceCompareCommonAskBidEntity arg0, PriceCompareCommonAskBidEntity arg1) {
			double d1 = arg0.getPriceGapPercent2();
			double d2 = arg1.getPriceGapPercent2();
			return d1 > d2 ? -1 : d1 < d2 ? 1:0;
		}
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
	static class GapPercent2DescCompare4 implements Comparator<PriceCompareCommonAskBidEntity2> {
		/**
		 * 내림차순(DESC)
		 */
		@Override
		public int compare(PriceCompareCommonAskBidEntity2 arg0, PriceCompareCommonAskBidEntity2 arg1) {
			double d1 = arg0.getPriceGapPercent2();
			double d2 = arg1.getPriceGapPercent2();
			return d1 > d2 ? -1 : d1 < d2 ? 1:0;
		}
	}
}
