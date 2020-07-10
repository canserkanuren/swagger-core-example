package com.canserkan.swaggercoreexample.config;

import com.canserkan.swaggercoreexample.resource.TestResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

@ApplicationPath("/api")
@Path("/")
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TestResource.class);
        //register(OpenApiResource.class);
        register(SpecificOpenApiResource.class);
    }
}
