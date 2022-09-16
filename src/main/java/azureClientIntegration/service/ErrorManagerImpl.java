package azureClientIntegration.service;


import azureClientIntegration.Exceptions.CustomAzureException;
import com.azure.storage.blob.models.BlobStorageException;

import java.io.UncheckedIOException;
import java.util.concurrent.Callable;

/**
 * Manages the errors classes that extends this one.
 * @param <T> The type ob object to convert the output of the callable function.
 */
public class ErrorManagerImpl<T> {

    public T execute(Callable<T> callable) {

        try {
            return callable.call();
        } catch (BlobStorageException blobStorageException) {
            System.out.println(blobStorageException.getMessage());
            throw new CustomAzureException("CONNECT TO CLIENT", blobStorageException.getCause().getMessage(), blobStorageException.getMessage());
        } catch (UncheckedIOException uncheckedIOException) {
            System.out.println(uncheckedIOException.getMessage());
            throw new CustomAzureException("CANNOT RECOVER FILE OR CREATE FOLDER", uncheckedIOException.getCause().getMessage(), uncheckedIOException.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CustomAzureException("ERROR", ex.getCause().getMessage(), ex.getMessage());
        }

    }
    
    


}
