/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.admin.entity; 

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
public class ExchangeRateEntity extends RestCommonVO{
	/*
	 * [{"date":"2018-04-16 13:00:00","name":"USDKRW=X","rate":1073.72998}]
	 */
	@JsonProperty("date")
	private String date;			// 날짜
	@JsonProperty("name")
	private String name;			// 한국거래소 원화 가격 
	@JsonProperty("rate")
	private String rate;			// 환율
}
