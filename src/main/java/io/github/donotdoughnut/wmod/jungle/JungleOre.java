package io.github.donotdoughnut.wmod.jungle;

import io.github.donotdoughnut.wmod.Mod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModification;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

import java.util.Arrays;
import java.util.List;

public class JungleOre {

	static Block TEMP = Blocks.WHITE_WOOL;

	public static final String IDENTIFIER = "jungle_ore";

	public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIGURED_FEATURE = ConfiguredFeatureUtil.register(
			IDENTIFIER,
			Feature.ORE,
			new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, TEMP.getDefaultState(), 4)
	);

	public static final Holder<PlacedFeature> ORE_PLACED_FEATURE = PlacedFeatureUtil.register(
			IDENTIFIER,
			ORE_CONFIGURED_FEATURE,
			Arrays.asList(
					CountPlacementModifier.create(80),
					InSquarePlacementModifier.getInstance(),
					HeightRangePlacementModifier.createUniform(YOffset.BOTTOM, YOffset.fixed(20))
			)
	);


	public static void register() {
//		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, IDENTIFIER, ORE_CONFIGURED_FEATURE);
//		BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.upcast(configuredFeature), List.copyOf(modifiers)));
		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(Arrays.asList(BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE)),
				GenerationStep.Feature.UNDERGROUND_ORES,
				ORE_PLACED_FEATURE.getKey().get()
		);
	}
}
