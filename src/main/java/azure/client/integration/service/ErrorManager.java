package azure.client.integration.service;


import azure.client.integration.exceptions.CustomAzureException;
import com.azure.storage.blob.models.BlobStorageException;
import lombok.extern.slf4j.Slf4j;

import java.io.UncheckedIOException;
import java.util.concurrent.Callable;


/**
 * Encapsulates a Try Catch and diminishes verbosity
 * Manages the errors of classes like [BlobContainerClient] and java IO.
 *
 * @param <T> The type of object to convert the output of the callable function.
 */
@Slf4j
public class ErrorManager<T> {

  /**
   * Execute, runs a function passed as a lambda expresión and returns its result object.
   *
   * @param callable The function to be called inside the try catch.
   * @return The return of the function passed
   */
  public T execute(Callable<T> callable) throws CustomAzureException {

    try {
      return callable.call();
    } catch (BlobStorageException blobStorageException) {
      log.info(blobStorageException.getMessage());
      throw new CustomAzureException("Error : cannot connect to client or download/upload file",
          blobStorageException.getCause().getMessage(), blobStorageException.getMessage());
    } catch (UncheckedIOException ioException) {
      log.info(ioException.getMessage());
      throw new CustomAzureException("Error : cannot create/find the folder/file",
          ioException.getCause().getMessage(), ioException.getMessage());
    } catch (Exception ex) {
      throw new CustomAzureException("Error : ", ex.getMessage(), ex.getMessage());
    }

  }


}
