package de.bergwerklabs.framework.nick.command;

import de.bergwerklabs.framework.nick.LabsNickPlugin;
import de.bergwerklabs.framework.nick.api.NickApi;
import de.bergwerklabs.framework.nick.api.NickInfo;
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
public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (s.equals("nick")) { // TODO: check if player can nick
            if (commandSender instanceof Player) {
                NickApi api = LabsNickPlugin.getInstance().getNickApi();
                Player player = (Player) commandSender;

                if (api.isNicked(player)) {
                    api.removeNick(player);
                    player.sendMessage("§6>> §eNick §6❘ §7Du bist nun §centnickt!");
                    return true;
                }

                NickInfo info = LabsNickPlugin.getInstance().getNickApi().nickPlayer(player);
                player.sendMessage("§6>> §eNick §6❘ §7Dein Nickname lautet nun §b" + info.getNickName());
                player.sendMessage("§6>> §eNick §6❘ §7Führe §b/nick §7aus um ihn zu entfernen.");
                return true;
            }
        }
        return false;
    }
}
