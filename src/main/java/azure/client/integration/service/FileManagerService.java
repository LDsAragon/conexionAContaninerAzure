package azure.client.integration.service;


import azure.client.integration.utils.AzureClient;
import com.azure.storage.blob.BlobContainerClient;
//import org.springframework.stereotype.Service;

/**
 * Class created to be a proxy for the execution of the Azure client,
 * and encapsulate its possible errors with multiple try catch.
 */
//@Service
public class FileManagerService extends ErrorManager<Boolean> {

  public Boolean downloadFile(String fileNameInContainer, String fileNameToDownload,
                              String localFolder, BlobContainerClient containerClient) {
    return execute(
        () -> AzureClient.downloadFile(fileNameInContainer, fileNameToDownload, localFolder,
            containerClient));
  }


  public Boolean uploadFile(String fileNameToUpload, String localFolder,
                            BlobContainerClient containerClient) {
    return execute(() -> AzureClient.uploadFile(localFolder, fileNameToUpload, containerClient));
  }

}
