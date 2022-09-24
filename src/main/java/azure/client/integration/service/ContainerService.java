package azure.client.integration.service;


import azure.client.integration.utils.AzureClient;
import azure.client.integration.utils.PropertyGetter;
import com.azure.storage.blob.BlobContainerClient;
//import org.springframework.stereotype.Service;

/**
 * Class created to be a proxy for the execution of the Azure client,
 * And encapsulate its possible errors with multiple try catch.
 */
//@Service
public class ContainerService extends ErrorManager<BlobContainerClient> {

  public BlobContainerClient createContainer() {
    return execute(() -> AzureClient.generateContainer(PropertyGetter.getEndpoint(),
        PropertyGetter.getAzureFolder()));
  }


}
