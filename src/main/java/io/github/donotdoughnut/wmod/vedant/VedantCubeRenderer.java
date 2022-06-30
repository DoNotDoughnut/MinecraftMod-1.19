package io.github.donotdoughnut.wmod.vedant;

import io.github.donotdoughnut.wmod.Mod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class VedantCubeRenderer extends MobEntityRenderer<VedantCube, VedantCubeModel> {

	public static final Identifier IDENTIFIER = Mod.identifier("vedant_cube");

	@Environment(EnvType.CLIENT)
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(IDENTIFIER, "main");

	public VedantCubeRenderer(EntityRendererFactory.Context context) {
		super(context, new VedantCubeModel(context.getPart(MODEL_LAYER)), 0.5f);
	}

	@Override
	public Identifier getTexture(VedantCube entity) {
		return Mod.identifier("textures/entity/vedant_cube/vedant_cube.png");
	}

	@Environment(EnvType.CLIENT)
	public static void register() {
		EntityRendererRegistry.register(VedantCube.VEDANT_CUBE, (context -> {
			return new VedantCubeRenderer(context);
		}));
		EntityModelLayerRegistry.registerModelLayer(MODEL_LAYER, VedantCubeModel::getTexturedModelData);
	}

}
