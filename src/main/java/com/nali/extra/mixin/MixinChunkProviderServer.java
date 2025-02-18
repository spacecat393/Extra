//package com.nali.extra.mixin;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraConfig;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.gen.ChunkProviderServer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
////force multi thread
//@Mixin(ChunkProviderServer.class)
//public abstract class MixinChunkProviderServer
//{
//	private final static byte B_LOCK = 1;
//	@Unique
//	private byte extra$state;
//
//	@Inject(method = "tick", at = @At(value = "HEAD"))
//	private void nali_extra_tickH(CallbackInfoReturnable<Boolean> cir)
//	{
//		while ((this.extra$state & B_LOCK) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
//	}
//
//	@Inject(method = "tick", at = @At(value = "TAIL"))
//	private void nali_extra_tickT(CallbackInfoReturnable<Boolean> cir)
//	{
//		this.extra$state &= 255 - B_LOCK;
//	}
//
//	@Inject(method = "queueUnload", at = @At(value = "HEAD"))
//	private void nali_extra_queueUnloadH(Chunk chunkIn, CallbackInfo ci)
//	{
//		while ((this.extra$state & B_LOCK) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
//	}
//
//	@Inject(method = "queueUnload", at = @At(value = "TAIL"))
//	private void nali_extra_queueUnloadT(Chunk chunkIn, CallbackInfo ci)
//	{
//		this.extra$state &= 255 - B_LOCK;
//	}
//}
