package space.devport.wertik.builder.system.placement.struct;

import com.bgsoftware.superiorskyblock.api.island.Island;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import space.devport.utils.ConsoleOutput;
import space.devport.utils.DevportPlugin;
import space.devport.utils.configuration.Configuration;
import space.devport.utils.struct.Context;
import space.devport.utils.struct.Rewards;
import space.devport.utils.text.Placeholders;
import space.devport.wertik.builder.IslandBuilderPlugin;
import space.devport.wertik.builder.placeholders.operator.Operators;

import java.util.HashMap;
import java.util.Map;

public class Placement {

    @Getter
    private final String name;

    @Getter
    private final String trigger;

    @Getter
    private final long delay;

    @Getter
    private final Rewards rewards;

    @Getter
    private final Map<String, String> placeholders = new HashMap<>();

    public Placement(String name, String trigger, long delay, Rewards rewards) {
        this.name = name;
        this.trigger = trigger;
        this.delay = delay;
        this.rewards = rewards;
    }

    public void run(Player player, Island island) {
        if (delay != 0) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(IslandBuilderPlugin.getInstance(), () -> give(player, island), delay * 20L);
        } else give(player, island);
    }

    private void give(Player player, Island island) {
        updatePlaceholders(player, island);
        this.rewards.give(player);
    }

    private void updatePlaceholders(Player player, Island island) {

        if (player == null)
            return;

        Placeholders localPlaceholders = new Placeholders(DevportPlugin.getInstance().getGlobalPlaceholders())
                .addContext(new Context().fromPlayer(player))
                .addContext(island);

        rewards.getPlaceholders().copy(localPlaceholders);

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String str = localPlaceholders.parse(entry.getValue());
            ConsoleOutput.getInstance().debug(str);
            str = Operators.parse(str);
            rewards.getPlaceholders().add("%" + entry.getKey() + "%", str);
        }
        ConsoleOutput.getInstance().debug(rewards.getPlaceholders().toString());
    }

    public void addPlaceholder(String key, String value) {
        this.placeholders.put(key, value);
    }

    @Nullable
    public static Placement load(Configuration configuration, String path) {
        ConfigurationSection section = configuration.getFileConfiguration().getConfigurationSection(path);

        if (section == null) {
            ConsoleOutput.getInstance().warn("Could not load Placement at " + configuration.getFile().getName() + "@" + path + ", configuration section is invalid.");
            return null;
        }

        String name = section.getName();
        String trigger = section.getString("trigger");
        Rewards rewards = configuration.getRewards(path + ".rewards");

        Placement placement = new Placement(name, trigger, section.getLong("delay", 0), rewards);

        ConfigurationSection placeholderSection = section.getConfigurationSection("placeholders");
        if (placeholderSection != null)
            for (String key : placeholderSection.getKeys(false)) {
                String value = placeholderSection.getString(key);
                if (value != null)
                    placement.addPlaceholder(key, value);
            }

        return placement;
    }

    @Override
    public String toString() {
        return name + ";" + trigger + ";" + rewards.toString() + ";" + placeholders.toString();
    }
}