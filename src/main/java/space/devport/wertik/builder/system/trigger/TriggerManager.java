package space.devport.wertik.builder.system.trigger;

import com.bgsoftware.superiorskyblock.api.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import space.devport.utils.ConsoleOutput;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.system.placement.struct.Placement;
import space.devport.wertik.builder.system.trigger.struct.TriggerListener;
import space.devport.wertik.builder.system.trigger.struct.impl.IslandListener;

import java.util.HashSet;
import java.util.Set;

public class TriggerManager {

    private final IslandBuilderPlugin plugin;

    private final Set<TriggerListener> registeredListeners = new HashSet<>();

    public TriggerManager(IslandBuilderPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerListener(TriggerListener listener) {
        this.registeredListeners.add(listener);
        plugin.registerListener(listener);
        ConsoleOutput.getInstance().debug("Registered trigger listener " + listener.getClass().getSimpleName() + " for " + String.join(", ", listener.getRegisteredTriggers()));
    }

    public void registerDefaults() {
        registerListener(new IslandListener());
    }

    public void handle(String triggerName, Player player, Island island) {
        ConsoleOutput.getInstance().debug("Handling " + triggerName + " for " + player.getName() + " and island " + island.getName());
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (Placement placement : plugin.getPlacementManager().getPlacements(placement -> placement.getTrigger().equalsIgnoreCase(triggerName))) {
                placement.run(player, island);
            }
        });
    }
}
