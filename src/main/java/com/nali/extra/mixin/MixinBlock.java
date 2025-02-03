//package com.nali.extra.mixin;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockDoor;
//import net.minecraft.block.BlockTrapDoor;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.EntityPlayerSP;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IBlockAccess;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
////cull leaves
//@Mixin(Block.class)
//public abstract class MixinBlock
//{
//	@Inject(method = "shouldSideBeRendered", at = @At("HEAD"), cancellable = true)
//	private void shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> cir)
//	{
////		cir.setReturnValue(false);
//		Minecraft minecraft = Minecraft.getMinecraft();
////		EnumFacing enumfacing = side;
//		BlockPos blockpos = pos;
//		EntityPlayerSP entityplayersp = minecraft.player;
////		Vec3i direction_vec3i = enumfacing.getDirectionVec();
////		double x = blockpos.getX() + direction_vec3i.getX() - entityplayersp.posX;
////		double y = blockpos.getY() + direction_vec3i.getY() - entityplayersp.posY;
////		double z = blockpos.getZ() + direction_vec3i.getZ() - entityplayersp.posZ;
//		double x = blockpos.getX() + 0.5D - entityplayersp.posX;
//		double y = blockpos.getY() + 0.5D - entityplayersp.posY;
//		double z = blockpos.getZ() + 0.5D - entityplayersp.posZ;
////		double x = blockpos.getX();
////		double y = blockpos.getY();
////		double z = blockpos.getZ();
////		double x = entityplayersp.posX - blockpos.getX() + 0.5D;
////		double y = entityplayersp.posY - blockpos.getY() + 0.5D;
////		double z = entityplayersp.posZ - blockpos.getZ() + 0.5D;
//
//		//		switch (enumfacing)
//		//		{
//		//			case NORTH:
//		//				break;
//		//			case WEST:
//		//				break;
//		//			case SOUTH:
//		//				break;
//		//			case EAST:
//		//				break;
//		//			case DOWN:
//		//				break;
//		//			case UP:
//		//				break;
//		//		}
//
//		double yaw = Math.toDegrees(Math.atan2(-x, z));
////		float yaw = EntityMath.normalize((float)Math.toDegrees(Math.atan2(-x, z)), 360.0F);
//		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));//Math.asin
//
////		float entityplayersp_rotationyaw = EntityMath.normalize(entityplayersp.rotationYaw, 360.0F);
////		float entityplayersp_rotationpitch = EntityMath.normalize(entityplayersp.rotationPitch, 180.0F);
//		float entityplayersp_rotationyaw = ((entityplayersp.rotationYaw + 180) % 360 + 360) % 360 - 180;
//		float entityplayersp_rotationpitch = ((entityplayersp.rotationPitch + 90) % 180 + 180) % 180 - 90;
//
//		float epsilon = 90.0F;
//		x = Math.abs(entityplayersp_rotationyaw - yaw);
//		y = Math.abs(entityplayersp_rotationpitch - pitch);
//
//		if (x > 180)
//		{
//			x = 360 - x;
//		}
//
//		if (y > 90)
//		{
//			y = 180 - y;
//		}
//
//		if (x > epsilon || y > epsilon)
//		{
//			cir.setReturnValue(false);
//		}
//
//		if (!((Object)this instanceof BlockDoor) && !((Object)this instanceof BlockTrapDoor))
//		{
////			Block block = blockAccess.getBlockState(pos.offset(side)).getBlock();
////			if (block == (Object)this/* || block instanceof BlockLeaves*/ || (((Object)this instanceof BlockLeaves) && block != Blocks.AIR))
////	//		if (blockAccess.getBlockState(pos.offset(side)).getBlock() != Blocks.AIR)
//			if (blockAccess.getBlockState(pos.offset(side)).getBlock() == (Object)this)
//			{
//				cir.setReturnValue(false);
//			}
//		}
//	}
//
////	protected final Vec3d getVectorForRotation(double pitch, double yaw)
////	{
////		double f = Math.cos(-yaw * 0.017453292F - Math.PI);
////		double f1 = Math.sin(-yaw * 0.017453292F - Math.PI);
////		double f2 = -Math.cos(-pitch * 0.017453292F);
////		double f3 = Math.sin(-pitch * 0.017453292F);
////		return new Vec3d(f1 * f2, f3, f * f2);
////	}
//}
