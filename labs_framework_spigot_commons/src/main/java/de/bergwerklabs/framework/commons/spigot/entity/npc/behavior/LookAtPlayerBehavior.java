package de.bergwerklabs.framework.commons.spigot.entity.npc.behavior;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.util.math.SQRT;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;

/**
 * Created by Yannic Rieger on 23.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class LookAtPlayerBehavior extends AbstractBehavior implements Listener {

    /**
     *
     */
    public boolean isGlobal() {
        return global;
    }

    private boolean global = false;
    private int range;
    private Float yaw, pitch, defaultYaw, defaultPitch;
    private Player focused;


    public LookAtPlayerBehavior(boolean global, int range, Float defaultPitch, Float defaultYaw) {
        this.global = global;
        this.defaultPitch = defaultPitch;
        this.defaultYaw = defaultYaw;
        this.range = range <= 0 ? 25 * 25 : range * range;
    }

    @Override
    public void perform(Player player) {
        double dX = associated.getLocation().getX() - player.getLocation().getX();
        double dY = associated.getLocation().getY() - player.getLocation().getY();
        double dZ = associated.getLocation().getZ() - player.getLocation().getZ();

        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(SQRT.fastest(dZ * dZ + dX * dX), dY) + Math.PI;
        this.focused = player;
        this.associated.setHeadRotation((float) (-Math.toDegrees(pitch) - 90), (float) (90 + Math.toDegrees(yaw)));
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent e) {
        if (this.global) this.globalBehavior();
        else this.singleBehavior(e.getPlayer());
    }

    private void globalBehavior() {
        System.out.println("adawd");

        if (focused == null) focused = Bukkit.getOnlinePlayers().iterator().next();

        Bukkit.getOnlinePlayers().forEach(player -> {
            Location focusedLocation = this.focused.getLocation();
            Location newPlayerLoc = player.getLocation();

            if (focusedLocation.getWorld().getName().equals(this.associated.getLocation().getWorld().getName())) {

                double focusedDistance = focusedLocation.distanceSquared(this.associated.getLocation());
                double newPlayerDistance = newPlayerLoc.distanceSquared(this.associated.getLocation());

                if (newPlayerDistance > this.range || focusedDistance > this.range) {
                    if (this.defaultYaw != null && this.defaultPitch != null) {
                        System.out.println("this.defaultYaw != null && this.defaultPitch != null");
                        this.associated.setHeadRotation(defaultPitch, defaultYaw);
                    }
                }
                else if (newPlayerDistance < range) {
                    System.out.println("newPlayerDistance < range");
                    if (newPlayerDistance < focusedDistance || player.getUniqueId().equals(this.focused.getUniqueId())) {
                        System.out.println("newPlayerDistance < focusedDistance");
                        this.perform(player);
                    }
                }
            }
        });
    }

    private void singleBehavior(Player player) {
        double playerDistance = player.getLocation().distanceSquared(this.associated.getLocation());

        if (playerDistance > this.range) {
            if (this.defaultYaw != null && this.defaultPitch != null)
                this.associated.setHeadRotation(defaultPitch, defaultYaw);
        }
        else if (playerDistance > range) {
            Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.perform(player), 20L);
        }
    }
}

