package jk.framework.common.util.etc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JKStringUtil {

	private static final Logger logger = LoggerFactory.getLogger(JKStringUtil.class);
	
	/**
	 * <pre>
	 * 1. 개요 : String 타입 int 타입으로 변환
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : parseInt
	 * @date : 2018. 2. 23.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 2. 23.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param str
	 * @return
	 */
	public static int parseInt(final String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 :  String 타입 Double 타입으로 변환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : parseDouble
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param str
	 * @return
	 */ 	
	public static double parseDouble(final String str) {
		double result = 0L;
		try {
			if(str != null) {
				result = Double.parseDouble(str);
			}
		} catch (NumberFormatException e) {
			// logger.error(e.getMessage());
		}
		return result;
	}
	
	public static float parseFloat(final String str) {
		float result = 0f;
		try {
			result = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * <pre>
	 * 1. 개요 : null값 또는 빈 문자을 체크해서 지정된 문자열로 대체
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : nvl
	 * @date : 2018. 3. 15.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 15.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param str - 변환할 문자열
	 * @param newStr - 변환된 문자열
	 * @return 널 또는 빈 문자대체 문자열
	 */
	public static String nvl(final String str, final String newStr) {
		if(str == null || str.trim().equals("")) {
			return newStr;
		} else {
			return str;
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 해당 문자가 null이거나 공백이면 true 아니면 false반환
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : isEmptyString
	 * @date : 2018. 3. 15.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 15.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param str - 검사할 문자열
	 * @return 검사 결과 여부
	 */
	public static boolean isEmptyString(final String str){
		return (str == null || "".equals(str)) ? true : false;
	}

	/**
	 * <pre>
	 * 1. 개요 : 해당 객체가 null 이거나 empty 일경우 true를 반환
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : isEmpty
	 * @date : 2018. 3. 15.
	 * @author : "Wonjongkyu"
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 15.		"Wonjongkyu"				최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(final Object obj){
		return (obj == null || "".equals(obj.toString())) ? true : false;
	}
	
	 
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : mathRound
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param num
	 * @return
	 */ 	
	public static double mathRound(String num, int round){
		Double doNum = Double.parseDouble(num);
		return mathRound(doNum, round);
	}
	
 
	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : mathRound
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param num
	 * @return
	 */ 	
	public static double mathRound(double num){
		return mathRound(num, 2);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 소수점에서 반올림
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : mathRound
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param num
	 * @return
	 */ 	
	public static double mathRound(double num, int round){
		double rnum = Math.pow(10, round);
		return Math.round(num*rnum) / rnum;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 소수점에서 반올림
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : mathRound
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param num
	 * @return
	 */ 	
	public static String mathKrwRound(String str){
		double num = Double.parseDouble(str);
		return mathKrwRound(num);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 소수점에서 반올림
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : mathRound
	 * @date : 2018. 4. 16.
	 * @author : Hyundai
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 16.		Hyundai				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param num
	 * @return
	 */ 	
	public static String mathKrwRound(double num){
		return String.valueOf((int)Math.round(num));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 현재시간 가져오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getNowTime
	 * @date : 2018. 4. 29.
	 * @author : jongkyu
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 4. 29.		jongkyu				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return
	 */ 	
	public static String getNowTime(){
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		return mTime;
	}
}
