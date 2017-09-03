package de.bergwerklabs.framework.nick;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.google.common.collect.ImmutableSet;
import de.bergwerklabs.framework.commons.spigot.entity.npc.PlayerSkin;
import de.bergwerklabs.framework.nick.api.NickApi;
import de.bergwerklabs.framework.nick.api.NickInfo;
import de.bergwerklabs.framework.nick.api.event.NickAction;
import de.bergwerklabs.framework.nick.api.event.NickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class NickManager implements NickApi {

    Map<UUID, NickInfo> nickedPlayers  = new HashMap<>();
    private Set<String> takenNickNames = new HashSet<>();
    private List<String> nickNames;
    private List<PlayerSkin> skins;

    /**
     *
     * @param nickNames
     * @param skins
     */
    NickManager(List<String> nickNames, List<PlayerSkin> skins) {
        this.nickNames = nickNames;
        this.skins     = skins;
    }

    @Override
    public boolean isNicked(Player player) {
        return nickedPlayers.containsKey(player.getUniqueId());
    }

    @Override
    public String getRealName(Player player) {
        return this.nickedPlayers.get(player.getUniqueId()).getRealGameProfile().getName();
    }

    @Override
    public Set<NickInfo> getNickedPlayerInfos() {
        return ImmutableSet.copyOf(nickedPlayers.values());
    }

    @Override
    public NickInfo getNickInfo(Player player) {
        return nickedPlayers.get(player.getUniqueId());
    }

    @Override
    public void removeNick(Player player) {
        NickInfo info = this.nickedPlayers.get(player.getUniqueId());
        this.nickedPlayers.remove(player.getUniqueId());
        String realName = info.getRealGameProfile().getName();

        player.setDisplayName(realName);
        player.setCustomName(realName);

        this.resendPlayerInfo(player);
        Bukkit.getPluginManager().callEvent(new NickEvent(player, info, NickAction.REMOVE));
    }

    @Override
    public NickInfo nickPlayer(Player player) {
        String nickName = NickUtil.getUniqueNickName(this.nickNames, this.takenNickNames);
        PlayerSkin skin = this.skins.get(new Random().nextInt(this.skins.size()));

        player.setDisplayName(nickName);
        player.setCustomName(nickName);

        WrappedGameProfile real = WrappedGameProfile.fromPlayer(player);
        WrappedGameProfile fake = new WrappedGameProfile(player.getUniqueId(), nickName);
        skin.inject(fake);

        NickInfo info = new NickInfo(real, fake, skin, nickName);
        this.nickedPlayers.put(player.getUniqueId(), info);
        this.takenNickNames.add(info.getNickName());

        this.resendPlayerInfo(player);

        Bukkit.getPluginManager().callEvent(new NickEvent(player, info, NickAction.NICKED));
        return info;
    }

    /**
     *
     * @param player
     */
    private void resendPlayerInfo(Player player) {
        Bukkit.getScheduler().callSyncMethod(LabsNickPlugin.getInstance(), () -> {
            List<Player> others = Bukkit.getOnlinePlayers().stream().filter(p -> !p.getUniqueId().equals(player.getUniqueId())).collect(Collectors.toList());
            others.forEach(p -> p.hidePlayer(player));
            others.forEach(p -> p.showPlayer(player));
            return null;
        });
    }
}
