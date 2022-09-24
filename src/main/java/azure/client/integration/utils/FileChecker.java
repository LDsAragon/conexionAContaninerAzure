package azure.client.integration.utils;

import lombok.Generated;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Utilidad para validar la existencia de archivos y directorios.
 */
@Generated
@Slf4j
@UtilityClass
public class FileChecker {

    static final String ARROWS = "] ? -->> [";

    /**
     * Checks if a directory exists.
     *
     * @return True if the directory exists.
     */
    public boolean checkDirectoryExistence(String directory) {

        boolean doesExist;

        File file = new File(directory);

        doesExist = file.exists();


        log.info("Does the directory exist? [" + directory + ARROWS + doesExist + "]");

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
        log.info("Has the directory been created? [" + directory + ARROWS + wasCreated + "]");

        return wasCreated;
    }

    /**
     * Deletes a directory. If empty only
     *
     * @return True if the directory was deleted.
     */
    public boolean deleteDirectory(String directory) throws IOException {

        boolean wasDeleted = Boolean.FALSE;

        File file = new File(directory);

        if (file.exists()) {
            Files.delete(Paths.get(directory)) ;
            wasDeleted = true;
        }
        log.info("Has the directory been deleted? [" + directory + ARROWS + wasDeleted + "]");

        return wasDeleted;
    }


    /**
     * Deletes a directory. And everything under the sun.
     *
     * @return True if the directory was deleted.
     */
    public boolean deleteAllDirectoryAndFiles(String directory) throws IOException {

        boolean wasDeleted = Boolean.FALSE;

        File file = new File(directory);

        if (file.exists()) {
            FolderDeleteNio.deleteAll(directory);
            wasDeleted = true;
        }
        log.info("Has the directory been deleted? [" + directory + ARROWS + wasDeleted + "]");

        return wasDeleted;
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
        log.info("Does the file exist? [" + fileName + ARROWS + doesExist + "]");

        return doesExist;
    }

    /**
     * Checks if a file exists in the directory and also the existence of the directory.
     *
     * @return The file if it exists. Or null
     */
    public File getFile(String directory, String fileName) {

        File returnFile = null ;
        File file = new File(directory + fileName);
        boolean doesFileExist = file.exists();

        if (checkDirectoryExistence(directory) && doesFileExist ) {
            returnFile = file ;
        }


        return returnFile;
    }

    /**
     * Gets the content of a file in base 64
     *
     * @return The file if it exists. Or null
     */
    public String getFileContentsBase64(String directory, String fileName) {

        String returnFile = null ;

        File file = getFile(directory, fileName);
        returnFile = encodeFileToBase64(file) ;

        return returnFile;
    }

    /**
     * Encodes a file into base64 and returns a string
     *
     * @param file The file to be encoded into base64
     * @return The file in StringFormat
     */
    private String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

}
