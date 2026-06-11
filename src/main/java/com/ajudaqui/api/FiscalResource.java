package com.ajudaqui.api;

import com.ajudaqui.core.model.FiscalDocument;
import com.ajudaqui.core.service.FiscalParseService;
import com.ajudaqui.inputs.UrlInput;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/fiscal")
public class FiscalResource {

    @Inject
    FiscalParseService service;

    @POST
    @Path("/parse-url")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FiscalDocument parseUrl(ParseRequest request) {
    var response= service.parse(new UrlInput(request.url()));
    System.out.println(response);
        return response;
    }

    public record ParseRequest(String url) {}
}
