package space.devport.wertik.builder.system.trigger.struct.impl;

import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent;
import com.bgsoftware.superiorskyblock.api.events.IslandUpgradeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import space.devport.wertik.builder.system.trigger.struct.TriggerListener;

import java.util.Arrays;
import java.util.List;

public class IslandListener extends TriggerListener {

    @Override
    public List<String> getRegisteredTriggers() {
        return Arrays.asList("create", "upgrade", "disband");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCreate(IslandCreateEvent event) {
        handle("create", event.getPlayer().asPlayer(), event.getIsland());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLevelUp(IslandUpgradeEvent event) {
        handle("upgrade", event.getPlayer().asPlayer(), event.getIsland());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDisband(IslandDisbandEvent event) {
        handle("disband", event.getPlayer().asPlayer(), event.getIsland());
    }
}
