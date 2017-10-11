package de.bergwerklabs.framework.commons.spigot;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.wrappers.EnumWrappers;
import de.bergwerklabs.framework.commons.spigot.entity.Entity;
import de.bergwerklabs.framework.commons.spigot.entity.EntityManager;
import de.bergwerklabs.framework.commons.spigot.file.FileUtil;
import de.bergwerklabs.framework.commons.spigot.general.LabsController;
import de.bergwerklabs.framework.commons.spigot.nms.packet.clientside.v1_8.useentity.WrapperPlayClientUseEntity;
import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import de.bergwerklabs.framework.commons.spigot.npc.NpcManager;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.Action;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.NpcInteractAtEvent;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.NpcInteractEvent;
import de.bergwerklabs.framework.commons.spigot.pluginmessage.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashSet;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p>
 * Main class
 *
 * @author Yannic Rieger
 */
public class SpigotCommons extends JavaPlugin implements Listener, LabsController, PluginMessageListener {

    /**
     * Gets a {@link ProtocolManager}.
     */
    public ProtocolManager getProtocolManager() { return this.protocolManager; }

    /**
     * Contains all joining players. A player will be hold in this list for approx. 2 seconds.
     */
    public HashSet<UUID> getJoiningPlayers() { return this.joiningPlayers; }

    /**
     * Gets the instance of the Framework Plugin.
     */
    public static SpigotCommons getInstance() { return instance; }

    /**
     * Prefix for console output.
     */
    public final String CONSOLE_PREFIX = "[SpigotCommons] ";

    private static SpigotCommons instance;
    private ProtocolManager protocolManager;
    private HashSet<UUID> joiningPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Just for test purposes
        FileUtil.createFolderIfNotExistent(this.getDataFolder());
        instance = this;

        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new NpcManager(), this);
        this.getServer().getPluginManager().registerEvents(new EntityManager(), this);

        this.protocolManager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.USE_ENTITY) {
            private Action previous = null;

            @Override
            public void onPacketReceiving(PacketEvent event) {
                WrapperPlayClientUseEntity useEntityPacket = new WrapperPlayClientUseEntity(event.getPacket());
                int id = useEntityPacket.getTargetID();
                if (EntityManager.getEntities().keySet().contains(id)) {
                    Entity entity = EntityManager.getEntities().get(id);

//                    if (entity instanceof Npc) {
//                        Npc npc = (Npc)entity;
//                        Action action = this.determineAction(useEntityPacket.getType());
//
//                        // Nasty hack to stop it from executing the event twice
//                        if (previous != action || previous == Action.HIT) {
//                            this.previous = action;
//                            if (action != Action.INTERACT_AT) {
//                                Bukkit.getScheduler().callSyncMethod(SpigotCommons.getInstance(), () -> {
//                                    Bukkit.getServer().getPluginManager().callEvent(new NpcInteractEvent(npc, event.getPlayer(), action));
//                                    return null;
//                                });
//                            }
//                            else {
//                                Bukkit.getScheduler().callSyncMethod(SpigotCommons.getInstance(), () -> {
//                                    Bukkit.getServer().getPluginManager().callEvent(new NpcInteractAtEvent(npc, event.getPlayer(), action, useEntityPacket.getTargetVector()));
//                                    return null;
//                                });
//                            }
//                        }
//                    }
                }
            }

            private Action determineAction(EnumWrappers.EntityUseAction action) {
                switch (action) {
                    case INTERACT: return Action.RIGHT_CLICK;
                    case ATTACK: return Action.HIT;
                    case INTERACT_AT: return Action.INTERACT_AT;
                    default: return null;
                }
            }
        });
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }


    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        this.joiningPlayers.add(uuid);
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> this.joiningPlayers.remove(uuid), 40);


        PacketContainer container = SpigotCommons.getInstance().getProtocolManager().createPacket(PacketType.Play.Server.WORLD_PARTICLES);

        container.getParticles().write(0, EnumWrappers.Particle.CLOUD);
        container.getFloat().write(0, ); // X
        container.getFloat().write(1, ); // Y
        container.getFloat().write(2, ); // Z
        container.getFloat().write(3, ); // OFFSET X
        container.getFloat().write(4, ); // OFFSET Y
        container.getFloat().write(5, ); // OFFSET Z
        container.getFloat().write(6, ); // DATA
        container.getIntegers().write(0, 2); // COUNT
        container.getBooleans().write(0, true); // LONG DISTANCE
        SpigotCommons.getInstance().getProtocolManager().sendServerPacket(player, container);
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {}
}
