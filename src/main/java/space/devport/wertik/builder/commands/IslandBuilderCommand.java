package space.devport.wertik.builder.commands;

import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.MainCommand;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.subcommands.ListSubCommand;
import space.devport.wertik.builder.commands.subcommands.ReloadSubCommand;
import space.devport.wertik.builder.commands.subcommands.RunHereSubCommand;
import space.devport.wertik.builder.commands.subcommands.RunSubCommand;

public class IslandBuilderCommand extends MainCommand {

    public IslandBuilderCommand(IslandBuilderPlugin plugin) {
        super("islandbuilder");
        addSubCommand(new RunHereSubCommand(plugin));
        addSubCommand(new RunSubCommand(plugin));
        addSubCommand(new ReloadSubCommand(plugin));
        addSubCommand(new ListSubCommand(plugin));
    }

    @Override
    public @Nullable String getDefaultUsage() {
        return "/%label%";
    }

    @Override
    public @Nullable String getDefaultDescription() {
        return "Displays this.";
    }
}
