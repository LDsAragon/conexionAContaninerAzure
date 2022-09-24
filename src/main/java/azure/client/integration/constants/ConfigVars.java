package azure.client.integration.constants;


/**
 * Class with configurable parameters and default values for those configurable parameters.
 */
public class ConfigVars {

  private ConfigVars() {
  }

  /**
   * SAS key from Azure Container.
   */
  public static final String AZURE_SAS_KEY =
      "https://sabrmdev.blob.core.windows.net/saft?sp=racwdli&st=2022-09-15T13:34:40Z&se=2025-09-15T21:34:40Z&spr=https&sv=2021-06-08&sr=c&sig=QurqTW1i7%2BId%2FwD8ZQuIPntdzJ8XGHxnlaJWI7P8qAw%3D";

  /**
   * Name from the folder in the Azure Container.
   */
  public static final String AZURE_FOLDER = "saft";

  /**
   * Overwrite files both locally and in the Azure Container.
   */
  public static final boolean OVERWRITE_FILES = Boolean.TRUE;

  /**
   * Local location where the recovered file is to be downloaded from the Azure Container.
   */
  public static final String LOCAL_FOLDER = "descargas/";

}
