package de.bergwerklabs.framework.commons.spigot.entity.npc.behavior;

import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 23.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public abstract class AbstractBehavior implements Listener {

    protected Npc associated;

    public abstract void perform(Player player);

    public void setNpc(Npc npc) {
        this.associated = npc;
    }
}
