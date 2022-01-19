package me.monkeykiller.punishgui.commands;

import com.dndcraft.command.annotations.Cmd;
import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.BukkitComponentBuilder;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Map;

public class MainCommand extends BaseCommand {
    @Override
    protected void invoke() {
        help().send(getSender());
    }

    @Cmd(value = "getitem")
    public void getitem(Player player) {
        if (!player.hasPermission("punishgui.command.getitem")) {
            Utils.errorMsg("You don't have permissions to execute this command!").send(getSender());
            return;
        }
        ItemStack item = ItemUtil.getSkullFromTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGY3ZjQwYTg2YmVlOGUyMWE1NTM0MDg1NmE4NjIxYWNhNDk1NjczYWExN2JmZGUxOGQzYjdhYTYxYjQyYyJ9fX0=");
        ItemUtil.decorate(item, Component.text("Open Punish GUI", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false), List.of(Component.text("Right click to open the GUI", AtlasColor.LIME.toTextColor()).decoration(TextDecoration.ITALIC, false)));
        ItemUtil.setTag(item, "OpenPunishGUI", (byte) 1);
        player.getInventory().addItem(item).values().forEach(i -> player.getWorld().dropItemNaturally(player.getLocation(), item));
    }

    @Cmd(value = "checkitem")
    public void checkitem(Player player) {
        if (!player.hasPermission("punishgui.command.checkitem")) {
            Utils.errorMsg("You don't have permissions to execute this command!").send(getSender());
            return;
        }
        PlayerInventory inv = player.getInventory();
        for (ItemStack item : List.of(inv.getItemInMainHand(), inv.getItemInOffHand())) {
            if (!Utils.isGUIOpener(item)) continue;
            Utils.successMsg("The current item is able to open the gui").send(getSender());
            return;
        }
        Utils.errorMsg("You are not holding any item able to open the gui").send(getSender());
    }

    private BukkitComponentBuilder help() {
        Map<String, String> cmds = Map.of(
                "/punishgui", "Plugin's main command",
                "/punishgui getitem", "Create a clickable item to open the gui",
                "/punishgui checkitem", "Check if the current item is able to open the gui",
                "/punish <player>", "Opens the punish gui for the selected victim"
        );
        var builder = Utils.builder().append("Plugin Commands:");
        cmds.forEach((cmd, desc) -> builder.newline()
                .append("> ", AtlasColor.DARK_GRAY)
                .append(Utils.builder().append(cmd, AtlasColor.LIME).onClickCopyToClipboard(cmd).build())
                .append(": " + desc, AtlasColor.GRAY));
        return builder;
    }
}
