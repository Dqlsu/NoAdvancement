package me.dalsu.noadvancement;

import io.papermc.paper.advancement.AdvancementDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoAdvancement extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        AdvancementDisplay display = event.getAdvancement().getDisplay();
        if (display != null && display.doesAnnounceToChat()) {
            Player player = event.getPlayer();
            AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            maxHealthAttribute.setBaseValue(maxHealthAttribute.getBaseValue() - 2);
            if (maxHealthAttribute.getBaseValue() == 0) {
                player.kick(Component.text("모든 체력을 소진했습니다!", NamedTextColor.RED).decorate(TextDecoration.BOLD));
            }
        }
    }
}
