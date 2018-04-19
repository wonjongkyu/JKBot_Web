package jk.framework.web.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.web.price.entity.ExchangeRateEntity;
import jk.framework.web.price.entity.PriceExchangeInfoEntity;
import jk.framework.web.price.mapper.ExchangeInfoMapper;
import jk.framework.web.user.entity.UserBalanceEntity;

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
	public Double getExchangeRate(){
		
		Double exchangeRate = 0D;	// 환율
		List<ExchangeRateEntity> entity = null;
		
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		try {
		    String result = api.callCommonApi("/KRW/USD.json", null);
		    Gson gson = new Gson();
		    
		    entity = gson.fromJson(result, new TypeToken<List<ExchangeRateEntity>>(){}.getType()); 
		    for (ExchangeRateEntity exchangeRateEntity : entity) {
		    	exchangeRate = Double.parseDouble(exchangeRateEntity.getRate());
			}
		} catch (Exception e) {
		   // e.printStackTrace();
		}
		return exchangeRate;
	}
}
