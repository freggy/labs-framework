package de.bergwerklabs.framework.commons.spigot.pluginmessage;

/**
 * Created by Yannic Rieger on 08.10.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public enum PluginMessageOption {
    CONNECT("Connect"),
    CONNECT_OTHER("ConnectOther"),
    IP("IP"),
    PLAYER_COUNT("PlayerCount"),
    PLAYER_LIST("PlayerList"),
    GET_SERVERS("GetServers"),
    MESSAGE("Message"),
    GET_SERVER("GetServer"),
    FORWARD("Forward"),
    FORWARD_TO_PLAYER("ForwardToPlayer"),
    UUID("UUID"),
    UUID_OTHER("UUIDOther"),
    SERVER_IP("ServerIP"),
    KICK_PLAYER("KickPlayer");

    /**
     *
     */
    public String getId() {
        return id;
    }

    private String id;

    PluginMessageOption(String id) {
        this.id = id;
    }
}
