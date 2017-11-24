package de.bergwerklabs.framework.bedrock.gameserver.listener;

import de.bergwerklabs.chat.client.gameserver.LabsAsyncChatEvent;
import de.bergwerklabs.framework.bedrock.gameserver.GameserverManagement;
import de.bergwerklabs.framework.bedrock.gameserver.history.ActionType;
import de.bergwerklabs.framework.bedrock.gameserver.history.ChatAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private void onChat(LabsAsyncChatEvent event) {
        GameserverManagement.getInstance().getActionLogger().log(new ChatAction(ActionType.CHAT, event.getSender(), event.getMessage()));
    }
}
