package io.github.donotdoughnut.wmod.curse;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class CurseStatus extends StatusEffect {

	public static final CurseStatus EFFECT = new CurseStatus();

	protected CurseStatus() {
		super(StatusEffectType.HARMFUL, 0x243578);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		// In our case, we just make it return true so that it applies the status effect every tick.
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).addExperience(1 << amplifier); // Higher amplifier gives you EXP faster
		}
	}


}
