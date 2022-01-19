package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FreezePunish extends Punish implements Listener {
    private final List<UUID> victims = new ArrayList<>();

    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        var uuid = target.getUniqueId();
        if (victims.contains(uuid)) {
            victims.remove(uuid);
            Utils.successMsg(target.getName() + " is no longer frozen.").send(player);
        } else {
            victims.add(uuid);
            Utils.successMsg(target.getName() + " is now frozen!").send(player);
        }
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Freeze", AtlasColor.MAGENTA.toTextColor()).decoration(TextDecoration.ITALIC, false);
        var item = ItemUtil.make(Material.PACKED_ICE, name);
        if (victims.contains(target.getUniqueId())) {
            item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
            item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return item;
    }

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        if (victims.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

}
