package de.bergwerklabs.commons.spigot.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yannic Rieger on 23.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public class CenteredFancyMessageBuilder {

    private Player player;
    private String header = "&6&m------------------------";
    private String footer = "&6&m------------";
    private ChatColor footerColor = ChatColor.AQUA;
    private ChatColor headerColor = ChatColor.AQUA;
    private List<String> text;
    private List<Player> players;
    private boolean isCentered = false;

    public CenteredFancyMessageBuilder(Player player) {
        this.player = player;
    }

    public CenteredFancyMessageBuilder() {}

    /**
     *
     * @param color
     * @return
     */
    public CenteredFancyMessageBuilder setFooterColor(ChatColor color) {
        this.footerColor = color;
        return this;
    }

    /**
     *
     * @param color
     * @return
     */
    public CenteredFancyMessageBuilder setHeaderColor(ChatColor color) {
        this.headerColor = color;
        return this;
    }

    /**
     *
     * @param header
     * @return
     */
    public CenteredFancyMessageBuilder setHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     *
     * @param footer
     * @return
     */
    public CenteredFancyMessageBuilder setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    /**
     *
     * @param player
     * @return
     */
    public CenteredFancyMessageBuilder setPlayer(Player player) {
        this.player = player;
        return this;
    }

    /**
     *
     * @param players
     * @return
     */
    public CenteredFancyMessageBuilder setPlayers(Player... players) {
        this.players = Arrays.asList(players);
        return this;
    }

    /**
     *
     *
     * @param isCentered
     * @param text
     * @return
     */
    public CenteredFancyMessageBuilder setText(boolean isCentered, String... text) {
        this.isCentered = true;
        this.text = Arrays.asList(text);
        return this;
    }

    /**
     *
     */
    public void send() {
        if (this.players != null) {
            this.players.forEach(player -> {
                MessageUtil.sendCenteredTextWithHeaderAndFooter(player, this.headerColor + this.header, this.footerColor + this.footer,  this.isCentered, this.text);
            });
        }
        else MessageUtil.sendCenteredTextWithHeaderAndFooter(this.player, this.headerColor + this.header, this.footerColor + this.footer,  this.isCentered, this.text);
    }
}
