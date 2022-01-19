package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InfiniteFallPunish extends Punish implements Listener {
    private final List<UUID> victims = new ArrayList<>();

    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        var uuid = target.getUniqueId();
        if (victims.contains(uuid)) {
            victims.remove(uuid);
            Utils.successMsg(target.getName() + " will no longer fall infinitely").send(target);
        } else {
            launch(target);
            victims.add(uuid);
            Utils.successMsg(target.getName() + " will fall infinitely").send(target);
        }
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Infinite Fall", AtlasColor.LIME.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return ItemUtil.make(Material.LEATHER_BOOTS, name);
    }

    private void launch(@NotNull Player player) {
        var start = player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().clone().add(0, 30, 0);
        start.setYaw(player.getLocation().getYaw());
        start.setPitch(player.getLocation().getPitch());
        player.teleport(start);
    }

    @EventHandler
    private void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player) || event.getCause() != EntityDamageEvent.DamageCause.FALL || !victims.contains(player.getUniqueId()))
            return;
        event.setCancelled(true);
        launch(player);
    }
}
