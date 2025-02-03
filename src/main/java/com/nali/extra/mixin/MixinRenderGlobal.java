package com.nali.extra.mixin;

import com.nali.extra.ExtraColor;
import com.nali.extra.ExtraCubeLine;
import com.nali.list.da.BothDaExtraSky;
import com.nali.list.render.RenderExtraSky;
import com.nali.render.RenderO;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(value = RenderGlobal.class, priority = Integer.MAX_VALUE)
@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal
{
	@Shadow
	public static void drawSelectionBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha)
	{
	}

	@Shadow private ViewFrustum viewFrustum;

	//*extra-s0
	private static float X_ANGLE/* = 359.0F*/, Z_ANGLE/* = 359.0F*/;
//	private static BoxImage BOXIMAGE;
	private static RenderExtraSky RENDEREXTRASKY = new RenderExtraSky();
//	private static float[] GL_CURRENT_COLOR = new float[4];

//	@Shadow
//	private int renderDistanceChunks;
//
////    @Shadow @Final private Minecraft mc;
//
//	@Redirect(method = "loadRenderers", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderDistanceChunks:I"))
//	private void nali_loadRenderers(RenderGlobal instance, int value)
//	{
//		this.renderDistanceChunks = 64*2;
//	}
//
//	@Redirect(method = "setupTerrain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;loadRenderers()V"))
//	private void nali_setupTerrain(RenderGlobal instance)
//	{
//	}
//
//	@ModifyVariable(method = "renderWorldBorder", at = @At(value = "STORE", ordinal = 3))
//	private double nali_renderWorldBorder(CallbackInfo ci)
//	{
//		return 64;
//	}

//	@Inject(method = "renderSky(FI)V", at = @At(value = "HEAD"), cancellable = true)
//	private void nali_renderSky(float partialTicks, int pass, CallbackInfo ci)
	@Overwrite
	public void renderSky(float partialTicks, int pass)
	{
//		RenderO.FLOATBUFFER.limit(16);
//		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, RenderO.FLOATBUFFER);
//		GL_CURRENT_COLOR[0] = RenderO.FLOATBUFFER.get(0);
//		GL_CURRENT_COLOR[1] = RenderO.FLOATBUFFER.get(1);
//		GL_CURRENT_COLOR[2] = RenderO.FLOATBUFFER.get(2);
//		GL_CURRENT_COLOR[3] = RenderO.FLOATBUFFER.get(3);

		Minecraft minecraft = Minecraft.getMinecraft();
		if (!minecraft.isGamePaused())
		{
			float tick_length = minecraft.getTickLength();
			X_ANGLE += tick_length * 0.01F;
			Z_ANGLE += tick_length * 0.01F;
			X_ANGLE %= 360.0F;
			Z_ANGLE %= 360.0F;
//			Nali.warn("X_ANGLE " + X_ANGLE);
//			Nali.warn("Z_ANGLE " + Z_ANGLE);
		}
		//take draw free
		RenderO.take();

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		boolean gl_texture_2d = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glDepthMask(false);
		GL11.glPushMatrix();
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glRotatef(-90.0F + X_ANGLE, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(Z_ANGLE, 0.0F, 0.0F, 1.0F);
//		EntityPlayerSP entityplayersp = minecraft.player;
//		GL11.glTranslated(0.0D, (entityplayersp.prevPosY + (entityplayersp.posY - entityplayersp.prevPosY) * minecraft.getRenderPartialTicks()), 0.0D);
		GL11.glScalef(1000.0F, 1000.0F, 1000.0F);
//		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
//		GL11.glDisable(GL11.GL_CULL_FACE);
		RENDEREXTRASKY.draw(BothDaExtraSky.IDA);
//		GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		if (gl_texture_2d)
//		{
//			GL11.glEnable(GL11.GL_TEXTURE_2D);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_TEXTURE_2D);
//		}
		GL11.glPopMatrix();
//		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
//		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
//		RenderO.FLOATBUFFER.limit(16);
//		GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF, RenderO.FLOATBUFFER);
//		float gl_alpha_test_ref = RenderO.FLOATBUFFER.get(0);
//		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
////		boolean gl_alpha_test = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
//
////		GL11.glDisable(GL11.GL_ALPHA_TEST);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, RenderO.BYTEBUFFER);
//		int gl_depth_writemask = RenderO.BYTEBUFFER.get(0);
//		GL11.glDepthMask(false);
//
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
//		int gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, RenderO.INTBUFFER);
//		int gl_blend_equation_alpha = RenderO.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, RenderO.INTBUFFER);
//		int gl_blend_src_rgb = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, RenderO.INTBUFFER);
//		int gl_blend_src_alpha = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, RenderO.INTBUFFER);
//		int gl_blend_dst_rgb = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, RenderO.INTBUFFER);
//		int gl_blend_dst_alpha = RenderO.INTBUFFER.get(0);
//
//		GL11.glEnable(GL11.GL_BLEND);
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//
//
////		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
////		int gl_current_program = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
////		int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
////		int gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		int gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
////		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
////
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP + 1));
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
////
//////		if (BOXIMAGE == null)
//////		{
//////			int width = Display.getWidth();
//////			int height = Display.getHeight();
//////			BOXIMAGE = new BoxImage();
//////			BOXIMAGE.x0 = 0;
//////			BOXIMAGE.y0 = 0;
//////			BOXIMAGE.x1 = width;
//////			BOXIMAGE.y1 = height;
//////
//////			BOXIMAGE.u0 = 0;
//////			BOXIMAGE.v0 = 0;
//////			BOXIMAGE.u1 = 1920;
//////			BOXIMAGE.v1 = 1080;
//////
//////			BOXIMAGE.v_width = width;
//////			BOXIMAGE.v_height = height;
//////			BOXIMAGE.t_width = 1920;
//////			BOXIMAGE.t_height = 1080;
//////			BOXIMAGE.gen();
//////		}
////
////		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
////		OpenGlHelper.glUseProgram(rs.program);
////		int v = rs.attriblocation_int_array[0];
////		GL20.glEnableVertexAttribArray(v);
////
//////		BOXIMAGE.draw(rs, new float[2], new float[]{1.0F, 1.0F, 1.0F, 0.1F});
////
////		GL20.glDisableVertexAttribArray(v);
////
////		OpenGlHelper.glUseProgram(gl_current_program);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_0);
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);


