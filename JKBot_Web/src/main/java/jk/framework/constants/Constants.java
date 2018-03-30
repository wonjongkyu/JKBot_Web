package jk.framework.constants;

/**
 * Constants class 상수 정의 클래스
 *
 * @version : 1.0
 * @author : Copyright (c) HYUNDAI CARD Corp.. All Rights Reserved.
 */
public class Constants {

    /**
     * 사이트를 좋아요 시 CTG_KEY 좋아요 사이트와 카테고리 구분 값
     */
    public static final String LIKE_SITE_CTG_KET = "__SITE_HOME__";

    public static final String NO_MEMBER_ID_SQ = "T999999999999999";
    public static final String NO_USER_ID_SQ = "NO_USER";
    public static final String HCC_AUTH_N = "N";
    public static final String HCC_AUTH_Y = "Y";

    // 검색 filter type
    public static final String FILTER_TYPE_CATEGORY = "CATEGORY";// 직접적 가격표현
    public static final String FILTER_TYPE_BRAND = "BRAND";// 고가,중가,저가

    // 내게 맞는 유형
    public static final String PERSONAL_TYPE_BAS_1 = "1";// 기본형 1
    public static final String PERSONAL_TYPE_BAS_2 = "2";// 기본형 2
    public static final String PERSONAL_TYPE_BAS_3 = "3";// 기본형 3
    public static final String PERSONAL_TYPE_BAS_4 = "8";// 기본형 4
    public static final String PERSONAL_TYPE_MEM_1 = "4";// 회원형 1
    public static final String PERSONAL_TYPE_MEM_2 = "5";// 회원형 2
    public static final String PERSONAL_TYPE_MEM_3 = "6";// 회원형 3
    public static final String PERSONAL_TYPE_MEM_4 = "7";// 회원형 4
    public static final String PERSONAL_TYPE_MEM_5 = "9";// 회원형 5

    // log type
    public static final String TO_LIKE = "LIKE";
    public static final String TO_BOOKMARK = "FAV";
    public static final String TO_CLICK = "CLICK";

    // query type Code Limit size 8
    public static final String QUERY_TYPE_1 = "POPULAR"; // 인기 있는
    public static final String QUERY_TYPE_2 = "NEWPOP"; // 최근 뜨는
    public static final String QUERY_TYPE_3_1 = "PERSONAL"; // 내게 맞는
    public static final String QUERY_TYPE_3_2 = "QUESTION"; // 내게 맞는 비회원 설문형
    public static final String QUERY_TYPE_3_3 = "LOGGED"; // 내게 맞는 비회원 로그형
    public static final String QUERY_TYPE_3_4 = "Q_LOG"; // 내게 맞는 비회원 로그형
    
    public static final String APP_VERSION_DEFAULT = "001";

}