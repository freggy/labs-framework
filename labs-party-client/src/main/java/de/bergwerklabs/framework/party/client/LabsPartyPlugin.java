package de.bergwerklabs.framework.party.client;

import de.bergwerklabs.framework.party.client.command.PartyParentCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class LabsPartyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new PartyParentCommand("party");


    }
}
