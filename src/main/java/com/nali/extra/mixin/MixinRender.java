package com.nali.extra.mixin;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//*extra
@Mixin(Render.class)
public abstract class MixinRender<T extends Entity>
{
	@Overwrite
	public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
	{
		if (livingEntity.ignoreFrustumCheck)
		{
			return true;
		}

//		AxisAlignedBB axisalignedbb = livingEntity.getRenderBoundingBox().grow(0.5D);
//
//		if (axisalignedbb.hasNaN() || axisalignedbb.getAverageEdgeLength() == 0.0D)
//		{
//			axisalignedbb = new AxisAlignedBB(livingEntity.posX - 2.0D, livingEntity.posY - 2.0D, livingEntity.posZ - 2.0D, livingEntity.posX + 2.0D, livingEntity.posY + 2.0D, livingEntity.posZ + 2.0D);
//		}

//		return /*livingEntity.isInRangeToRender3d(camX, camY, camZ) && (*/livingEntity.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(axisalignedbb)/*)*/;
		return camera.isBoundingBoxInFrustum(livingEntity.getRenderBoundingBox());
	}

	//remove light
	@Overwrite
	public static void renderOffsetAABB(AxisAlignedBB boundingBox, double x, double y, double z)
	{
////		GlStateManager.disableTexture2D();
//		float c = Minecraft.getSystemTime() % 1000 / 1000.0F;
//		Color color = Color.getHSBColor(c, 1.0F, 1.0F);
//		GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
//		ExtraCubeLine.update(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
//		ExtraCubeLine.draw();
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
////		GlStateManager.enableTexture2D();

//		GlStateManager.disableTexture2D();
//		Tessellator tessellator = Tessellator.getInstance();
//		BufferBuilder bufferbuilder = tessellator.getBuffer();
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//		bufferbuilder.setTranslation(x, y, z);
//		bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
//		bufferbuilder.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
//		tessellator.draw();
//		bufferbuilder.setTranslation(0.0D, 0.0D, 0.0D);
//		GlStateManager.enableTexture2D();
	}
}


