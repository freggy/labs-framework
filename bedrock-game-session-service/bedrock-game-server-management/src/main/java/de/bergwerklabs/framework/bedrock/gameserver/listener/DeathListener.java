package de.bergwerklabs.framework.bedrock.gameserver.listener;

import de.bergwerklabs.framework.bedrock.api.history.ActionType;
import de.bergwerklabs.framework.bedrock.gameserver.GameserverManagement;
import de.bergwerklabs.framework.bedrock.gameserver.history.DeathAction;
import de.bergwerklabs.framework.bedrock.gameserver.history.KillAction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class DeathListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private void onPlayerKill(EntityDamageByEntityEvent event) {
        Entity killer = event.getDamager();
        Entity killed = event.getEntity();
        if ((killer instanceof Player) && (killed instanceof Player)) {
            Player killedPlayer = (Player) killed;
            if (killedPlayer.getHealth() <= 0) {
                Player playerKiller = (Player) killer;
                GameserverManagement.getInstance().getHistoryLogger().log(new KillAction(ActionType.KILL, System.currentTimeMillis(), playerKiller, killedPlayer));
            }
        }
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        GameserverManagement.getInstance().getHistoryLogger().log(new DeathAction(ActionType.DEATH, System.currentTimeMillis(), event.getEntity()));
    }
}
