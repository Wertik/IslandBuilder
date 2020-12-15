package space.devport.wertik.builder.system.placement;

import lombok.Getter;
import space.devport.utils.ConsoleOutput;
import space.devport.utils.configuration.Configuration;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.system.placement.struct.Placement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlacementManager {

    private final IslandBuilderPlugin plugin;

    private final Map<String, Placement> loadedPlacements = new HashMap<>();

    @Getter
    private final Configuration configuration;

    public PlacementManager(IslandBuilderPlugin plugin) {
        this.plugin = plugin;
        this.configuration = new Configuration(plugin, "placements");
    }

    public void load() {
        configuration.load();
        this.loadedPlacements.clear();

        for (String name : configuration.getFileConfiguration().getKeys(false)) {
            Placement placement = Placement.load(configuration, name);
            if (placement == null)
                continue;
            this.loadedPlacements.put(name, placement);
            ConsoleOutput.getInstance().debug("Loaded placement " + name + " [" + placement.toString() + "]");
        }
        ConsoleOutput.getInstance().info("Loaded " + this.loadedPlacements.size() + " placement(s)...");
    }

    public Placement getPlacement(String name) {
        return this.loadedPlacements.get(name);
    }

    public Set<Placement> getPlacements(Predicate<Placement> condition) {
        return this.loadedPlacements.values().stream().filter(condition).collect(Collectors.toSet());
    }

    public Map<String, Placement> getLoadedPlacements() {
        return Collections.unmodifiableMap(loadedPlacements);
    }
}
