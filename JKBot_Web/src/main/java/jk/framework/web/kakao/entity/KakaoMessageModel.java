/*
 * Copyright yysvip.tistory.com.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of yysvip.tistory.com.,LTD. ("Confidential Information").
 */
package jk.framework.web.kakao.entity;

import jk.framework.web.admin.entity.PriceExchangeInfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class KakaoUserEntity extends PriceExchangeInfoEntity{
		private String userName;
		private String profileImagePath;
		private String snsId;
		private String email;
		private String nickName;
}
