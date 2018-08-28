/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.admin.entity; 

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class CommonInfoEntity extends RestCommonVO{

	private String binanceBtcKrwPrice;		// 비트코인-원화 가격 (바이낸스 기준)
	private String btcUpdateDt;				// BTC 업데이트 날짜
	private String exchangeRate;			// 환율
	private String exchangeUpdateDt;		// 환율 업데이트 날짜
}
