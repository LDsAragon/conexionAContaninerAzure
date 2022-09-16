package integrationFixed.mains;

import com.azure.storage.blob.BlobContainerClient;
import integrationFixed.service.ContainerService;
import integrationFixed.service.UploaderDownloaderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class MainConLogs {


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
        UploaderDownloaderService uploaderDownloaderService = new UploaderDownloaderService();

        BlobContainerClient containerClient = containerService.createContainer();

        uploaderDownloaderService.downloadFile(fileNameInContainer, fileNameToDownload, containerClient);

        uploaderDownloaderService.uploadFile(fileNameToUpload, containerClient);

    }


}
