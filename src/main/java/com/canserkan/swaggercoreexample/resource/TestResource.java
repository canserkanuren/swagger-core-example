package com.canserkan.swaggercoreexample.resource;

import com.canserkan.swaggercoreexample.dto.TestDto;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.jaxrs2.Reader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.OpenAPI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    @Operation(
            method = "GET",
            description = "Get all applications by trigram",
            tags = {"applications"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All tests have been fetched",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TestDto.class)))
                    ),
                    @ApiResponse(responseCode = "204", description = "No test has been found with trigram")
            }
    )
    public Response test() {
        List<TestDto> tests = Collections.singletonList(TestDto.builder().test("This is a test!!").build());
        return Response.ok(tests).build();
    }

    @GET
    @Path("read")
    public Response read() {
        Reader reader = new Reader(new OpenAPI());

        OpenAPI openAPI = reader.read(TestResource.class);
        Json.prettyPrint(openAPI);
        Yaml.prettyPrint(openAPI);
        return Response.ok().build();
    }
}
