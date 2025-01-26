//package com.nali.extra.mixin;
//
//import net.minecraft.client.particle.Particle;
//import net.minecraft.client.particle.ParticleSweepAttack;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.RenderHelper;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.client.renderer.vertex.VertexFormat;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.*;
//
////remove light
//@Mixin(ParticleSweepAttack.class)
//public abstract class MixinParticleSweepAttack extends Particle
//{
//	@Shadow private int life;
//
//	@Shadow @Final private int lifeTime;
//
//	@Shadow @Final private float size;
//
//	@Shadow @Final private TextureManager textureManager;
//
//	@Shadow @Final private static ResourceLocation SWEEP_TEXTURE;
//
//	@Mutable
//	@Shadow @Final private static VertexFormat VERTEX_FORMAT;
//
//	static
//	{
//		VERTEX_FORMAT = null;
//	}
//
//	protected MixinParticleSweepAttack(World worldIn, double posXIn, double posYIn, double posZIn)
//	{
//		super(worldIn, posXIn, posYIn, posZIn);
//	}
//
//	@Overwrite
//	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
//	{
//		int i = (int)(((float)this.life + partialTicks) * 3.0F / (float)this.lifeTime);
//
//		if (i <= 7)
//		{
//			this.textureManager.bindTexture(SWEEP_TEXTURE);
//			float f = (float)(i % 4) / 4.0F;
//			float f1 = f + 0.24975F;
//			float f2 = (float)(i / 2) / 2.0F;
//			float f3 = f2 + 0.4995F;
//			float f4 = 1.0F * this.size;
//			float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
//			float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
//			float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
//			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//			GlStateManager.disableLighting();
//			RenderHelper.disableStandardItemLighting();
//			buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
//			buffer.pos((f5 - rotationX * f4 - rotationXY * f4), (f6 - rotationZ * f4 * 0.5F), (f7 - rotationYZ * f4 - rotationXZ * f4)).tex(f1, f3).endVertex();
//			buffer.pos((f5 - rotationX * f4 + rotationXY * f4), (f6 + rotationZ * f4 * 0.5F), (f7 - rotationYZ * f4 + rotationXZ * f4)).tex(f1, f2).endVertex();
//			buffer.pos((f5 + rotationX * f4 + rotationXY * f4), (f6 + rotationZ * f4 * 0.5F), (f7 + rotationYZ * f4 + rotationXZ * f4)).tex(f, f2).endVertex();
//			buffer.pos((f5 + rotationX * f4 - rotationXY * f4), (f6 - rotationZ * f4 * 0.5F), (f7 + rotationYZ * f4 - rotationXZ * f4)).tex(f, f3).endVertex();
//			Tessellator.getInstance().draw();
//			GlStateManager.enableLighting();
//		}
//	}
//}
