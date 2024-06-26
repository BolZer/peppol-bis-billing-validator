package io.github.bolzer.Controllers;

import io.github.bolzer.Contracts.IValidationService;
import io.github.bolzer.Dtos.ValidationResult;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/")
public final class IndexController {

    private final IValidationService validationService;

    public IndexController(IValidationService validationService) {
        this.validationService = validationService;
    }

    @POST
    @Path("/validation")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @APIResponses(
        {
            @APIResponse(
                responseCode = "200",
                description = "The submitted XML is valid "
            ),
            @APIResponse(
                responseCode = "400",
                description = "Schematron validation for the submitted XML failed. Response will contain the failed assertions"
            ),
        }
    )
    public RestResponse<@NonNull String> validation(String xml)
        throws Exception {
        ValidationResult result = this.validationService.validateXml(xml);

        if (result.isValid()) {
            return RestResponse.ResponseBuilder
                .ok(result.getXmlReport(), MediaType.APPLICATION_XML)
                .build();
        }

        return RestResponse.ResponseBuilder
            .create(RestResponse.Status.BAD_REQUEST, result.getXmlReport())
            .type(MediaType.APPLICATION_XML)
            .build();
    }
}