//		GL11.glAlphaFunc(GL11.GL_GREATER, gl_alpha_test_ref);
////		if (gl_alpha_test)
////		{
////			GL11.glEnable(GL11.GL_ALPHA_TEST);
////		}
////		else
////		{
////			GL11.glDisable(GL11.GL_ALPHA_TEST);
////		}
//		if (gl_blend)
//		{
//			GL11.glEnable(GL11.GL_BLEND);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_BLEND);
//		}
//		GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
//		GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
//
//		GL11.glDepthMask(gl_depth_writemask == 1);
//		if (gl_depth_test)
//		{
//			GL11.glEnable(GL11.GL_DEPTH_TEST);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_DEPTH_TEST);
//		}

		RenderO.free();
//		ci.cancel();
	}
	//*extra-e0

	//disable weather
	@Overwrite
	public void renderWorldBorder(Entity entityIn, float partialTicks)
	{
	}

	@Redirect(method = "drawSelectionBox", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/RayTraceResult;typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;"))
	public RayTraceResult.Type nali_extra_drawSelectionBox_Type(RayTraceResult instance)
	{
		return RayTraceResult.Type.BLOCK;
	}

	@Redirect(method = "drawSelectionBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"))
	public Material nali_extra_drawSelectionBox_Material(IBlockState instance)
	{
		return Material.GRASS;
	}

	@Redirect(method = "drawSelectionBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawSelectionBoundingBox(Lnet/minecraft/util/math/AxisAlignedBB;FFFF)V"))
	public void nali_extra_drawSelectionBox_Material(AxisAlignedBB box, float red, float green, float blue, float alpha)
	{
//		if (Minecraft.getMinecraft().objectMouseOver.entityHit != null)
//		{
//			alpha = 1.0F;
////			GlStateManager.glLineWidth(20.0F);
//		}
////		else
////		{
////			alpha = 0.25F;
////		}
		drawSelectionBoundingBox(box, 1.0F, 1.0F, 1.0F, 1.0F);
	}

//	@Overwrite
//	public static void drawSelectionBoundingBox(AxisAlignedBB box, float red, float green, float blue, float alpha)
//	{
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
////		ExtraCubeLine.update((float)minX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ);
////		ExtraCubeLine.draw();
//	}

