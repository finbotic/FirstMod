package io.github.finbotic.firstmod.mixin;

import io.github.finbotic.firstmod.FirstMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
    @Shadow
    @Nullable
    public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);
	@Shadow
	public abstract boolean damage(DamageSource source, float amount);
	@Shadow
	public abstract void heal(float amount);
	@ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
	private float multiplyDamageForMortality(float amount) {
		if(this.hasStatusEffect(FirstMod.MORTALITY)){
			return amount + (amount * (this.getStatusEffect(FirstMod.MORTALITY).getAmplifier() + 1));
		}
		return amount;
	}
	@ModifyVariable(method = "heal", at = @At("HEAD"), argsOnly = true)
	private float multiplyHealForMortality(float amount) {
		if(this.hasStatusEffect(FirstMod.MORTALITY)){
			return amount + (amount * (this.getStatusEffect(FirstMod.MORTALITY).getAmplifier() + 1));
		}
		return amount;
	}
}
