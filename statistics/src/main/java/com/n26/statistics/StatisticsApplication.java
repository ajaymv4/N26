package com.n26.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.n26.statistics.constants.AppConstants;

@SpringBootApplication(scanBasePackages={AppConstants.PACKAGE_SCAN})
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}
}
