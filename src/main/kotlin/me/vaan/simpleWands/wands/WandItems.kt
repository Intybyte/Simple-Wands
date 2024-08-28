package me.vaan.simpleWands.wands

import me.vaan.simpleWands.utils.KeyMaker
import me.vaan.simpleWands.utils.RecipeUtil
import me.vaan.simpleWands.utils.WandFactory
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Tag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe

enum class WandItems(val itemStack: ItemStack) {
    WOOD_WAND(WandFactory.createWand(Material.WOODEN_HOE, 64)),
    STONE_WAND(WandFactory.createWand(Material.STONE_HOE, 128)),
    GOLD_WAND(WandFactory.createWand(Material.GOLDEN_HOE, 1)), //HAHHAHAHAAH
    IRON_WAND(WandFactory.createWand(Material.IRON_HOE, 256)),
    DIAMOND_WAND(WandFactory.createWand(Material.DIAMOND_HOE, 1024)),
    NETH_WAND(WandFactory.createWand(Material.NETHERITE_HOE, 8192));

    companion object {
        @JvmStatic
        fun registerRecipes() {
            val wood = RecipeUtil.wand(WOOD_WAND)
            wood.setIngredient('A', RecipeChoice.MaterialChoice(Tag.PLANKS))
            Bukkit.addRecipe(wood)

            val stone = RecipeUtil.wand(STONE_WAND)
            stone.setIngredient('A', RecipeChoice.MaterialChoice(Material.COBBLESTONE, Material.COBBLED_DEEPSLATE))
            Bukkit.addRecipe(stone)

            val gold = RecipeUtil.wand(GOLD_WAND)
            gold.setIngredient('A', Material.GOLD_INGOT)
            Bukkit.addRecipe(gold)

            val iron = RecipeUtil.wand(IRON_WAND)
            iron.setIngredient('A', Material.IRON_INGOT)
            Bukkit.addRecipe(iron)

            val diamond = RecipeUtil.wand(DIAMOND_WAND)
            diamond.setIngredient('A', Material.DIAMOND)
            Bukkit.addRecipe(diamond)

            val neth = RecipeUtil.wand(NETH_WAND)
            neth.setIngredient('A', Material.NETHERITE_INGOT)
            Bukkit.addRecipe(neth)
        }
    }
}