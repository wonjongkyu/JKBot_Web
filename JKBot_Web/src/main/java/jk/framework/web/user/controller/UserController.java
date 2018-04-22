package jk.framework.web.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jk.framework.common.util.etc.SessionService;
import jk.framework.web.user.entity.UserBalanceEntity;
import jk.framework.web.user.entity.UserInfoEntity;
import jk.framework.web.user.service.UserBalanceService;
import jk.framework.web.user.service.UserInfoService;


@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserBalanceService userBalanceService;
	
    @Autowired
    SessionService sessionService;
	
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
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String userMypage(Model model) {
		ModelAndView mav = new ModelAndView();
		UserBalanceEntity entity = new UserBalanceEntity();
		entity.setUserId("wonjongkyu");
		
		List<UserBalanceEntity> entityList = this.getUserBalanceInfo(entity);
		
		model.addAttribute("entityList", entityList);
		return "/user/myPage";
	}
	
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
	@GetMapping("/balanceInfo")
	public List<UserBalanceEntity> getUserBalanceInfo(@ModelAttribute UserBalanceEntity entity) {
		
		List<UserBalanceEntity> resultList = new ArrayList<UserBalanceEntity>();
		String userId = entity.getUserId();
		
		logger.info("getUserId {}", entity.getUserId());
		logger.info("getCoinSymbolName {}", entity.getCoinSymbolName());
		
		// 모든 회원의 계좌 정보를 조회해옴
		if("ALL".equals(userId)) {
			// 모든 사용자 조회
			List<UserInfoEntity> entityList = userInfoService.getAllList();
			return null;
			// return entityList;
		}else {
			// 특정 사용자 조회
			UserInfoEntity infoEntity = new UserInfoEntity();
			infoEntity.setUserId(userId);
			infoEntity = userInfoService.getUserInfo(infoEntity);
			if(infoEntity != null) {
				UserBalanceEntity balanceEntity = new UserBalanceEntity();
				balanceEntity = userBalanceService.selectOne(balanceEntity);
				resultList.add(balanceEntity);
			}
		}
		
		return resultList; // entity;
	}
	
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
	@PostMapping("/balanceInfo")
	public List<UserBalanceEntity> postUserBalanceInfo(@ModelAttribute UserBalanceEntity entity) {
		
		List<UserBalanceEntity> resultList = new ArrayList<UserBalanceEntity>();
		String userId = entity.getUserId();
		
		logger.info("getUserId {}", entity.getUserId());
		logger.info("getCoinSymbolName {}", entity.getCoinSymbolName());
		
		// 모든 회원의 계좌 정보를 조회해옴
		if("ALL".equals(userId)) {
			// 모든 사용자 조회
			List<UserInfoEntity> entityList = userInfoService.getAllList();
			return null;
			// return entityList;
		}else {
			// 특정 사용자 조회
			UserInfoEntity infoEntity = new UserInfoEntity();
			infoEntity.setUserId(userId);
			infoEntity = userInfoService.getUserInfo(infoEntity);
			if(infoEntity != null) {
				UserBalanceEntity balanceEntity = new UserBalanceEntity();
				balanceEntity = userBalanceService.selectOne(balanceEntity);
				resultList.add(balanceEntity);
			}
		}
		
		return resultList; // entity;
	}
}
