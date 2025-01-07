package com.nali.extra.mixin;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
	//*extra-s0
	@Redirect(method = "setIngameFocus", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I"))
	private void nali_setIngameFocus(Minecraft instance, int value)
	{
	}

	@Redirect(method = "clickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I", ordinal = 2))
	private void nali_clickMouse(Minecraft instance, int value)
	{
	}

	@Redirect(method = "runTick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;leftClickCounter:I", ordinal = 0))
	private void nali_runTick(Minecraft instance, int value)
	{

	}

	@Redirect(method = "rightClickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;rightClickDelayTimer:I"))
	private void nali_rightClickMouse_rightClickDelayTimer(Minecraft instance, int value)
	{
	}

//	@Redirect(method = "rightClickMouse", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/RayTraceResult;typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;", ordinal = 0))
//	private RayTraceResult.Type nali_rightClickMouse_Type(RayTraceResult instance)
//	{
//		return RayTraceResult.Type.BLOCK;
//	}

	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"))
	private Material nali_rightClickMouse_Material(IBlockState instance)
	{
		return Material.GRASS;
	}

//	@Redirect(method = "rightClickMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"))
//	private boolean nali_rightClickMouse_getIsHittingBlock(PlayerControllerMP instance)
//	{
//		return false;
//	}
	//*extra-e0
}
