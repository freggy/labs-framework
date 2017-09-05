package de.bergwerklabs.framework.nick;

import de.bergwerklabs.framework.commons.spigot.entity.npc.PlayerSkin;

import java.util.*;

/**
 * Created by Yannic Rieger on 03.09.2017.
 * <p>
 * Contains some useful utilities.
 *
 * @author Yannic Rieger
 */
class NickUtil {

    /**
     * Retrieves all nicknames available.
     *
     * @return {@link List} of nicknames
     */
    static List<String> retrieveNickNames() {
        return Arrays.asList("hello", "duno", "wtf");
    }

    /**
     * Gets all skins available as {@link PlayerSkin}s.
     *
     * @return {@link List} of {@link PlayerSkin}.
     */
    static List<PlayerSkin> retrieveSkins() {
        return Arrays.asList(new PlayerSkin("eyJ0aW1lc3RhbXAiOjE1MDQ0Mzg5MzM1MjQsInByb2ZpbGVJZCI6ImE1NzI1ZWQ2OGNlNzQ3ODliNjhiMzA1NWE3NmM3YWZlIiwicHJvZmlsZU5hbWUiOiIxMTFCaXRlXyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjhiNGI1NmU5MmM1NTIyNmEyNTFlZWM2MTdkYjlkYTZmY2IyMzFmZjg5NjA2OGJjYjc1MjFkYzVkNWFmYWUifX19", "d54xWysNFt7G818FNXvo7ZSk83r1FADaj0kMYwTXjVqAq6dl8pugX+0gLmtm5hX2RI/v6OQLiSCZlFII9QngsgiWVfFzyKxoIgl1ehpGJ/QyLx3KyUtXEAoxroJHkH1jsK716FOBx5BuYgVOuuh0+NIF0Tb7Eo0tItkKyiH1LYqA4KWjY1DAsVQUulXn8UqGhikdl/w1+d9I+eqtjbSCI8qJR6GLuWw/nK1P4unWIKLa7DFyinu+JVUFnaMAP4Ktd1p7UtfkGJGqGqYgxbRy/s7VPdsd/+7KJXOs/69uHvmJSY0gV9/dnsqYf5cQ4yBnuhMon7dfavpYDlh9Vgg4w3D0ZsxL6J2Q0FDAfjGP9ErGfjOEayj8yh0ms10PqqA/1UDMHWSZTq5nlhZjZMZdfVevMppsYnnNb3PnY1YWEyS3K9c0WyR4BcDeyjzHX/F0OXqrp5PA0mHyW1xl1f5X2/kdqbq9caLtywClmAeHEjn8SzfTCp7UloARnU8prV+N8YLV+6tcQQjCiKVuyInPBxZFIZ5NG1hq1cO2EAp862WNVjicIZ79PSrXPm3bPdqKuaztwAbWIzESZqvkls/gDwJ1zJ4uRCegGJbnwLHCkKW5PYDHOWGJk7ND8MvdyfNOgFYM3jh0XMSPrSizliDuCKZ3a8xjUJ9kiTfDHsgUYws="));
    }

    /**
     * Gets a unique nick name which is contained in availableNickNames but not in takenNickNames.
     *
     * @param availableNickNames {@link List} of available nicknames.
     * @param takenNickNames     {@link List} of taken nicknames.
     * @return a unique nick name which is contained in availableNickNames but not in takenNickNames.
     */
    static String getUniqueNickName(List<String> availableNickNames, Set<String> takenNickNames) {
        Random random = new Random();
        String selected;

        do {
            selected = availableNickNames.get(random.nextInt(availableNickNames.size()));
        }
        while (takenNickNames.contains(selected));

        return selected;
    }
}
