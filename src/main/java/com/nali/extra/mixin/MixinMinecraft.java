package com.nali.extra.mixin;

import com.nali.extra.*;
import com.nali.list.render.RenderExtraSky;
import com.nali.small.Small;
import com.nali.small.SmallConfig;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.FrameTimer;
import net.minecraftforge.client.GuiIngameForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
	@Shadow protected abstract void clickMouse();

	@Shadow protected abstract void rightClickMouse();

//	@Shadow private int rightClickDelayTimer;
//
//	@Shadow private int leftClickCounter;

	@Shadow public GameSettings gameSettings;

	@Shadow public GuiIngame ingameGUI;

	@Shadow @Final public FrameTimer frameTimer;

	@Shadow private int fpsCounter;

	//*extra-s0
	//click
	@Redirect(method = "setIngameFocus", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I"))
	private void nali_extra_setIngameFocus(Minecraft instance, int value)
	{
//		this.leftClickCounter = ExtraConfig.LEFTCLICK_DELAY;
	}

	@Redirect(method = "clickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I", ordinal = 2))
	private void nali_extra_clickMouse(Minecraft instance, int value)
	{
//		this.leftClickCounter = ExtraConfig.LEFTCLICK_DELAY;
	}

	@Redirect(method = "runTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I", ordinal = 0))
	private void nali_extra_runTick(Minecraft instance, int value)
	{
//		this.leftClickCounter = ExtraConfig.LEFTCLICK_DELAY;
	}

//	@Redirect(method = "rightClickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;rightClickDelayTimer:I"))
//	private void nali_extra_rightClickMouse_rightClickDelayTimer(Minecraft instance, int value)
//	{
////		this.rightClickDelayTimer = ExtraConfig.RIGHTCLICK_DELAY;
//	}

//	@Redirect(method = "rightClickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/RayTraceResult;typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;", ordinal = 0))
//	private RayTraceResult.Type nali_rightClickMouse_Type(RayTraceResult instance)
//	{
//		return RayTraceResult.Type.BLOCK;
//	}

	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"))
	private Material nali_extra_rightClickMouse_Material(IBlockState instance)
	{
		return Material.GRASS;
	}

//	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"))
//	private boolean nali_rightClickMouse_getIsHittingBlock(PlayerControllerMP instance)
//	{
//		return false;
//	}
	//*extra-e0

	//remove CreativeTabs
	@Overwrite
	public void populateSearchTreeManager()
	{
	}

	//init gl
	@Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;setFramebufferColor(FFFF)V", shift = At.Shift.AFTER))
	private void nali_extra_init(CallbackInfo callbackinfo)
	{
		ExtraView.init();
		Display.sync(this.getLimitFramerate());
		RenderExtraSky.RENDEREXTRASKY = new RenderExtraSky();
//		if (!ExtraConfig.RAW_FPS)
		if (!SmallConfig.FAST_RAW_FPS)
		{
			ExtraFBO.init();
		}
//		ExtraGTime.init();
		ExtraCubeLine.init();
//		ExtraQuad.init();
		ExtraQuadLine.init();
	}

//	//clean gui
//	@Redirect(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;"))
//	private void nali_extra_init_gui(Minecraft instance, GuiIngame value)
//	{
//		this.ingameGUI = new GuiIngame(instance);
//	}

	@Inject(method = "runTick", at = @At(value = "HEAD"))
//	@Inject(method = "runGameLoop", at = @At("HEAD"))
	private void nali_extra_runTick(CallbackInfo callbackinfo)
	{
		ExtraColor.update();
	}

	@Redirect(method = "processKeyBinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isPressed()Z", ordinal = 12))
	private boolean processKeyBindsA(KeyBinding instance)
	{
		if (instance.isKeyDown())
		{
			this.clickMouse();
		}
		return false;
	}

	@Redirect(method = "processKeyBinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isPressed()Z", ordinal = 13))
	private boolean processKeyBindsP(KeyBinding instance)
	{
		if (instance.isKeyDown())
		{
			this.rightClickMouse();
		}
		return false;
	}

	//clean sleep
	@Redirect(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isPlayerSleeping()Z", ordinal = 0))
	public boolean runTick(EntityPlayerSP instance)
	{
		return false;
	}

	//fbo
//	//gtime
	//clean gui
	@Unique
	private static GuiIngame extra$GUIINGAME;
	@Unique
	private static GuiIngameForge extra$GUIINGAMEFORGE;
	@Unique
	private static byte extra$STATE;
	@Inject(method = "runGameLoop", at = @At(value = "HEAD"))
	private void nali_extra_runGameLoopH(CallbackInfo callbackinfo)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_F4))
		{
			if ((extra$STATE & 1) == 0)
			{
				extra$STATE |= 1;
				if (this.ingameGUI instanceof GuiIngameForge)
				{
					if (extra$GUIINGAME == null)
					{
						extra$GUIINGAMEFORGE = (GuiIngameForge)this.ingameGUI;
						extra$GUIINGAME = new GuiIngame((Minecraft)(Object)this);
					}
					this.ingameGUI = extra$GUIINGAME;
				}
				else
				{
//				if (GUIINGAMEFORGE == null)
//				{
//					GUIINGAMEFORGE = new GuiIngameForge((Minecraft)(Object)this);
//				}
					this.ingameGUI = extra$GUIINGAMEFORGE;
				}
			}
		}
		else
		{
			extra$STATE &= 255-1;
		}
