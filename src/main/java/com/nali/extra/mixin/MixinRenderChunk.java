//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.chunk.RenderChunk;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
////cull leaves
//@Mixin(RenderChunk.class)
//public abstract class MixinRenderChunk
//{
//	@Shadow private boolean needsUpdate;
//
//	@Shadow private boolean needsImmediateUpdate;
//
//	@Overwrite
//	public void setNeedsUpdate(boolean immediate)
//	{
////		if (this.needsUpdate)
////		{
////			immediate |= this.needsImmediateUpdate;
////		}
//
//		this.needsUpdate = true;
//		this.needsImmediateUpdate = false;
////		this.needsImmediateUpdate = immediate;
//	}
//////	@Redirect(method = "rebuildChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;canRenderInLayer(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockRenderLayer;)Z"))
//////	public boolean rebuildChunk(Block instance, IBlockState state, BlockRenderLayer layer)
//////	{
////////		return BlockRenderLayer.SOLID == layer;
//////		if (instance.getRenderLayer() != BlockRenderLayer.TRANSLUCENT)
//////		{
//////			return BlockRenderLayer.SOLID == layer;
//////		}
//////		return instance.canRenderInLayer(state, layer);
//////	}
////
////	@Redirect(method = "rebuildChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;isOpaqueCube()Z"))
////	private boolean nali_extra_rebuildChunk(IBlockState instance)
////	{
////		return !instance.isFullCube();
////	}
//}
