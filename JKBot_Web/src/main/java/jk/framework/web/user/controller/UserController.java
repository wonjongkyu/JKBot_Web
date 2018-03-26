package jk.framework.web.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jk.framework.web.user.entity.UserBalanceEntity;
import jk.framework.web.user.entity.UserInfoEntity;
import jk.framework.web.user.service.UserInfoService;


@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserInfoService userService;
	
	@ResponseBody
	@RequestMapping(value = "/balance/{currency}", method = RequestMethod.GET)
	public UserInfoEntity getUserBalance(@PathVariable String currency) {
		
		UserInfoEntity entity = new UserInfoEntity();
		userService.selectAll();
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
}
