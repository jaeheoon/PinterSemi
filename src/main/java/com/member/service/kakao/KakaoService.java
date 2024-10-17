package com.member.service.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import spring.conf.KakaoConfiguration;

@Service
public class KakaoService {
	@Autowired
	KakaoConfiguration kakaoConfiguration;
	
	public String getToken(String code) throws IOException {
	    String host = "https://kauth.kakao.com/oauth/token";
	    String clientId = kakaoConfiguration.getClientId();
	    String redirectUri = kakaoConfiguration.getRedirectUri();
	    String clientSecret = kakaoConfiguration.getClientSecret();
	    
	    URL url = new URL(host);
	    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	    String token = "";
	    try {
	        urlConnection.setRequestMethod("POST");
	        urlConnection.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
	        StringBuilder sb = new StringBuilder();
	        sb.append("grant_type=authorization_code");
	        sb.append("&client_id=").append(clientId);
	        sb.append("&redirect_uri=").append(redirectUri);
	        sb.append("&code=").append(code);
	        sb.append("&client_secret=").append(clientSecret);
	        bw.write(sb.toString());
	        bw.flush();
            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    System.out.println("result = " + result);
                    // JSON 파싱
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(result.toString());
                    token = jsonNode.get("access_token").asText();
                    System.out.println("access_token = " + token);
                }
            } else {
                System.out.println("Error response: " + urlConnection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect(); // 연결 종료
            }
        }
        return token;
    }
	public Map<String, Object> getUserInfo(String access_token) throws IOException {
	    String host = "https://kapi.kakao.com/v2/user/me";
	    Map<String, Object> result = new HashMap<>();
	    
	    HttpURLConnection urlConnection = null; // HttpURLConnection 객체를 따로 선언
	    
	    try {
	        urlConnection = (HttpURLConnection) new URL(host).openConnection();
	        urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
	        urlConnection.setRequestMethod("GET");
	        int responseCode = urlConnection.getResponseCode();
	        System.out.println("responseCode = " + responseCode);
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
	                StringBuilder res = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    res.append(line);
	                }
	                System.out.println("res = " + res);
	                // JSON 파싱
	                ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode obj = objectMapper.readTree(res.toString());
	                JsonNode properties = obj.get("properties");
	                JsonNode kakaoAccount = obj.get("kakao_account");
	                String id = obj.get("id").asText();
	                String nickname = properties.get("nickname").asText();
	                String email = kakaoAccount.has("email") ? kakaoAccount.get("email").asText() : null;
	                String profileImage = properties.get("profile_image").asText();
	                result.put("id", id);
	                result.put("nickname", nickname);
	                result.put("email", email);
	                result.put("profile_image", profileImage);
	            }
	        } else {
	            System.out.println("Error response: " + urlConnection.getResponseMessage());
	        }
	    } finally {
	        if (urlConnection != null) {
	            urlConnection.disconnect(); // 연결 종료
	        }
	    }
	    return result;
	}
    public String getAgreementInfo(String access_token) {
        StringBuilder result = new StringBuilder();
        String host = "https://kapi.kakao.com/v2/user/scopes";
        
        HttpURLConnection urlConnection = null; // HttpURLConnection 객체를 따로 선언
        
        try {
            urlConnection = (HttpURLConnection) new URL(host).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                }
            } else {
                System.out.println("Error response: " + urlConnection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect(); // 연결 종료
            }
        }
        return result.toString(); // JSON 문자열로 반환
    }
}