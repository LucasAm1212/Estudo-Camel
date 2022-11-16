package com.example.cursocamelv2;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CursoCamelv2Application {

	public static void main(String[] args) {
		SpringApplication.run(CursoCamelv2Application.class, args);
	}

	@Component
	class TimerRoute extends RouteBuilder {

		//programar para o dia e hora que executara = time=yyyy-MM-dd HH:mm:ss or yyyy-MM-dd'T'HH:mm:ss
		@Override
		public void configure() throws Exception {
			from("timer:my-timer?period=5000&repeatCount=2")
					.log("Hora: ${date:now:HH:mm:ss}");
		}
	}

}
