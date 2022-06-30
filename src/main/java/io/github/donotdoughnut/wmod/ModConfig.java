package io.github.donotdoughnut.wmod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = Mod.ID)
public class ModConfig implements ConfigData {

	boolean vedant = false;

	public static void register() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}

}
