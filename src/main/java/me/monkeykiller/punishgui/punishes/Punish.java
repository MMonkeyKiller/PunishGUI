package me.monkeykiller.punishgui.punishes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Punish {
    private static final List<Punish> REGISTRY = new ArrayList<>();

    public static List<Punish> getRegistry() {
        return Collections.unmodifiableList(REGISTRY);
    }

    public static void register(@NotNull Punish... punishes) {
        REGISTRY.addAll(List.of(punishes));
    }

    public abstract void invoke(@NotNull Player player, @NotNull Player target);

    public abstract ItemStack getIcon(@NotNull Player player, @NotNull Player target);
}
