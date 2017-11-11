package de.bergwerklabs.framework.gameservice.listener;

import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayClientClientCommand;
import de.bergwerklabs.framework.gameservice.api.LabsPlayer;
import de.bergwerklabs.framework.gameservice.PlayerRegistry;
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
     * @param playerRegistry
     */
    public PlayerDeathListener(PlayerRegistry<T> playerRegistry, GameServiceConfig config) {
        super(playerRegistry, config);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        this.playerRegistry.getPlayers().remove(player.getUniqueId());

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
            T playerObj = this.playerRegistry.getPlayers().get(player.getUniqueId());
            playerObj.setSpectator();
            this.playerRegistry.getSpectators().put(player.getUniqueId(), playerObj);
        }
    }
}
