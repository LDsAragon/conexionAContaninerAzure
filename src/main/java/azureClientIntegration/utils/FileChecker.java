package azureClientIntegration.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@UtilityClass
public class FileChecker {


    /**
     * Checks if a directory exists.
     *
     * @return True if the directory exists.
     */
    public boolean checkDirectoryExistence(String directory) {

        Boolean doesExist;

        File file = new File(directory);

        doesExist = file.exists();

        System.out.println("¿Existe el directorio [" + directory + "] ? -->> [" + doesExist + "]");

        return doesExist;
    }

    /**
     * Creates a directory.
     *
     * @return True if the directory was created.
     */
    public boolean createDirectory(String directory) {

        Boolean wasCreated = Boolean.FALSE;

        File file = new File(directory);

        if (!file.exists()) {
            wasCreated = file.mkdirs();
        }
        System.out.println("¿Fue creado el directorio [" + directory + "] ? -->> [" + wasCreated + "]");

        return wasCreated;
    }


    /**
     * Checks if a file exists in the directory and also the existence of the directory
     *
     * @return True if the file exists.
     */
    public boolean checkFileExistence(String directory, String fileName) {

        Boolean doesExist = Boolean.FALSE;

        File file = new File(directory + fileName);

        if (checkDirectoryExistence(directory)) {
            doesExist = file.exists();
        }
        System.out.println("¿Existe el archivo [" + fileName + "] ? -->> [" + doesExist + "]");

        return doesExist;
    }


    public static void main(String[] args) {

        String directory = "descargas";
        String fileName = "descargame.txt";

        FileChecker.checkDirectoryExistence(directory);

        FileChecker.createDirectory(directory);

        FileChecker.checkFileExistence(directory, fileName);

    }

}
