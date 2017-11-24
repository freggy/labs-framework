package de.bergwerklabs.framework.bedrock.gameserver;

import de.bergwerklabs.framework.bedrock.gameserver.logging.ActionLogger;
import de.bergwerklabs.nick.api.NickApi;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 23.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameserverManagement extends JavaPlugin {


    private static GameserverManagement instance;
    private NickApi nickApi;
    private ActionLogger actionLogger;

    public static GameserverManagement getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // TODO register listeners
        instance = this;
        // TODO: check if registerd.
        this.nickApi = this.getServer().getServicesManager().load(NickApi.class);
        this.actionLogger = new ActionLogger();
    }

    public NickApi getNickApi() {
        return nickApi;
    }

    public ActionLogger getActionLogger() {
        return actionLogger;
    }
}
