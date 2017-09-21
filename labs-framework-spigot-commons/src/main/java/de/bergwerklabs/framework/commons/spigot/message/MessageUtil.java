package de.bergwerklabs.framework.commons.spigot.message;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class MessageUtil {

    private final static int CENTER_PX = 154;
    private final static int MAX_PX = 250;

    /**
     *
     * @param player
     * @param message
     */
    public static void sendCenteredMessage(Player player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        boolean previousCode = false;
        boolean isBold = false;

        double messagePxSize = 0;
        int charIndex = 0;
        int lastSpaceIndex = 0;

        String toSendAfter = null;
        String recentColorCode = "";

        for(char c : message.toCharArray()) {
            if(c == '§'){
                previousCode = true;
                continue;
            }
            else if(previousCode) {

                previousCode = false;
                recentColorCode = "§" + c;

                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }
                else isBold = false;
            }
            else if(c == ' ') {
                lastSpaceIndex = charIndex;
            }
            else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                if (c == '-') {
                    messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength() - 1;
                    messagePxSize++;
                }
                else {
                    messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                    messagePxSize++;
                }
            }

            if(messagePxSize >= MAX_PX){
                toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1, message.length());
                message = message.substring(0, lastSpaceIndex + 1);
                break;
            }
            charIndex++;
        }

        double halvedMessageSize = messagePxSize / 2;
        double toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;

        StringBuilder sb = new StringBuilder();

        while(compensated <= toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }

        player.sendMessage(sb.toString() + message);
        if(toSendAfter != null) sendCenteredMessage(player, toSendAfter);
    }

    /**
     *
     *
     * @param player
     * @param messages
     */
    public static void sendCenteredMessages(Player player, String... messages) {
        Arrays.stream(messages).forEach(message -> sendCenteredMessage(player, message));
    }

    /**
     *
     *
     * @param player
     * @param messages
     */
    public static void sendCenteredMessages(Player player, Collection<String> messages) {
        messages.forEach(message -> sendCenteredMessage(player, message));
    }

    /**
     *
     *
     * @param player
     * @param header
     * @param footer
     * @param textCentered
     * @param text
     */
    public static void sendCenteredTextWithHeaderAndFooter(Player player, String header, String footer, boolean textCentered, String... text) {
        header = checkHeader(header);
        footer = checkFooter(footer);

        sendCenteredMessage(player, header);

        if (textCentered) sendCenteredMessages(player, text);
        else Arrays.stream(text).forEach(player::sendMessage);

        sendCenteredMessage(player, footer);
    }

    /**
     *
     *
     * @param player
     * @param header
     * @param footer
     * @param textCentered
     * @param text
     */
    public static void sendCenteredTextWithHeaderAndFooter(Player player, String header, String footer, boolean textCentered, Collection<String> text) {
        header = checkHeader(header);
        footer = checkFooter(footer);

        sendCenteredMessage(player, header);

        if (textCentered) sendCenteredMessages(player, text);
        else text.forEach(player::sendMessage);

        sendCenteredMessage(player, footer);
    }

    /**
     *
     *
     * @param footer
     * @return
     */
    private static String checkFooter(String footer) {
        if (Strings.isNullOrEmpty(footer)) return "&6&m------------"; // 12 '-'
        else return new StringBuilder(footer).insert(0, "&6&m------").append("&6&m------").toString(); // ------footer------
    }

    /**
     *
     *
     * @param header
     * @return
     */
    private static String checkHeader(String header) {
        if (Strings.isNullOrEmpty(header)) return "&6&m------------------------"; // 24 '-'
        else return new StringBuilder(header).insert(0, "&6&m------------").append("&6&m------------").toString(); // ------------header------------
    }
}
