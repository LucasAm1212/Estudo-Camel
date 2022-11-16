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

    @Override
    public void configure() throws Exception {
        //pegar arquivos da pasta

        //deletar pasta .camel = input?delete=true
        //mover, criar pasta e cria copia do arquivo = input?move=${date:now:yyyyMMdd}/${file:name}
        //para que arquivos repetidos nao sejam processados = input?noop=true
        //criar mesma hierarquia de pastas do input para o output = input?recursive=true
        //para ignorar extensao no processamento = input?excludeExt=png,txt
        //delay de 10 segundos para comecar varredura = input?timeUnit=SECONDS&initialDelay=10
        //delay entre varredura e limitar quantidade de varreduras = input?delay=10000&repeatCount=3
        //limitar tamanho do arquivo aceito = input?filterFile=${file:size} < 423
        from("file://" + path + "input?recursive=true&delete=true")
                //Imprimir path do arquivo
                //.log("Arquivo: ${header.CamelFileName} - Path: ${header.CamelFilePath}")
                //Imprimir nome do arquivo
                .log("${file:name}")
                //Imprimir Processor
                //.process(new FileProcessor())
                //Imprimir FileComponent
                //.bean("fileComponent")
                //move arquivos para a pasta output
                //flatten ignora todas subpastas do input e agrupa tudo em uma no output
                .to("file://" + path + "output?flatten=true");
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



