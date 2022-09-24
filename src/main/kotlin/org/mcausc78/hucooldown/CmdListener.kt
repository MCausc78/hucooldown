package org.mcausc78.hucooldown

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import java.util.logging.Level

class CmdListener : Listener {
    @EventHandler
    fun onCmd(ev: PlayerCommandPreprocessEvent) {
        val cmd = ev.message.split(" ")[0].substring(1).lowercase()
        val instance: HuCooldown = HuCooldown.getInstance()
        if(cmd.isEmpty() || !instance.config.contains("commands.$cmd"))
            return
        if(!(HuCooldown.getStorage().containsKey(ev.player.name))) {
            val l: Long? = HuCooldown.getStorage()[ev.player.name]
            if (l != null) {
                if (l < System.currentTimeMillis()) {
                    ev.player.sendMessage(Utils.papi(ev.player, HuCooldown.getInstance().config.getString("language.remaining", "?"))!!.replace("\$time\$", l.toString()))
                    ev.isCancelled = true
                }
            } else {
                instance.logger.log(Level.SEVERE, "Invalid time at \"${cmd}\" command.")
            }
        } else {
            HuCooldown.getStorage()[ev.player.name] = (System.currentTimeMillis() + (instance.config.getLong("commands.${cmd}.time") * 1000))
            ev.isCancelled = false
        }
    }
}