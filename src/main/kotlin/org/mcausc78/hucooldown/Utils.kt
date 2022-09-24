package org.mcausc78.hucooldown

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class Utils {
    companion object {
        @JvmStatic
        fun isPlaceholdersAvailable(): Boolean {
            return try {
                Class.forName("me.clip.placeholderapi.PlaceholderAPI")
                true
            } catch (ex: ClassNotFoundException) {
                false
            } catch (ex: Exception) {
                ex.printStackTrace()
                false
            }
        }
        @JvmStatic
        fun papi(p: Player?, s: String?): String? {
            if(s == null)
                return ""
            else if(p == null)
                return s
            return when(isPlaceholdersAvailable()) {
                true -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, s))
                else -> s
            }
        }
    }
}