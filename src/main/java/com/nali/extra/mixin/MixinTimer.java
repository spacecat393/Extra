//package com.nali.extra.mixin;
//
//import net.minecraft.util.Timer;
//import org.lwjgl.Sys;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
////fix tr
//@Mixin(Timer.class)
//public abstract class MixinTimer
//{
//	@Shadow public float elapsedPartialTicks;
//
//	@Shadow private long lastSyncSysClock;
//
//	@Shadow public float renderPartialTicks;
//
//	@Shadow public int elapsedTicks;
//
//	@Overwrite
//	public void updateTimer()
//	{
//		long i = Sys.getTime();
//		this.elapsedPartialTicks = (float)(i - this.lastSyncSysClock) / 40;
//		this.lastSyncSysClock = i;
//		this.renderPartialTicks += this.elapsedPartialTicks;
//		this.elapsedTicks = (int)this.renderPartialTicks;
//		this.renderPartialTicks -= (float)this.elapsedTicks;
//	}
//}
