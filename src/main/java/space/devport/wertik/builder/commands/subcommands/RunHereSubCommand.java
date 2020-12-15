package space.devport.wertik.builder.commands.subcommands;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.commands.struct.Preconditions;
import space.devport.utils.text.StringUtil;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.IslandBuilderSubCommand;
import space.devport.wertik.builder.system.placement.struct.Placement;

public class RunHereSubCommand extends IslandBuilderSubCommand {

    public RunHereSubCommand(IslandBuilderPlugin plugin) {
        super(plugin, "runhere");
        this.preconditions = new Preconditions()
                .playerOnly();
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {

        Placement placement = plugin.getPlacementManager().getPlacement(args[0]);

        if (placement == null) {
            language.sendPrefixed(sender, "Commands.Invalid-Placement");
            return CommandResult.FAILURE;
        }

        Player player = (Player) sender;

        Island island = SuperiorSkyblockAPI.getIslandAt(player.getLocation());
        if (island == null) {
            language.send(sender, "Commands.Run-Here.Not-On-Island");
            return CommandResult.FAILURE;
        }

        placement.run(island.getOwner().asPlayer(), island);
        language.getPrefixed("Commands.Run-Here.Done")
                .replace("%placement%", placement.getName())
                .replace("%island%", island.getName())
                .send(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @Nullable String getDefaultUsage() {
        return "/%label% runhere <placement>";
    }

    @Override
    public @Nullable String getDefaultDescription() {
        return "Run a placement for the island you're on.";
    }

    @Override
    public @Nullable ArgumentRange getRange() {
        return new ArgumentRange(1);
    }
}
