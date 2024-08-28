package me.vaan.simpleWands.utils

import me.vaan.simpleWands.SimpleWands
import org.bukkit.NamespacedKey

object KeyMaker {
    fun of(s: String): NamespacedKey {
        return NamespacedKey(SimpleWands.getInstance(), s)
    }
}