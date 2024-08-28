package me.vaan.simpleWands.listeners

import me.vaan.simpleWands.utils.WandFactory
import me.vaan.simpleWands.wands.Config
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.persistence.PersistentDataType
import org.bukkit.util.Vector

object WandUseListener : Listener {

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_BLOCK) return

        val inventory = event.player.inventory
        val wand = inventory.itemInMainHand
        if (wand.type.isAir) return

        val block = event.clickedBlock ?: return
        val buildType = block.type

        var durability = wand.itemMeta.persistentDataContainer.get(WandFactory.durabilityKey, PersistentDataType.INTEGER) ?: return
        val startAmount = countMaterials(inventory, buildType)
        var amount = startAmount

        val face = event.blockFace
        val center = block.getRelative(face).location

        val northSouth = isNorthSouth(face)
        val execute: (Location) -> Unit = fun(l: Location) {

            if (durability == 0) {
                inventory.remove(wand)
                return
            }

            if (amount == 0) return

            val type = l.block.type
            if(!type.isAir) return

            durability--
            amount--

            l.block.type = buildType
        }

        if (isHorizontal(face)) {
            val vector = Vector(
                if (northSouth) Config.radius else 0.0,
                Config.radius,
                if (!northSouth) Config.radius else 0.0
            )
            val start = center.clone().subtract(vector)
            val end = center.clone().add(vector)

            executeInBox(start, end, execute)

        } else {
            val vector = Vector(Config.radius, 0.0, Config.radius)
            val start = center.clone().subtract(vector)
            val end = center.clone().add(vector)

            executeInBox(start, end, execute)
        }

        inventory.remove(ItemStack(buildType, startAmount - amount))
    }

    private fun isHorizontal(face: BlockFace): Boolean {
        return when (face) {
            BlockFace.NORTH -> true
            BlockFace.SOUTH -> true
            BlockFace.WEST -> true
            BlockFace.EAST -> true
            else -> false
        }
    }

    private fun isNorthSouth(face: BlockFace): Boolean {
        return when (face) {
            BlockFace.NORTH -> true
            BlockFace.SOUTH -> true
            else -> false
        }
    }

    private fun countMaterials(inv: PlayerInventory, m: Material): Int {
        var total = 0
        for (stack in inv) {
            if (stack.type == m && !stack.itemMeta.hasDisplayName() && stack.itemMeta.persistentDataContainer.isEmpty)
                total += stack.amount
        }

        return total
    }

    private fun executeInBox(corner1: Location, corner2: Location, action: (Location) -> Unit) {
        val world: World = corner1.world ?: return

        // Calculate the minimum and maximum bounds
        val minX = minOf(corner1.blockX, corner2.blockX)
        val maxX = maxOf(corner1.blockX, corner2.blockX)
        val minY = minOf(corner1.blockY, corner2.blockY)
        val maxY = maxOf(corner1.blockY, corner2.blockY)
        val minZ = minOf(corner1.blockZ, corner2.blockZ)
        val maxZ = maxOf(corner1.blockZ, corner2.blockZ)

        // Loop through all blocks within the bounds
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    val currentLocation = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
                    action(currentLocation) // Perform the action on each location
                }
            }
        }
    }
}