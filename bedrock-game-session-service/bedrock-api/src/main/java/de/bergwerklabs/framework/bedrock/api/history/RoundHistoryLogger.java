package de.bergwerklabs.framework.bedrock.api.history;

import com.google.gson.JsonElement;

/**
 * Created by Yannic Rieger on 24.11.2017.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public interface RoundHistoryLogger {

  void log(Action action);

  JsonElement exportHistory();
}
