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
            //TODO lang
            sender.sendMessage(StringUtil.color("&cInvalid placement."));
            return CommandResult.FAILURE;
        }

        SuperiorPlayer superiorPlayer;
        if (args.length > 1) {
            superiorPlayer = SuperiorSkyblockAPI.getPlayer(args[1]);

            if (superiorPlayer == null) {
                //TODO lang
                sender.sendMessage(StringUtil.color("&cPlayer does not exist."));
                return CommandResult.FAILURE;
            }
        } else {
            if (!(sender instanceof Player))
                return CommandResult.NO_CONSOLE;

            superiorPlayer = SuperiorSkyblockAPI.getPlayer(((Player) sender).getUniqueId());
        }

        if (superiorPlayer == null || superiorPlayer.asPlayer() == null) {
            //TODO lang
            sender.sendMessage(StringUtil.color("&cInvalid player."));
            return CommandResult.FAILURE;
        }

        Island island = superiorPlayer.getIsland();

        if (island == null) {
            //TODO lang
            sender.sendMessage(StringUtil.color("&cPlayer does not have an island."));
            return CommandResult.FAILURE;
        }

        placement.run(superiorPlayer.asPlayer(), island);
        sender.sendMessage(StringUtil.color("&7Placement &f" + placement.getName() + " &7ran for the island of &f" + superiorPlayer.getName()));
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
