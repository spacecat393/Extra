package com.nali.extra.mixin;

import net.minecraft.client.renderer.OpenGlHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//remove light
//disable shader framebuffer
@Mixin(OpenGlHelper.class)
public abstract class MixinOpenGlHelper
{
	@Shadow public static boolean shadersSupported;

	@Shadow public static boolean framebufferSupported;

	@Inject(method = "initializeTextures", at = @At("TAIL"))
	private static void nali_extra_initializeTextures(CallbackInfo ci)
	{
		shadersSupported = false;
		framebufferSupported = false;
	}

	@Overwrite
	public static boolean areShadersSupported()
	{
		return false;
	}
}
