package de.bergwerklabs.framework.commons.spigot.file;

import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yannic Rieger on 25.04.2017.
 * <p> Class containing useful methods for working with files. </p>
 * @author Yannic Rieger
 */
public class FileUtil {

    /**
     * Creates a folder if it doesn't already exists.
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
     * @param file File object representing a file.
     * @throws IOException
     */
    public static void createFileIfNotExistent(File file) throws IOException {
        Validate.notNull(file, "Parameter 'file' cannot be null");
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
