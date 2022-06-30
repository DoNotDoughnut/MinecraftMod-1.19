package io.github.donotdoughnut.wmod.vedant;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

public class VedantCubeModel extends EntityModel<VedantCube> {

	private final ModelPart base;

	public VedantCubeModel(ModelPart modelPart) {
		this.base = modelPart.getChild("vedant_cube");
	}

	@Override
	public void setAngles(VedantCube entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.base).forEach((modelRenderer) -> {
			modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		});
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("vedant_cube", ModelPartBuilder.create().uv(0, 0).cuboid(-8F, 16F, -8F, 16F, 16F, 16F), ModelTransform.pivot(0F, 0F, 0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

}
