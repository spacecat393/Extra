//package com.nali.extra.mixin;
//
//import com.nali.extra.array.ExtraClassInheritanceMultiMap;
//import com.nali.extra.array.ExtraHashMap;
//import net.minecraft.entity.Entity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ClassInheritanceMultiMap;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.chunk.Chunk;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Map;
//
////force multi thread
//@Mixin(Chunk.class)
//public abstract class MixinChunk
//{
//	@Mutable
//	@Shadow @Final private Map<BlockPos, TileEntity> tileEntities;
//
//	@Mutable
//	@Shadow @Final private ClassInheritanceMultiMap<Entity>[] entityLists;
//
//	@Inject(method = "<init>(Lnet/minecraft/world/World;II)V", at = @At("TAIL"))
//	private void nali_extra_init(World worldIn, int x, int z, CallbackInfo ci)
//	{
//		this.tileEntities = new ExtraHashMap();
//		this.entityLists = new ExtraClassInheritanceMultiMap[16];
//
//		for (int i = 0; i < this.entityLists.length; ++i)
//		{
//			this.entityLists[i] = new ExtraClassInheritanceMultiMap(Entity.class);
//		}
//	}
//}
