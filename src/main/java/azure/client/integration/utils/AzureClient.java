package azure.client.integration.utils;

import azure.client.integration.constants.ConfigVars;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Client to be able to perform operations with the azure container,
 * such as uploading and downloading a file.
 */
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
    log.info("Generating container for the folder : [" + container + "] which is in the url ["
        + endpointProp + "]");
    BlobServiceClient blobServiceClientByEndpoint =
        new BlobServiceClientBuilder().endpoint(endpointProp).buildClient();
    return blobServiceClientByEndpoint.getBlobContainerClient(container);
  }

  /**
   * Downloads the file from the container and saves it locally with the name provided.
   *
   * @param fileNameInContainer The fileName of the file to be downloaded from the container.
   * @param fileNameToDownload  The fileName to name the downloaded file.
   * @param localPath           The path to download the file to.
   * @param containerClient     The azure client connected to the provided container and a folder.
   */
  public Boolean downloadFile(String fileNameInContainer, String fileNameToDownload,
                              String localPath, BlobContainerClient containerClient) {
    log.info("We are going to download: " + fileNameToDownload + " which is in "
        + containerClient.getBlobContainerName() + " -> container " + ConfigVars.CONTAINER);
    BlobClient blobClient = containerClient.getBlobClient(fileNameInContainer);
    boolean wasDownloaded = Boolean.FALSE;

    FileChecker.createDirectory(localPath);
    boolean localPathExists = FileChecker.checkDirectoryExistence(localPath);

    if (localPathExists && blobClient.exists().equals(Boolean.TRUE)) {
      log.info("Downloading blob to: " + localPath + fileNameToDownload);
      blobClient.downloadToFile(localPath + fileNameToDownload, ConfigVars.OVERWRITE_FILES);
      wasDownloaded = true;
    } else {
      log.info("The file to download from the container [" + fileNameToDownload + "] does not exist");
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
    log.info("We are going to load: " + fileNameToUpload + " which is in [" + localPath + "]");

    FileChecker.createDirectory(localPath);
    boolean checkFileExistence = FileChecker.checkFileExistence(localPath, fileNameToUpload);
    boolean wasUploaded = Boolean.FALSE;

    BlobClient blobClient2 = containerClient.getBlobClient(fileNameToUpload);
    if (checkFileExistence) {
      log.info("Uploading blob to: " + containerClient.getBlobContainerName() + " -> container "
          + ConfigVars.CONTAINER);
      blobClient2.uploadFromFile(localPath + fileNameToUpload, ConfigVars.OVERWRITE_FILES);
      wasUploaded = true;
    } else {
      log.info("The file you want to upload from your local machine [" + fileNameToUpload
          + "] does not exist");
    }
    return wasUploaded;
  }


}
