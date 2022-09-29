package org.mcausc78.hucooldown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;
import java.util.logging.Level;

public class HuListener implements Listener {
    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent ev) {
        String cmd = ev.getMessage().split(" ")[0].substring(1).toLowerCase();
        HuCooldown instance = HuCooldown.Companion.getInstance();
        if(cmd.isEmpty() || !Objects.requireNonNull(instance).getConfig().contains("commands." + cmd))
            return;
        if(!(Objects.requireNonNull(HuCooldown.Companion.getStorage()).containsKey(ev.getPlayer().getName()))) {
            Long li = HuCooldown.Companion.getStorage().get(ev.getPlayer().getName());
            if (li != null) {
                long l = li;
                if (l < System.currentTimeMillis()) {
                    ev.getPlayer().sendMessage(Objects.requireNonNull(Utils.papi(ev.getPlayer(), HuCooldown.Companion.getInstance().getConfig().getString("language.remaining", "?"))).replace("$time$", String.valueOf(l)));
                    ev.setCancelled(true);
                }
            } else {
                instance.getLogger().log(Level.SEVERE, "Invalid time at \"" + cmd + "\" command.");
            }
        } else {
            HuCooldown.Companion.getStorage().put(ev.getPlayer().getName(), (System.currentTimeMillis() + (instance.getConfig()).getLong("commands." + cmd + ".time") * 1000));
            ev.setCancelled(false);
        }
    }
}
