package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class LaunchPunish extends Punish {
    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        target.setVelocity(new Vector(0, 10, 0));
        Utils.successMsg("Sucessfull launch").send(player);
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Launch", AtlasColor.AQUA.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return ItemUtil.make(Material.ARROW, name);
    }
}
