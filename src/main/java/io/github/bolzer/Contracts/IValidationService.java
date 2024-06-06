package io.github.bolzer.Contracts;

import io.github.bolzer.Dtos.ValidationResult;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface IValidationService {
    @NonNull
    ValidationResult validateXml(@NonNull String xml) throws Exception;

    boolean isLoadedSchematronValid();
}
