package com.licenta.aplicatie;

import com.licenta.aplicatie.POJO.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class AplicatieApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieApplication.class, args);
	}

}
