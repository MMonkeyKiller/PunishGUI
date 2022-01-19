package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreeperPunish extends Punish {
    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        var world = target.getWorld();
        for (int i = 0; i < 10; i++) world.spawn(target.getLocation(), Creeper.class, e -> e.setPowered(true));
        Utils.successMsg("Creeper punish successfull").send(player);
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Electric Creepers", AtlasColor.GREEN.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return ItemUtil.make(Material.CREEPER_HEAD, name);
    }
}
