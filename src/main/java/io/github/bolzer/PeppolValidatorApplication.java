package io.github.bolzer;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "Peppol Validator API",
        version = "0.1.1",
        contact = @Contact(
            name = "easybill GmbH",
            url = "https://www.easybill.de",
            email = "dev@easybill.de"
        ),
        license = @License(
            name = "MIT",
            url = "https://mit-license.org"
        )
    )
)
public final class PeppolValidatorApplication extends Application {}
