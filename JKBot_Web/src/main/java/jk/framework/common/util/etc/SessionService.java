package jk.framework.common.util.etc;

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

		if (attributName.equals("uuid")) {
			final String uuid = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(uuid)) {
				return "testUuid";
			}
			return uuid;
		} /**/else if (attributName.equals("userId")) {
			final String userId = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(userId)) {
				return Constants.NO_USER_ID_SQ;
			}
			return userId;
		} else if (attributName.equals("memberIdSq")) {
			final String memberIdSq = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(memberIdSq)) {
				return Constants.NO_MEMBER_ID_SQ;
			}
			return memberIdSq;
		} else if (attributName.equals("hccAuthYN")) {
			final String hccAuthYN = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(hccAuthYN)) {
				return Constants.HCC_AUTH_N;
			}
			return hccAuthYN;
		} else if (attributName.equals("customerNo")) {
			final String customerNo = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(customerNo)) {
				return "1";
			}
			return customerNo;
		} else if (attributName.equals("searchId")) {
			// 2018.03.13 검색어 로그 저장을 위한 날짜데이터 저장
			final String searchLogDate = (String) httpSession.getAttribute(attributName);
			if (StringUtils.isBlank(searchLogDate)) {
				return "";
			}
			return searchLogDate;
		} else if (attributName.equals("sessionId")) {
			return httpSession.getId();
		}

		return (String) httpSession.getAttribute(attributName);
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
	 *
	 * @Mehtod Name : getLoginUser
	 * @param
	 * @return
	 */
	// public TempUserEntity getLoginUser() {
	// return (TempUserEntity)httpSession.getAttribute("loginUser");
	// }

}
