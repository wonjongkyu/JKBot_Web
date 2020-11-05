/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.huobi.controller; 

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jk.framework.rest.huobi.entity.HuobiAskBidResultEntity;
import jk.framework.rest.huobi.service.HuobiPublicRestService;

 
 
/**
 * <pre>
 * jk.framework.rest.huobi.controller 
 *    |_ HuobiPublicRestController.java
 * 
 * </pre>
 * @date : 2020. 9. 10. 오후 9:33:26
 * @version : 
 * @author : jongkyu
 */
@ResponseBody
@RequestMapping("/huobi/api/v1")
@Controller
public class HuobiPublicRestController{
	private static final Logger logger = LoggerFactory.getLogger(HuobiPublicRestController.class);
    
    @Value("${binance.apiUrl}")
    private String apiUrl ;
	
    @Autowired
    HuobiPublicRestService huobiPublicService;

	 
	/**
	 * <pre>
	 * 1. 개요 : 바이낸스 모든 코인의 시세를 가져온다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getTicker
	 * @date : 2018. 4. 13.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 13.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */ 	
	@RequestMapping(value = "/market/depth", method = RequestMethod.GET)
	public List<HuobiAskBidResultEntity> getTicker() {
		// 모든 코인 찾기
		List<HuobiAskBidResultEntity> resultEntity = null;
		// List<HuobiAskBidResultEntity> resultEntity = huobiPublicService.getAllTicker(apiUrl);
		return resultEntity;
	}
	
}
