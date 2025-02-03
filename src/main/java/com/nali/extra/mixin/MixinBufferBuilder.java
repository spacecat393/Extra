//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.BufferBuilder;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(BufferBuilder.class)
//public abstract class MixinBufferBuilder
//{
////	//force remove light
////	@Overwrite
////	public void putNormal(float x, float y, float z)
////	{
////	}
////
////	@Overwrite
////	public BufferBuilder normal(float x, float y, float z)
////	{
////		return (BufferBuilder)(Object)this;
////	}
//
////	//check odd bug
////	@Shadow private boolean isDrawing;
////
////	@Inject(method = "begin", at = @At("HEAD"), cancellable = true)
////	private void nali_extra_begin(int glMode, VertexFormat format, CallbackInfo ci)
////	{
////		if (this.isDrawing)
////		{
////			Nali.warn("BufferBuilder begin");
////			ci.cancel();
////		}
////	}
////
////	@Inject(method = "finishDrawing", at = @At("HEAD"), cancellable = true)
////	private void nali_extra_finishDrawing(CallbackInfo ci)
////	{
////		if (!this.isDrawing)
////		{
////			Nali.warn("BufferBuilder finishDrawing");
////			ci.cancel();
////		}
////	}
//}
