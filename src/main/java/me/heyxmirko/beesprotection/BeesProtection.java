package me.heyxmirko.beesprotection;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.heyxmirko.beesprotection.listeners.BeesEvent;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BeesProtection extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin has been loaded!");
        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new BeesEvent(this), this);
        getLogger().info("Registered listeners!");
    }


    public boolean isLocationAllowed(Location location) {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        WorldGuardPlugin worldGuard = (WorldGuardPlugin) plugin;

        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(location.getWorld()));
        ProtectedRegion warzoneRegion = regionManager.getRegion("warzone");

        if(location.getWorld().getName().equals("world_events")) return false;

        return warzoneRegion != null && !warzoneRegion.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin has been unloaded!");
    }
}
