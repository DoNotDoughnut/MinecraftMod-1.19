package io.github.donotdoughnut.wmod.curse;

import io.github.donotdoughnut.wmod.Mod;
import net.minecraft.util.registry.Registry;

public class CurseMain {

	public static void register() {
		Registry.register(Registry.STATUS_EFFECT, Mod.identifier("curse"), CurseStatus.EFFECT);
		Registry.register(Registry.ENCHANTMENT, Mod.identifier("curse"), CursingEnchant.ENCHANT);
	}
}
