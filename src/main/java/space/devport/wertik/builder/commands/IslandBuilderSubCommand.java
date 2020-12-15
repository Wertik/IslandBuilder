package space.devport.wertik.builder.commands;

import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.SubCommand;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.wertik.builder.IslandBuilderPlugin;

public abstract class IslandBuilderSubCommand extends SubCommand {

    protected final IslandBuilderPlugin plugin;

    public IslandBuilderSubCommand(IslandBuilderPlugin plugin, String name) {
        super(name);
        setPermissions();
        this.plugin = plugin;
    }

    @Override
    public @Nullable
    abstract String getDefaultUsage();

    @Override
    public @Nullable
    abstract String getDefaultDescription();

    @Override
    public @Nullable
    abstract ArgumentRange getRange();
}
