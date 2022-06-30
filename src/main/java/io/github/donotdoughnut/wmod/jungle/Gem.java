package io.github.donotdoughnut.wmod.jungle;

import io.github.donotdoughnut.wmod.Mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class Gem {

	public static final Item UNCUT_JUNGLE_GEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item JUNGLE_GEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

	public static void register() {
		Registry.register(Registry.ITEM, Mod.identifier("uncut_jungle_gem"), UNCUT_JUNGLE_GEM);
		Registry.register(Registry.ITEM, Mod.identifier("jungle_gem"), JUNGLE_GEM);
	}

}
