//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.BufferBuilder;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//
////force remove light
//@Mixin(BufferBuilder.class)
//public abstract class MixinBufferBuilder
//{
//	@Overwrite
//	public void putNormal(float x, float y, float z)
//	{
//	}
//
//	@Overwrite
//	public BufferBuilder normal(float x, float y, float z)
//	{
//		return (BufferBuilder)(Object)this;
//	}
//}
