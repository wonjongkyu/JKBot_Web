package jk.framework.web.user.entity; 

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserInfoEntity{
	@JsonProperty("userId")
	private String userId;				// 사용자 ID
	@JsonProperty("userPwd")
	private String userPwd;				// 사용자 패스워드
}
