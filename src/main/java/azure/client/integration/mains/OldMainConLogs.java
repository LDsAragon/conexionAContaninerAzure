package azure.client.integration.mains;

import azure.client.integration.constants.ConfigVars;
import azure.client.integration.utils.AzureClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobStorageException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.UncheckedIOException;
import java.util.Arrays;


@Slf4j
@Data
/**
 *
 */
public class OldMainConLogs {

  /**
   * Clase que contiene un main para ejemplificar el funcionamiento de la conexion a azure.
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
     * Nombre del archivo a cargar en contenedor de Azure
     */
    String fileNameToUpload;

    try {

      fileNameInContainer = fileNameToDownload = "descargame.txt";
      fileNameToUpload = "noExistoXD.txt";

      BlobContainerClient containerClient =
          AzureClient.generateContainer(ConfigVars.AZURE_SAS_KEY, ConfigVars.AZURE_FOLDER);
      AzureClient.downloadFile(fileNameInContainer, fileNameToDownload, ConfigVars.LOCAL_FOLDER,
          containerClient);
      AzureClient.uploadFile(ConfigVars.LOCAL_FOLDER, fileNameToUpload, containerClient);

    } catch (BlobStorageException | UncheckedIOException exception) {
      log.info(exception.getMessage());
    } catch (Exception ex) {
      log.info(ex.getMessage());
      log.info(Arrays.toString(ex.getStackTrace()));
    }

  }


}
