package io.github.donotdoughnut.wmod.jungle;

import io.github.donotdoughnut.wmod.Mod;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
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

	public static final String IDENTIFIER = "jungle_ore";

	public static final Block JUNGLE_ORE = new ExperienceDroppingBlock(Block.Settings.of(Material.STONE, MapColor.PALE_GREEN).requiresTool().strength(1.0F, 3.0F), UniformIntProvider.create(7, 9));

	public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CONFIGURED_FEATURE = ConfiguredFeatureUtil.register(
			IDENTIFIER,
			Feature.ORE,
			new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, JUNGLE_ORE.getDefaultState(), 4)
	);

	public static final Holder<PlacedFeature> ORE_PLACED_FEATURE = PlacedFeatureUtil.register(
			IDENTIFIER,
			ORE_CONFIGURED_FEATURE,
			Arrays.asList(
					CountPlacementModifier.create(2),
					InSquarePlacementModifier.getInstance(),
					HeightRangePlacementModifier.createUniform(YOffset.BOTTOM, YOffset.fixed(20))
			)
	);

	public static void register() {
		Registry.register(Registry.BLOCK, Mod.identifier(IDENTIFIER), JUNGLE_ORE);
		Registry.register(Registry.ITEM, Mod.identifier(IDENTIFIER), new BlockItem(JUNGLE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
//		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, IDENTIFIER, ORE_CONFIGURED_FEATURE);
//		BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.upcast(configuredFeature), List.copyOf(modifiers)));
		var biomeKeys = BiomeSelectors.includeByKey(Arrays.asList(BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE));
		BiomeModifications.addFeature(
				biomeKeys,
				GenerationStep.Feature.UNDERGROUND_ORES,
				ORE_PLACED_FEATURE.getKey().get()
		);
	}
}
