package jk.framework.web.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.etc.SessionService;
import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceAskResultEntity;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.service.BinanacePublicRestService;
import jk.framework.rest.bithumb.entity.BithumbResultEntity;
import jk.framework.rest.bithumb.service.BithumbPublicRestService;
import jk.framework.rest.upbit.entity.UpbitResultEntity;
import jk.framework.rest.upbit.service.UpbitPublicRestService;
import jk.framework.web.admin.entity.ExchangeCoinPriceEntity;
import jk.framework.web.admin.entity.ExchangeRateEntity;
import jk.framework.web.admin.entity.PriceCompareAskBidEntity;
import jk.framework.web.admin.entity.PriceCompareCommonAskBidEntity;
import jk.framework.web.admin.entity.PriceCompareCommonAskBidEntity2;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.entity.PriceExchangeInfoEntity;
import jk.framework.web.admin.entity.PriceHistoryEntity;
import jk.framework.web.admin.mapper.AdminMapper;

@Service
@EnableScheduling
public class AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	// exchange apiKey
    @Value("${exchange.apiUrl}")
    private String apiUrl ;
	
    
    @Autowired
    AdminMapper mapper;
    
    @Autowired
    SessionService sessionService;
    
    @Autowired
    BinanacePublicRestService binancePublicService;

    @Autowired
    UpbitPublicRestService upbitPublicService;
    
    @Autowired
    BithumbPublicRestService bithumbPublicService;
    
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
    
    Map<String, Object> resultMapUSDT = new HashMap<String, Object>();
    Map<String, Object> resultMapBTC = new HashMap<String, Object>();
    Map<String, Object> resultMapSession = new HashMap<String, Object>();
    
	// ExchangeRateEntity
	public List<PriceExchangeInfoEntity> getAllExchangeInfo(PriceExchangeInfoEntity entity){
		return mapper.getAllExchangeInfo(entity);
	}
	
	public void updateCoinPriceInfo(List<PriceCompareEntity> list){
		List<ExchangeCoinPriceEntity> param = new ArrayList<ExchangeCoinPriceEntity>();
		
		for (PriceCompareEntity entity : list) {
			// A는 코인 거래소 upbit 가격 입력
			// B는 코인 거래소 binance 가격 입력
			if( entity.getPriceKrwA() != null) {
				ExchangeCoinPriceEntity exchangeEntity = new ExchangeCoinPriceEntity();
				exchangeEntity.setCoinSymbol(entity.getCoinSymbol());
				exchangeEntity.setExchangeName("upbit");
				exchangeEntity.setPriceKrw(entity.getPriceKrwA());
				param.add(exchangeEntity);
			}
			if( entity.getPriceKrwB() != null) {
				ExchangeCoinPriceEntity exchangeEntity = new ExchangeCoinPriceEntity();
				exchangeEntity.setCoinSymbol(entity.getCoinSymbol());
				exchangeEntity.setExchangeName("binance");
				exchangeEntity.setPriceKrw(entity.getPriceKrwB());
				param.add(exchangeEntity);
			}
		}
		mapper.updateCoinPriceInfo(param);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : BTC - KRW 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateAllCoinPrice
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 */ 	
	public void updateBtcCoinPrice(List<PriceCompareEntity> list){
		mapper.updateBtcCoinPrice(list);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : USDT 업데이트 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateAllCoinPrice
	 * @date : 2018. 4. 22.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 22.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 */ 	
	public void updateUsdtCoinPrice(List<PriceCompareEntity> list){
		mapper.updateUsdtCoinPrice(list);
	}
	
	 
	/**
	 * <pre>
	 * 1. 개요 : 현재 환율 정보를 가져온다.
	 * 2. 처리내용 : 
	 * http://api.manana.kr/exchange/rate/KRW/USD.json
	 * Json 포맷 : [{"date":"2018-04-16 13:00:00","name":"USDKRW=X","rate":1073.72998}]
	 * </pre>
	 * @Method Name : getExchangeRate
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */ 	
	public List<ExchangeRateEntity> getExchangeRate(){
		List<ExchangeRateEntity> entity = null;
		Api_Client api = new Api_Client(apiUrl, null, null);
		try {
		    String result = api.callCommonApi("/KRW/USD.json", null);
		    Gson gson = new Gson();
		    
		    entity = gson.fromJson(result, new TypeToken<List<ExchangeRateEntity>>(){}.getType()); 

		} catch (Exception e) {
			logger.info("환율 못가져옴");
		   // e.printStackTrace();
		}
		return entity;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 가격 히스토리 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertPriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 리턴 파라미터로 List<PriceCompareEntity> 리턴하도록.. 가중치 및 이전 가격 표시하도록 하기 위하여
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param list
	 */ 	
	public void insertPriceHistory(List<PriceCompareEntity> list){
		
		// 히스토리 테이블 조회
		List<PriceHistoryEntity> historyList = this.getAllPriceHistory();
		Map<String, PriceHistoryEntity> historyMap = new HashMap<String, PriceHistoryEntity>();
		for (PriceHistoryEntity entity : historyList) {
			historyMap.put(entity.getCoinSymbolName(), entity);
		}
		
		// 히스토리
		// 히스토리 테이블에 조회한 데이터가 있을 경우, 가중치 계산
		for (PriceCompareEntity entity : list) {
			String coinSymbol = entity.getCoinSymbol();
			if(historyMap.containsKey(entity.getCoinSymbol())) {
				double lastPriceA = JKStringUtil.parseDouble(JKStringUtil.nvl(historyMap.get(coinSymbol).getCoinPriceKrwA(), "0"));
				entity.setCoinPriceWeightA("0");
				if(lastPriceA > 0) {
					double nowPriceA = JKStringUtil.parseDouble(entity.getPriceKrwA());
					if(nowPriceA > lastPriceA) {
						entity.setCoinPriceWeightA("1");
					}else if(nowPriceA < lastPriceA) {
						entity.setCoinPriceWeightA("-1");
					}
				}
				
				double lastPriceB = JKStringUtil.parseDouble(JKStringUtil.nvl(historyMap.get(coinSymbol).getCoinPriceKrwB(), "0"));
				entity.setCoinPriceWeightB("0");
				if(lastPriceB > 0) {
					double nowPriceB = JKStringUtil.parseDouble(entity.getPriceKrwB());
					if(nowPriceB > lastPriceA) {
						entity.setCoinPriceWeightB("1");
					}else if(nowPriceB < lastPriceB) {
						entity.setCoinPriceWeightB("-1");
					} 
				}
			}
		}
		mapper.insertPriceHistory(list);
		// list와 historyList를 비교하여 아래 mapper를 실행할 list에 세팅해준다.
	}
	
	// ExchangeRateEntity
	public List<PriceHistoryEntity> getAllPriceHistory(){
		return mapper.getAllPriceHistory();
	}
	
	
	// ExchangeRateEntity
	public List<PriceCompareEntity> getPriceHistory(List<PriceCompareEntity> list){
		for (PriceCompareEntity entity : list) {
			PriceCompareEntity tempEntity = mapper.getOnePriceHistory(entity);
			if(tempEntity != null) {
				entity.setCoinPriceWeightA( tempEntity.getCoinPriceWeightA()  );
				entity.setCoinPriceWeightB( tempEntity.getCoinPriceWeightB()  );
			}
		}
		return list;
	}
	
	// ExchangeRateEntity
	public PriceCompareEntity getOnePriceHistory(PriceCompareEntity entity){
		return mapper.getOnePriceHistory(entity);
	}
 
	/**
	 * <pre>
	 * 1. 개요 : 히스토리 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deletePriceHistory
	 * @date : 2018. 4. 28.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 28.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 */ 	
	public void deletePriceHistory(HashSet<String> coinList){
		mapper.deletePriceHistory(coinList);
	}
	
	// public void updateBtcKrwPrice
	
	
	// priceCompare3 스프링 스케쥴링 적용
	@Scheduled(cron = "0/15 * * * * ?")
	public void scheduledPriceCompare3() {
		System.out.println("동작해라");
		resultMapUSDT = priceCompare3(null, "USDT");
		resultMapBTC = priceCompare3(null, "BTC");
	}
	
	public Map<String, Object> getPriceCompare3ForUSDT() {
		return resultMapUSDT;
	}
	
	public Map<String, Object> getPriceCompare3ForBTC() {
		return resultMapBTC;
	}
	
	public Map<String, Object> getSessionMap() {
		return resultMapSession;
	}
	
	// priceCompare3 스프링 스케쥴링 적용
   	public HashMap<String, Object> priceCompare3(Model model, @PathVariable String symbolType) {
    	
    	System.out.println("symbolType = " + symbolType);
    	
    	
    	// 최종 리턴되는 결과
    	List<PriceCompareAskBidEntity> result = new ArrayList<PriceCompareAskBidEntity>();
    	
    	// 환율 가져오기
    	Double exchangeRate = 1105D;
    	
    	// 환율
    	List<ExchangeRateEntity> resultExchangeRate = this.getExchangeRate();
    	
		if(result != null) {
            exchangeRate = 0D;	// 환율
    	    for (ExchangeRateEntity exchangeRateEntity : resultExchangeRate) {
    	    	exchangeRate = Double.parseDouble(exchangeRateEntity.getRate());
    		}
    	    
    	    // jk_common_infomation에 업데이트 작업 수행
    	    logger.info("환율::::{}", String.valueOf(exchangeRate) );
			//sessionService.setAttribute("exchangeRate", String.valueOf(exchangeRate) );
		}
    	
    	// 업비트 상장 코인 symbol 리스트
 		HashSet<String> coinList = new HashSet<String>();
 		// USDT 전용 코인 symbol 리스트
 		HashSet<String> coinList2 = new HashSet<String>();
 		coinList2.add("BTC");
 		
 		// Bithumb 상장 코인 symbol 리스트
 		HashSet<String> coinList3 = new HashSet<String>();
 		// binance 상장 코인 symbol 리스트
 		HashSet<String> coinList4 = new HashSet<String>();
 		
 	 	
 		// 업비트 - 바이낸스 결과값 저장
 		Map<String, PriceCompareAskBidEntity> resultEntity = new HashMap<String, PriceCompareAskBidEntity>();
 		// 빗썸 - 바이낸스 결과값 저장
 		Map<String, PriceCompareCommonAskBidEntity2> resultEntity2 = new HashMap<String, PriceCompareCommonAskBidEntity2>();
 		// 업비트 - 빗썸 결과값 저장
 		Map<String, PriceCompareCommonAskBidEntity> resultEntity3 = new HashMap<String, PriceCompareCommonAskBidEntity>();
 		
 		PriceExchangeInfoEntity param = new PriceExchangeInfoEntity();
 		param.setCoinExchangeType(symbolType);
 		
 		// 거래소 정보 가져오기 (전송 수수료)
 		List<PriceExchangeInfoEntity> entityList = this.getAllExchangeInfo(param);
 		
 		for (PriceExchangeInfoEntity e : entityList) {
			if("upbit".equals(e.getExchangeName())){
				PriceCompareAskBidEntity entity = new PriceCompareAskBidEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
				resultEntity.put(entity.getCoinSymbol(), entity);
				
				coinList.add(e.getCoinSymbolName());		
			 }
			if("binance".equals(e.getExchangeName())){
			/*	PriceCompareAskBidEntity entity = new PriceCompareAskBidEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
				resultEntity2.put(entity.getCoinSymbol(), entity);*/
				
				coinList4.add(e.getCoinSymbolName());
			}
 			if("bithumb".equals(e.getExchangeName())){
 				PriceCompareCommonAskBidEntity entity = new PriceCompareCommonAskBidEntity();
				entity.setCoinSymbol(e.getCoinSymbolName());
				resultEntity3.put(entity.getCoinSymbol(), entity);
				
				PriceCompareCommonAskBidEntity2 entity2 = new PriceCompareCommonAskBidEntity2();
				entity2.setCoinSymbol(e.getCoinSymbolName());
				resultEntity2.put(entity2.getCoinSymbol(), entity2);
				
				coinList3.add(e.getCoinSymbolName());
 			}
 		}
 	 	
 	 	// 바이낸스 USDT-BTC 가져오는 기능 개발 필요함
 	 	List<BinanceTickerResultEntity> binanceResultEntity = binancePublicService.getTicker(binanceApiUrl,coinList2, symbolType);
 		for (BinanceTickerResultEntity entity : binanceResultEntity) {
 			if("USDT".equals(symbolType)){
 				resultEntity = new HashMap<String, PriceCompareAskBidEntity>();
	 	 		PriceCompareAskBidEntity entityTemp = new PriceCompareAskBidEntity();
	 	 		entityTemp.setCoinSymbol("BTC");
				resultEntity.put(entityTemp.getCoinSymbol(), entityTemp);
				
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
 	 				resultMapSession.put("BTCKRW", resultEntity.get("BTC").getPriceKrwB());
 	 				resultMapSession.put("BTCKRW_UPDATE_DT", JKStringUtil.getNowTime());
 	 				logger.info("BTCKRW:::{}", resultMapSession.get("BTCKRW"));
 	 				//sessionService.setAttribute("BTCKRW", resultEntity.get("BTC").getPriceKrwB());
 	 				//sessionService.setAttribute("BTCKRW_UPDATE_DT", JKStringUtil.getNowTime());
 	 				//logger.info("BTCKRW:::{}", sessionService.getAttribute("BTCKRW"));
 	 			}
 	 			HashMap<String, Object> resultData = new HashMap<String, Object>();
 	 			return resultData;
 			}
 		}
 	 	
 		Double buyPriceDouble = Double.valueOf(buyPrice);
 		
 		// 업비트 시세 가져오기
 		List<UpbitResultEntity> askEntityList2 = upbitPublicService.getBidAskPrice(upbitApiUrl2,coinList, symbolType, exchangeRate, buyPriceDouble);
 		for (UpbitResultEntity e : askEntityList2) {
 			if(resultEntity.containsKey(e.getCoinSymbolName())){
 				resultEntity.get(e.getCoinSymbolName()).setUpbitBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setUpbitSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 			}
 			if(resultEntity3.containsKey(e.getCoinSymbolName())){
 				resultEntity3.get(e.getCoinSymbolName()).setUpbitBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
				resultEntity3.get(e.getCoinSymbolName()).setUpbitSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 			}
 		}
 		
 		// 빗썸 시세 가져오기
 		List<BithumbResultEntity> askEntityList3 = bithumbPublicService.getBidAskPrice(bithumbApiUrl,coinList3, symbolType, exchangeRate, buyPriceDouble);
 		for (BithumbResultEntity e : askEntityList3) {
 			if(resultEntity3.containsKey(e.getCoinSymbolName())){
 				resultEntity3.get(e.getCoinSymbolName()).setBinanceBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity3.get(e.getCoinSymbolName()).setBinanceSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 			}
 			
 			if(resultEntity2.containsKey(e.getCoinSymbolName())){
 				resultEntity2.get(e.getCoinSymbolName()).setUpbitBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity2.get(e.getCoinSymbolName()).setUpbitSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 			}
 		}

 		binancePublicService.setBTCKRW(Double.parseDouble(resultMapSession.get("BTCKRW").toString()));
 		binancePublicService.setExchangeRate(exchangeRate);
 		
 		// 바이낸스 시세 가져오기
 	 	List<BinanceAskResultEntity> askEntityList = binancePublicService.getBidAskPrice(binanceApiUrl,coinList4, symbolType, exchangeRate, buyPriceDouble);
 	 	for (BinanceAskResultEntity e : askEntityList) {
 			if(resultEntity.containsKey(e.getCoinSymbolName())){
 				resultEntity.get(e.getCoinSymbolName()).setBinanceBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceBuySatosiPrice(String.valueOf(e.getBidCoinSatosiPrice()));
 				resultEntity.get(e.getCoinSymbolName()).setBinanceSellSatosiPrice(String.valueOf(e.getAskCoinSatosiPrice()));
			}
 			
 			if(resultEntity2.containsKey(e.getCoinSymbolName())){
 				resultEntity2.get(e.getCoinSymbolName()).setBinanceBuyPrice(String.valueOf(JKStringUtil.mathRound(e.getBidCoinAveragePrice(),2)));
 				resultEntity2.get(e.getCoinSymbolName()).setBinanceSellPrice(String.valueOf(JKStringUtil.mathRound(e.getAskCoinAveragePrice(),2)));
 				resultEntity2.get(e.getCoinSymbolName()).setBinanceBuySatosiPrice(String.valueOf(e.getBidCoinSatosiPrice()));
 				resultEntity2.get(e.getCoinSymbolName()).setBinanceSellSatosiPrice(String.valueOf(e.getAskCoinSatosiPrice()));
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
 		
 		// 업비트 - 빗썸 페어
 		for (String str : coinList3) {
 			if(resultEntity3.containsKey(str)){
 				String bithumbBuyPrice = JKStringUtil.nvl(resultEntity3.get(str).getBinanceBuyPrice(), "-");
 				String bithumbSellPrice = JKStringUtil.nvl(resultEntity3.get(str).getBinanceSellPrice(), "-");
 				String upbitBuyPrice = JKStringUtil.nvl(resultEntity3.get(str).getUpbitBuyPrice(), "-");
 				String upbitSellPrice = JKStringUtil.nvl(resultEntity3.get(str).getUpbitSellPrice(), "-");
 				// 원화 차액 / 김프 계산
 				if(!("-").equals(bithumbBuyPrice) && (!("-").equals(upbitSellPrice))) {
 					double krwGap = Double.parseDouble(upbitSellPrice) - Double.parseDouble(bithumbBuyPrice);
 					resultEntity3.get(str).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(upbitSellPrice) - Double.parseDouble(bithumbBuyPrice)) * 100) / Double.parseDouble(bithumbBuyPrice);
					resultEntity3.get(str).setPriceGapPercent(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 				if(!("-").equals(bithumbSellPrice) && (!("-").equals(upbitBuyPrice))) {
					double krwGap = Double.parseDouble(upbitBuyPrice) - Double.parseDouble(bithumbSellPrice);
					resultEntity3.get(str).setPriceGapKrw2(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(upbitBuyPrice) - Double.parseDouble(bithumbSellPrice)) * 100) / Double.parseDouble(bithumbSellPrice);
					resultEntity3.get(str).setPriceGapPercent2(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 			}
		}
 		
 		// 빗썸 - 바이낸스 페어
 		for (String str : coinList3) {
 			if(resultEntity2.containsKey(str)){
 				String binanceBuyPrice = JKStringUtil.nvl(resultEntity2.get(str).getBinanceBuyPrice(), "-");
 				String binanceSellPrice = JKStringUtil.nvl(resultEntity2.get(str).getBinanceSellPrice(), "-");
 				String bithumbBuyPrice = JKStringUtil.nvl(resultEntity2.get(str).getUpbitBuyPrice(), "-");
 				String bithumbSellPrice = JKStringUtil.nvl(resultEntity2.get(str).getUpbitSellPrice(), "-");

 				// 원화 차액 / 김프 계산
 				if(!("-").equals(binanceBuyPrice) && (!("-").equals(bithumbSellPrice))) {
 					double krwGap = Double.parseDouble(bithumbSellPrice) - Double.parseDouble(binanceBuyPrice);
					resultEntity2.get(str).setPriceGapKrw(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(bithumbSellPrice) - Double.parseDouble(binanceBuyPrice)) * 100) / Double.parseDouble(binanceBuyPrice);
					resultEntity2.get(str).setPriceGapPercent(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 				if(!("-").equals(binanceSellPrice) && (!("-").equals(bithumbBuyPrice))) {
					double krwGap = Double.parseDouble(bithumbBuyPrice) - Double.parseDouble(binanceSellPrice);
					resultEntity2.get(str).setPriceGapKrw2(String.valueOf(JKStringUtil.mathRound(krwGap,2)));
					double priceGapPercent = ((Double.parseDouble(bithumbBuyPrice) - Double.parseDouble(binanceSellPrice)) * 100) / Double.parseDouble(binanceSellPrice);
					resultEntity2.get(str).setPriceGapPercent2(JKStringUtil.mathRound(priceGapPercent,2));
 				}
 			}
		}

 		
 		//----------- 업비트 - 바이낸스 페어 --------------------------------------------------------------------//
    	List<PriceCompareAskBidEntity> result1 = new ArrayList<PriceCompareAskBidEntity>();
    	List<PriceCompareAskBidEntity> result2 = new ArrayList<PriceCompareAskBidEntity>();
    	
 		// 값 정상 세팅 확인
 		for( String key : resultEntity.keySet() ){
 			if( resultEntity.get(key).getBinanceBuySatosiPrice() != null) {
 				result1.add(resultEntity.get(key));
 				// logger.info("키 : {}, 값 : {}", key, resultEntity.get(key));
 			}
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
 		HashMap<String, Object> resultData = new HashMap<String, Object>();
 		resultData.put("upbit", result1);
 		//----------- 업비트 - 바이낸스 페어 --------------------------------------------------------------------//

 		
 		//----------- 업비트 - 빗썸 페어 --------------------------------------------------------------------//
 		// 빗썸-업비트 정렬
    	List<PriceCompareCommonAskBidEntity> result3 = new ArrayList<PriceCompareCommonAskBidEntity>();
    	List<PriceCompareCommonAskBidEntity> result4 = new ArrayList<PriceCompareCommonAskBidEntity>();
    	
    	// 값 정상 세팅 확인
 		for( String key : resultEntity3.keySet() ){
 			if( (resultEntity3.get(key).getUpbitBuyPrice() != null) && ( resultEntity3.get(key).getBinanceBuyPrice() != null) ) {
 				if( !resultEntity3.get(key).getBinanceBuyPrice().equals("0.0")) {
 					result3.add(resultEntity3.get(key));
 				}
 				// logger.info("키 : {}, 값 : {}", key, resultEntity3.get(key));
 			}
 	    }
 		
 		// 깊은 복사 (deep copy)
 		for(int i=0; i<result3.size(); i++) {
 			result4.add(new PriceCompareCommonAskBidEntity(result3.get(i)));
 		}
 		
 		// 상승률 내림차순 정렬 (앞에꺼)
 		Collections.sort(result3, new GapPercentAscCompare3());
 		// 상승률 오름 정렬 (뒤에꺼)
 		Collections.sort(result4, new GapPercent2DescCompare3());
 		
 		for(int i=0; i<result3.size(); i++) {
 			result3.get(i).setCoinSymbol2(result4.get(i).getCoinSymbol());
 			result3.get(i).setUpbitBuyPrice(result4.get(i).getUpbitBuyPrice());
 			result3.get(i).setBinanceSellPrice(result4.get(i).getBinanceSellPrice());
 			result3.get(i).setPriceGapKrw2(result4.get(i).getPriceGapKrw2());
 			result3.get(i).setPriceGapPercent2(result4.get(i).getPriceGapPercent2());
		}
 		resultData.put("bithumb", result3);
 		//----------- 업비트 - 빗썸 페어 --------------------------------------------------------------------//
 		
 		
 		//----------- 업비트 - 빗썸 페어 --------------------------------------------------------------------//
 		// 빗썸-업비트 정렬
    	List<PriceCompareCommonAskBidEntity2> result5 = new ArrayList<PriceCompareCommonAskBidEntity2>();
    	List<PriceCompareCommonAskBidEntity2> result6 = new ArrayList<PriceCompareCommonAskBidEntity2>();
    	
    	// 값 정상 세팅 확인
 		for( String key : resultEntity2.keySet() ){
 			if( (resultEntity2.get(key).getUpbitBuyPrice() != null) && ( resultEntity2.get(key).getBinanceBuyPrice() != null) ) {
 				if( !resultEntity2.get(key).getBinanceBuyPrice().equals("0.0")) {
 					result5.add(resultEntity2.get(key));
 				}
 				// logger.info("키 : {}, 값 : {}", key, resultEntity3.get(key));
 			}
 	    }
 		
 		// 깊은 복사 (deep copy)
 		for(int i=0; i<result5.size(); i++) {
 			result6.add(new PriceCompareCommonAskBidEntity2(result5.get(i)));
 		}
 		
 		// 상승률 내림차순 정렬 (앞에꺼)
 		Collections.sort(result5, new GapPercentAscCompare4());
 		// 상승률 오름 정렬 (뒤에꺼)
 		Collections.sort(result6, new GapPercent2DescCompare4());
 		
 		for(int i=0; i<result5.size(); i++) {
 			result5.get(i).setCoinSymbol2(result6.get(i).getCoinSymbol());
 			result5.get(i).setUpbitBuyPrice(result6.get(i).getUpbitBuyPrice());
 			result5.get(i).setBinanceSellPrice(result6.get(i).getBinanceSellPrice());
 			result5.get(i).setPriceGapKrw2(result6.get(i).getPriceGapKrw2());
 			result5.get(i).setPriceGapPercent2(result6.get(i).getPriceGapPercent2());
		}
 		resultData.put("binance", result5);
 		//----------- 업비트 - 빗썸 페어 --------------------------------------------------------------------//
 		
 		return resultData;
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
