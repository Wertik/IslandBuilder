package space.devport.wertik.builder.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.IslandBuilderSubCommand;

public class ReloadSubCommand extends IslandBuilderSubCommand {

    public ReloadSubCommand(IslandBuilderPlugin plugin) {
        super(plugin, "reload");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {
        plugin.reload(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @Nullable String getDefaultUsage() {
        return "/%label% reload";
    }

    @Override
    public @Nullable String getDefaultDescription() {
        return "Reload the plugin.";
    }

    @Override
    public @Nullable ArgumentRange getRange() {
        return new ArgumentRange(0);
    }
}
