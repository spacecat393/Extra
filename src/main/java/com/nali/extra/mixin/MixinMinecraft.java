package com.nali.extra.mixin;

import com.nali.extra.ExtraColor;
import com.nali.extra.ExtraCubeLine;
import com.nali.extra.ExtraQuadLine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
	@Shadow public GuiIngame ingameGUI;

	@Shadow protected abstract void clickMouse();

	@Shadow protected abstract void rightClickMouse();

	@Shadow private int rightClickDelayTimer;

	@Shadow private int leftClickCounter;

	//*extra-s0
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
		ExtraCubeLine.init();
//		ExtraQuad.init();
		ExtraQuadLine.init();
	}

	//clean gui
	@Redirect(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;"))
	private void nali_extra_init_gui(Minecraft instance, GuiIngame value)
	{
		this.ingameGUI = new GuiIngame(instance);
	}

	@Inject(method = "runTick", at = @At(value = "HEAD"))
//	@Inject(method = "runGameLoop", at = @At("HEAD"))
	private void nali_extra_runGameLoop(CallbackInfo callbackinfo)
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
}
