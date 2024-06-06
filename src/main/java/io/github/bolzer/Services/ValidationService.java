package io.github.bolzer.Services;

import com.helger.commons.io.ByteArrayWrapper;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.schematron.sch.SchematronResourceSCH;
import com.helger.schematron.svrl.SVRLMarshaller;
import io.github.bolzer.Contracts.IValidationService;
import jakarta.inject.Singleton;
import java.nio.charset.StandardCharsets;

@Singleton
public final class ValidationService implements IValidationService {

    private final SchematronResourceSCH schematron;

    ValidationService() {
        schematron =
            new SchematronResourceSCH(
                new ClassPathResource("PEPPOL-BIS-Billing-3.0.sch")
            );

        if (!schematron.isValidSchematron()) {
            throw new RuntimeException("Schematron validation failed");
        }
    }

    @Override
    public String validateXml(String xml) throws Exception {
        var outputSVRL = schematron.applySchematronValidationToSVRL(
            new ByteArrayWrapper(xml.getBytes(StandardCharsets.UTF_8), false)
        );

        if (outputSVRL == null) {
            throw new Exception("validation failed. the SVRL output is null");
        }

        String output = new SVRLMarshaller().getAsString(outputSVRL);

        if (output == null) {
            throw new Exception("validation output is null");
        }

        return output;
    }

    @Override
    public boolean isLoadedSchematronValid() {
        return schematron.isValidSchematron();
    }
}
