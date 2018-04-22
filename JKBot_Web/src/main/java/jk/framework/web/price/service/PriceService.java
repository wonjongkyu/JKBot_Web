package jk.framework.web.price.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.web.price.entity.ExchangeCoinPriceEntity;
import jk.framework.web.price.entity.ExchangeRateEntity;
import jk.framework.web.price.entity.PriceCompareEntity;
import jk.framework.web.price.entity.PriceExchangeInfoEntity;
import jk.framework.web.price.mapper.ExchangeInfoMapper;

@Service
public class PriceService {
	
	// exchange apiKey
    @Value("${exchange.apiUrl}")
    private String apiUrl ;
	
    
    @Autowired
    ExchangeInfoMapper mapper;
    
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
		   // e.printStackTrace();
		}
		return entity;
	}
}
