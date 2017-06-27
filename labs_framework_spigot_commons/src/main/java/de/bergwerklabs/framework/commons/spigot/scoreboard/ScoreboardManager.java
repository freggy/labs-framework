package de.bergwerklabs.framework.commons.spigot.scoreboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannic Rieger on 03.05.2017.
 * <p> This class provides functionalities managing created LabsScoreboards. </p>
 *
 * @author Yannic Rieger
 */
public class ScoreboardManager {
    /**
     * Contains every created LabsScoreboard.
     * This map is unmodifiable.
     */
    public static Map<String, LabsScoreboard> getScoreboards() { return Collections.unmodifiableMap(scoreboards); }

    static HashMap<String, LabsScoreboard> scoreboards = new HashMap<>();
}
