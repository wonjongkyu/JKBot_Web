/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.binance.controller; 

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity.BinanceTickerData;

 
/**
 * <pre>
 * jk.framework.rest.binance.controller 
 *    |_ BinancePublicRestController.java
 * 
 * </pre>
 * @date : 2018. 4. 9. 오후 1:11:21
 * @version : 
 * @author : Hyundai
 */
@ResponseBody
@RequestMapping("/binance/api/v1")
@Controller
public class BinancePublicRestController{
	private static final Logger logger = LoggerFactory.getLogger(BinancePublicRestController.class);
    
    @Value("${binance.apiUrl}")
    private String apiUrl ;
	
/*   
    @Autowired
	BithumbPublicRestService publicService;
*/
	 
	@RequestMapping(value = "/ticker/24hr", method = RequestMethod.GET)
	public BinanceTickerResultEntity getTicker() {
		BinanceTickerResultEntity entity = null;
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		try {
		    String result = api.callApi("/ticker/24hr", null, "GET");
		    System.out.println(result);
		    
		    Gson gson = new Gson();
		    entity = gson.fromJson(result, BinanceTickerResultEntity.class);
		    // DB에 시세 저장
		    // publicService.save(entity);
		    List<BinanceTickerData> test = entity.getList();
		    for (BinanceTickerData binanceTickerData : test) {
				System.out.println(binanceTickerData.getSymbol());
			}
			System.out.println();
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return entity;
	}
	
}
