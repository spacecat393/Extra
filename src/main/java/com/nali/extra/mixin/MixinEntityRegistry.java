//package com.nali.extra.mixin;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityTracker;
//import net.minecraftforge.fml.common.registry.EntityRegistry;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//
////force multi thread
//@Mixin(EntityRegistry.class)
//public abstract class MixinEntityRegistry
//{
//	@Overwrite(remap = false)
//	public boolean tryTrackingEntity(EntityTracker entityTracker, Entity entity)
//	{
//		return false;
//	}
//}
