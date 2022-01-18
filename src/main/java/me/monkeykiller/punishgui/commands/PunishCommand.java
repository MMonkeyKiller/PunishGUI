package me.monkeykiller.punishgui.commands;

import com.dndcraft.command.annotations.Arg;
import me.monkeykiller.punishgui.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishCommand extends BaseCommand{
    public void invoke(Player player, @Arg(value="target") String targetSel) {
        if (!player.hasPermission("punishgui.opengui")) {
            Utils.errorMsg("You don't have permissions to execute this command!").send(getSender());
            return;
        }
        Player target = Bukkit.getPlayerExact(targetSel);
        if (target == null) {
            Utils.errorMsg("Player with name \"" + targetSel + "\" not found");
            return;
        }
        // TODO: Open punish gui
    }
}
