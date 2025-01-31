package com.nali.extra.mixin;

import com.nali.extra.Extra;
import com.nali.small.Small;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//*extra
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
	@Shadow
	private float farPlaneDistance;

	@Shadow @Final private Minecraft mc;

//	@Shadow private Entity pointedEntity;

//	@Shadow private boolean lightmapUpdateNeeded;

//	@Mutable
	@Shadow @Final private DynamicTexture lightmapTexture;

//	@Mutable
	@Shadow @Final private int[] lightmapColors;

	@Shadow private float thirdPersonDistancePrev;
//	private static float ROTATE_Y;
	private static byte STATE;//1key 4swim 8blink
	private static long LAST_TIME;
	private static float PARTIALTICKS;

//	@Mutable
//	@Shadow @Final private ResourceLocation locationLightMap;

	//won't work with Nothirium
	@Overwrite
	private void setupFog(int startCoords, float partialTicks)
	{

	}

	@Overwrite
	private void hurtCameraEffect(float partialTicks)
	{

	}

	@Overwrite
	private float getFOVModifier(float partialTicks, boolean useFOVSetting)
	{
		return 90.0F;
	}

	@Redirect(method = "setupCameraTransform", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 0))
	private void nali_extra_setupCameraTransform(EntityRenderer instance, float value)
	{
		this.farPlaneDistance = 64 * 16;
//		this.farPlaneDistance = 64 * 64 * 64 * 64 * 64;
	}

//	@Redirect(method = "getMouseOver", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;", ordinal = 0))
//	private void nali_getMouseOver(Minecraft instance, RayTraceResult value)
//	{
//		Nali.warn("RayTraceResult " + value);
//		if (value != null || value.typeOfHit == RayTraceResult.Type.MISS)
//		{
//			this.mc.objectMouseOver = new RayTraceResult(value.hitVec, value.sideHit, value.getBlockPos());
//			Nali.warn("new RayTraceResult " + this.mc.objectMouseOver);
//		}
//	}

	@Overwrite
	private boolean isDrawBlockOutline()
	{
		return this.mc.objectMouseOver != null && this.mc.objectMouseOver.getBlockPos() != null;
	}

	@Redirect(method = "getMouseOver", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;", ordinal = 0))
	private void nali_extra_getMouseOver(Minecraft instance, RayTraceResult value)
	{
		if (value != null)
		{
			this.mc.objectMouseOver = value;
			if (value.typeOfHit == RayTraceResult.Type.MISS)
			{
				value.typeOfHit = RayTraceResult.Type.BLOCK;
			}
		}
	}

	//remove shader framebuffer
	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 0))
	private void nali_extra_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
	{
		PARTIALTICKS = partialTicks;
//		GlStateManager.enableAlpha();
//		GlStateManager.disableBlend();
	}

	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 0))
	private int nali_extra_renderWorldPassL0(RenderGlobal instance, BlockRenderLayer k, double d0, int d1, Entity d2)
	{
		if ((Small.FLAG & 1) == 0)
		{
			GlStateManager.enableAlpha();
			GlStateManager.disableBlend();
			return this.mc.renderGlobal.renderBlockLayer(BlockRenderLayer.SOLID, d0, d1, d2);
		}
		else
		{
			GlStateManager.enableBlend();
			int i = this.mc.renderGlobal.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, d0, d1, d2);
			GlStateManager.disableBlend();
			return i;
		}
	}

	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 3))
	private int nali_extra_renderWorldPassL2(RenderGlobal instance, BlockRenderLayer k, double d0, int d1, Entity d2)
	{
		if ((Small.FLAG & 1) == 0)
		{
//			GlStateManager.enableBlend();
			return this.mc.renderGlobal.renderBlockLayer(BlockRenderLayer.TRANSLUCENT, d0, d1, d2);
		}
		else
		{
			GlStateManager.enableAlpha();
			GlStateManager.disableBlend();
			return this.mc.renderGlobal.renderBlockLayer(BlockRenderLayer.SOLID, d0, d1, d2);
		}
	}

