package com.example.logsasync;

import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootApplication
public class LogsasyncApplication {

	public static void main(String[] args) {

		SpringApplication.run(LogsasyncApplication.class, args);

		long start = System.currentTimeMillis();

		for (int i = 0; i < 7000000; i++) {
            log.info("Mensaje de log asíncrono número: {} {}", i, time());
		}

		long end = System.currentTimeMillis();
		log.info("Tiempo total: {} ms", end - start);
		//ms to seconds
		log.info("Tiempo total: {} s", (end - start) / 1000);

		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		context.stop();


	}

	private static String time(){
		long currentMillis = System.currentTimeMillis();
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentMillis), ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

		return dateTime.format(formatter);
	}

}
