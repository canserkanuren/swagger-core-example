package com.canserkan.swaggercoreexample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.jaxrs2.integration.resources.BaseOpenApiResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/openapi.{type:json|yaml}")
public class SpecificOpenApiResource extends BaseOpenApiResource {
    @Context
    ServletConfig config;
    @Context
    Application app;

    @GET
    @Produces({"application/json", "application/yaml"})
    @Operation(
            hidden = true
    )
    public Response getOpenApi(@Context HttpHeaders headers, @Context UriInfo uriInfo, @PathParam("type") String type) throws Exception {
        Response response = super.getOpenApi(headers, this.config, this.app, uriInfo, type);

        ObjectMapper mapper = new ObjectMapper();
        // --> this next line fails !!! Even though we agree that in the super.getOpenApi, we use OpenAPI class...
        OpenAPI oas = mapper.readValue(response.getEntity().toString(), OpenAPI.class);
        return response;
    }
}
