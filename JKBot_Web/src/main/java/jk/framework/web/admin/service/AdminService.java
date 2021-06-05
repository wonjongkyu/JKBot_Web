package jk.framework.web.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.http.Api_Client;
import jk.framework.web.admin.entity.ExchangeCoinPriceEntity;
import jk.framework.web.admin.entity.ExchangeRateEntity;
import jk.framework.web.admin.entity.KimpEntity;
import jk.framework.web.admin.entity.PriceCompareEntity;
import jk.framework.web.admin.entity.PriceExchangeInfoEntity;
import jk.framework.web.admin.entity.PriceHistoryEntity;
import jk.framework.web.admin.mapper.AdminMapper;

@Service
public class AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	// exchange apiKey
    @Value("${exchange.apiUrl}")
    private String apiUrl ;
	
    
    @Autowired
    AdminMapper mapper;
    
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
	 * @return
	 * 김프 알람용 
	 */
	public KimpEntity getKimp(){
		
		KimpEntity entity = mapper.getKimp();
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
	 
}
