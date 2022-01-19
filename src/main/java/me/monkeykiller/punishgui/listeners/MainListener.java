package me.monkeykiller.punishgui.listeners;

import me.monkeykiller.punishgui.Utils;
import me.monkeykiller.punishgui.menus.TargetSelectionMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class MainListener implements Listener {
    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        if (!Utils.isGUIOpener(event.getItem())) return;
        event.setCancelled(true);
        new TargetSelectionMenu(event.getPlayer()).openMenu(0);
    }
}
