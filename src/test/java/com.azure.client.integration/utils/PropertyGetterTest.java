package com.azure.client.integration.utils;

import azure.client.integration.utils.PropertyGetter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PropertyGetterTest {

    @Test
    void getPropertyEndpoint() {

        String propertyEndpoint = PropertyGetter.getEndpoint();
        Assertions.assertNotNull(propertyEndpoint);

    }

    @Test
    void getPropertyFolder() {
        String propertyFolder = PropertyGetter.getAzureFolder();
        Assertions.assertNotNull(propertyFolder);
    }

    @Test
    void getPropertyLocalPath() {
        String propertyLocalPath = PropertyGetter.getLocalFolder();
        Assertions.assertNotNull(propertyLocalPath);
    }
}