package me.monkeykiller.punishgui.commands;

import com.dndcraft.util.AtlasColor;
import net.kyori.adventure.text.Component;

public class HelloWorldCommand extends BaseCommand {
    @Override
    protected void invoke() {
        getSender().sendMessage(Component.text("Hello World", AtlasColor.AQUA.toTextColor()));
    }
}
