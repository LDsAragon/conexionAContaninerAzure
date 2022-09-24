package com.azure.client.integration.exceptions;

import azure.client.integration.exceptions.CustomAzureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomAzureExceptionTest {

    String actionType = "ProbandoExcepción";
    String statusCode = "666";
    String message = "Este es un mensaje de error para probar la excepción";

    CustomAzureException customAzureException = new CustomAzureException(actionType, statusCode, message);

    @Test
    void getActionType() {
        String actionType = customAzureException.getActionType();
        Assertions.assertNotNull(actionType);
    }

    @Test
    void getStatusCode() {
        String statusCode = customAzureException.getStatusCode();
        Assertions.assertNotNull(statusCode);
    }

    @Test
    void getMessage() {
        String message = customAzureException.getMessage();
        Assertions.assertNotNull(message);
    }
}