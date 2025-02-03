//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.ViewFrustum;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.gen.Accessor;
//import org.spongepowered.asm.mixin.gen.Invoker;
//
//@Mixin(ViewFrustum.class)
//public interface IMixinViewFrustum
//{
//	@Accessor("countChunksX")
//	int countChunksX();
//	@Accessor("countChunksY")
//	int countChunksY();
//	@Accessor("countChunksZ")
//	int countChunksZ();
//
//	@Invoker("getBaseCoordinate")
//	int GOgetBaseCoordinate(int p_178157_1_, int p_178157_2_, int p_178157_3_);
//}
