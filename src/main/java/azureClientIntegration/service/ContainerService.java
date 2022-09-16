package azureClientIntegration.service;

import azureClientIntegration.constants.ConfigVars;
import com.azure.storage.blob.BlobContainerClient;
import azureClientIntegration.utils.AzureClient;

/**
 * Class created to be a proxy for the execution of the Azure client and encapsulate its possible errors with multiple try catch
 */
public class ContainerService extends ErrorManagerImpl<BlobContainerClient>  {

    public BlobContainerClient createContainer(){
        return execute(() -> AzureClient.generateContainer(ConfigVars.ENDPOINT_PROP, ConfigVars.CONTAINER)) ;
    }


}
