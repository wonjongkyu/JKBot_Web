package jk.framework.common.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.fasterxml.jackson.databind.ObjectMapper;

import jk.framework.common.util.etc.Util;
import jk.framework.common.util.lib.HttpRequest;


@SuppressWarnings("unused")
public class Api_Client {
	
	 /**
     * The echo server on websocket.org.
     */
    private static final String SERVER = "wss://api.upbit.com/websocket/v1";
 
    /**
     * The timeout value in milliseconds for socket connection.
     */
    private static final int TIMEOUT = 50000;
    
    protected String api_url;
    protected String api_key;
    protected String api_secret;
    
    public static String websocketResult = "";

    public Api_Client(String api_url, String api_key, String api_secret) {
    	this.api_url = api_url;
		this.api_key = api_key;
		this.api_secret = api_secret;
    }

    /**
     * 현재의 시간을 ns로 리턴한다.(1/1,000,000,000 초)
     * 
     * @return int
     */
    private String usecTime() {
    	/*
		long start = System.nanoTime();
		// do stuff
		long nanoseconds = System.nanoTime();
		long microseconds = TimeUnit.NANOSECONDS.toMicros(nanoseconds);
		long seconds = TimeUnit.NANOSECONDS.toSeconds(nanoseconds);
	
		int elapsedTime = (int) (microseconds + seconds);
	
		System.out.println("elapsedTime ==> " + microseconds + " : " + seconds);
		*/
    	
		return String.valueOf(System.currentTimeMillis());
    }

    @SuppressWarnings("null")
	private String request(String strHost, String strMemod, HashMap<String, String> rgParams,  HashMap<String, String> httpHeaders) {
    	String response = "";

		// SSL 여부
		if (!strHost.contains("binance.com") && strHost.startsWith("https://")) {
		    HttpRequest request = HttpRequest.get(strHost);
		    // Accept all certificates
		    request.trustAllCerts();
		    // Accept all hostnames
		    request.trustAllHosts();
		}
	
		if (strMemod.toUpperCase().equals("HEAD")) {
		
		} else {
		    HttpRequest request = null;
	
		    // POST/GET 설정
		    if (strMemod.toUpperCase().equals("POST")) {
				request = new HttpRequest(strHost, "POST");
				request.readTimeout(2000);
		
				System.out.println("POST ==> " + request.url());
		
				if (httpHeaders != null && !httpHeaders.isEmpty()) {
				    httpHeaders.put("api-client-type", "2");
				    httpHeaders.put("cookie", "2");
				    httpHeaders.put("Access-Control-Allow-Origin", "*");	// 추가
				    request.headers(httpHeaders);
				    System.out.println(httpHeaders.toString());
				}
				if (rgParams != null && !rgParams.isEmpty()) {
				    request.form(rgParams);
				    System.out.println(rgParams.toString());
				}
		    } else {
		    	// request.headers(httpHeaders);
				request = HttpRequest.get(strHost + Util.mapToQueryString(rgParams));
				// System.out.println("request:" + request);
				if(strHost.contains("http://api.manana.kr")) {
					request.readTimeout(30000);
				}else {
					request.readTimeout(600);
				}
				// System.out.println("Response was: " + response);
		    }
	
		    if (request.ok()) {
			response = request.body();
		    } else {
			response = "error : " + request.code() + ", message : "
				+ request.body();
		    }
		    request.disconnect();
		}
	
		return response;
    }
    
    public static String encodeURIComponent(String s)
    {
      String result = null;
   
      try
      {
        result = URLEncoder.encode(s, "UTF-8")
                           .replaceAll("\\+", "%20")
                           .replaceAll("\\%21", "!")
                           .replaceAll("\\%27", "'")
                           .replaceAll("\\%28", "(")
                           .replaceAll("\\%29", ")")
                           .replaceAll("\\%26", "&")
                           .replaceAll("\\%3D", "=")
                           .replaceAll("\\%7E", "~");
      }
   
      // This exception should never occur.
      catch (UnsupportedEncodingException e)
      {
        result = s;
      }
   
      return result;
    }

