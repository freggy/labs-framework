package de.bergwerklabs.framework.bedrock.gameserver;

import de.bergwerklabs.framework.bedrock.api.Management;
import de.bergwerklabs.framework.bedrock.api.history.RoundHistoryLogger;
import de.bergwerklabs.framework.bedrock.gameserver.history.ActionLogger;
import de.bergwerklabs.nick.api.NickApi;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 23.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class GameserverManagement extends JavaPlugin implements Management {

    private static GameserverManagement instance;
    private NickApi nickApi;
    private ActionLogger actionLogger;

    public static GameserverManagement getInstance() {
        return instance;
    }

    public NickApi getNickApi() {
        return nickApi;
    }

    public RoundHistoryLogger getHistoryLogger() {
        return actionLogger;
    }

    @Override
    public void onEnable() {
        // TODO register listeners
        instance = this;
        // TODO: check if registerd.
        this.nickApi = this.getServer().getServicesManager().load(NickApi.class);
        this.actionLogger = new ActionLogger();
        this.getServer().getServicesManager().register(Management.class, this, this, ServicePriority.Normal);
    }
}
