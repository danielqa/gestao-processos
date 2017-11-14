package br.com.softplan.sajadv.processos.app;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("br.com.softplan.sajadv.processos", "com.fasterxml.jackson.jaxrs.json");
    }
}