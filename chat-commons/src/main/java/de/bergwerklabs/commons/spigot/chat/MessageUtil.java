package de.bergwerklabs.commons.spigot.chat;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Provides methods for text message handling in Minecraft using the Spigot API.
 *
 * @author Yannic Rieger
 */
public class MessageUtil {

    private final static int CENTER_PX = 154;
    private final static int MAX_PX    = 250;

    /**
     * Centers text and send it to the given player.
     * <b>NOTE:</b> This may work with custom texture packs
     *
     * @param player  Player to send the message to.
     * @param message Message to be sent.
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
     * Centers text and send it to the given player.
     * <b>NOTE:</b> This may work with custom texture packs
     *
     * @param player   Player to send the messages to.
     * @param messages Messages to be sent.
     */
    public static void sendCenteredMessages(Player player, String... messages) {
        Arrays.stream(messages).forEach(message -> sendCenteredMessage(player, message));
    }

    /**
     * Centers text and send it to the given player.
     * <b>NOTE:</b> This may work with custom texture packs
     *
     * @param player   Player to send the messages to.
     * @param messages Messages to be sent.
     */
    public static void sendCenteredMessages(Player player, Collection<String> messages) {
        messages.forEach(message -> sendCenteredMessage(player, message));
    }

    /**
     * Send a message in the following format:
     * <pre>
     *     --------[HEADER]-------- <- always centered
     *              [TEXT]          <- can be centered
     *       ------[FOOTER]------   <- always centered
     * </pre>
     *
     * if the header or footer is null or empty only a line will be displayed.
     *
     * @param player Player to send the message to.
     * @param header Header
     * @param footer Footer
     * @param textCentered Determines whether or not the text should be centerd.
     * @param text Text to display.
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
     * Send a message in the following format:
     * <pre>
     *     --------[HEADER]-------- <- always centered
     *              [TEXT]          <- can be centered
     *       ------[FOOTER]------   <- always centered
     * </pre>
     *
     * if the header or footer is null or empty only a line will be displayed.
     *
     * @param player Player to send the message to.
     * @param header Header
     * @param footer Footer
     * @param textCentered Determines whether or not the text should be centerd.
     * @param text Text to display.
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
     * Checks if the footer is null or empty.
     *
     * @param footer footer to check.
     * @return a new string
     */
    private static String checkFooter(String footer) {
        if (Strings.isNullOrEmpty(footer)) return "&6&m------------"; // 12 '-'
        else return new StringBuilder(footer).insert(0, "&6&m------").append("&6&m------").toString(); // ------footer------
    }

    /**
     * Checks if the footer is null or empty.
     *
     * @param header header to check.
     * @return a new string
     */
    private static String checkHeader(String header) {
        if (Strings.isNullOrEmpty(header)) return "&6&m------------------------"; // 24 '-'
        else return new StringBuilder(header).insert(0, "&6&m------------").append("&6&m------------").toString(); // ------------header------------
    }
}
