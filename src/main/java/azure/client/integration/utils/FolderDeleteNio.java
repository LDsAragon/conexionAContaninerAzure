package azure.client.integration.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

// Java NIO Delete Directory
@Slf4j
@UtilityClass
public class FolderDeleteNio {

  /**
   * Deletes recursively all subfolder and files from the directory provided if posible.
   * Then deletes the directory provided.
   *
   * @param directory The route to the directory to delete.
   * @throws IOException Permissions to delete can be absent.
   *                     I don't divise any other case in which deletion would fail.
   */
  public void deleteAll(String directory) throws IOException {
    //declaring the path to delete
    Path path = Paths.get(directory);
    log.info("Deleting recursively : " + path);
    //browsing the file directory and delete recursively using java nio
    Files.walkFileTree(path, new FileVisitor<Path>() {

      public FileVisitResult postVisitDirectory(Path dir, IOException exc)
          throws IOException {

        log.info("Deleting directory :" + dir);
        Files.deleteIfExists(dir);
        return FileVisitResult.CONTINUE;
      }

      public FileVisitResult preVisitDirectory(Path dir,
                                               BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
      }

      public FileVisitResult visitFile(Path file,
                                       BasicFileAttributes attrs) throws IOException {
        log.info("Deleting file: " + file);
        Files.deleteIfExists(file);
        return FileVisitResult.CONTINUE;
      }

      public FileVisitResult visitFileFailed(Path file, IOException exc)
          throws IOException {
        log.info(exc.toString());
        return FileVisitResult.CONTINUE;
      }
    });
  }

}