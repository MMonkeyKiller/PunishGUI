package me.monkeykiller.punishgui;

import com.dndcraft.command.Commands;
import com.dndcraft.command.exception.InvalidPluginCommandException;
import me.monkeykiller.punishgui.commands.HelloWorldCommand;
import me.monkeykiller.punishgui.commands.MainCommand;
import me.monkeykiller.punishgui.commands.PunishCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main get() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        setupCommands();
        Utils.log("&aAtlas Test Plugin by MonkeyKiller");
    }

    @Override
    public void onDisable() {
    }

    @SuppressWarnings("ConstantConditions")
    private void setupCommands() {
        Map.of("helloworld", new HelloWorldCommand(),
                "punishgui", new MainCommand(),
                "punish", new PunishCommand()
        ).forEach((cmd, inst) -> {
            try {
                Commands.build(getCommand(cmd), () -> inst);
            } catch (InvalidPluginCommandException e) {
                e.printStackTrace();
            }
        });
    }
}
