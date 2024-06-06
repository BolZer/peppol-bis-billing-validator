package io.github.bolzer.Controllers;

import io.github.bolzer.Contracts.IValidationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    public String validation(String xml) throws Exception {
        return this.validationService.validateXml(xml);
    }
}
