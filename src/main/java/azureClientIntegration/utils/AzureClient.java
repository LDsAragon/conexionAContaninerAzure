package azureClientIntegration.utils;

import azureClientIntegration.constants.ConfigVars;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AzureClient {

    /**
     * Generates a container with a SAS key and a folder name.
     *
     * @param endpointProp The SAS key from the azure container.
     * @param container  The folder in azure to use.
     * @return The container :D.
     */
    public static BlobContainerClient generateContainer(String endpointProp, String container) {
        BlobServiceClient blobServiceClientByEndpoint = new BlobServiceClientBuilder().endpoint(endpointProp).buildClient();
        BlobContainerClient containerClient = blobServiceClientByEndpoint.getBlobContainerClient(container);
        return containerClient;
    }

    /**
     * Downloads
     *
     * @param fileNameInContainer The file name of the file to be downloaded from the container.
     * @param fileNameToDownload The file name to name the downloaded file.
     * @param localPath The path to download the file to.
     * @param containerClient The azure client connected to the provided container and a folder.
     */
    public static Boolean downloadFile(String fileNameInContainer, String fileNameToDownload, String localPath, BlobContainerClient containerClient) {
        System.out.println("VAMOS A DESCARGAR: " + fileNameToDownload + " que esta en sabrmdev -> container " + ConfigVars.CONTAINER + "\n");
        BlobClient blobClient = containerClient.getBlobClient(fileNameInContainer);
        boolean wasDownloaded = Boolean.FALSE ;

        boolean localPathWasCreated = FileChecker.createDirectory(localPath);
        boolean localPathExists = FileChecker.checkDirectoryExistence(localPath);

        if (localPathExists && blobClient.exists()) {
            System.out.println("\nDownloading blob to: " + localPath + fileNameToDownload);
            blobClient.downloadToFile(localPath + fileNameToDownload, ConfigVars.OVERWRITE_FILES);
            wasDownloaded=true;
        } else {
            System.out.println("\nEl archivo a descargar desde el contenedor [" + fileNameToDownload + "] no existe");
        }

        return wasDownloaded ;
    }

    /**
     * Uploads the file to the configured container.
     * Checks if the file exists first.
     *
     * @param localPath The path to recover the file to download.
     * @param fileNameToUpload The name of the file to upload.
     * @param containerClient The azure client connected to the provided container and a folder.
     */
    public static Boolean uploadFile(String localPath, String fileNameToUpload, BlobContainerClient containerClient) {
        System.out.println("\nVAMOS A CARGAR: " + fileNameToUpload + " que esta en folderPath a sabrmdev -> container " + ConfigVars.CONTAINER + "\n");
        boolean localPathWasCreated = FileChecker.createDirectory(localPath);
        boolean checkFileExistence = FileChecker.checkFileExistence(localPath, fileNameToUpload);
        boolean wasUploaded = Boolean.FALSE ;

        BlobClient blobClient2 = containerClient.getBlobClient(fileNameToUpload);
        if (checkFileExistence) {
            System.out.println("\nUploading blob to: sabrmdev -> container " + ConfigVars.CONTAINER);
            blobClient2.uploadFromFile(localPath + fileNameToUpload, ConfigVars.OVERWRITE_FILES);
            wasUploaded = true ;
        } else {
            System.out.println("\nEl archivo que queres subir desde tu local [" + fileNameToUpload + "] no existe");
        }
        return wasUploaded ;
    }


}
