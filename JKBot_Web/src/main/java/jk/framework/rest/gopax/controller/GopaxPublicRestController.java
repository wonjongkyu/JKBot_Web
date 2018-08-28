/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.gopax.controller; 

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.binance.entity.BinanceTickerResultEntity;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;
import jk.framework.rest.gopax.entity.GopaxTickerResultEntity;
import jk.framework.rest.gopax.service.GopaxPublicRestService;

 
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
@RequestMapping("/gopax/api")
@Controller
public class GopaxPublicRestController{
	private static final Logger logger = LoggerFactory.getLogger(GopaxPublicRestController.class);
    
    // @Value("${gopax.apiUrl}")
    private String apiUrl ;
	
    @Autowired
    GopaxPublicRestService gopaxPublicService;

    @RequestMapping(value = "/ticker/{currency}", method = RequestMethod.GET)
	public GopaxTickerResultEntity getTicker(@PathVariable String currency) {
    	List<GopaxTickerResultEntity> entity = null;
    	GopaxTickerResultEntity resultEntity = new GopaxTickerResultEntity();
		Api_Client api = new Api_Client(apiUrl, null, null);
		
		try {
		    String result = api.callCommonApi("/trading-pairs/"+currency+"/trades", null);
		    System.out.println(result);
		    
		    Gson gson = new Gson();
		    entity = gson.fromJson(result, new TypeToken<List<GopaxTickerResultEntity>>(){}.getType()); 
			
		    for (GopaxTickerResultEntity gopaxEntity : entity) {
		    	resultEntity = gopaxEntity;
		    	break;
			}
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return resultEntity;
	}
	
}
