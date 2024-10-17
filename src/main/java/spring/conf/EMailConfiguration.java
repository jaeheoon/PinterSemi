package spring.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:spring/email.properties")
@Getter
@Setter
public class EMailConfiguration {

    private @Value("${spring.mail.host}") String host;
    private @Value("${spring.mail.port}") String port;
    private @Value("${spring.mail.username}") String username;
    private @Value("${spring.mail.password}") String password;
    private @Value("${spring.mail.properties.mail.smtp.auth}") String auth;
    private @Value("${spring.mail.properties.mail.smtp.timeout}") String timeout;
    private @Value("${spring.mail.properties.mail.smtp.starttls.enable}") String enable;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", enable);
        props.put("mail.debug", "true");

        return mailSender;
    }
}