//	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 1))
//	private int nali_extra_renderWorldPassCM(RenderGlobal instance, BlockRenderLayer k, double d0, int d1, Entity d2)
//	{
//		return d1;
//	}
//
//	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 2))
//	private int nali_extra_renderWorldPassC(RenderGlobal instance, BlockRenderLayer k, double d0, int d1, Entity d2)
//	{
//		return d1;
//	}

	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isInsideOfMaterial(Lnet/minecraft/block/material/Material;)Z"))
	private boolean nali_extra_renderWorldPass(Entity instance, Material blockpos)
	{
		return this.mc.gameSettings.hideGUI || !(instance instanceof EntityPlayer);
	}

//	//*extra fix transparent
//	@Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;depthMask(Z)V", ordinal = 2))
//	private void nali_extra_renderWorldPass2(boolean flagIn)
//	{
//	}

	//display box on entity
//	@Redirect(method = "getMouseOver", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;objectMouseOver:Lnet/minecraft/util/math/RayTraceResult;", ordinal = 5))
//	private void nali_getMouseOver5(Minecraft instance, RayTraceResult value)
//	{
//		((IMixinRayTraceResult)value).blockPos(new BlockPos(value.hitVec));
//		this.mc.objectMouseOver = value;
////		this.mc.objectMouseOver.sideHit = getEnumFacing(this.mc.player.getLookVec());
//	}

//	private static EnumFacing getEnumFacing(Vec3d look_vec3d)
//	{
//		double absX = Math.abs(look_vec3d.x);
//		double absY = Math.abs(look_vec3d.y);
//		double absZ = Math.abs(look_vec3d.z);
//
//		if (absX > absY && absX > absZ)
//		{
//			return look_vec3d.x > 0 ? EnumFacing.EAST : EnumFacing.WEST;
//		}
//		else if (absY > absX && absY > absZ)
//		{
//			return look_vec3d.y > 0 ? EnumFacing.UP : EnumFacing.DOWN;
//		}
//		else
//		{
//			return look_vec3d.z > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;
//		}
//	}

