package com.eliseu.aprendendoCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AprendendoCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendendoCacheApplication.class, args);
	}

}