//	@Overwrite
//	public static void drawBoundingBox(BufferBuilder buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha)
//	{
//////		GlStateManager.color(ExtraColor.RED, ExtraColor.GREEN, ExtraColor.BLUE, 1.0F);
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//////		float c = Minecraft.getSystemTime() % 1000 / 1000.0F;
//////		int color = Color.HSBtoRGB(c, 1.0F, 1.0F);
//////		GlStateManager.color(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, 1.0F);
//////		GL11.glColor4f(ExtraColor.RED, ExtraColor.GREEN, ExtraColor.BLUE, 1.0F);
////		ExtraCubeLine.update((float)minX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ);
////		ExtraCubeLine.draw();
//
////		GL11.glBegin(GL11.GL_LINES);
////
////		//b
////		GL11.glVertex3d(minX, minY, minZ);
////		GL11.glVertex3d(maxX, minY, minZ);
////		GL11.glVertex3d(maxX, minY, minZ);
////		GL11.glVertex3d(maxX, minY, maxZ);
////		GL11.glVertex3d(maxX, minY, maxZ);
////		GL11.glVertex3d(minX, minY, maxZ);
////		GL11.glVertex3d(minX, minY, maxZ);
////		GL11.glVertex3d(minX, minY, minZ);
////
////		//t
////		GL11.glVertex3d(minX, maxY, minZ);
////		GL11.glVertex3d(maxX, maxY, minZ);
////		GL11.glVertex3d(maxX, maxY, minZ);
////		GL11.glVertex3d(maxX, maxY, maxZ);
////		GL11.glVertex3d(maxX, maxY, maxZ);
////		GL11.glVertex3d(minX, maxY, maxZ);
////		GL11.glVertex3d(minX, maxY, maxZ);
////		GL11.glVertex3d(minX, maxY, minZ);
////
////		//s
////		//north
////		GL11.glVertex3d(minX, minY, minZ);
////		GL11.glVertex3d(minX, maxY, minZ);
////		GL11.glVertex3d(maxX, minY, minZ);
////		GL11.glVertex3d(maxX, maxY, minZ);
////		//south
////		GL11.glVertex3d(maxX, minY, maxZ);
////		GL11.glVertex3d(maxX, maxY, maxZ);
////		GL11.glVertex3d(minX, minY, maxZ);
////		GL11.glVertex3d(minX, maxY, maxZ);
////
////		GL11.glEnd();
//	}

//	@Redirect(method = "getVisibleFacings", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;isOpaqueCube()Z"))
//	private boolean nali_extra_getVisibleFacings(IBlockState instance)
//	{
////		return true;
//		if (instance.getBlock() instanceof BlockLeaves)
//		{
//			return true;
//		}
//		return instance.isOpaqueCube();
//	}

