package io.github.finbotic.firstmod.enchantment;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class ModdedEnchantment extends Enchantment {
	public ModdedEnchantment(Enchantment.Rarity RARITY, EnchantmentTarget TARGET) {
		super(
			RARITY,
			TARGET,
			new EquipmentSlot[]{EquipmentSlot.MAINHAND}
		);
	}
	@Override
	public int getMinPower(int level) {
		return 1;
	}
	@Override
	public int getMaxLevel() {
		return 1;
	}
}
