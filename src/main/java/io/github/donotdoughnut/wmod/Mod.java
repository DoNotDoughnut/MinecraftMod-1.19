package io.github.donotdoughnut.wmod;

import io.github.donotdoughnut.wmod.jungle.JungleOre;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.ModMetadata;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static Logger logger;

	public static final String ID = "wmod";

	@Override
	public void onInitialize(ModContainer mod) {
		ModMetadata metadata = mod.metadata();
		logger = LoggerFactory.getLogger(metadata.name());
		assert metadata.id().equals(ID) : " Mod IDs not equal";
		ModConfig.register();
		JungleOre.register();
	}

	public static Identifier identifier(String id) {
		return new Identifier(ID, id);
	}
}
