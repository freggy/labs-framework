package de.bergwerklabs.framework.nabs.standalone;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Represents player specific settings.
 *
 * @author Benedikt Wüller
 */
public interface PlayerSettings {

    class Flags {
        // <editor-fold desc="GLOBAL (0 - 4)">
        public static final int GLOBAL_AUTO_NICK_DISABLED = 0;
        public static final int GLOBAL_CHAT_MENTION_NOTIFICATIONS_ENABLED = 1;
        public static final int GLOBAL_CHAT_FRIEND_NOTIFICATIONS_ENABLED = 2;
        public static final int GLOBAL_PARTY_REQUESTS_ENABLED = 3;
        public static final int GLOBAL_FRIEND_REQUESTS_ENABLED = 4;
        // </editor-fold>

        // <editor-fold desc="LOBBY (54 - 62)">
        public static final int LOBBY_STACKING_ENABLED = 54;
        public static final int LOBBY_GADGET_INFLUENCE_ENABLED = 55;
        public static final int LOBBY_PARTICLES_ENABLED = 56;
        public static final int LOBBY_PETS_ENABLED = 57;
        public static final int LOBBY_PLAYERS_VISIBLE = 58;
        public static final int LOBBY_FRIENDS_AND_PARTY_VISIBLE = 59;
        public static final int LOBBY_PREMIUMS_VISIBLE = 60;
        public static final int LOBBY_YOUTUBERS_VISIBLE = 61;
        public static final int LOBBY_TEAM_VISIBLE = 62;
        // </editor-fold>

        // Initialization for bit shifter values
        static final Integer[] values = new Integer[63];

        static {
            IntStream.range(0, 63).forEach(i -> values[i] = i);
        }
    }

    boolean isSet(int flag);

    void set(int flag, boolean set);

    void save();

    @NotNull UUID getTarget();
}
