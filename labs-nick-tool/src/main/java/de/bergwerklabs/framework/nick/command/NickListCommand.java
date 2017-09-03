package de.bergwerklabs.framework.nick.command;

import de.bergwerklabs.framework.nick.LabsNickPlugin;
import de.bergwerklabs.framework.nick.api.NickInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class NickListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equals("nicklist")) { // TODO: check if player can nick
            if (commandSender instanceof Player) {

                Player player = (Player) commandSender;
                player.sendMessage("§6>> §eNick §6❘ §7Liste aller zurzeit genickten Spieler:");

                Set<NickInfo> nickInfos = LabsNickPlugin.getInstance().getNickApi().getNickedPlayerInfos();

                if (nickInfos.size() != 0) {
                    LabsNickPlugin.getInstance().getNickApi().getNickedPlayerInfos().forEach(info -> {
                        player.sendMessage("§a■ §f" + info.getRealGameProfile().getName() + " §b➟ §f" + info.getNickName());
                    });
                }
                else player.sendMessage("§6>> §eNick §6❘ §cEs sind keine Spieler genickt.");

            }
            return true;
        }
        return false;
    }
}
