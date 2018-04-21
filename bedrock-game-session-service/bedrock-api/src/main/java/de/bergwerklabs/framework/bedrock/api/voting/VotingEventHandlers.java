package de.bergwerklabs.framework.bedrock.api.voting;

import de.bergwerklabs.framework.commons.spigot.item.ItemStackUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Yannic Rieger on 08.02.2018.
 *
 * <p>
 *
 * @author Yannic Rieger
 */
public class VotingEventHandlers implements Listener {

  private MapVoting voting;
  private Map<UUID, MapEntry> votedFor = new HashMap<>();

  VotingEventHandlers(@NotNull MapVoting voting) {
    this.voting = voting;
  }

  @EventHandler
  private void onInventoryClick(InventoryClickEvent event) {
    event.setCancelled(true);
    // TODO: add color to name string
    if (!event.getClickedInventory().getTitle().equalsIgnoreCase("§bMap auswahl")) return;

    ItemStack clicked = event.getCurrentItem();

    if (clicked == null) return;

    Player player = (Player) event.getWhoClicked();

    List<MapEntry> entries =
        this.voting
            .getPickedMaps()
            .stream()
            .filter(entry -> ItemStackUtil.equals(entry.getDisplayItem(), clicked))
            .collect(Collectors.toList());

    if (entries.size() != 0) {
      MapEntry voted = entries.get(0);
      UUID uuid = player.getUniqueId();

      MapEntry lastVoted = this.votedFor.get(uuid);
      if (lastVoted != null) lastVoted.removeVoter(uuid);

      voted.addVoter(player.getUniqueId());
      this.votedFor.put(player.getUniqueId(), voted);
      this.voting
          .getMessenger()
          .message("Du hast für §b" + voted.getName() + " §7gestimmt.", player);
    }
  }

  @EventHandler
  private void onPlayerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    this.voting
        .getPickedMaps()
        .stream()
        .filter(entry -> entry.getVoters().contains(player.getUniqueId()))
        .forEach(entry -> entry.removeVoter(player.getUniqueId()));
  }

  @EventHandler
  private void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (player.getItemInHand().getType() == Material.COMPASS) {
      player.openInventory(this.voting.getVotingInventory());
    }
  }
}
