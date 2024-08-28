package me.vaan.simpleWands

import me.vaan.simpleWands.listeners.WandUseListener
import me.vaan.simpleWands.wands.WandItems
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SimpleWands : JavaPlugin() {

    companion object {
        @JvmStatic
        private lateinit var instance : SimpleWands

        @JvmStatic
        fun getInstance() : SimpleWands {
            return instance;
        }
    }

    override fun onEnable() {
        instance = this
        WandItems.registerRecipes()
        registerListeners()
    }

    override fun onDisable() {

    }

    private fun registerListeners() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(WandUseListener, this)
    }
}
