package azureClientIntegration.mains;

import azureClientIntegration.constants.ConfigVars;
import azureClientIntegration.utils.AzureClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobStorageException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UncheckedIOException;

@Slf4j
@Data
public class OldMainConSouts {


    public static void main(String args[]) {

        /**
         * Nombre del archivo a buscar en azure. Puede ser igual a [fileNameToDownload]
         */
        String fileNameInContainer;

        /**
         * Nombre del archivo a descargar en local. Puede ser igual a [fileNameInContainer]
         */
        String fileNameToDownload;

        /**
         * Nombre del archivo a cargar en contenedor de Azure
         */
        String fileNameToUpload;

        try {

            fileNameInContainer = fileNameToDownload = "descargame.txt";
            fileNameToUpload = "noExistoXD.txt";

            BlobContainerClient containerClient = AzureClient.generateContainer(ConfigVars.ENDPOINT_PROP, ConfigVars.CONTAINER);
            AzureClient.downloadFile(fileNameInContainer, fileNameToDownload, ConfigVars.LOCAL_PATH, containerClient);
            AzureClient.uploadFile(ConfigVars.LOCAL_PATH, fileNameToUpload, containerClient);

        } catch (BlobStorageException blobStorageException) {
            System.out.println(blobStorageException.getMessage());
        } catch (UncheckedIOException uncheckedIOException) {
            System.out.println(uncheckedIOException.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
