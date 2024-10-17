package spring.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:spring/email.properties")
@Getter
@Setter
public class EMailConfiguration {

}
