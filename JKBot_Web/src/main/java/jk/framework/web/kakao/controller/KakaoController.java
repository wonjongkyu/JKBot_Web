package jk.framework.web.kakao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import jk.framework.web.kakao.entity.KakaoUserEntity;

/**
 * Handles requests for the application home page. 
 */
@RequestMapping("/login")
@Controller
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	
	@ResponseBody
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public JsonNode kakaoLogin(@RequestParam("code") String code , HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

	  // JsonNode tokenTemp = KakaoLogin.getautorizeCode(code); 
	  JsonNode token = KakaoLogin.getAccessToken(code);

	  // JsonNode profile = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());
	  // KakaoUserEntity vo = KakaoLogin.changeData(profile);
	  // System.out.println(profile);

	  JsonNode sendToken = KakaoLogin.sendMessage(token.path("access_token").toString());
	  logger.info(":::{}", sendToken);
	  // vo.setSnsId("k"+vo.getSnsId());

	  System.out.println(session);
	  // session.setAttribute("login", vo);
	  // System.out.println(vo.toString());

	  // vo = service.kakaoLogin(vo);  
	  return sendToken;
	}
	
}
