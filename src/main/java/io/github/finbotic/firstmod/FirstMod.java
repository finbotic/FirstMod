package io.github.finbotic.firstmod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
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
	public static final Item SILVER_APPLE = new Item(new QuiltItemSettings().food(
		new FoodComponent.Builder()
			.hunger(4)
			.saturationModifier(2.4f)
			.alwaysEdible()
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 120), 1f)
			.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 0), 1f)
			.build()
	));


	public static final Logger LOGGER = LoggerFactory.getLogger("First Mod");


	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "first_item"), FIRST_ITEM);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "silver_ingot"), SILVER_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "silver_apple"), SILVER_APPLE);
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
