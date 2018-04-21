package de.bergwerklabs.framework.commons.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yannic Rieger on 01.09.2017.
 *
 * <p>Contains utilities for working with files.
 *
 * @author Yannic Rieger
 */
public class LabsFileUtil {

  /**
   * Lists files and directories recursively contained in the given one, even empty ones.
   *
   * @param directoryName Name of the directory to search through.
   * @return a {@link List} of files and directories.
   */
  public static List<File> listFilesAndDirectories(String directoryName) {
    List<File> collected = new ArrayList<>();
    listFilesAndDirectories(directoryName, collected);
    return collected;
  }

  /**
   * Just a method used in {@link LabsFileUtil#listFilesAndDirectories(String)} so that it can
   * return a list.
   *
   * @param directoryName Name of the directory to search through.
   * @param collected a {@link List} of files and directories.
   */
  private static void listFilesAndDirectories(String directoryName, List<File> collected) {
    File directory = new File(directoryName);
    File[] fList = directory.listFiles();

    for (File file : fList) {
      if (file.isFile()) {
        collected.add(file);
      } else if (file.isDirectory()) {
        collected.add(file);
        listFilesAndDirectories(file.getAbsolutePath(), collected);
      }
    }
  }

  /**
   * Creates {@link File} if it not exists.
   *
   * @param file File to create.
   */
  public static void createFileIfNotExists(File file) {
    if (!file.exists()) {
      try {
        file.getParentFile().mkdirs();
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
