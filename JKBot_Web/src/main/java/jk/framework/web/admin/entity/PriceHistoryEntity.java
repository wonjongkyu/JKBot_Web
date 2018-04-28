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
public class PriceHistoryEntity extends RestCommonVO{

	private String coinSymbolName;		// 코인명
	private String coinPriceKrwA;		// 코인 원화가격
	private String coinPriceKrwB;		// 코인 원화가격
	private String coinPriceWeightA;	// 코인 변동폭
	private String coinPriceWeightB;	// 코인 변동폭
	private String insertDt;			// 
	private String updateDt;			// 업데이트 날짜
}
