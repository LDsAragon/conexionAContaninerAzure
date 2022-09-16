package azure.client.integration;

import azure.client.integration.service.ContainerService;
import azure.client.integration.service.FileManagerService;
import com.azure.storage.blob.BlobContainerClient;
import lombok.Data;

/**
 * Clase para contener un Main con la implementacion
 * de genericos que resguardan la verbosidad de un try catch.
 */
@Data
public class MainConLogs {

  /**
   * Clase que contiene un main para ejemplificar el funcionamiento de la conexion a azure
   * y el llamado de los metodos construidos.
   *
   * @param args nada.
   */
  public static void main(String[] args) {

    /*
     * Nombre del archivo a buscar en azure. Puede ser igual a [fileNameToDownload]
     */
    String fileNameInContainer;

    /*
     * Nombre del archivo a descargar en local. Puede ser igual a [fileNameInContainer]
     */
    String fileNameToDownload;

    /*
      Nombre del archivo a cargar en contenedor de Azure
     */
    String fileNameToUpload;

    fileNameInContainer = fileNameToDownload = "descargame.txt";
    fileNameToUpload = "noExistoXD.txt";
    ContainerService containerService = new ContainerService();
    FileManagerService fileManagerService = new FileManagerService();

    BlobContainerClient containerClient = containerService.createContainer();

    fileManagerService.downloadFile(fileNameInContainer, fileNameToDownload,
        containerClient);

    fileManagerService.uploadFile(fileNameToUpload, containerClient);

  }


}
