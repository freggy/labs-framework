package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.atlantis.client.base.playerdata.PlayerdataSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

/**
 * Created by Yannic Rieger on 01.05.2017.
 * <p>
 * Base class wrapper class for the {@link Player} interface. The use of this class is to provide more specific functionality.
 * Every player specific operation that is not covered by the {@link Player} interface should be contained in a class that extends
 * this one.
 *
 * @author Yannic Rieger
 */
public class LabsPlayer {

    /**
     * Gets the {@link Player} object.
     */
    public Player getPlayer() { return  this.player; }

    /**
     * Gets whether or not the player is a spectator.
     */
    public boolean isSpectator() {
        return this.isSpectator;
    }

    /**
     * Gets whether or not the player is currently frozen.
     */
    public boolean isFrozen() { return this.isFrozen; }

    /**
     * Gets the {@link PlayerdataSet} for this player.
     */
    public Map<String, Statistic> getStatistics() {
        return dataSet;
    }

    /**
     * Sets the {@link PlayerdataSet} for this player.
     *
     * @param dataSet data set to be set.
     */
    public void setDataSet(Map<String, Statistic> dataSet) {
        this.dataSet = dataSet;
    }

    protected Player player;
    protected boolean isSpectator = false;
    protected boolean isFrozen = false;
    protected Map<String, Statistic> dataSet;

    /**
     * @param player {@link Player} to be wrapped.
     */
    public LabsPlayer(Player player) {
        this.player = player;
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
        this.isFrozen = true;
        player.setWalkSpeed(0);
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128, false, false);
        effect.apply(this.player);
    }

    /**
     * Unfreezes the player.
     */
    public void unfreeze() {
        this.isFrozen = false;
        if (this.player.hasPotionEffect(PotionEffectType.JUMP)) {
            this.player.removePotionEffect(PotionEffectType.JUMP);
        }
        player.setWalkSpeed(0.2F);
    }
}
