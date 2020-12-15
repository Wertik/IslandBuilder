package space.devport.wertik.builder.system.trigger.struct;

import com.bgsoftware.superiorskyblock.api.island.Island;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import space.devport.wertik.builder.IslandBuilderPlugin;

import java.util.List;

public abstract class TriggerListener implements Listener {

    public abstract List<String> getRegisteredTriggers();

    public void register() {
        IslandBuilderPlugin.getInstance().getTriggerManager().registerListener(this);
    }

    public void handle(String triggerName, Player player, Island island) {
        IslandBuilderPlugin.getInstance().getTriggerManager().handle(triggerName, player, island);
    }
}
