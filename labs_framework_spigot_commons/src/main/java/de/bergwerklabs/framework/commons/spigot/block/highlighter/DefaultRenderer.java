package de.bergwerklabs.framework.commons.spigot.block.highlighter;

import de.bergwerklabs.framework.commons.spigot.LabsFrameworkSpigotCommons;
import de.bergwerklabs.util.effect.Particle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by Yannic Rieger on 02.07.2017.
 * <p> Default implementation of the {@link BlockHighlightRenderer}.
 *
 * @author Yannic Rieger
 */
public class DefaultRenderer extends BlockHighlightRenderer {

    DefaultRenderer(Vector min, Vector max)  {
        super(min, max);
    }

    @Override
    public void highlight(Facing facing) {
        if (facing == Facing.COMPLETE) {
            this.highlightComplete();
        }
    }

    /**
     * Highlights the given block completely.
     */
    private void highlightComplete() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(LabsFrameworkSpigotCommons.getInstance(), () -> {
            for (Double x = minPoint.getX(); x < maxPoint.getX(); x += .1) {
                for (Double z = minPoint.getZ(); z < maxPoint.getZ(); z += .1) {
                    for (Double y = minPoint.getY(); y < maxPoint.getY(); y += .1) {

                        String stringX = String.valueOf(String.valueOf(x).split("\\.")[1].split("")[0]);
                        String stringY = String.valueOf(String.valueOf(y).split("\\.")[1].split("")[0]);
                        String stringZ = String.valueOf(String.valueOf(z).split("\\.")[1].split("")[0]);

                        int xDec = Integer.valueOf(stringX);
                        int yDec = Integer.valueOf(stringY);
                        int zDec = Integer.valueOf(stringZ);

                        if (yDec == 9) {
                            highlightBaseArea(xDec, zDec, new Location(Bukkit.getWorld("spawn"), x, y, z));
                        }
                        else if (yDec == 0) {
                            highlightBaseArea(xDec, zDec, new Location(Bukkit.getWorld("spawn"), x, y, z));
                        }
                        else {
                            highlightY(xDec, zDec, new Location(Bukkit.getWorld("spawn"), x, y, z));
                        }
                    }
                }
            }
        }, 0, 1L);
    }

    /**
     * Highlights 2 vertical edges of the block on the same side.
     *
     * @param rX X coordinate relative to the block.
     * @param rZ Z coordinate relative to the block.
     * @param absolute {@link Location} of the block.
     */
    private void highlightBaseArea(int rX, int rZ, Location absolute) {
        if (rZ <= 9 && rX == 0) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute,
                                  0F, 0F, 0F, 0F, 1);
        }

        if (rX <= 9 && rZ == 0) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute,
                                  0F, 0F, 0F, 0F, 1);
        }

        if (rX <= 9 && rZ == 9) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE,  absolute,
                                  0F, 0F, 0F, 0F, 1);
        }

        if (rZ <= 9 && rX == 9) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute.add(0.14, 0, 0),
                                  0F, 0F, 0F, 0F, 1);
        }
    }

    /**
     * Highlights one horizontal edge of the block
     *
     * @param rX X coordinate relative to the block.
     * @param rZ Z coordinate relative to the block.
     * @param absolute {@link Location} of the block.
     */
    private void highlightY(int rX, int rZ, Location absolute) {
        if (rX == 0 && rZ == 0) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute,
                                  0F, 0F, 0F, 0F, 1);
        }
        if (rX == 9 && rZ == 0) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute.add(0.14, 0, 0),
                                  0F, 0F, 0F, 0F, 1);
        }

        if (rX == 0 && rZ == 9) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute,
                                  0F, 0F, 0F, 0F, 1);
        }
        if (rX == 9 && rZ == 9) {
            Particle.broadcastParticle(Bukkit.getOnlinePlayers(), Particle.ParticleEffect.REDSTONE, absolute.add(0.14, 0, 0),
                                  0F, 0F, 0F, 0F, 1);
        }
    }
}
