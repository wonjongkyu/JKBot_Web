package jk.framework.web.kakao.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jk.framework.web.kakao.entity.KakaoUserEntity;

public class KakaoLogin {
	
	// Login
	public static JsonNode getautorizeCode(String autorize_code) {
		 String RequestUrl = "https://kauth.kakao.com/oauth/authorize";
		RequestUrl += "?client_id=cc04df8dc625cce522bad9cb5fede3df&redirect_uri=http://localhost/login/kakaologin&response_type=code";
		
		
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("client_id", "cc04df8dc625cce522bad9cb5fede3df")); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost/login/kakaologin")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("response_type", "code")); // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(RequestUrl);
		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(get);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;

	}

	public static JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "cc04df8dc625cce522bad9cb5fede3df")); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost/login/kakaologin")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;

	}

	public static JsonNode getKakaoUserInfo(String autorize_code) {

		final String RequestUrl = "https://kapi.kakao.com/v1/user/me";

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);

		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
	
	public static JsonNode sendMessage(String autorize_code) {

		final String RequestUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);
		
		// add header
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("template_object", "{\r\n" + 
				"  \"object_type\": \"feed\",\r\n" + 
				"  \"content\": {\r\n" + 
				"    \"title\": \"업비트 NEO 구매 추천 \",\r\n" + 
				"    \"description\": \"업비트 : 75,000원 / 바이낸스 : 74,000원 (-5.0%) \",\r\n" + 
				"    \"image_url\": \"\",\r\n" + 
				"    \"link\": {\r\n" + 
				"      \"web_url\": \"https://developers.kakao.com\",\r\n" + 
				"      \"mobile_web_url\": \"https://developers.kakao.com\"\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  \"social\": {\r\n" + 
				"    \"like_count\": 1,\r\n" + 
				"    \"comment_count\": 1\r\n" + 
				"  },\r\n" + 
				"  \"button_title\": \"\"\r\n" + 
				"}"));
		JsonNode returnNode = null;

		try {
			// UTF-8로 전송하도록 변경
			post.setEntity(new UrlEncodedFormEntity(postParams, HTTP.UTF_8));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
	

	public static KakaoUserEntity changeData(JsonNode userInfo) {
		KakaoUserEntity vo = new KakaoUserEntity();

		vo.setSnsId(userInfo.path("id").asText()); // id -> vo 넣기

		if (userInfo.path("kaccount_email_verified").asText().equals("true")) { // 이메일 받기 허용 한 경우
			vo.setEmail(userInfo.path("kaccount_email").asText()); // email -> vo 넣기

		} else { // 이메일 거부 할 경우 코드 추후 개발

		}

		JsonNode properties = userInfo.path("properties"); // 추가정보 받아오기
		if (properties.has("nickname"))
			vo.setNickName(properties.path("nickname").asText());
			vo.setProfileImagePath(properties.path("profile_image").asText());
		return vo;
	}
	
}
