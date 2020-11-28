package jk.framework.rest.bithumb.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import jk.framework.common.util.etc.CommonUtil;
import jk.framework.common.util.etc.JKStringUtil;
import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.bithumb.entity.BithumbAskBidResultEntity;
import jk.framework.rest.bithumb.entity.BithumbAskBidResultEntity.Data.BIDS;
import jk.framework.rest.bithumb.entity.BithumbResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity.BithumbTickerResultData.BithumbTickerEntity;
import jk.framework.rest.bithumb.mapper.BithumbPublicRestMapper;

@Service
public class BithumbPublicRestService {
	
	@Autowired
	BithumbPublicRestMapper mapper;
	
	public List<BithumbTickerResultEntity> findByBno(int bno){
		return mapper.findByBno(bno);
	}
	
	public List<BithumbTickerResultEntity> findByBno2(int bno){
		return mapper.findByBno2(bno);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : findAll
	 * @date : 2018. 3. 21.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 21.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param bno
	 * @return
	 */ 	
	public List<BithumbTickerResultEntity> findAll(){
		return mapper.findAll();
	}
	
	public void save(BithumbTickerResultEntity entity){
		if(entity != null) {
			BithumbTickerResultData data = entity.getData();
			List<BithumbTickerEntity> list = CommonUtil.BithumbTickerEntityToList(data);
			if(list != null) {
				mapper.save(list);
			}else {
				
			}
		}
	}
	
	public BithumbTickerResultEntity findOne(BithumbTickerResultEntity entity){
		return mapper.findOne(entity);
	}
	
	public void delete(BithumbTickerResultEntity entity){
		mapper.delete(entity);
	}
	
	
	public List<BithumbResultEntity> getBidAskPrice(String apiUrl, HashSet<String> coinList, String symbolType, double exchangeRate, double buyPrice){
		
		
		BithumbAskBidResultEntity entity = null;
		List<BithumbResultEntity> resultList = new ArrayList<BithumbResultEntity>();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		// 현재 Upbit BTC 가격 가져와서 400만원으로 몇 비트 살 수 있는지 계산
		double BtcPrice = buyPrice;	// 400만원 (추후 실제 구매 가능 금액으로 변경 필요함)
	 
		for(String str : coinList) {
			try {
				// 결과 리스트 저장할 VO
				BithumbResultEntity resultEntity = new BithumbResultEntity();
				// Thread.sleep(50);	// 1000이 1초
				String param = str + "_KRW";
				String result = api.callUpbitApi("/public/orderbook/"+param, null);
			    Gson gson = new Gson();
			    
			    entity = gson.fromJson(result, BithumbAskBidResultEntity.class ); 
			    
				double purchasableAmount = BtcPrice;	// 구매 가능 금액
				// 업비트 매수물량 체크
				jk.framework.rest.bithumb.entity.BithumbAskBidResultEntity.Data data = entity.getData();
				
				List<BIDS> bids = data.getBids();
			    Double coinAmount = 0.0;		// 구매 가능 코인수
			    int num = 1;
			    
			    for (BIDS object : bids) {
			    	Double array1 = Double.parseDouble(object.getPrice());
			    	Double array2 = Double.parseDouble(object.getQuantity());
			    	Double temp = JKStringUtil.mathRound(array1*array2,9);
			    	// 로직 추가
			    	if(purchasableAmount >= temp) {
			    		coinAmount += array2;
			    		purchasableAmount = purchasableAmount - (array1*array2);
			    	}else if(purchasableAmount < temp){
			    		coinAmount += (purchasableAmount / array1);
		    			purchasableAmount = 0;
			    	}
			    	
			    	if(num == 1 ) {
			    		resultEntity.setAskCoinAveragePrice(array1+"");
			    	}
			    	
			    	if(purchasableAmount <= 0.0) {
			    		if(coinAmount > 0) {
			    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,0)));
			    		}else {
			    			resultEntity.setBidCoinAmout(String.valueOf(JKStringUtil.mathRound(coinAmount,4)));
			    		}
			    		
			    		resultEntity.setCoinSymbolName(str);
			    		resultEntity.setBidCoinAveragePrice(((BtcPrice)/coinAmount)+"");
			    		resultList.add(resultEntity);
			    		break;
			    	}
			    	
			    	if(num == bids.size()) {
			    		resultEntity.setCoinSymbolName(str);
			    		resultEntity.setBidCoinAveragePrice(0+"");
				    	resultList.add(resultEntity);
				    	break;
				    }
				    num++;
			    }
			} catch (Exception e) {
			// TODO: handle exception
			}
		}
		return resultList;
	}
}
