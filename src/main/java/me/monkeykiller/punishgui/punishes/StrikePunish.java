package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StrikePunish extends Punish {
    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        target.getWorld().strikeLightning(target.getLocation());
        Utils.successMsg("Lightning spawned successfully").send(player);
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Lightning Strike", AtlasColor.GOLD.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return ItemUtil.make(Material.BLAZE_ROD, name);
    }
}
