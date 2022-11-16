package com.example.cursocamelv2.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileWatchRoute extends RouteBuilder {
    private String path = "C:\\Users\\lucas\\Desktop\\API\\Arquivo\\input\\";

    //para nao pegar o que tem dentro de pastas criadas dentro de input = ?recursive=false
    //pegar apenas um tipo de evento = ?events=MODIFY/CREATE/DELETE
    @Override
    public void configure() throws Exception {
        from("file-watch:" + path + "?events=MODIFY")
                .log("Evento: ${header.CamelFileEventType} Arquivo: ${header.CamelFileName}");
    }
}
