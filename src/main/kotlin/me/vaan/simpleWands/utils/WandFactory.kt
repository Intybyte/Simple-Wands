package me.vaan.simpleWands.utils

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

object WandFactory {
    val durabilityKey = KeyMaker.of("durability")

    public fun createWand(m: Material, durability: Int) : ItemStack {
        val result = ItemStack(m, 1)
        result.editMeta {
            it.displayName( Component.text("$6Building Wand") )
        }

        val meta = result.itemMeta
        meta.persistentDataContainer.set(durabilityKey, PersistentDataType.INTEGER, durability)
        result.itemMeta = meta

        return result
    }
}