package de.bergwerklabs.framework.bedrock.gameserver.logging;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import de.bergwerklabs.framework.bedrock.gameserver.GameserverManagement;
import de.bergwerklabs.nick.api.NickApi;
import de.bergwerklabs.nick.api.NickInfo;
import org.bukkit.entity.Player;

/**
 * Created by Yannic Rieger on 24.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
class JsonUtil {

    static JsonObject playerToJson(Player player) {
        NickApi api = GameserverManagement.getInstance().getNickApi();
        NickInfo info = api.getNickInfo(player);
        String name, nick = "null";

        if (info != null) {
            name = api.getRealName(player);
            nick = info.getNickName();
        }
        else name = player.getDisplayName();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("uuid", new JsonPrimitive(player.getUniqueId().toString()));
        jsonObject.add("name-at-that-timestamp", new JsonPrimitive(name));
        jsonObject.add("nickname", new JsonPrimitive(nick));
        return jsonObject;
    }


}
