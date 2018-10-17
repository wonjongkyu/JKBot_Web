<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<script src="<c:url value='js/views/main/main.js'/>"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>Login Demo - Kakao JavaScript SDK</title>
<script src="<c:url value='js/kakao.min.js'/>"></script>

</head>
<body>
<a id="kakao-login-btn"></a>
<a href="http://developers.kakao.com/logout"></a>
<body>
 
 
<a id="kakao-link-btn" href="javascript:;">
<img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"/>
</a>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('fad841a6440735562e73d03a1f2482a0');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
    	  console.log(authObj.access_token);
        alert(JSON.stringify(authObj));
      },
      fail: function(err) {
         alert(JSON.stringify(err));
      }
    });
    
 	/* // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    Kakao.Link.createCustomButton({
      container: '#kakao-link-btn',
      templateId: 9196,
      templateArgs: {
        'title': '제목 영역입니다.',
        'description': '설명 영역입니다.'
      }
    }); */
 
 
    Kakao.API.request({
        url: 'https://kapi.kakao.com/v2/api/talk/memo/default/send',
        data: {
            template_object:   {
          	  "object_type": "feed",
        	  "content": {
        	    "title": "코인 가격",
        	    "description": "프리미엄 얼마",
        	    "image_url": "",
        	    "link": {
        	      "web_url": "",
        	      "mobile_web_url": ""
        	    }
        	  }
        	}
        }
    });

  //]]>
</script>

</body>
</html>
 