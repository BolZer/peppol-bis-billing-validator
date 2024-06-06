FROM openjdk:21

WORKDIR .

COPY ./build/peppol-bis-billing-validator-0.1.0-runner.jar .

ENTRYPOINT ["java", "-Xmx512m", "-jar", "peppol-bis-billing-validator-0.1.0-runner.jar"]