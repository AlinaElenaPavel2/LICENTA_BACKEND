package com.licenta.aplicatie;

//import com.licenta.aplicatie.Controller.Security.JwtTokenFilter;
import com.licenta.aplicatie.POJO.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class AplicatieApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
