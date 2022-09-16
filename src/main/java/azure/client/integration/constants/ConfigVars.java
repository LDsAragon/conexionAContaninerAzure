package azure.client.integration.constants;


import org.springframework.beans.factory.annotation.Value;

public class ConfigVars {

  private ConfigVars() {
  }

  /**
   * Valor de la llave SAS para conectarse al contenedor de azure.
   */
  @Value("${endpoint.azure.container}")
  public String endpointProperty;

  /**
   * Valor de la llave SAS para conectarse al contenedor de azure.
   */
  public static final String ENDPOINT_PROP_FOR_MAIN =
      "https://sabrmdev.blob.core.windows.net/saft?sp=racwdli&st=2022-09-15T13:34:40Z&se=2025-09-15T21:34:40Z&spr=https&sv=2021-06-08&sr=c&sig=QurqTW1i7%2BId%2FwD8ZQuIPntdzJ8XGHxnlaJWI7P8qAw%3D";

  /**
   * Sobreescribir los archivos tanto en local como en el contenedor de azure.
   */
  public static final String CONTAINER = "saft";

  /**
   * Sobreescribir los archivos tanto en local como en el contenedor de azure.
   */
  public static final boolean OVERWRITE_FILES = Boolean.TRUE;

  /**
   * Ubicacion local en la que se va a descargar el archivo recuperado desde el contenedor de azure
   */
  public static final String LOCAL_PATH = "descargas/";

}