//	@Overwrite
//	private boolean isOutlineActive(Entity entityIn, Entity viewer, ICamera camera)
//	{
//		boolean flag = entityIn.isGlowing();
//		if (flag)
//		{
////			AxisAlignedBB viewer_axisalignedbb = viewer.getEntityBoundingBox();
//			AxisAlignedBB boundingBox = entityIn.getEntityBoundingBox();
////			GL11.glDisable(GL11.GL_DEPTH_TEST);
//			float c = Minecraft.getSystemTime() % 1000 / 1000.0F;
//			Color color = Color.getHSBColor(c, 1.0F, 1.0F);
//			GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
//			ExtraCubeLine.update(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
////			ExtraCubeLine.update(viewer_axisalignedbb.minX - boundingBox.minX, viewer_axisalignedbb.minY - boundingBox.minY, viewer_axisalignedbb.minZ - boundingBox.minZ, viewer_axisalignedbb.maxX - boundingBox.maxX, viewer_axisalignedbb.maxY - boundingBox.maxY, viewer_axisalignedbb.maxZ - boundingBox.maxZ);
//			ExtraCubeLine.draw();
////			GL11.glEnable(GL11.GL_DEPTH_TEST);
//		}
//		return flag;
//	}

	//switch to buffer with rgb
	@Overwrite
	public static void drawBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha)
	{
		if (!Minecraft.getMinecraft().gameSettings.hideGUI)
		{
			//			AxisAlignedBB viewer_axisalignedbb = viewer.getEntityBoundingBox();
	//		AxisAlignedBB boundingBox = entityIn.getEntityBoundingBox();
	//			GL11.glDisable(GL11.GL_DEPTH_TEST);
			if (red == 1.0F && green == 1.0F && blue == 1.0F/* && alpha == 1.0F*/)
			{
				GlStateManager.color(ExtraColor.RED, ExtraColor.GREEN, ExtraColor.BLUE, 1.0F);
			}
			else
			{
				GlStateManager.color(red, green, blue, alpha);
			}
			ExtraCubeLine.update((float)minX, (float)minY, (float)minZ, (float)maxX, (float)maxY, (float)maxZ);
	//		ExtraCubeLine.update(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	//			ExtraCubeLine.update(viewer_axisalignedbb.minX - boundingBox.minX, viewer_axisalignedbb.minY - boundingBox.minY, viewer_axisalignedbb.minZ - boundingBox.minZ, viewer_axisalignedbb.maxX - boundingBox.maxX, viewer_axisalignedbb.maxY - boundingBox.maxY, viewer_axisalignedbb.maxZ - boundingBox.maxZ);
			ExtraCubeLine.draw();
	//			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
	}

	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isPlayerSleeping()Z"))
	private boolean nali_extra_renderEntities(EntityLivingBase instance)
	{
		return false;
	}

//	@Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 1))
//	private int nali_extra_renderEntities(List instance)
//	{
//		return 0;
//	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z", shift = At.Shift.BEFORE))
	private void nali_extra_renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{

	}

	//force chunk render
	private static int
		YAW,
		PITCH;
	private static long
		TIME;

//	@Inject(method = "setupTerrain", at = @At(value = "HEAD"))
	@Inject(method = "setupTerrain", at = @At(value = "TAIL"))
	private void nali_extra_setupTerrain(Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator, CallbackInfo ci)
	{
		int yaw = (int)viewEntity.rotationYaw;
		int pitch = (int)viewEntity.rotationPitch;
		if (YAW != yaw || PITCH != pitch)
		{
			long time = Minecraft.getSystemTime();
			if (time - TIME >= 1000)
			{
				TIME = time;
				YAW = yaw;
				PITCH = pitch;
	//				updateChunkPositions(this.viewFrustum, viewEntity.posX, viewEntity.posZ);
	//				this.loadRenderers();
	//				updateRenderChunk();
				for (RenderChunk renderchunk : this.viewFrustum.renderChunks)
				{
					if (renderchunk != null)
					{
						renderchunk.setNeedsUpdate(false);
					}
				}
			}
		}
	}

//	@Inject(method = "setupTerrain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ViewFrustum;updateChunkPositions(DD)V", shift = At.Shift.AFTER))
//	private void nali_extra_setupTerrainC(Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator, CallbackInfo ci)
//	{
//		YAW = -YAW;
//		for (RenderChunk renderchunk : this.viewFrustum.renderChunks)
//		{
//			if (renderchunk != null)
//			{
//				renderchunk.setNeedsUpdate(false);
//			}
//		}
//	}

//	private static void updateChunkPositions(ViewFrustum viewfrustum, double viewEntityX, double viewEntityZ)
//	{
//		IMixinViewFrustum imixinviewfrustum = (IMixinViewFrustum)viewfrustum;
//		int baseX = MathHelper.floor(viewEntityX) - (8 * 16);
//		int baseZ = MathHelper.floor(viewEntityZ) - (8 * 16);
//		int chunkSize = imixinviewfrustum.countChunksX() * 16;
//
//		for (int chunkX = 0; chunkX < imixinviewfrustum.countChunksX(); ++chunkX)
//		{
//			int worldX = imixinviewfrustum.GOgetBaseCoordinate(baseX, chunkSize, chunkX);
//
//			for (int chunkZ = 0; chunkZ < imixinviewfrustum.countChunksZ(); ++chunkZ)
//			{
//				int worldZ = imixinviewfrustum.GOgetBaseCoordinate(baseZ, chunkSize, chunkZ);
//
//				for (int chunkY = 0; chunkY < imixinviewfrustum.countChunksY(); ++chunkY)
//				{
//					int worldY = chunkY * 16;
//					RenderChunk renderChunk = viewfrustum.renderChunks[(chunkZ * imixinviewfrustum.countChunksY() + chunkY) * imixinviewfrustum.countChunksX() + chunkX];
//
//					if (renderChunk != null)
//					{
//						renderChunk.setPosition(worldX, worldY, worldZ);
//					}
//				}
//			}
//		}
//	}

//	private void updateRenderChunk()
//	{
////		EntityPlayerSP entityplayersp = this.mc.player;
////
////		int renderdistancechunks = this.mc.gameSettings.renderDistanceChunks;
////
////		for (int dx = -renderdistancechunks; dx <= renderdistancechunks; dx++)
////		{
////			for (int dz = -renderdistancechunks; dz <= renderdistancechunks; dz++)
////			{
////				int chunkX = entityplayersp.chunkCoordX + dx;
////				int chunkZ = entityplayersp.chunkCoordZ + dz;
////
////				RenderChunk renderchunk = this.getRenderChunk(chunkX, chunkZ);
////				if (renderchunk != null)
////				{
////					renderchunk.setNeedsUpdate(false);
////				}
////			}
////		}
//	}
//
////	private RenderChunk getRenderChunk(int chunkX, int chunkZ)
////	{
//////		if (this.viewFrustum != null)
//////		{
////		for (RenderChunk renderchunk : this.viewFrustum.renderChunks)
////		{
//////			if (renderchunk != null)
//////			{
////			BlockPos pos = renderchunk.getPosition();
////			if (pos.getX() >> 4 == chunkX && pos.getZ() >> 4 == chunkZ)
////			{
////				return renderchunk;
////			}
//////			}
////		}
//////		}
////		return null;
////	}
}
