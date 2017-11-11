package de.bergwerklabs.framework.gameservice.api;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Yannic Rieger on 11.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameId {

    private static String cachedId;

    public static String create() {
        if (cachedId != null) return cachedId;
        String id;
        do {
            id = generateId();
        }
        while (id.equalsIgnoreCase("bla")); // check if id already exists
        cachedId = id;
        return id;
    }

    private static String generateId() {
        byte[] bytes = new byte[8];
        new SecureRandom().nextBytes(bytes);

        byte[] base64 = Base64.getEncoder().encode(bytes);

        try {
           return new String(base64, "UTF-8").replace("/", "_")
                                                .replace("+", "-")
                                                .replace("=", "");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
