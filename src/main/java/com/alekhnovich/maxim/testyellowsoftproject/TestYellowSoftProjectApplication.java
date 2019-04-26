package com.alekhnovich.maxim.testyellowsoftproject;

import com.alekhnovich.maxim.testyellowsoftproject.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageConfig.class)
public class TestYellowSoftProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestYellowSoftProjectApplication.class, args);
	}

}
