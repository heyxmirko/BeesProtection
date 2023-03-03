package me.heyxmirko.beesprotection.listeners;

import me.heyxmirko.beesprotection.BeesProtection;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class BeesEvent implements Listener {

    private final BeesProtection plugin;
    public BeesEvent(BeesProtection beesProtection) {
        this.plugin = beesProtection;
    }

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Bee) {
            Location interactLocation = event.getPlayer().getLocation();
            if(!plugin.isLocationAllowed(interactLocation)) {
                event.getPlayer().sendMessage(Component.text(ChatColor.RED + "You can't interact with bees on spawn!"));
                event.setCancelled(true);
            }
        }
    }
}
