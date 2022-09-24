package com.azure.client.integration.service;


import azure.client.integration.service.ContainerService;
import azure.client.integration.service.FileManagerService;
import azure.client.integration.utils.FileChecker;
import azure.client.integration.utils.PropertyGetter;
import com.azure.storage.blob.BlobContainerClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileManagerServiceTest {

    @InjectMocks
    ContainerService containerService;

    @InjectMocks
    FileManagerService fileManagerService;

    @Test
    void downloadFile() {

        BlobContainerClient container = containerService.createContainer();

        String fileName = "descargame.txt" ;
        String localFolder = PropertyGetter.getLocalFolder();
        Boolean downloaded = fileManagerService.downloadFile(fileName, fileName,localFolder, container);
        Assertions.assertTrue(downloaded);
        Assertions.assertTrue(FileChecker.checkFileExistence(localFolder,fileName));

    }

    @Test
    void uploadFile() {

        BlobContainerClient container = containerService.createContainer();

        String fileName = "pruebaSubida.txt" ;
        String localFolder = "src/test/java/azure/client/integration/resources/";

        Boolean downloaded = fileManagerService.uploadFile(fileName,localFolder,container);
        Assertions.assertTrue(downloaded);
        Assertions.assertTrue(FileChecker.checkFileExistence(localFolder,fileName));


    }
}