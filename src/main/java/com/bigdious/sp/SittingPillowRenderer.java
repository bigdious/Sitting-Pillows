package com.bigdious.sp;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SittingPillowRenderer extends EntityRenderer<SittingPillow> {
	protected static final ResourceLocation TEXTURE = SP.prefix("textures/entity/sitting_pillow.png");
	private final Model model;

	public SittingPillowRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SittingPillowModel<>(context.bakeLayer(SPModelLayers.SITTING_PILLOW));
	}

	@Override
	public void render(SittingPillow entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		int color = entity.getColor();
		super.render(entity, yaw, partialTicks, stack, buffer, light);
		stack.pushPose();
		stack.mulPose(Axis.XP.rotationDegrees(180));
		stack.translate(0, -1.55, 0);
		this.model.renderToBuffer(stack, buffer.getBuffer(this.model.renderType(TEXTURE)), light, OverlayTexture.NO_OVERLAY, entity.getEntityData().get(SittingPillow.DATA_COLOR));
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(SittingPillow sittingPillow) {
		return TEXTURE;
	}
}

