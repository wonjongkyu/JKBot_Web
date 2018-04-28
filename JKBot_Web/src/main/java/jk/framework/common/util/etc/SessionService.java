package jk.framework.common.util.etc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jk.framework.constants.Constants;


/**
 * <pre>
 * com.hyundaicard.cse.app.bookmark.service
 *    |_ SessionService.java
 *
 * </pre>
 * @date : 2018. 2. 8. 오후 5:45:43
 * @version : 0.0.1
 * @author : GGBY25
 */
@Service
public class SessionService {

	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

	// @Autowired
	// private HttpSession httpSession;

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 : 세션 서비스
	 * </pre>
	 * @Method Name : getAttribute
	 * @date : 2018. 2. 8.
	 * @author : GGBY25
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 2. 8.		GGBY25				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param attributName
	 * @return
	 */
	public String getAttribute(final String attributName) {

		final ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpServletRequest request = sra.getRequest(); // 리퀘스트 가져오기
		final HttpSession httpSession = request.getSession(); // 세션가져오기

		return (String) httpSession.getAttribute(attributName);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Int 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAttributeInt
	 * @date : 2018. 4. 21.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 21.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param attributName
	 * @return
	 */ 	
	public int getAttributeInt(final String attributName) {

		final ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpServletRequest request = sra.getRequest(); // 리퀘스트 가져오기
		final HttpSession httpSession = request.getSession(); // 세션가져오기
		
		if("priceCompare".equals(attributName)) {
			if(httpSession.getAttribute(attributName) == null) {
				return 0;
			}
		}else if("historyDeleteTime".equals(attributName)) {
			if(httpSession.getAttribute(attributName) == null) {
				return 0;
			}
		}
		return (int) httpSession.getAttribute(attributName);
	}

	/**
	 * <pre>
	 * 1. 개요 : 문자열로 attribute 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getAttributeStr
	 * @date : 2018. 4. 20.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 20.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param attributName
	 * @return
	 */ 	
	public String getAttributeStr(final String attributName) {

		final ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpServletRequest request = sra.getRequest(); // 리퀘스트 가져오기
		final HttpSession httpSession = request.getSession(); // 세션가져오기
 
		return (String)httpSession.getAttribute(attributName);
	}
	/**
	 * <pre>
	 * 1. 개요 : 세션 추가
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : setAttribute
	 * @date : 2018. 3. 13.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 13.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param attributName
	 * @param attributVal
	 */
	public void setAttribute(final String attributName, final String attributVal) {

		final ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpServletRequest request = sra.getRequest(); // 리퀘스트 가져오기
		final HttpSession httpSession = request.getSession(); // 세션가져오기

		httpSession.setAttribute(attributName, attributVal);
		/*if (attributName.equals("searchId")) {
			// to-do : 예외처리
		}*/
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 세션 추가
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : setAttribute
	 * @date : 2018. 3. 13.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 13.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param attributName
	 * @param attributVal
	 */
	public void setAttributeInt(final String attributName, final int attributVal) {

		final ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final HttpServletRequest request = sra.getRequest(); // 리퀘스트 가져오기
		final HttpSession httpSession = request.getSession(); // 세션가져오기

		httpSession.setAttribute(attributName, attributVal);
		/*if (attributName.equals("searchId")) {
			// to-do : 예외처리
		}*/
	}
}
