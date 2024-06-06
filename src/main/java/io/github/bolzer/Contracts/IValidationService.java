package io.github.bolzer.Contracts;

public interface IValidationService {
    String validateXml(String xml) throws Exception;

    boolean isLoadedSchematronValid();
}
