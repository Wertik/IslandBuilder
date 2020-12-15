package space.devport.wertik.builder.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.text.StringUtil;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.IslandBuilderSubCommand;

public class ListSubCommand extends IslandBuilderSubCommand {

    public ListSubCommand(IslandBuilderPlugin plugin) {
        super(plugin, "list");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {
        //TODO lang
        StringBuilder stringBuilder = new StringBuilder("&7Loaded placements:\n&r ");
        plugin.getPlacementManager().getPlacements(p -> true).forEach(p -> stringBuilder.append("\n &8 - &f").append(p.getName())
                .append(" &7(trigger: &f").append(p.getTrigger()).append("&7)"));
        sender.sendMessage(StringUtil.color(stringBuilder.toString()));
        return CommandResult.SUCCESS;
    }

    @Override
    public @Nullable String getDefaultUsage() {
        return null;
    }

    @Override
    public @Nullable String getDefaultDescription() {
        return null;
    }

    @Override
    public @Nullable ArgumentRange getRange() {
        return null;
    }
}
