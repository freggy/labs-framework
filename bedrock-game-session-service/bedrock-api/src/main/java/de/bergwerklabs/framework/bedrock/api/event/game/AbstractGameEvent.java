package de.bergwerklabs.framework.bedrock.api.event.game;

import de.bergwerklabs.framework.bedrock.api.LabsGame;
import de.bergwerklabs.framework.bedrock.api.LabsPlayer;
import de.bergwerklabs.framework.commons.spigot.general.LabsEvent;

/**
 * Created by Yannic Rieger on 21.09.2017.
 *
 * <p>Base class for game events.
 *
 * @author Yannic Rieger
 */
public class AbstractGameEvent<T extends LabsPlayer> extends LabsEvent {

  private LabsGame<T> game;

  /** @param game Instance of a {@link LabsGame}. */
  AbstractGameEvent(LabsGame<T> game) {
    this.game = game;
  }

  /** Gets the {@link LabsGame} that has been started. */
  public LabsGame<T> getGame() {
    return this.game;
  }
}
