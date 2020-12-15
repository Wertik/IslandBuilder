package space.devport.wertik.builder.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.text.StringUtil;
import space.devport.utils.text.message.Message;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.IslandBuilderSubCommand;

public class ListSubCommand extends IslandBuilderSubCommand {

    public ListSubCommand(IslandBuilderPlugin plugin) {
        super(plugin, "list");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {

        Message header = language.get("Commands.List.Header");
        String lineFormat = language.get("Commands.List.Format").toString();

        plugin.getPlacementManager().getPlacements()
                .forEach(p -> header.append(lineFormat
                        .replace("%name%", p.getName())
                        .replace("%triggers%", p.getTrigger())));
        header.send(sender);
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