//	//	private static BoxImage BOXIMAGE;
//	private static RenderSky RENDERSKY = new RenderSky();
//
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 6))
//	private void nali_renderWorldPassE(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//	{
//		GL11.glDisable(GL11.GL_FOG);
//
////take draw free
//		RenderO.take();
//		GL11.glDepthMask(false);
//		GL11.glPushMatrix();
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
//		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
////		Minecraft minecraft = Minecraft.getMinecraft();
////		EntityPlayerSP entityplayersp = minecraft.player;
////		GL11.glTranslated(0.0D, (entityplayersp.prevPosY + (entityplayersp.posY - entityplayersp.prevPosY) * minecraft.getRenderPartialTicks()), 0.0D);
//		GL11.glScalef(200.0F, -200.0F, 200.0F);
////		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		RENDERSKY.draw(BothDaSky.IDA);
//		GL11.glPopMatrix();
////		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
////		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
////		RenderO.FLOATBUFFER.limit(16);
////		GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF, RenderO.FLOATBUFFER);
////		float gl_alpha_test_ref = RenderO.FLOATBUFFER.get(0);
////		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
//////		boolean gl_alpha_test = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
////
//////		GL11.glDisable(GL11.GL_ALPHA_TEST);
////		GL11.glEnable(GL11.GL_DEPTH_TEST);
////		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, RenderO.BYTEBUFFER);
////		int gl_depth_writemask = RenderO.BYTEBUFFER.get(0);
////		GL11.glDepthMask(false);
////
////		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
////		int gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_equation_alpha = RenderO.INTBUFFER.get(0);
////
////		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, RenderO.INTBUFFER);
////		int gl_blend_src_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_src_alpha = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, RenderO.INTBUFFER);
////		int gl_blend_dst_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_dst_alpha = RenderO.INTBUFFER.get(0);
////
////		GL11.glEnable(GL11.GL_BLEND);
////		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
////		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
////
////
//////		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
//////		int gl_current_program = RenderO.INTBUFFER.get(0);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
//////		int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
//////		int gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		int gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//////		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
//////
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP + 1));
////////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//////
////////		if (BOXIMAGE == null)
////////		{
////////			int width = Display.getWidth();
////////			int height = Display.getHeight();
////////			BOXIMAGE = new BoxImage();
////////			BOXIMAGE.x0 = 0;
////////			BOXIMAGE.y0 = 0;
////////			BOXIMAGE.x1 = width;
////////			BOXIMAGE.y1 = height;
////////
////////			BOXIMAGE.u0 = 0;
////////			BOXIMAGE.v0 = 0;
////////			BOXIMAGE.u1 = 1920;
////////			BOXIMAGE.v1 = 1080;
////////
////////			BOXIMAGE.v_width = width;
////////			BOXIMAGE.v_height = height;
////////			BOXIMAGE.t_width = 1920;
////////			BOXIMAGE.t_height = 1080;
////////			BOXIMAGE.gen();
////////		}
//////
//////		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
//////		OpenGlHelper.glUseProgram(rs.program);
//////		int v = rs.attriblocation_int_array[0];
//////		GL20.glEnableVertexAttribArray(v);
//////
////////		BOXIMAGE.draw(rs, new float[2], new float[]{1.0F, 1.0F, 1.0F, 0.1F});
//////
//////		GL20.glDisableVertexAttribArray(v);
//////
//////		OpenGlHelper.glUseProgram(gl_current_program);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_0);
//////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//
//
////		GL11.glAlphaFunc(GL11.GL_GREATER, gl_alpha_test_ref);
//////		if (gl_alpha_test)
//////		{
//////			GL11.glEnable(GL11.GL_ALPHA_TEST);
//////		}
//////		else
//////		{
//////			GL11.glDisable(GL11.GL_ALPHA_TEST);
//////		}
////		if (gl_blend)
////		{
////			GL11.glEnable(GL11.GL_BLEND);
////		}
////		else
////		{
////			GL11.glDisable(GL11.GL_BLEND);
////		}
////		GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
////		GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
////
////		GL11.glDepthMask(gl_depth_writemask == 1);
////		if (gl_depth_test)
////		{
////			GL11.glEnable(GL11.GL_DEPTH_TEST);
////		}
////		else
////		{
////			GL11.glDisable(GL11.GL_DEPTH_TEST);
////		}
//
////		ci.cancel();
//		RenderO.free();
//	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;F)V", shift = At.Shift.AFTER))
////	private void nali_renderWorldPassE(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		RenderO.take();
////
////		draw(FIRST_MODEL_MAP);
////
////		if (!SECOND_MODEL_MAP.isEmpty())
////		{
////			GL11.glDepthMask(false);
////			draw(SECOND_MODEL_MAP);
////		}
////
////		RenderO.free();
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 2))
////	private void nali_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
//////		FIRST_MODEL_MAP.clear();
//////		SECOND_MODEL_MAP.clear();
//////		DRAWWORLDDATA_LIST.clear();
//////		DATA_SIZE = 0;
////
////		DrawWorld.run();
//////		Nali.LOGGER.info("END");
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
////	private void nali_renderWorldPassA(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		if (!SECOND_MODEL_MAP.isEmpty())
////		{
////			RenderO.take();
////
////			GL11.glDepthMask(false);
////			draw(SECOND_MODEL_MAP);
////			SECOND_MODEL_MAP.clear();
////
////			RenderO.free();
////		}
////
////		DRAWWORLDDATA_LIST.clear();
////		DATA_SIZE = 0;
////		FIRST_MODEL_MAP.clear();
////	}

	//s0-remove light
	@Inject(method = "<init>", at = @At(value = "TAIL"))
	private void nali_extra_init(Minecraft mcIn, IResourceManager resourceManagerIn, CallbackInfo ci)
	{
//		mcIn.getTextureManager().deleteTexture(this.locationLightMap);
//
//		this.lightmapTexture = new DynamicTexture(1, 1);
//		this.locationLightMap = mcIn.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
//		this.lightmapColors = this.lightmapTexture.getTextureData();
//
//		this.lightmapColors[0] = 0xFFFFFFFF;
		for (int i = 0; i < 16*16; ++i)
		{
			//0xFF000000
//					this.lightmapColors[i] = -16777216 | 255 << 16 | 255 << 8 | 255;
			this.lightmapColors[i] = 0xFFFFFFFF;
		}

		this.lightmapTexture.updateDynamicTexture();
	}

	@Overwrite
	private void updateLightmap(float partialTicks)
	{
//		if (this.lightmapUpdateNeeded)
//		{
//			World world = this.mc.world;
//
//			if (world != null)
//			{
//				this.lightmapUpdateNeeded = false;
//			}
//		}
	}

	@Overwrite
	private void updateTorchFlicker()
	{

	}
	//e0-remove light

	//disable weather
	@Overwrite
	private void renderCloudsCheck(RenderGlobal renderGlobalIn, float partialTicks, int pass, double x, double y, double z)
	{
	}

	//disable weather
	@Overwrite
	private void addRainParticles()
	{
	}

