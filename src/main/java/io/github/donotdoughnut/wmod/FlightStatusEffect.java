package io.github.donotdoughnut.wmod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;
import java.util.Map;

public class FlightStatusEffect extends StatusEffect {

	public static final FlightStatusEffect EFFECT = new FlightStatusEffect();

	private boolean couldFly = false;

	protected FlightStatusEffect() {
		super(StatusEffectType.BENEFICIAL, 0xffc208);
	}

	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).getAbilities().allowFlying = this.couldFly;
		}
	}

	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onApplied(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity) {
			this.couldFly = ((PlayerEntity) entity).getAbilities().allowFlying;
			((PlayerEntity) entity).getAbilities().allowFlying = true;
		}
	}

	public static void register() {
		Registry.register(Registry.STATUS_EFFECT, Mod.identifier("flight"), EFFECT);
	}

}
