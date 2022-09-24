package com.azure.client.integration.utils;


import azure.client.integration.utils.AzureClient;
import azure.client.integration.utils.FileChecker;
import azure.client.integration.utils.PropertyGetter;
import com.azure.storage.blob.BlobContainerClient;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AzureClientTest {

    @Test
    void generateContainer() {
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Assertions.assertNotNull(containerClient);
        Assertions.assertEquals(PropertyGetter.getAzureFolder(),containerClient.getBlobContainerName());
    }

    @Test
    void downloadFile() throws IOException {
        String fileName = "descargame.txt" ;
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Boolean downloaded = AzureClient.downloadFile(fileName, fileName, PropertyGetter.getLocalFolder(), containerClient);
        Assertions.assertTrue(downloaded);
        Assertions.assertTrue(FileChecker.checkFileExistence(PropertyGetter.getLocalFolder(),fileName));
    }

    @Test
    void downloadFile_NotFound() throws IOException {
        String fileName = "noExisto.txt" ;
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Boolean downloaded = AzureClient.downloadFile(fileName, fileName, PropertyGetter.getLocalFolder(), containerClient);
        Assertions.assertFalse(downloaded);
    }

    @Test
    void uploadFile() {

        String fileName = "pruebaSubida.txt" ;
        String localPath = "src/test/java/azure/client/integration/resources/";
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Boolean uploaded = AzureClient.uploadFile(localPath,fileName,containerClient);
        Assertions.assertTrue(uploaded);
    }

    @Test
    void uploadFile_NoDirectory() throws IOException {

        String fileName = "pruebaSubida.txt" ;
        String localPath = "src/test/java/azure/client/integration/resourcesssss/";
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Boolean uploaded = AzureClient.uploadFile(localPath,fileName,containerClient);
        Assertions.assertFalse(uploaded);

        boolean deleted = FileChecker.deleteDirectory(localPath);
        Assertions.assertTrue(deleted);
    }

    @Test
    void uploadFile_NoFile() {

        String fileName = "QWERTY.txt" ;
        String localPath = "src/test/java/azure/client/integration/resources/";
        BlobContainerClient containerClient = AzureClient.generateContainer(PropertyGetter.getEndpoint(), PropertyGetter.getAzureFolder());
        Boolean aBoolean = AzureClient.uploadFile(localPath,fileName,containerClient);
        Assertions.assertFalse(aBoolean);
    }
}