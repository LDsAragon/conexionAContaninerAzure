package azure.client.integration.utils;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import azure.client.integration.constants.ConfigVars;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class AzureClient {

  /**
   * Generates a container with a SAS key and a folder name.
   *
   * @param endpointProp The SAS key from the azure container.
   * @param container    The folder in azure to use.
   * @return The container :D.
   */
  public BlobContainerClient generateContainer(String endpointProp, String container) {
    log.info("Generando contenedor para la carpeta : [" + container + "] que esta en la url [" +
        endpointProp + "]");
    BlobServiceClient blobServiceClientByEndpoint =
        new BlobServiceClientBuilder().endpoint(endpointProp).buildClient();
    return blobServiceClientByEndpoint.getBlobContainerClient(container);
  }

  /**
   * Downloads
   *
   * @param fileNameInContainer The file name of the file to be downloaded from the container.
   * @param fileNameToDownload  The file name to name the downloaded file.
   * @param localPath           The path to download the file to.
   * @param containerClient     The azure client connected to the provided container and a folder.
   */
  public Boolean downloadFile(String fileNameInContainer, String fileNameToDownload,
                              String localPath, BlobContainerClient containerClient) {
    log.info("VAMOS A DESCARGAR: " + fileNameToDownload + " que esta en sabrmdev -> container " +
        containerClient.getBlobContainerName());
    BlobClient blobClient = containerClient.getBlobClient(fileNameInContainer);
    boolean wasDownloaded = Boolean.FALSE;

    FileChecker.createDirectory(localPath);
    boolean localPathExists = FileChecker.checkDirectoryExistence(localPath);

    if (localPathExists && blobClient.exists().equals(Boolean.TRUE)) {
      log.info("Downloading blob to: " + localPath + fileNameToDownload);
      blobClient.downloadToFile(localPath + fileNameToDownload, ConfigVars.OVERWRITE_FILES);
      wasDownloaded = true;
    } else {
      log.info("El archivo a descargar desde el contenedor [" + fileNameToDownload + "] no existe");
    }

    return wasDownloaded;
  }

  /**
   * Uploads the file to the configured container.
   * Checks if the file exists first.
   *
   * @param localPath        The path to recover the file to download.
   * @param fileNameToUpload The name of the file to upload.
   * @param containerClient  The azure client connected to the provided container and a folder.
   */
  public Boolean uploadFile(String localPath, String fileNameToUpload,
                            BlobContainerClient containerClient) {
    log.info("VAMOS A CARGAR: " + fileNameToUpload + " que esta en [" + localPath + "]");

    FileChecker.createDirectory(localPath);
    boolean checkFileExistence = FileChecker.checkFileExistence(localPath, fileNameToUpload);
    boolean wasUploaded = Boolean.FALSE;

    BlobClient blobClient2 = containerClient.getBlobClient(fileNameToUpload);
    if (checkFileExistence) {
      log.info("Uploading blob to: sabrmdev -> container " + ConfigVars.CONTAINER);
      blobClient2.uploadFromFile(localPath + fileNameToUpload, ConfigVars.OVERWRITE_FILES);
      wasUploaded = true;
    } else {
      log.info("El archivo que queres subir desde tu local [" + fileNameToUpload + "] no existe");
    }
    return wasUploaded;
  }


}
