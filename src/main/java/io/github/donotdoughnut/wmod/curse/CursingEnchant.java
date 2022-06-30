package io.github.donotdoughnut.wmod.curse;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class CursingEnchant extends Enchantment {

	public static final CursingEnchant ENCHANT = new CursingEnchant();

	CursingEnchant() {
		super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
	}

	@Override
	public int getMinPower(int level) {
		return 1;
	}

	@Override
	public int getMaxPower(int level) {
		return 1;
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		if(target instanceof LivingEntity) {
			var effect = ((LivingEntity) target).getStatusEffect(CurseStatus.EFFECT);
			int time = 6;
			if (effect != null) {
				time = effect.getDuration() + 6;
			}
			((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CurseStatus.EFFECT, time, 1));
		}

		super.onTargetDamaged(user, target, level);
	}

}
