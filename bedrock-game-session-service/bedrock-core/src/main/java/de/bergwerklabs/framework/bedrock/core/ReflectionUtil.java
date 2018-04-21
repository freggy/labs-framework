package de.bergwerklabs.framework.bedrock.core;

import de.bergwerklabs.framework.bedrock.api.PlayerFactory;
import de.bergwerklabs.framework.bedrock.api.PlayerRegistry;
import de.bergwerklabs.framework.bedrock.api.lobby.AbstractLobby;
import de.bergwerklabs.framework.bedrock.api.session.GameSession;
import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * Created by Yannic Rieger on 14.11.2017.
 *
 * <p>Mainly used for getting instances of {@link PlayerFactory} and {@link AbstractLobby}.
 *
 * @author Yannic Rieger
 */
public class ReflectionUtil {

  /**
   * Gets the factory that will be used for the plugin.
   *
   * @param clazz fully qualified name of the class. e.g {@code java.lang.Object}.
   * @return {@link Optional} containing the factory instance or just an empty one.
   */
  static <T> Optional<T> getFactoryClassInstance(String clazz) {
    try {
      return Optional.of((T) Class.forName(clazz).newInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  /**
   * Gets the {@link AbstractLobby} that will be used for the plugin. If standard config is used
   * it's {@link de.bergwerklabs.framework.bedrock.api.lobby.SimpleLobby}.
   *
   * @param clazz fully qualified name of the class. e.g {@code java.lang.Object}.
   * @param waitingDuration duration the players have to wait until the game starts.
   * @param maxPlayers maximum amount of players.
   * @param minPlayers minimal amount of players.
   * @param session corresponding Bedrock Session
   * @return {@link Optional} containing the {@link AbstractLobby} instance or just an empty one.
   */
  static Optional<AbstractLobby> getLobbyInstance(
      String clazz,
      int waitingDuration,
      int maxPlayers,
      int minPlayers,
      GameSession session,
      PlayerRegistry registry) {
    try {
      Class<?> c = Class.forName(clazz);
      Constructor<?> constructor =
          c.getConstructor(
              int.class, int.class, int.class, GameSession.class, PlayerRegistry.class);
      return Optional.of(
          (AbstractLobby)
              constructor.newInstance(waitingDuration, maxPlayers, minPlayers, session, registry));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
