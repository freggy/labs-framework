package de.bergwerklabs.framework.bedrock.core;

import de.bergwerklabs.framework.bedrock.api.PlayerFactory;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;
import de.bergwerklabs.framework.bedrock.api.session.GameSession;

import java.lang.reflect.Constructor;
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
     *
     * @param clazz
     * @return
     */
    static Optional<PlayerFactory> getFactoryClassInstance(String clazz) {
        try {
            return Optional.of((PlayerFactory) Class.forName(clazz).newInstance());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    static Optional<AbstractLobby> getLobbyInstance(String clazz, int waitingDuration, int maxPlayers, int minPlayers, GameSession session) {
        try {
            Class<?> c = Class.forName(clazz);
            Constructor<?> constructor = c.getConstructor(int.class, int.class, int.class, GameSession.class);
            return Optional.of((AbstractLobby)constructor.newInstance(waitingDuration, maxPlayers, minPlayers, session));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
