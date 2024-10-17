package spring.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:spring/kakao.properties")
@Getter
@Setter
public class KakaoConfiguration {
	private @Value("${kakao.client_id}") String clientId;
	private @Value("${kakao.redirect_uri}") String redirectUri;
	private @Value("${kakao.client_secret}") String clientSecret;
}
