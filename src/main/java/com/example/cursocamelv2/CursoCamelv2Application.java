package com.example.cursocamelv2;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CursoCamelv2Application {

    public static void main(String[] args) {

        SpringApplication.run(CursoCamelv2Application.class, args);

    }

    class DirectRoute extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:log-file")
                    .log("Log: ${header.CamelFileName}")
                    .to("direct:log-file2");

            from("direct:log-file2")
                    .process(new FileProcessor());

            from("file://C:\\Users\\lucas\\Desktop\\API\\Arquivo\\input")
                    .to("direct:log-file");

            from("file://C:\\Users\\lucas\\Desktop\\API\\Arquivo\\input2")
                    .to("direct:log-file");
        }
    }

    class FileProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            File file = exchange.getIn().getBody(File.class);
            System.out.println("Processor: " + file.getName());
        }
    }

}
