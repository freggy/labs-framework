package de.bergwerklabs.framework.nick;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import de.bergwerklabs.framework.commons.spigot.SpigotCommons;
import de.bergwerklabs.framework.commons.spigot.entity.npc.PlayerSkin;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.entitydestroy.WrapperPlayServerEntityDestroy;
import de.bergwerklabs.framework.commons.spigot.nms.packet.serverside.namedentityspawn.v1_8.WrapperPlayServerNamedEntitySpawn;
import de.bergwerklabs.framework.commons.spigot.nms.packet.v1_8.WrapperPlayServerPlayerInfo;
import de.bergwerklabs.framework.nick.api.NickApi;
import de.bergwerklabs.framework.nick.api.NickInfo;
import de.bergwerklabs.framework.nick.api.event.NickAction;
import de.bergwerklabs.framework.nick.api.event.NickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class NickManager implements NickApi {

    private Map<UUID, NickInfo> nickedPlayers = new HashMap<>();
    private Set<String> takenNickNames        = new HashSet<>();
    private List<String> nickNames;
    private List<PlayerSkin> skins;

    /**
     *
     * @param nickNames
     * @param skins
     */
    NickManager(List<String> nickNames, List<PlayerSkin> skins) {
        this.nickNames = nickNames;
        this.skins = skins;
    }

    @Override
    public boolean isNicked(Player player) {
        return nickedPlayers.containsKey(player.getUniqueId());
    }

    @Override
    public String getRealName(Player player) {
        return nickedPlayers.get(player.getUniqueId()).getRealGameProfile().getName();
    }

    @Override
    public Set<NickInfo> getNickedPlayerInfos() {
        return new HashSet<>(nickedPlayers.values());
    }

    @Override
    public NickInfo getNickInfo(Player player) {
        return nickedPlayers.get(player.getUniqueId());
    }

    @Override
    public void removeNick(Player player) {
        NickInfo info = this.nickedPlayers.get(player.getUniqueId());
        player.setDisplayName(info.getRealGameProfile().getName());
        this.updatePlayerProfile(player, info.getRealGameProfile());
        Bukkit.getPluginManager().callEvent(new NickEvent(player, info, NickAction.REMOVE));
    }

    @Override
    public NickInfo nickPlayer(Player player) {
        String nickName = NickUtil.getUniqueNickName(this.nickNames, this.takenNickNames);
        PlayerSkin skin = this.skins.get(new Random().nextInt(this.skins.size()));

        player.setDisplayName(nickName);

        WrappedGameProfile real = WrappedGameProfile.fromPlayer(player);
        WrappedGameProfile fake = new WrappedGameProfile(player.getUniqueId(), nickName);
        skin.inject(fake);

        this.updatePlayerProfile(player, fake);

        NickInfo info = new NickInfo(real, skin, nickName);
        this.nickedPlayers.put(player.getUniqueId(), info);
        this.takenNickNames.add(info.getNickName());
        Bukkit.getPluginManager().callEvent(new NickEvent(player, info, NickAction.NICKED));
        return info;
    }


    /**
     *
     * @param player
     * @param profile
     */
    private void updatePlayerProfile(Player player, WrappedGameProfile profile) {
        WrapperPlayServerPlayerInfo playerInfoPacket = new WrapperPlayServerPlayerInfo();
        playerInfoPacket.setAction(EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        playerInfoPacket.setData(Arrays.asList(new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()), WrappedChatComponent.fromText(profile.getName()))));
        Bukkit.getOnlinePlayers().forEach(playerInfoPacket::sendPacket);

        WrapperPlayServerEntityDestroy entityDestroyPacket = new WrapperPlayServerEntityDestroy();
        entityDestroyPacket.setEntityIds(new int[] { player.getEntityId() });
        Bukkit.getOnlinePlayers().forEach(playerInfoPacket::sendPacket);

        SpigotCommons.getInstance().getProtocolManager().broadcastServerPacket(WrapperPlayServerNamedEntitySpawn.fromPlayer(player));
    }
}
