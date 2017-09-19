package de.bergwerklabs.framework.gameservice;

import de.bergwerklabs.framework.gameservice.statistic.Statistic;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

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

    /**
     *
     */
    public boolean isSpectator() {
        return this.isSpectator;
    }

    /**
     *
     */
    public HashMap<String, Statistic> getStatistics() {
        return statistics;
    }

    protected Player player;
    private boolean isSpectator = false;
    private HashMap<String, Statistic> statistics;

    /**
     * @param player
     */
    public LabsPlayer(Player player, HashMap<String, Statistic> statistics) {
        this.player = player;
        this.statistics = statistics;
    }

    /**
     * Sets the spectator mode of a player.
     */
    public void setSpectator() {
        Bukkit.getOnlinePlayers().forEach(p -> p.hidePlayer(this.player));
        this.isSpectator = true;
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
