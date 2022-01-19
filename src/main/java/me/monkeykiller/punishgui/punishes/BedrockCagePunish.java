package me.monkeykiller.punishgui.punishes;

import com.dndcraft.util.AtlasColor;
import com.dndcraft.util.ItemUtil;
import me.monkeykiller.punishgui.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BedrockCagePunish extends Punish {

    @Override
    public void invoke(@NotNull Player player, @NotNull Player target) {
        var base = target.getLocation().getBlock();
        List<Block> bedrock = new ArrayList<>(getAroundBlocks(base));
        bedrock.addAll(getAroundBlocks(base.getRelative(0, 1, 0)));
        bedrock.addAll(getAroundBlocks(base.getRelative(0, 2, 0)));
        bedrock.add(base.getRelative(0, -1, 0));
        bedrock.add(base.getRelative(0, 3, 0));
        bedrock.forEach(block -> block.setType(Material.BEDROCK));

        base = base.getRelative(0, 1, 0);

        List.of(base.getRelative(BlockFace.NORTH), base.getRelative(BlockFace.SOUTH), base.getRelative(BlockFace.EAST), base.getRelative(BlockFace.WEST))
                .forEach(block -> block.setType(Material.IRON_BARS, true));

        Utils.successMsg("Sucessfull bedrock cage").send(player);
    }

    @Override
    public ItemStack getIcon(@NotNull Player player, @NotNull Player target) {
        var name = Component.text("Bedrock Cage", AtlasColor.YELLOW.toTextColor()).decoration(TextDecoration.ITALIC, false);
        return ItemUtil.make(Material.BEDROCK, name);
    }

    private List<Block> getAroundBlocks(@NotNull Block base) {
        return List.of(base.getRelative(1, 0, 0),
                base.getRelative(-1, 0, 0),
                base.getRelative(0, 0, 1),
                base.getRelative(0, 0, -1),
                base.getRelative(1, 0, 1),
                base.getRelative(1, 0, -1),
                base.getRelative(-1, 0, -1),
                base.getRelative(-1, 0, 1));
    }
}
