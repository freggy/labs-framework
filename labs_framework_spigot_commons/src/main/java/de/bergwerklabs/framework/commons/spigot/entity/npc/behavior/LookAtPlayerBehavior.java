package de.bergwerklabs.framework.commons.spigot.entity.npc.behavior;

import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.util.math.SQRT;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Yannic Rieger on 23.07.2017.
 * <p> Behavior which will let the {@link de.bergwerklabs.framework.commons.spigot.entity.npc.Npc}
 *     look at a player in a given range.
 *
 * @author Yannic Rieger
 */
public class LookAtPlayerBehavior extends ActivatedBehavior {

    /**
     * Gets whether or not this behavior will be executed globally.
     */
    public boolean isGlobal() {
        return global;
    }

    private boolean global = false;
    private int range;
    private Float defaultYaw, defaultPitch;
    private Player focused;

    /**
     * @param global Determines whether or not this behavior will be executed globally.
     * @param range Range in which the {@link de.bergwerklabs.framework.commons.spigot.entity.npc.Npc} will look at a player.
     *              if 0, the default value if 25 will be set.
     * @param defaultPitch Default pitch to which the {@link de.bergwerklabs.framework.commons.spigot.entity.npc.Npc} will fall back
     *                     if out of range.
     * @param defaultYaw Default yaw to which the {@link de.bergwerklabs.framework.commons.spigot.entity.npc.Npc} will fall back
     *                   if out of range.
     */
    public LookAtPlayerBehavior(boolean global, int range, Float defaultPitch, Float defaultYaw) {
        this.global = global;
        this.defaultPitch = defaultPitch;
        this.defaultYaw = defaultYaw;
        this.range = range <= 0 ? 25 * 25 : range * range;
    }

    public void activate() {}

    public void deactivate() {
        PlayerMoveEvent.getHandlerList().unregister(this);
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR) {
            if (this.global) this.globalBehavior(e.getPlayer());
            else this.singleBehavior(e.getPlayer());
        }
    }


    /**
     * The {@link de.bergwerklabs.framework.commons.spigot.entity.npc.Npc} will look at the closest player near him.
     * This method will be executed for every one on the server.
     */
    private void globalBehavior(Player player) {
        if (focused == null) focused = Bukkit.getOnlinePlayers().iterator().next();

        Location focusedLocation = this.focused.getLocation();
        Location newPlayerLoc = player.getLocation();


        if (focusedLocation.getWorld().getName().equals(this.associated.getLocation().getWorld().getName()) &&
            newPlayerLoc.getWorld().getName().equals(this.associated.getLocation().getWorld().getName())) {

            double focusedDistance = focusedLocation.distanceSquared(this.associated.getLocation());
            double newPlayerDistance = newPlayerLoc.distanceSquared(this.associated.getLocation());

            if (focusedDistance > this.range) {
                if (this.defaultYaw != null && this.defaultPitch != null) {
                    this.associated.setHeadRotation(defaultPitch, defaultYaw);
                }
            }
            else if (newPlayerDistance < range) {
                if (newPlayerDistance < focusedDistance || player.getUniqueId().equals(this.focused.getUniqueId())) {
                    this.lookAtPlayer(player);
                }
            }
        }
    }

    /**
     * This method does the same thing but for only one specific player.
     *
     * @param player
     */
    private void singleBehavior(Player player) {
            double playerDistance = player.getLocation().distanceSquared(this.associated.getLocation());

            if (playerDistance > this.range) {
                if (this.defaultYaw != null && this.defaultPitch != null)
                    this.associated.setHeadRotation(defaultPitch, defaultYaw);
            }
            else if (playerDistance > range) {
                Bukkit.getScheduler().runTaskLater(SpigotCommons.getInstance(), () -> this.lookAtPlayer(player), 20L);
            }
    }

    private void lookAtPlayer(Player player) {
        // logic for looking in the direction of the player.
        double dX = associated.getLocation().getX() - player.getLocation().getX();
        double dY = associated.getLocation().getY() - player.getLocation().getY();
        double dZ = associated.getLocation().getZ() - player.getLocation().getZ();

        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(SQRT.fastest(dZ * dZ + dX * dX), dY) + Math.PI;
        this.focused = player;
        this.associated.setHeadRotation((float) (-Math.toDegrees(pitch) - 90), (float) (90 + Math.toDegrees(yaw)));
    }
}

