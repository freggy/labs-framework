package de.bergwerklabs.framework.commons.spigot.game;

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
     * Freezes the player completely.
     *
     * @param player Player to freeze.
     * @param ticks ticks the player shouldn't be able to jump.
     */
    public static void freeze(Player player, int ticks) {
        player.setWalkSpeed(0);
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, ticks, 128, false, false);
        effect.apply(player);
    }

    /**
     * Unfreezes the player.
     *
     * @param player Player to unfreeze.
     */
    public static void unfreeze(Player player) {
        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
        player.setWalkSpeed(0.2F);
    }
}
