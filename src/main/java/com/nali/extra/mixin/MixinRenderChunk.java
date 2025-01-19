//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.chunk.RenderChunk;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(RenderChunk.class)
//public abstract class MixinRenderChunk
//{
////	@Redirect(method = "rebuildChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;canRenderInLayer(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockRenderLayer;)Z"))
////	public boolean rebuildChunk(Block instance, IBlockState state, BlockRenderLayer layer)
////	{
//////		return BlockRenderLayer.SOLID == layer;
////		if (instance.getRenderLayer() != BlockRenderLayer.TRANSLUCENT)
////		{
////			return BlockRenderLayer.SOLID == layer;
////		}
////		return instance.canRenderInLayer(state, layer);
////	}
//}
