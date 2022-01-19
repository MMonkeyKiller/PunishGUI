package me.monkeykiller.punishgui;

import com.dndcraft.command.Commands;
import com.dndcraft.command.exception.InvalidPluginCommandException;
import me.monkeykiller.punishgui.commands.HelloWorldCommand;
import me.monkeykiller.punishgui.commands.MainCommand;
import me.monkeykiller.punishgui.commands.PunishCommand;
import me.monkeykiller.punishgui.listeners.MainListener;
import me.monkeykiller.punishgui.punishes.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main get() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        setupCommands();
        setupPunishes();
        Utils.log("&aPunishGUI&8> &7Plugin started!");
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

    private void setupPunishes() {
        List<Punish> punishes = List.of(
                new BedrockCagePunish(),
                new LaunchPunish(),
                new StrikePunish(),
                new InfiniteFallPunish(),
                new FreezePunish(),
                new CreeperPunish()
        );
        for (Punish punish : punishes) {
            Punish.register(punish);
            if (punish instanceof Listener listener)
                Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
