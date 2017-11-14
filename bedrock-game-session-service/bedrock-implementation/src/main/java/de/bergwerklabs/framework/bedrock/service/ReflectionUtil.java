package de.bergwerklabs.framework.bedrock.service;

import de.bergwerklabs.framework.bedrock.api.PlayerFactory;

import java.util.Optional;

/**
 * Created by Yannic Rieger on 14.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ReflectionUtil {

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends PlayerFactory> Optional<T> getClassInstance(Class<T> to, String clazz) {
        try {
            return Optional.of(to.cast(Class.forName(clazz).newInstance()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
