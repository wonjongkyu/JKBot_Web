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
@EqualsAndHashCode(callSuper=false)
public class BithumbInfoBalance extends RestCommonVO{
	@JsonProperty("data")
	private Data data;

	@Getter
	@Setter
    public static class Data {
		@JsonProperty("total_btc")
		private String total_btc;
		@JsonProperty("total_krw")
		private String total_krw;
		@JsonProperty("in_use_btc")
		private String in_use_btc;
		@JsonProperty("in_use_krw")
		private String in_use_krw;
		@JsonProperty("available_btc")
		private String available_btc;
		@JsonProperty("available_krw")
		private String available_krw;
		@JsonProperty("xcoin_last")
		private String xcoin_last;
    }
}
