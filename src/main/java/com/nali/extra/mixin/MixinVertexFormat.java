package com.nali.extra.mixin;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//force remove light
@Mixin(VertexFormat.class)
public abstract class MixinVertexFormat
{
	@Inject(method = "addElement", at = @At("HEAD"), cancellable = true)
	public void nali_extra_addElement(VertexFormatElement element, CallbackInfoReturnable<VertexFormat> cir)
	{
		if (element.getUsage() == VertexFormatElement.EnumUsage.NORMAL/* || element.getUsage() == VertexFormatElement.EnumUsage.PADDING*/)
		{
			cir.cancel();
		}
	}

	@Overwrite
	public boolean hasNormal()
	{
		return true;
	}
}
