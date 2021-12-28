package br.com.zup.desafio.comics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ComicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicsApplication.class, args);
	}

}
