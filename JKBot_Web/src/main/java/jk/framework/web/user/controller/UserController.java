package jk.framework.web.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jk.framework.web.user.entity.UserInfoEntity;
import jk.framework.web.user.service.UserInfoService;


@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 ID를 통해 사용자 계좌를 조회한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getUserBalance
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param currency
	 * @return
	 */ 	
	@ResponseBody
	@RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
	public UserInfoEntity getUserBalance(@PathVariable String currency) {
		
		// 특정 사용자 조회
		UserInfoEntity entity = new UserInfoEntity();
		entity = userInfoService.selectOne(entity);
		
		/* 가입된 회원인 경우, 
		 * 회원ID를 통해 계좌정보를 가져온다.
		 */
		if(entity != null) {
			
		}
		
		return entity;
		
		/*BithumbInfoAccountEntity entity = null;

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
		return entity;*/
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 모든 사용자의 계좌를 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAllUserBalance
	 * @date : 2018. 3. 27.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 27.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param currency
	 * @return
	 */ 	
	@ResponseBody
	@RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
	public List<UserInfoEntity> getAllUserBalance(@PathVariable String currency) {
		// 모든 사용자 조회
		List<UserInfoEntity> entityList = userInfoService.selectAll();
		return entityList;
	}
}
