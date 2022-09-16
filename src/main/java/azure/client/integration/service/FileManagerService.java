package azure.client.integration.service;

import azure.client.integration.constants.ConfigVars;
import azure.client.integration.utils.AzureClient;
import com.azure.storage.blob.BlobContainerClient;

/**
 * Class created to be a proxy for the execution of the Azure client and encapsulate its possible errors with multiple try catch.
 */
public class FileManagerService extends ErrorManagerImpl<Boolean>  {

    public Boolean downloadFile(String fileNameInContainer, String  fileNameToDownload, BlobContainerClient containerClient  ){
       return execute(() -> AzureClient.downloadFile(fileNameInContainer, fileNameToDownload, ConfigVars.LOCAL_PATH, containerClient));
    }


    public Boolean uploadFile(String fileNameToUpload, BlobContainerClient containerClient){
        return execute(() -> AzureClient.uploadFile(ConfigVars.LOCAL_PATH, fileNameToUpload, containerClient));
    }

}
