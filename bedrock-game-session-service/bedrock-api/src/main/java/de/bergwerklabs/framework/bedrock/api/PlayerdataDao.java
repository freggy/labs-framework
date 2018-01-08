package de.bergwerklabs.framework.bedrock.api;

import de.bergwerklabs.framework.nabs.standalone.PlayerdataFactory;
import de.bergwerklabs.framework.nabs.standalone.PlayerdataSet;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yannic Rieger on 08.01.2018.
 * <p>
 *
 * @author Yannic Rieger
 */
public class PlayerdataDao {

    private Map<UUID, PlayerdataSet> playerdataCache = new HashMap<>();
    private PlayerdataFactory factory;

    public PlayerdataDao(PlayerdataFactory factory) {
        this.factory = factory;
    }

    public PlayerdataSet load(UUID uuid) {
        return playerdataCache.put(uuid, factory.createInstance(uuid));
    }

    public PlayerdataSet remove(UUID uuid) {
        return playerdataCache.remove(uuid);
    }

    public void setStatistic(UUID player, String key, Object value, String group) {
        PlayerdataSet set = this.playerdataCache.get(player);
        if (set != null) {
            set.getGroup(group).set(key, value);
            set.save();
        }
    }

    public Object getStatistic(UUID player, String key, Object defaultValue, String group) {
        PlayerdataSet set = this.playerdataCache.get(player);
        if (set != null) {
            return set.getGroup(group).getObject(key, defaultValue);
        }
        return defaultValue;
    }
}