    private HashMap<String, String> getHttpHeaders(String endpoint, HashMap<String, String> rgData, String apiKey, String apiSecret) {
	    	
		String strData = Util.mapToQueryString(rgData).replace("?", "");
		String nNonce = usecTime();
		HashMap<String, String> array = new HashMap<String, String>();
		String str = endpoint + ";" + nNonce;
		
		if(!strData.isEmpty()) {
			strData = strData.substring(0, strData.length()-1);
			strData = encodeURIComponent(strData);
			str += ";"	+ strData;
		}
		
		//String str = "/info/balance;order_currency=BTC&payment_currency=KRW&endpoint=%2Finfo%2Fbalance;272184496";
		
		// public일때는 제외
		System.out.println(str);
		String encoded = "";
		if(str.contains("/bithumb/pri")){
			encoded = asHex(hmacSha512(str, apiSecret));
		}
		
		System.out.println("strData was: " + str);
		System.out.println("apiSecret was: " + apiSecret);
		array.put("Api-Key", apiKey);
		array.put("Api-Sign", encoded);
		array.put("Api-Nonce", String.valueOf(nNonce));
	
		return array;
		
    }
    
    private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String HMAC_SHA512 = "HmacSHA512";
	 
	public static byte[] hmacSha512(String value, String key){
	    try {
	        SecretKeySpec keySpec = new SecretKeySpec(
	                key.getBytes(DEFAULT_ENCODING),
	                HMAC_SHA512);
	 
	        Mac mac = Mac.getInstance(HMAC_SHA512);
	        mac.init(keySpec);
	
	        final byte[] macData = mac.doFinal( value.getBytes( ) );
	        byte[] hex = new Hex().encode( macData );
	        
	        //return mac.doFinal(value.getBytes(DEFAULT_ENCODING));
	        return hex;
	 
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    } catch (InvalidKeyException e) {
	        throw new RuntimeException(e);
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}
	 
	public static String asHex(byte[] bytes){
	    return new String(Base64.encodeBase64(bytes));
	}

    /**
     * <pre>
     * 1. 개요 : 빗썸 API 전용
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : callApi
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param endpoint
     * @param params
     * @return
     */ 	
    @SuppressWarnings("unchecked")
    public String callApi(String endpoint, HashMap<String, String> params) {
		String rgResultDecode = "";
		HashMap<String, String> rgParams = new HashMap<String, String>();
		rgParams.put("endpoint", endpoint);
	
		if (params != null) {
		    rgParams.putAll(params);
		}
	
		String api_host = api_url + endpoint;
		HashMap<String, String> httpHeaders = getHttpHeaders(endpoint, rgParams, api_key, api_secret);
	
		rgResultDecode = request(api_host, "POST", rgParams, httpHeaders);
	
		if (!rgResultDecode.startsWith("error")) {
		    // json 파싱
		    HashMap<String, String> result;
		    try {
			result = new ObjectMapper().readValue(rgResultDecode,
				HashMap.class);
	
			System.out.println("==== 결과 출력 ====");
			System.out.println(result.get("status"));
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
		return rgResultDecode;
    }
    
   
    /**
     * <pre>
     * 1. 개요 : 기본 API 적용
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : callCommonApi
     * @date : 2018. 4. 16.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 16.		Hyundai				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param endpoint
     * @param params
     * @return
     */ 	
    @SuppressWarnings("unchecked")
    public String callCommonApi(String endpoint, HashMap<String, String> params) {
		String rgResultDecode = "";
		HashMap<String, String> rgParams = new HashMap<String, String>();
		
		String api_host = api_url + endpoint;
		HashMap<String, String> httpHeaders = new HashMap<String, String>();
		String nNonce = usecTime();
		httpHeaders.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		httpHeaders.put("Api-Nonce", String.valueOf(nNonce));
	
		rgResultDecode = request(api_host, "POST", rgParams, httpHeaders);
		return rgResultDecode;
    }
    
    /**
     * <pre>
     * 1. 개요 : 바이낸스 API 전용
     * 2. 처리내용 : GET 방식으로 변경
     * </pre>
     * @Method Name : callBinanceApi
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		jongkyu.won				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param endpoint
     * @param params
     * @return
     */ 	
    @SuppressWarnings("unchecked")
    public String callBinanceApi(String endpoint, HashMap<String, String> params) {
		String rgResultDecode = "";
		HashMap<String, String> rgParams = new HashMap<String, String>();
 
		String api_host = api_url + endpoint;
		// HashMap<String, String> httpHeaders = getHttpHeaders(endpoint, rgParams, api_key, api_secret);
		HashMap<String, String> httpHeaders = new HashMap<String, String>();
		String nNonce = usecTime();
		httpHeaders.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		httpHeaders.put("Accept", "application/json, text/javascript, */*; q=0.01");
		httpHeaders.put("Api-Nonce", String.valueOf(nNonce));
	
		rgResultDecode = request(api_host, "GET", rgParams, httpHeaders);
	
		// 예외처리 필요함 { "code": -1121, "msg":"Invalid symbol." }
		/*if (rgResultDecode.startsWith("error")) {
		    // json 파싱
		    HashMap<String, String> result;
		    try {
				result = new ObjectMapper().readValue(rgResultDecode, HashMap.class);
		
				System.out.println("==== 결과 출력 ====");
				System.out.println(result.get("msg"));
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}*/
		return rgResultDecode;
    }
    
    /**
     * <pre>
     * 1. 개요 : 업비트 API 전용
     * 2. 처리내용 : GET 방식으로 변경
     * </pre>
     * @Method Name : callBinanceApi
     * @date : 2018. 4. 13.
     * @author : Hyundai
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2018. 4. 13.		jongkyu.won				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param endpoint
     * @param params
     * @return
     */ 	
    @SuppressWarnings("unchecked")
    public String callUpbitApi(String endpoint, HashMap<String, String> params) {
		String rgResultDecode = "";
		HashMap<String, String> rgParams = new HashMap<String, String>();
 
		String api_host = api_url + endpoint;
		// HashMap<String, String> httpHeaders = getHttpHeaders(endpoint, rgParams, api_key, api_secret);
		HashMap<String, String> httpHeaders = new HashMap<String, String>();
		String nNonce = usecTime();
		httpHeaders.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		httpHeaders.put("Api-Nonce", String.valueOf(nNonce));
		
		rgResultDecode = request(api_host, "GET", rgParams, httpHeaders);
	
		// 예외처리 필요함 { "code": -1121, "msg":"Invalid symbol." }
		/*if (!rgResultDecode.startsWith("code")) {
		    // json 파싱
		    HashMap<String, String> result;
		    try {
				result = new ObjectMapper().readValue(rgResultDecode, HashMap.class);
		
				System.out.println("==== 결과 출력 ====");
				System.out.println(result.get("msg"));
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}*/
		return rgResultDecode;
    }
    
   /* // Connect to the echo server.
    WebSocket ws;
    String coinListStr = "";
    for(String str : coinList) {
    	coinListStr = "\"KRW-"+ str + "\"," + coinListStr;
    }
    if(coinListStr.length() > 0){
    	coinListStr = coinListStr.substring(0,coinListStr.length()-1);
    }
    
    try {
		ws = connect();
		String text = "[{\"ticket\":\"UNIQUE_TICKET\"},{\"type\":\"orderbook\",\"codes\":["+ coinListStr + "],\"isOnlySnapshot\":\"false\"}]";
		System.out.println("text:" + text);
        ws.sendText(text);
        result = websocketResult;
        websocketResult = "";
        System.out.println("result:"  + result);
        ws.disconnect();
	} catch (IOException | WebSocketException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
    
	private WebSocket connect() throws IOException, WebSocketException {
		System.out.println("Start get data from upbit");
		return new WebSocketFactory().setConnectionTimeout(TIMEOUT).createSocket(SERVER)
				.addListener(new WebSocketAdapter() {

					// binary message arrived from the server
					public void onBinaryMessage(WebSocket websocket, byte[] binary) {
						String str = new String(binary);
						System.out.println(str);
						websocketResult = str + websocketResult +","; // 배열로 변환
					}

					// A text message arrived from the server.
					public void onTextMessage(WebSocket websocket, String message) {
						websocketResult = websocketResult+ "[" + message + "]"; // 배열로 변환
					}

				}).addExtension(WebSocketExtension.PERMESSAGE_DEFLATE).connect();
	}*/

    
}
