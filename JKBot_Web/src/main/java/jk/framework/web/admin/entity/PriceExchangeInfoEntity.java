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
public class PriceExchangeInfoEntity extends RestCommonVO{
	/*
	 * [{"date":"2018-04-16 13:00:00","name":"USDKRW=X","rate":1073.72998}]
	 */
	private String exchangeName;		// 거래소 명
	private String coinKorName;			// 코인 한글명
	private String coinSymbolName;		// 코인 심볼명
	private String coinExchangeType;	// 코인 거래단위
	private String coinTransFeeKrw;		// 코인 전송 수수료 (krw)
	private String coinPriceKrw;		// 코인 최근 가격
	private String coinPriceUpdateDt;	// 코인 최근 가격 업데이트 시간
}
