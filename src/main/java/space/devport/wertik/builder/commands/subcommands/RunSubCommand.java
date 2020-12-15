package space.devport.wertik.builder.commands.subcommands;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.commands.struct.ArgumentRange;
import space.devport.utils.commands.struct.CommandResult;
import space.devport.utils.text.StringUtil;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.commands.IslandBuilderSubCommand;
import space.devport.wertik.builder.system.placement.struct.Placement;

public class RunSubCommand extends IslandBuilderSubCommand {

    public RunSubCommand(IslandBuilderPlugin plugin) {
        super(plugin, "run");
    }

    @Override
    protected CommandResult perform(CommandSender sender, String label, String[] args) {
        Placement placement = plugin.getPlacementManager().getPlacement(args[0]);
        if (placement == null) {
            language.sendPrefixed(sender, "Commands.Invalid-Placement");
            return CommandResult.FAILURE;
        }

        SuperiorPlayer superiorPlayer;
        if (args.length > 1) {
            superiorPlayer = SuperiorSkyblockAPI.getPlayer(args[1]);

            if (superiorPlayer == null) {
                language.sendPrefixed(sender, "Commands.Invalid-Player");
                return CommandResult.FAILURE;
            }
        } else {
            if (!(sender instanceof Player))
                return CommandResult.NO_CONSOLE;

            superiorPlayer = SuperiorSkyblockAPI.getPlayer(((Player) sender).getUniqueId());
        }

        if (superiorPlayer == null || superiorPlayer.asPlayer() == null) {
            language.sendPrefixed(sender, "Commands.Invalid-Player");
            return CommandResult.FAILURE;
        }

        Island island = superiorPlayer.getIsland();

        if (island == null) {
            language.sendPrefixed(sender, "Commands.Run.No-Island");
            return CommandResult.FAILURE;
        }

        placement.run(superiorPlayer.asPlayer(), island);
        language.getPrefixed("Commands.Run.Done")
                .replace("%placement%", placement.getName())
                .replace("%player%", superiorPlayer.getName())
                .send(sender);
        return CommandResult.SUCCESS;
    }

    @Override
    public @Nullable String getDefaultUsage() {
        return "/%label% run <placement> (player)";
    }

    @Override
    public @Nullable String getDefaultDescription() {
        return "Run a placement on players island, or yours.";
    }

    @Override
    public @Nullable ArgumentRange getRange() {
        return new ArgumentRange(1, 2);
    }
}
