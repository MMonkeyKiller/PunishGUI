package me.monkeykiller.punishgui.menus;

import com.dndcraft.menu.MenuBuilder;
import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TargetSelectionMenu {
    private final Player player;

    public TargetSelectionMenu(@NotNull Player player) {
        this.player = player;
    }

    private static int getPageCount() {
        return (int) Math.ceil(Bukkit.getOnlinePlayers().size() / (9 * 5f));
    }

    public void openMenu(int page) {
        page = Math.max(0, Math.min(page, getPageCount() - 1));
        if (!player.hasPermission("punishgui.opengui")) {
            Utils.errorMsg("You don't have permissions to open the gui!").send(player);
            return;
        }
        MenuBuilder menu = new MenuBuilder(Utils.colorize("&7Select Target - Page " + (page + 1)), 6);
        var players = new ArrayList<>(Bukkit.getOnlinePlayers()).subList(page * 9 * 5, Math.min((page + 1) * 9 * 5, Bukkit.getOnlinePlayers().size()));
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            try {
                var item = ItemUtil.make(Material.PLAYER_HEAD, Component.text(p.getName(), AtlasColor.YELLOW.toTextColor()));
                item.editMeta(meta -> {
                    if (meta instanceof SkullMeta skullMeta) skullMeta.setPlayerProfile(p.getPlayerProfile());
                });
                menu.icon(i, item, (menuAction) -> PunishMenu.openMenu(player, p));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int finalPage = page;
        menu.icon(9 * 5, ItemUtil.make(Material.ARROW, Component.text("Back", AtlasColor.YELLOW.toTextColor()).decoration(TextDecoration.ITALIC, false)), (menuAction) -> openMenu(finalPage - 1));
        menu.icon(9 * 5 + 8, ItemUtil.make(Material.ARROW, Component.text("Next", AtlasColor.YELLOW.toTextColor()).decoration(TextDecoration.ITALIC, false)), (menuAction) -> openMenu(finalPage + 1));

        menu.build().openSession(player);
    }
}
