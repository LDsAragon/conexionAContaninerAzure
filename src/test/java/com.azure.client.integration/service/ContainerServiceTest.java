package com.azure.client.integration.service;

import azure.client.integration.service.ContainerService;
import azure.client.integration.utils.PropertyGetter;
import com.azure.storage.blob.BlobContainerClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContainerServiceTest {

    @InjectMocks
    ContainerService containerService;
    
    @Test
    void createContainer() {

        BlobContainerClient container = containerService.createContainer();
        Assertions.assertNotNull(container);
        Assertions.assertEquals(PropertyGetter.getAzureFolder(),container.getBlobContainerName());
    }
}