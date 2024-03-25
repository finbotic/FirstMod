package io.github.finbotic.firstmod;

import io.github.finbotic.firstmod.effect.ModdedEffect;
import io.github.finbotic.firstmod.enchantment.ModdedEnchantment;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Item FIRST_ITEM = new Item(new QuiltItemSettings());
	public static final Item SILVER_INGOT = new Item(new QuiltItemSettings());
	public static final StatusEffect MORTALITY = new ModdedEffect(StatusEffectType.HARMFUL,0x260006);
	public static final Enchantment MORTALITYENCHANTMENT = new ModdedEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.TRIDENT);
	public static final Item SILVER_APPLE = new Item(new QuiltItemSettings().food(
		new FoodComponent.Builder()
			.hunger(4)
			.saturationModifier(2.4f)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH), 1f)
			.build()
	));
	public static final Logger LOGGER = LoggerFactory.getLogger("First Mod");
	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "first_item"), FIRST_ITEM);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "silver_ingot"), SILVER_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "silver_apple"), SILVER_APPLE);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(mod.metadata().id(), "mortality"), MORTALITY);
		Registry.register(Registries.ENCHANTMENT, new Identifier(mod.metadata().id(), "mortality_enchantment"), MORTALITYENCHANTMENT);
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addItem(SILVER_INGOT);
			entries.addItem(FIRST_ITEM);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> {
			entries.addItem(SILVER_APPLE);
		});
	}
}
