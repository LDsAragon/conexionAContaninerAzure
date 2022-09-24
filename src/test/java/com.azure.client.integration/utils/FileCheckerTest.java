package com.azure.client.integration.utils;

import azure.client.integration.utils.FileChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class FileCheckerTest {

    @Test
    void checkDirectoryExistence_True() {

        boolean exists = FileChecker.checkDirectoryExistence("lib");
        Assertions.assertTrue(exists);
    }

    @Test
    void checkDirectoryExistence_False() {

        boolean exists = FileChecker.checkDirectoryExistence("organigramaProsegur");
        Assertions.assertFalse(exists);
    }

    @Test
    void createDirectory() throws IOException {
        boolean nuevoDirectorio = FileChecker.createDirectory("NuevoDirectorio");
        Assertions.assertTrue(nuevoDirectorio);

        if (nuevoDirectorio)  {
            boolean deleted = FileChecker.deleteDirectory("NuevoDirectorio/");
            Assertions.assertTrue(deleted);
        }
    }

    @Test
    void createDirectory_False() {
        boolean libFueCreado = FileChecker.createDirectory("lib");
        Assertions.assertFalse(libFueCreado);



    }

    @Test
    void checkFileExistence_True() {
        boolean exists = FileChecker.checkFileExistence("src/test/java/com.azure.client.integration/resources/","pruebaSubida.txt");
        Assertions.assertTrue(exists);
    }

    @Test
    void checkFileExistence_False_1() {
        boolean exists = FileChecker.checkFileExistence("carpetaInexistente/","Infranet.properties");
        Assertions.assertFalse(exists);
    }

    @Test
    void checkFileExistence_False_2() {
        boolean exists = FileChecker.checkFileExistence("source/","Infranet.zip");
        Assertions.assertFalse(exists);
    }

    /**
     * Referencia de la implementación de la clase.
     *
     * @param args nada.
     * */
    public static void main(String[] args) throws IOException {

        String directory = "descargas";
        String fileName = "descargame.txt";

        FileChecker.checkDirectoryExistence(directory);

        FileChecker.createDirectory(directory);

        FileChecker.checkFileExistence(directory, fileName);

        FileChecker.deleteDirectory(directory+"/");

    }
}