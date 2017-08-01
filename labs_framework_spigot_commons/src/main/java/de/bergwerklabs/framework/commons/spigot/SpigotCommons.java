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
import de.bergwerklabs.framework.commons.spigot.nms.packet.clientside.useentity.v1_8.WrapperPlayClientUseEntity;
import de.bergwerklabs.framework.commons.spigot.entity.npc.Npc;
import de.bergwerklabs.framework.commons.spigot.npc.NpcManager;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.Action;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.NpcInteractAtEvent;
import de.bergwerklabs.framework.commons.spigot.entity.npc.event.NpcInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by Yannic Rieger on 02.05.2017.
 * <p> Main class </p>
 * @author Yannic Rieger
 */
public class SpigotCommons extends JavaPlugin implements Listener, LabsController {

    /**
     *
     */
    public ProtocolManager getProtocolManager() { return this.protocolManager; }

    /**
     *
     */
    public HashSet<UUID> getJoiningPlayers() { return this.joiningPlayers; }

    /**
     * Gets the instance of the Framework Plugin.
     */
    public static SpigotCommons getInstance() { return instance; }

    /**
     *
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

                    if (entity instanceof Npc) {
                        Npc npc = (Npc)entity;
                        Action action = this.determineAction(useEntityPacket.getType());

                        // Nasty hack to stop it from executing the event twice
                        if (previous != action) {
                            this.previous = action;
                            if (action != Action.INTERACT_AT) {
                                Bukkit.getScheduler().callSyncMethod(SpigotCommons.getInstance(), () -> {
                                    Bukkit.getServer().getPluginManager().callEvent(new NpcInteractEvent(npc, event.getPlayer(), action));
                                    return null;
                                });
                            }
                            else {
                                Bukkit.getScheduler().callSyncMethod(SpigotCommons.getInstance(), () -> {
                                    Bukkit.getServer().getPluginManager().callEvent(new NpcInteractAtEvent(npc, event.getPlayer(), action, useEntityPacket.getTargetVector()));
                                    return null;
                                });
                            }
                        }
                    }
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
    }


}
