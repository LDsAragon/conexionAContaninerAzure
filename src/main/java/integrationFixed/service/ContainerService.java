package integrationFixed.service;

import integrationFixed.constants.ConfigVars;
import integrationFixed.utils.AzureClient;
import com.azure.storage.blob.BlobContainerClient;

/**
 * Class created to be a proxy for the execution of the Azure client and encapsulate its possible errors with multiple try catch
 */
public class ContainerService extends ErrorManagerImpl<BlobContainerClient>  {

    public BlobContainerClient createContainer(){
        return execute(() -> AzureClient.generateContainer(ConfigVars.ENDPOINT_PROP_FOR_MAIN, ConfigVars.CONTAINER)) ;
    }


}
