package de.bergwerklabs.framework.gameservice.listener;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayClientClientCommand;
import de.bergwerklabs.framework.gameservice.LabsPlayer;
import de.bergwerklabs.framework.gameservice.PlayerManager;
import de.bergwerklabs.framework.gameservice.config.GameServiceConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Yannic Rieger on 01.08.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class PlayerDeathListener<T extends LabsPlayer> extends LabsListener<T> {

    /**
     * @param playerManager
     */
    public PlayerDeathListener(PlayerManager<T> playerManager, GameServiceConfig config) {
        super(playerManager, config);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        this.playerManager.getPlayers().remove(player.getUniqueId());

        if (this.config.useAutoRespawn()) {
            WrapperPlayClientClientCommand playClientClientCommand = new WrapperPlayClientClientCommand();
            playClientClientCommand.setAction(EnumWrappers.ClientCommand.PERFORM_RESPAWN);
            playClientClientCommand.sendPacket(player);
        }

        if (this.config.incrementStatisticOnDeath()) {
            // TODO: increase died statistic on death
        }

        if (this.config.incrementGamesPlayedOnDeath()) {
            // TODO: increment games played statistic on death
        }

        if (this.config.spectateOnDeath()) {
            T playerObj = this.playerManager.getPlayers().get(player.getUniqueId());
            playerObj.setSpectator();
            this.playerManager.getSpectators().put(player.getUniqueId(),playerObj);
        }
    }
}
