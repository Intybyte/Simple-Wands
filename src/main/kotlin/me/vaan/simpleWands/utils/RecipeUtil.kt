package me.vaan.simpleWands.utils

import me.vaan.simpleWands.wands.WandItems
import org.bukkit.Material
import org.bukkit.inventory.ShapedRecipe

object RecipeUtil {

    fun wand(wand: WandItems): ShapedRecipe {
        val recipe = ShapedRecipe(KeyMaker.of(wand.name.lowercase()), wand.itemStack)
        recipe.shape("AA ", " B ", " B ")
        recipe.setIngredient('B', Material.BLAZE_ROD)
        return recipe
    }
}