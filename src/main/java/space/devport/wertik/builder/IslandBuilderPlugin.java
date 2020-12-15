package space.devport.wertik.builder;

import com.bgsoftware.superiorskyblock.api.island.Island;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import space.devport.utils.DevportPlugin;
import space.devport.utils.UsageFlag;
import space.devport.wertik.builder.commands.IslandBuilderCommand;
import space.devport.wertik.builder.system.placement.PlacementManager;
import space.devport.wertik.builder.system.trigger.TriggerManager;

public class IslandBuilderPlugin extends DevportPlugin {

    @Getter
    private TriggerManager triggerManager;

    @Getter
    private PlacementManager placementManager;

    public static IslandBuilderPlugin getInstance() {
        return getPlugin(IslandBuilderPlugin.class);
    }

    @Override
    public void onPluginEnable() {
        this.triggerManager = new TriggerManager(this);
        this.placementManager = new PlacementManager(this);

        this.triggerManager.registerDefaults();
        this.placementManager.load();

        getGlobalPlaceholders().addParser((str, island) -> {
            Location center = island.getCenter(World.Environment.NORMAL);
            str = str.replaceAll("(?i)%island_location_x%", String.valueOf(center.getX()));
            str = str.replaceAll("(?i)%island_location_y%", String.valueOf(center.getY()));
            str = str.replaceAll("(?i)%island_location_z%", String.valueOf(center.getZ()));
            if (center.getWorld() != null)
                str = str.replaceAll("(?i)%island_location_world%", center.getWorld().getName());
            return str;
        }, Island.class);

        addMainCommand(new IslandBuilderCommand(this));
    }

    @Override
    public void onPluginDisable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public void onReload() {
        this.placementManager.load();
    }

    @Override
    public UsageFlag[] usageFlags() {
        return new UsageFlag[]{UsageFlag.LANGUAGE, UsageFlag.COMMANDS, UsageFlag.CONFIGURATION};
    }
}
