package com.example.cursocamelv2.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileRoute extends RouteBuilder {
    //caminho pasta
    private String path = "C:\\Users\\lucas\\Desktop\\API\\Arquivo\\";

    //aceitar apenas um tipo de arquivo = input?includeExt=txt,png
    @Override
    public void configure() throws Exception {
        from("file://" + path + "input")
                //condicao
                .choice()
                    .when(simple("${header.CamelFileLenght} < 422"))
                        .to("bean:fileComponent")
                    .otherwise()
                        .process(new FileProcessor());
    }
}
@Component
//Imprimir FileComponent
class fileComponent {
    public void log(File file) {
        System.out.println("FileComponent: " + file.getName());
    }
}

//Imprimir Processor
class FileProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processor: "+exchange.getIn().getBody());
    }
}



