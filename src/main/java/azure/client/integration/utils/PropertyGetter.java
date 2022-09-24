package azure.client.integration.utils;

import azure.client.integration.constants.ConfigVars;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

@UtilityClass
public class PropertyGetter {

  /**
   * SAS key from Azure Container.
   */
  @Value("${azure.client.endpoint}")
  private String azureEndpoint;


  /**
   * Name from the folder in the Azure Container.
   */
  @Value("${azure.client.azureFolder}")
  private String azureFolder;


  /**
   * Name from the folder in the local fileSystem
   */
  @Value("${azure.client.localFolder}")
  private String localFolder;


  /**
   * Evaluates the configured property [azureEndpoint], to return it
   * Or return the default value in the class, [ConfigVars.ENDPOINT_PROP]
   *
   * @return a string with a SAS key
   */
  public String getEndpoint() {


    String endpointHardcoded = ConfigVars.AZURE_SAS_KEY;
    String endpointConfigured = azureEndpoint;
    String result = endpointHardcoded;

    if (endpointConfigured != null &&
        !endpointConfigured.isEmpty()) {
      result = endpointConfigured;
    }

    return result;
  }

  /**
   * Evaluates the configured property [azureFolder], to return it
   * Or return the default value in the class, [ConfigVars.FOLDER]
   *
   * @return a string with the folder
   */
  public String getAzureFolder() {

    String folderHardcoded = ConfigVars.AZURE_FOLDER;
    String folderConfigured = azureFolder;
    String result = folderHardcoded;

    if (folderConfigured != null &&
        !folderConfigured.isEmpty()) {
      result = folderConfigured;
    }

    return result;
  }

  public static void setLocalPath(String localPath) {
    PropertyGetter.localFolder = localPath;
  }

  /**
   * Evaluates the configured property [localFolder], to return it
   * Or return the default value in the class, [ConfigVars.LOCAL_PATH]
   *
   * @return a string with the local path
   */
  public String getLocalFolder() {

    String localPathHardcoded = ConfigVars.LOCAL_FOLDER;
    String localPathConfigured = localFolder;
    String result = localPathHardcoded;

    if (localPathConfigured != null &&
        !localPathConfigured.isEmpty()) {
      result = localPathConfigured;
    }

    return result;
  }

}
