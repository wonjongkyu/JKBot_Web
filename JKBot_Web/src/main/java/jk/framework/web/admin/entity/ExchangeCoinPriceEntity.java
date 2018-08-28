/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.admin.entity; 

import jk.framework.rest.common.entity.RestCommonVO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class ExchangeCoinPriceEntity extends RestCommonVO{

	private String exchangeName;	// 코인 거래소명
	private String coinSymbol;		// 코인명
	private String priceKrw;		// 한화 가격
}
