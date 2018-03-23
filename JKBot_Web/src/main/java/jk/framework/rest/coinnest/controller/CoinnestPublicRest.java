/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.coinnest.controller; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jk.framework.common.util.http.Api_Client;
import jk.framework.rest.bithumb.entity.BithumbTickerResultEntity;

/**
 * <pre>
 * com.jkbot.bithumb.RestAPI 
 *    |_ BithumbRestAPI.java
 * 
 * </pre>
 * @date : 2018. 1. 20. 오후 8:54:04
 * @version : 
 * @author : jongkyu
 */
@ResponseBody
@RequestMapping("/coinnest/publicapi")
@Controller
public class CoinnestPublicRest {
	private static final Logger logger = LoggerFactory.getLogger(CoinnestPublicRest.class);
    
	@Value("${coinnest.apiUrl}")
    private String apiUrl ;
    @Value("${coinnest.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${coinnest.apiSecretKey}")
    private String apiSecretKey;
	 
	@RequestMapping(value = "/ticker/{currency}", method = RequestMethod.GET)
	public BithumbTickerResultEntity getTicker(@PathVariable String currency) {
		BithumbTickerResultEntity entity = null;
		Api_Client api = new Api_Client(apiUrl, apiConnectKey, apiSecretKey);
		
		try {
		    String result = api.callApi("/api/pub/ticker/coin="+currency, null);
		    System.out.println(result);
		    
		    Gson gson = new Gson();
		    entity = gson.fromJson(result, BithumbTickerResultEntity.class);
			System.out.println(entity.getData().getBTC().getClosing_price());
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return entity;
	}
	
}
