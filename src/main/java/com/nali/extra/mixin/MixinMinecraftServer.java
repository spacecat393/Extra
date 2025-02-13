package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

//remove CreativeTabs
@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
	@Shadow private int tickCounter;

	@Shadow @Final private Random random;

	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;applyServerIconToResponse(Lnet/minecraft/network/ServerStatusResponse;)V", shift = At.Shift.AFTER))
	private void nali_extra_run(CallbackInfo ci)
	{
		CreativeTabs.CREATIVE_TAB_ARRAY = null;
	}

//	private static byte TICK = 0;

//	//add cloud
//	@Inject(method = "tick", at = @At("HEAD"))
//	public void nali_extra_tick(CallbackInfo ci)
//	{
//		if ((this.tickCounter % 20) == 0)
//		{
////			if (TICK-- == 0)
////			{
//			ExtraCloud.X = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
////			ExtraCloud.Y = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
//			ExtraCloud.Z = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
//
//////				else
//////				{
//////					ExtraCloud.STATE |= ExtraCloud.B_UPDATE;
//////				}
////				TICK = (byte)this.random.nextInt(100);
////			}
//
//			ExtraCloud.STATE ^= ExtraCloud.B_UPDATE;
//
//			if (ExtraCloud.X == 0/* && ExtraCloud.Y == 0*/ && ExtraCloud.Z == 0)
//			{
//				ExtraCloud.STATE &= 255 - ExtraCloud.B_UPDATE;
//			}
//		}
//	}
}
