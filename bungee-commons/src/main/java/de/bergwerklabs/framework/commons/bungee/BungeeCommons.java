package de.bergwerklabs.framework.commons.bungee;

import de.bergwerklabs.framework.commons.bungee.permissions.RankInfo;
import de.bergwerklabs.framework.commons.bungee.permissions.ZBridge;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.UUID;


/**
 * Created by Yannic Rieger on 28.11.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class BungeeCommons extends Plugin {

    @Override
    public void onEnable() {
        ZBridge z = new ZBridge("admin", "LphX3VULzQVgp2ry3f2ypkZKE5YeufMtaamfeNNNwZbLWyqm");
        RankInfo info = z.getRankInfo(UUID.fromString("99f5efbb-046f-4086-9a57-647959953d1f"));
        RankInfo inf2 = z.getRankInfo(UUID.fromString("08fd05e5-981e-4594-ae54-c85faf99b1f2"));
        System.out.println(info.getRankName());
        System.out.println(info.getGroup());
        System.out.println(inf2.getRankName());
        System.out.println(inf2.getGroup());
    }
}
