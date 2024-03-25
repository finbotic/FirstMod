package io.github.finbotic.firstmod.enchantment;

import io.github.finbotic.firstmod.FirstMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ModdedEnchantmentHelper extends EnchantmentHelper {
	public static boolean hasMortalityEnchantment(ItemStack stack) {
		return getLevel(FirstMod.MORTALITYENCHANTMENT, stack) > 0;
	}
}
