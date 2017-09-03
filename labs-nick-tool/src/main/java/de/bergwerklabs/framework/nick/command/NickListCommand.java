package de.bergwerklabs.framework.nick.command;

import de.bergwerklabs.framework.nick.LabsNickPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                player.sendMessage("§6>> §eNick &6❘ §7Liste aller zurzeit genickten Spieler:");

                LabsNickPlugin.getInstance().getNickApi().getNickedPlayerInfos().forEach(info -> {
                    player.sendMessage("§a■ " + info.getRealGameProfile().getName() + " §b➟ " + info.getNickName());
                });
            }
            return true;
        }
        return false;
    }
}
