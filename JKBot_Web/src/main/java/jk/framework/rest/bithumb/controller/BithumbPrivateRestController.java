/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.bithumb.controller; 

import java.util.HashMap;

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
import jk.framework.rest.bithumb.entity.BithumbInfoAccountEntity;
import jk.framework.rest.bithumb.entity.BithumbInfoBalance;

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
@RequestMapping("/api/bithumb/privateapi")
@Controller
public class BithumbPrivateRestController {
	private static final Logger logger = LoggerFactory.getLogger(BithumbPrivateRestController.class);

    @Value("${bithumb.apiUrl}")
    private String apiUrl ;
    @Value("${bithumb.apiConnectKey}")
    private String apiConnectKey ;
    @Value("${bithumb.apiSecretKey}")
    private String apiSecretKey;
	
	/**
	 * <pre>
	 * 1. 개요 : bithumb 거래소 회원 정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getInfoAccount
	 * @date : 2018. 1. 21.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 1. 21.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param currency :BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM, BTG, EOS (기본값: BTC), ALL(전체 )
	 * @return
	 */ 	
	@RequestMapping(value = "/info/account/{currency}", method = RequestMethod.GET)
	public BithumbInfoAccountEntity getInfoAccount(@PathVariable String currency) {
		BithumbInfoAccountEntity entity = null;

		Api_Client api = new Api_Client(apiUrl, apiConnectKey, apiSecretKey);
		
		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", currency);
		
		try {
		    String result = api.callApi("/info/account/", rgParams);
		    System.out.println(result);
		    
		    Gson gson = new Gson();
		    entity = gson.fromJson(result, BithumbInfoAccountEntity.class);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return entity;
	}

	/**
	 * <pre>
	 * 1. 개요 : bithumb 거래소 회원 지갑 정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getInfoBalance
	 * @date : 2018. 1. 21.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 1. 21.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param currency :BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM, BTG, EOS (기본값: BTC), ALL(전체 )
	 * @return
	 */ 	
	@RequestMapping(value = "/info/balance/{currency}", method = RequestMethod.GET)
	public BithumbInfoBalance getInfoBalance(@PathVariable String currency) {
		BithumbInfoBalance bithumbVO = null;
		
		Api_Client api = new Api_Client(apiUrl, apiConnectKey, apiSecretKey);
		
		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("currency", currency);
		
		try {
		    String result = api.callApi("/info/balance/", rgParams);
		    System.out.println(result);
		    
		    Gson gson = new Gson();
			bithumbVO = gson.fromJson(result, BithumbInfoBalance.class);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return bithumbVO;
	}
}
