//package com.nali.extra.mixin;
//
//import com.nali.extra.array.ExtraHashSet;
//import com.nali.extra.array.ExtraIntHashMap;
//import net.minecraft.entity.EntityTracker;
//import net.minecraft.entity.EntityTrackerEntry;
//import net.minecraft.util.IntHashMap;
//import net.minecraft.world.WorldServer;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Set;
//
////force multi thread
//@Mixin(EntityTracker.class)
//public abstract class MixinEntityTracker
//{
//	@Mutable
//	@Shadow @Final private Set<EntityTrackerEntry> entries;
//
//	@Mutable
//	@Shadow @Final private IntHashMap<EntityTrackerEntry> trackedEntityHashTable;
//
//	@Inject(method = "<init>", at = @At("TAIL"))
//	public void nali_extra_init(WorldServer theWorldIn, CallbackInfo ci)
//	{
//		this.entries = new ExtraHashSet();
//		this.trackedEntityHashTable = new ExtraIntHashMap();
//	}
//}