//	//disable update
//	@Overwrite
//	public void updateRenderer()
//	{
//	}

	//disable weather
	@Overwrite
	protected void renderRainSnow(float partialTicks)
	{
	}

	//fp
	@Redirect(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z"))
	private boolean renderWorldPass(EntityRenderer instance)
	{
		if (!this.mc.gameSettings.hideGUI)
		{
			if ((Small.FLAG & 1) == 1)
			{
				EntityPlayerSP entityplayersp = this.mc.player;

				//support aquaacrobatics
				boolean swim = /*((IMixinEntity)entityplayersp).GOgetFlag(2) || */((IMixinEntity)entityplayersp).GOgetFlag(4) || entityplayersp.height < 1.8F / 2.0F;
				STATE |= swim ? 4 : 0;
				if ((STATE & 4) == 4)
				{
					if (swim)
					{
						LAST_TIME = Minecraft.getSystemTime();
					}
					else
					{
						long new_time = Minecraft.getSystemTime();
						if (new_time - LAST_TIME >= 1000)//1sec
						{
							LAST_TIME = new_time;
							STATE &= 255-4;
						}
					}
				}

//				GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
//				if ((Extra.FP & 2) == 0)
				if (this.mc.gameSettings.thirdPersonView == 0 && !entityplayersp.isElytraFlying() && !entityplayersp.isPlayerSleeping() && !swim && (STATE & 4) == 0)
				{
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					Extra.FP |= 1;
//					GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
//					GlStateManager.bindTexture(0);
					this.mc.getRenderManager().renderEntity(this.mc.getRenderViewEntity(), 0, 0, 0, 0, PARTIALTICKS, false);
					Extra.FP &= 255-1;
				}
//				Extra.FP ^= 2;
			}
		}
		return false;
	}

//	@Overwrite
//	private void renderHand(float partialTicks, int pass)
//	{
//
//	}

	//tp
	//clean sleep
	@Inject(method = "orientCamera", at = @At("HEAD"), cancellable = true)
	private void nali_extra_orientCamera(float partialTicks, CallbackInfo ci)
	{
		float rotationYaw;
		float prevRotationYaw;
		float rotationPitch;
		float prevRotationPitch;

		Entity entity = this.mc.getRenderViewEntity();
		float f = entity.getEyeHeight();
		double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
		double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + (double)f;
		double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;

//		switch (this.mc.gameSettings.thirdPersonView)
//		{
//			case 0:
//				rotationYaw = entity.rotationYaw;
//				prevRotationYaw = entity.prevRotationYaw;
//				rotationPitch = entity.rotationPitch;
//				prevRotationPitch = entity.prevRotationPitch;
//				break;
//			case 1:
//				rotationYaw = Extra.YAW;
//				prevRotationYaw = Extra.P_YAW;
//				rotationPitch = Extra.PITCH;
//				prevRotationPitch = Extra.P_PITCH;
//				break;
//			default://case 2:
//				rotationYaw = 0;
//				prevRotationYaw = 0;
//				rotationPitch = -entity.rotationPitch;
//				prevRotationPitch = -entity.prevRotationPitch;
//		}

		/*if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPlayerSleeping())
		{
			f = (float)((double)f + 1.0D);
			GlStateManager.translate(0.0F, 0.3F, 0.0F);

			if (!this.mc.gameSettings.debugCamEnable)
			{
				BlockPos blockpos = new BlockPos(entity);
				IBlockState iblockstate = this.mc.world.getBlockState(blockpos);
				net.minecraftforge.client.ForgeHooksClient.orientBedCamera(this.mc.world, blockpos, iblockstate, entity);

				GlStateManager.rotate(prevRotationYaw + (rotationYaw - prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
				GlStateManager.rotate(prevRotationPitch + (rotationPitch - prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
			}
		}
		else */if (this.mc.gameSettings.thirdPersonView == 1)
		{
			rotationYaw = Extra.YAW;
			prevRotationYaw = Extra.P_YAW;
			rotationPitch = Extra.PITCH;
			prevRotationPitch = Extra.P_PITCH;

			double d3 = this.thirdPersonDistancePrev + (4.0F - this.thirdPersonDistancePrev) * partialTicks;

			if (this.mc.gameSettings.debugCamEnable)
			{
				GlStateManager.translate(0.0F, 0.0F, (float)(-d3));
			}
			else
			{
				float f1 = rotationYaw;
				float f2 = rotationPitch;

				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				{
					entity.rotationYaw -= partialTicks;
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				{
					entity.rotationYaw += partialTicks;
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_UP))
				{
					entity.rotationPitch -= partialTicks;
					entity.rotationPitch = MathHelper.clamp(entity.rotationPitch, -90.0F, 90.0F);
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				{
					entity.rotationPitch += partialTicks;
					entity.rotationPitch = MathHelper.clamp(entity.rotationPitch, -90.0F, 90.0F);
				}

//				if (this.mc.gameSettings.thirdPersonView == 2)
//				{
//					if (Keyboard.isKeyDown(Keyboard.KEY_TAB))
//					{
//						if ((STATE & 1) == 0)
//						{
//							ROTATE_Y += 45;
//							ROTATE_Y %= 360.0F;
//							STATE |= 1;
//						}
//					}
//					else
//					{
//						STATE &= 255-1;
//					}
//
//					f1 += ROTATE_Y;
//				}

				double d4 = (double)(-MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F)) * d3;
				double d5 = (double)(MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F)) * d3;
				double d6 = (double)(-MathHelper.sin(f2 * 0.017453292F)) * d3;

				for (int i = 0; i < 8; ++i)
				{
					float f3 = (float)((i & 1) * 2 - 1);
					float f4 = (float)((i >> 1 & 1) * 2 - 1);
					float f5 = (float)((i >> 2 & 1) * 2 - 1);
					f3 = f3 * 0.1F;
					f4 = f4 * 0.1F;
					f5 = f5 * 0.1F;
					RayTraceResult raytraceresult = this.mc.world.rayTraceBlocks(new Vec3d(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), new Vec3d(d0 - d4 + (double)f3 + (double)f5, d1 - d6 + (double)f4, d2 - d5 + (double)f5));

					if (raytraceresult != null)
					{
						double d7 = raytraceresult.hitVec.distanceTo(new Vec3d(d0, d1, d2));

						if (d7 < d3)
						{
							d3 = d7;
						}
					}
				}

//				if (this.mc.gameSettings.thirdPersonView == 2)
//				{
//					GlStateManager.rotate(ROTATE_Y, 0.0F, 1.0F, 0.0F);
//				}

				GlStateManager.rotate(rotationPitch - f2, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(rotationYaw - f1, 0.0F, 1.0F, 0.0F);
				GlStateManager.translate(0.0F, 0.0F, (float)(-d3));
				GlStateManager.rotate(f1 - rotationYaw, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(f2 - rotationPitch, 1.0F, 0.0F, 0.0F);
			}
		}
		else
		{
			rotationYaw = entity.rotationYaw;
			prevRotationYaw = entity.prevRotationYaw;
			rotationPitch = entity.rotationPitch;
			prevRotationPitch = entity.prevRotationPitch;
			GlStateManager.translate(0.0F, 0.0F, 0.05F);
		}

		if (!this.mc.gameSettings.debugCamEnable)
		{
			float yaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * partialTicks + 180.0F;
			float pitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * partialTicks;
			float roll = 0.0F;
			if (entity instanceof EntityAnimal)
			{
				EntityAnimal entityanimal = (EntityAnimal)entity;
				yaw = entityanimal.prevRotationYawHead + (entityanimal.rotationYawHead - entityanimal.prevRotationYawHead) * partialTicks + 180.0F;
			}
			IBlockState state = ActiveRenderInfo.getBlockStateAtEntityViewpoint(this.mc.world, entity, partialTicks);
			net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup event = new net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup((EntityRenderer)(Object)this, entity, state, partialTicks, yaw, pitch, roll);
			net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
			GlStateManager.rotate(event.getRoll(), 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(event.getPitch(), 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(event.getYaw(), 0.0F, 1.0F, 0.0F);
		}

		GlStateManager.translate(0.0F, -f, 0.0F);
		ci.cancel();
	}

	//clean gui
	@Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;drawScreen(Lnet/minecraft/client/gui/GuiScreen;IIF)V"))
	private void nali_extra_updateCameraAndRender(GuiScreen screen, int mouseX, int mouseY, float partialTicks)
	{
//		if ((STATE & 8) == 0)
		if ((Small.FLAG & 1) == 0)
		{
//			GlStateManager.enableBlend();
	//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
			ForgeHooksClient.drawScreen(this.mc.currentScreen, mouseX, mouseY, this.mc.getTickLength());
//			screen.drawScreen(mouseX, mouseY, partialTicks);
	//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//			GlStateManager.disableBlend();
		}

//		STATE ^= 8;
	}

//	@Overwrite
//	public void disableLightmap()
//	{
//	}
//
//	@Overwrite
//	public void enableLightmap()
//	{
//	}

//	@Redirect(method = "updateCameraAndRender", at = @At(value = "FIELD", target = "Lnet/minecraft/util/MouseHelper;deltaX:I"))
//	private int nali_extra_updateCameraAndRender(MouseHelper instance)
//	{
//		return this.mc.gameSettings.thirdPersonView == 0 ? instance.deltaX : 0;
//	}

	@Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;turn(FF)V"))
	private void nali_extra_updateCameraAndRender(EntityPlayerSP instance, float yaw, float pitch)
	{
		if (this.mc.gameSettings.thirdPersonView == 0)
		{
			instance.turn(yaw, pitch);
		}

		float f = Extra.PITCH;
		float f1 = Extra.YAW;
		Extra.YAW = (float)((double)Extra.YAW + (double)yaw * 0.15D);
		Extra.PITCH = (float)((double)Extra.PITCH - (double)pitch * 0.15D);
		Extra.PITCH = MathHelper.clamp(Extra.PITCH, -90.0F, 90.0F);
		Extra.P_PITCH += Extra.PITCH - f;
		Extra.P_YAW += Extra.YAW - f1;
	}
}
