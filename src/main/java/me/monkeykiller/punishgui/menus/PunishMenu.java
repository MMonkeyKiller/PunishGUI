package me.monkeykiller.punishgui.menus;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.dndcraft.menu.MenuBuilder;
import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import me.monkeykiller.punishgui.punishes.Punish;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PunishMenu {
    public static void openMenu(@NotNull Player player, @NotNull Player target) {
        if (!player.hasPermission("punishgui.opengui")) {
            Utils.errorMsg("You don't have permissions to open the gui!").send(player);
            return;
        }
        var punishes = Punish.getRegistry();
        MenuBuilder menu = new MenuBuilder(Utils.colorize("&8Punishing &a" + target.getName()), (int) (1 + Math.ceil(punishes.size() / 9f)));
        for (int i = 0; i < punishes.size(); i++) {
            Punish punish = punishes.get(i);
            try {
                menu.icon(i + 9 /*2nd row*/, punish.getIcon(player, target), (menuAction) -> {
                    if (!target.isOnline()) {
                        Utils.errorMsg(Utils.builder().append(target.displayName()).append(" is not online.").build());
                    } else punish.invoke(player, target);
                    menuAction.getMenuAgent().close();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        menu.pad(4, getTargetHead(target));
        menu.build().openSession(player);
    }

    public static ItemStack getTargetHead(@NotNull Player player) {
        ItemStack item = ItemUtil.getSkullFromProfile(WrappedGameProfile.fromPlayer(player));
        ItemUtil.decorate(item, Utils.builder().append("Target: ").append(player.displayName()).color(AtlasColor.YELLOW).build(), null);
        return item;
    }
}
