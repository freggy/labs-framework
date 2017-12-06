package de.bergwerklabs.framework.commons.bungee.chat.text;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Yannic Rieger on 21.09.2017.
 * <p>
 * Provides methods for text message handling in Minecraft using the Bungee API.
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
    public static void sendCenteredMessage(ProxiedPlayer player, String message) {
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

        player.sendMessage(ChatMessageType.CHAT, TextComponent.fromLegacyText(sb.toString() + message));
        if(toSendAfter != null) sendCenteredMessage(player, toSendAfter);
    }

    /**
     * Returns the spaces needed to center a message.
     *
     * @param message message that should be centred.
     * @return        the spaces needed to center a message.
     */
    public static int getSpacesToCenter(String message) {
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
        int spaces = 0;

        while(compensated <= toCompensate){
            spaces++;
            compensated += spaceLength;
        }

        return spaces;
    }

    /**
     * Centers text and send it to the given player.
     * <b>NOTE:</b> This may not work with custom texture packs
     *
     * @param player   Player to send the messages to.
     * @param messages Messages to be sent.
     */
    public static void sendCenteredMessages(ProxiedPlayer player, String... messages) {
        Arrays.stream(messages).forEach(message -> sendCenteredMessage(player, message));
    }

    /**
     * Centers text and send it to the given player.
     * <b>NOTE:</b> This may not work with custom texture packs
     *
     * @param player   Player to send the messages to.
     * @param messages Messages to be sent.
     */
    public static void sendCenteredMessages(ProxiedPlayer player, Collection<String> messages) {
        messages.forEach(message -> sendCenteredMessage(player, message));
    }
}
