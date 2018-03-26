/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.rest.bithumb.entity; 

import com.fasterxml.jackson.annotation.JsonProperty;

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * <pre>
 * com.jkbot.bithumb.RestAPI.domain 
 *    |_ BithumbRestVO.java
 * 
 * </pre>
 * @date : 2018. 1. 20. 오후 8:56:56
 * @version : 
 * @author : jongkyu
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class BithumbTickerResultEntity extends RestCommonVO{
	@JsonProperty("data")
	private BithumbTickerResultData data;
	@JsonProperty("date")
	private String date;
	
	@Setter
	public static class BithumbTickerResultData {
		@JsonProperty("BTC")
		private BithumbTickerEntity BTC;	// 비트코인
		@JsonProperty("ETH")
		private BithumbTickerEntity ETH;	// 이더리움
		@JsonProperty("DASH")
		private BithumbTickerEntity DASH;	// 대시
		@JsonProperty("LTC")
		private BithumbTickerEntity LTC;	// 라이트 코인
		@JsonProperty("ETC")
		private BithumbTickerEntity ETC;	// 이더리움 클래식
		@JsonProperty("XRP")
		private BithumbTickerEntity XRP;	// 리플
		@JsonProperty("BCH")
		private BithumbTickerEntity BCH;	// 비트코인 캐시
		@JsonProperty("XMR")
		private BithumbTickerEntity XMR;	// 모네로
		@JsonProperty("ZEC")
		private BithumbTickerEntity ZEC;	// 제트캐시
		@JsonProperty("QTUM")
		private BithumbTickerEntity QTUM;	// 퀀텀
		@JsonProperty("BTG")
		private BithumbTickerEntity BTG;	// 비트코인 골드
		@JsonProperty("EOS")
		private BithumbTickerEntity EOS;	// 이오스
		
		public BithumbTickerEntity getBTC() {
			BTC.setCurrency_name("비트코인");
			return BTC;
		}
		
		public BithumbTickerEntity getETH() {
			ETH.setCurrency_name("이더리움");
			return ETH;
		}
		
		public BithumbTickerEntity getDASH() {
			DASH.setCurrency_name("대시");
			return DASH;
		}
		
		public BithumbTickerEntity getLTC() {
			LTC.setCurrency_name("라이트코인");
			return LTC;
		}
		
		public BithumbTickerEntity getETC() {
			ETC.setCurrency_name("이더리움 클래식");
			return ETC;
		}
		
		public BithumbTickerEntity getXRP() {
			XRP.setCurrency_name("리플");
			return XRP;
		}
		
		public BithumbTickerEntity getBCH() {
			BCH.setCurrency_name("비트코인 캐시");
			return BCH;
		}
		
		public BithumbTickerEntity getXMR() {
			XMR.setCurrency_name("모네로");
			return XMR;
		}
		
		public BithumbTickerEntity getZEC() {
			ZEC.setCurrency_name("제트캐시");
			return ZEC;
		}
		
		public BithumbTickerEntity getQTUM() {
			QTUM.setCurrency_name("퀀텀");
			return QTUM;
		}
		
		public BithumbTickerEntity getBTG() {
			BTG.setCurrency_name("비트코인 골드");
			return BTG;
		}
		
		public BithumbTickerEntity getEOS() {
			EOS.setCurrency_name("이오스");
			return EOS;
		}
		
		@Getter
		@Setter
		public static class BithumbTickerEntity {
			private String currency_name;	// 코인 종류
			private String opening_price;	// 최근 24시간 내 시작 거래금액
			private String closing_price;	// 최근 24시간 내 마지막 거래금액
			private String min_price;		// 최근 24시간 내 최저 거래금액
			private String max_price;		// 최근 24시간 내 최고 거래금액
			private String average_price;	// 최근 24시간 내 평균 거래금액
			private String units_traded;	// 최근 24시간 내 Currency 거래량
			private String volume_1day;		// 최근 1일간 Currency 거래량
			private String volume_7day;		// 최근 7일간 Currency 거래량
			private String buy_price;		// 거래 대기건 최고 구매가
			private String sell_price;		// 거래 대기건 최소 판매가
			private String date;			// 현재 시간 Timestamp
		}
	}
	
}
