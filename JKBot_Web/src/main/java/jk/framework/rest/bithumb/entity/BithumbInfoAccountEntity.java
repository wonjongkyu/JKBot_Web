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

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class BithumbInfoAccountEntity extends RestCommonVO{
	@JsonProperty("data")
	private Data data;

	public Data getData() {
		return data;
	}
	
	public static class Data {
		@JsonProperty("created")
		private String created;		// 회원가입 일시 Timestamp
		@JsonProperty("account_id")
		private String account_id;	// 회원ID
		@JsonProperty("trade_fee")
		private String trade_fee;	// 거래 수수료
		@JsonProperty("balance")
		private String balance;		// 1Currency 잔액 (BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM, BTG, EOS)
	}
}
