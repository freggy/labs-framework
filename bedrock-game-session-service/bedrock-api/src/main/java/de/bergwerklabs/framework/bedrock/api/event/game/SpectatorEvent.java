package de.bergwerklabs.framework.bedrock.api.event.game;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;

/**
 * Created by Yannic Rieger on 01.05.2018.
 *
 * <p>Gets fired when a player enters spectator mode. This is enables specific spectator modes
 * can per mini game.
 *
 * @author Yannic Rieger
 */
public class SpectatorEvent<T extends LabsPlayer> extends AbstractGameEvent<T> {

  /** Player that has been set to spectator mode. */
  public T getSpectator() {
    return spectator;
  }

  private T spectator;

  /**
   * @param game {@link LabsGame} instance.
   * @param spectator player that has been set to spectator mode.
   */
  public SpectatorEvent(LabsGame<T> game, T spectator) {
    super(game);
    this.spectator = spectator;
  }
}
