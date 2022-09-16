package azure.client.integration.utils;

import java.io.File;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utilidad para validar la existencia de archivos y directorios.
 */
@Slf4j
@UtilityClass
public class FileChecker {

  final String arrows = "] ? -->> [";

  /**
   * Checks if a directory exists.
   *
   * @return True if the directory exists.
   */
  public boolean checkDirectoryExistence(String directory) {

    boolean doesExist;

    File file = new File(directory);

    doesExist = file.exists();


    log.info("¿Existe el directorio [" + directory + arrows + doesExist + "]");

    return doesExist;
  }

  /**
   * Creates a directory.
   *
   * @return True if the directory was created.
   */
  public boolean createDirectory(String directory) {

    boolean wasCreated = Boolean.FALSE;

    File file = new File(directory);

    if (!file.exists()) {
      wasCreated = file.mkdirs();
    }
    log.info("¿Fue creado el directorio [" + directory + arrows + wasCreated + "]");

    return wasCreated;
  }


  /**
   * Checks if a file exists in the directory and also the existence of the directory.
   *
   * @return True if the file exists.
   */
  public boolean checkFileExistence(String directory, String fileName) {

    boolean doesExist = Boolean.FALSE;

    File file = new File(directory + fileName);

    if (checkDirectoryExistence(directory)) {
      doesExist = file.exists();
    }
    log.info("¿Existe el archivo [" + fileName + arrows + doesExist + "]");

    return doesExist;
  }


  /**
   * Tester de la implementación de la clase.
   *
   * @param args nada.
   * */
  public static void main(String[] args) {

    String directory = "descargas";
    String fileName = "descargame.txt";

    FileChecker.checkDirectoryExistence(directory);

    FileChecker.createDirectory(directory);

    FileChecker.checkFileExistence(directory, fileName);

  }

}