//		if (!ExtraConfig.RAW_FPS)
		if (!SmallConfig.FAST_RAW_FPS)
		{
			ExtraFBO.update();
//		ExtraGTime.start();
		}
	}

//	@Inject(method = "runGameLoop", at = @At(value = "TAIL"))
//	private void nali_extra_runGameLoopT(CallbackInfo callbackinfo)
//	{
//		ExtraGTime.end();
//	}

	@Overwrite
	public int getLimitFramerate()
	{
		return this.gameSettings.limitFramerate;
//		return 60;
	}

//	@Overwrite
//	public boolean isFramerateLimitBelowMax()
//	{
//		return false;
//	}

	//fix tr
	private static long FRAME;
	@Redirect(method = "updateDisplay", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;update()V"))
	public void nali_extra_updateDisplay()
	{
		if (SmallConfig.FAST_RAW_FPS)
		{
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);

			++Small.FLAG;
			if (Small.FLAG == 4)
			{
				Small.FLAG = 0;
			}
		}
		else
		{
			if ((Small.FLAG & 1) == 1)
			{
				ExtraFBO.mix();
				Display.update();
				GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, ExtraFBO.FBO1);
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
				GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, ExtraFBO.FBO0);
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
				++this.fpsCounter;
				this.frameTimer.addFrame(FRAME);
			}
			else
			{
				GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, ExtraFBO.FBO1);
			}

			Small.FLAG ^= 1/* | 2*/;
		}
	}

	@Redirect(method = "runGameLoop", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FrameTimer;addFrame(J)V"))
	private void nali_extra_runGameLoop(FrameTimer instance, long runningTime)
	{
		if (SmallConfig.FAST_RAW_FPS)
		{
			this.frameTimer.addFrame(runningTime);
		}
		else
		{
			FRAME = runningTime;
		}
	}

	@Redirect(method = "runGameLoop", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;fpsCounter:I", ordinal = 0))
	private int nali_extra_runGameLoop(Minecraft instance)
	{
		if (SmallConfig.FAST_RAW_FPS)
		{
			return this.fpsCounter;
		}
		return 0;
	}

	@Redirect(method = "runGameLoop", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;fpsCounter:I", ordinal = 1))
	private void nali_extra_runGameLoop(Minecraft instance, int value)
	{
		if (SmallConfig.FAST_RAW_FPS)
		{
			++this.fpsCounter;
		}
	}

	//force player move
	@ModifyConstant(method = "processKeyBinds", constant = @Constant(intValue = 2))
	private int nali_extra_processKeyBinds(int constant)
	{
		return 1;
	}

	//clean gui
	@Redirect(method = "launchIntegratedServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/integrated/IntegratedServer;getUserMessage()Ljava/lang/String;"))
	private String nali_extra_launchIntegratedServer(IntegratedServer instance)
	{
		return null;
	}

//	@Redirect(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;loadingScreen:Lnet/minecraft/client/LoadingScreenRenderer;"))
//	private void nali_extra_init(Minecraft instance, LoadingScreenRenderer value)
//	{
//	}
//
//	@Redirect(method = "resize", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;loadingScreen:Lnet/minecraft/client/LoadingScreenRenderer;"))
//	private void nali_extra_resize(Minecraft instance, LoadingScreenRenderer value)
//	{
//	}
//
//	@Redirect(method = "launchIntegratedServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/LoadingScreenRenderer;displaySavingString(Ljava/lang/String;)V"))
//	private void nali_extra_launchIntegratedServer(LoadingScreenRenderer instance, String message)
//	{
//	}
//
//	@Redirect(method = "launchIntegratedServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/integrated/IntegratedServer;serverIsInRunLoop()Z"))
//	private boolean nali_extra_launchIntegratedServerLoop(IntegratedServer instance)
//	{
//		return true;
//	}
//
//	@Redirect(method = "launchIntegratedServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 1))
//	private void nali_extra_launchIntegratedServer(Minecraft instance, GuiScreen i)
//	{
//		instance.displayGuiScreen(null);
//	}
}
