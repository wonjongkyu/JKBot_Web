package jk.framework.rest.upbit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.upbit.entity.UpbitTickerResultEntity;

@Service
public class UpbitPublicRestService {
	
	public List<UpbitTickerResultEntity> getTicker(String apiUrl){
		HashSet<String> coinList = new HashSet<String>();
		coinList.add("BTC");
		return getTicker(apiUrl, null);
	}
	
	public List<UpbitTickerResultEntity> getTicker(String apiUrl, HashSet<String> coinList) {
		List<UpbitTickerResultEntity> entity = null;
		List<UpbitTickerResultEntity> resultEntity = new ArrayList<UpbitTickerResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		for(String str : coinList) {
			String param = "/minutes/1?code=CRIX.UPBIT.KRW-" + str;
			String result = api.callUpbitApi("/candles/"+param, null);

			try {
			    Gson gson = new Gson();
			    entity = gson.fromJson(result, new TypeToken<List<UpbitTickerResultEntity>>(){}.getType()); 
			    for (UpbitTickerResultEntity upbitTickerResultEntity : entity) {
			    	upbitTickerResultEntity.setTradeType(str);
			    	resultEntity.add(upbitTickerResultEntity);
				}
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
		 
		return resultEntity;
	}
}
