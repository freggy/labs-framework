package de.bergwerklabs.framework.commons.spigot.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public class LabsPlayer {

    /**
     *
     */
    public Player getPlayer() { return  this.player; }

    protected Player player;

    /**
     * @param player
     */
    public LabsPlayer(Player player) {
        this.player = player;
    }

    /**
     * Sets the spectator mode of a player.
     */
    public void setSpectator() {
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(this.player));
    }

    /**
     * Freezes the player completely.
     */
    public void freeze() {
        player.setWalkSpeed(0);
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128, false, false);
        effect.apply(this.player);
    }

    /**
     * Unfreezes the player.
     */
    public void unfreeze() {
        if (this.player.hasPotionEffect(PotionEffectType.JUMP)) {
            this.player.removePotionEffect(PotionEffectType.JUMP);
        }
        player.setWalkSpeed(0.2F);
    }
}
