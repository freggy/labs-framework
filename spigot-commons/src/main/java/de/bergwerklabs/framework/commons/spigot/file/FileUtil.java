package de.bergwerklabs.framework.commons.spigot.file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.lang.Validate;

/**
 * Created by Yannic Rieger on 25.04.2017.
 *
 * <p>Class containing useful methods for working with files.
 *
 * @author Yannic Rieger
 */
public class FileUtil {

  /**
   * Creates a folder if it doesn't already exists.
   *
   * @param file File object representing a folder.
   */
  public static void createFolderIfNotExistent(File file) {
    Validate.notNull(file, "Parameter 'file' cannot be null");
    if (!file.exists()) {
      file.mkdir();
    }
  }

  /**
   * Creates a file if it doesn't already exists
   *
   * @param file File object representing a file.
   * @return value indicating whether or not the file could be created.
   */
  public static boolean createFileIfNotExistent(File file) throws IOException {
    Validate.notNull(file, "Parameter 'file' cannot be null");
    return !file.exists() && file.createNewFile();
  }
}
