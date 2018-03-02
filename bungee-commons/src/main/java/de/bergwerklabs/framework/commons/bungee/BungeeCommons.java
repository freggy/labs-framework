package de.bergwerklabs.framework.commons.bungee;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Yannic Rieger on 28.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BungeeCommons extends Plugin {

    private static BungeeCommons instance;

    public static BungeeCommons getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }
}
