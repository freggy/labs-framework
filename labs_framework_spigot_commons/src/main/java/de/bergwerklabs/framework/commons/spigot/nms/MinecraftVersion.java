package de.bergwerklabs.framework.commons.spigot.nms;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yannic Rieger on 15.07.2017.
 * <p>  </p>
 *
 * @author Yannic Rieger
 */
public enum MinecraftVersion {
    V1_8("v1_8_R1.", "v1_8_R2.", "v1_8_R3."),
    v1_9("v1_9_R1.", "v1_9_R2."),
    v1_10("v1_10_R1."),
    v1_11("v1_11_R1."),
    v1_12("v1_12_R1.");

    private List<String> aliases;

    MinecraftVersion(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    /**
     *
     * @param version
     * @return
     */
    public static MinecraftVersion formString(String version) {
        System.out.println(version);

       return Arrays.stream(MinecraftVersion.values()).filter(v -> v.aliases.contains(version)).findFirst().get();
    }
}
