package com.nali.extra;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ExtraView
{
	public static double X, Y, Z;
	public final static float EPSILON = 90.0F;

	public static boolean check(Block block, BlockPos blockpos, IBlockAccess iblockaccess, EnumFacing enumfacing)
	{
//		Vec3i direction_vec3i = enumfacing.getDirectionVec();
//		double x = blockpos.getX() + direction_vec3i.getX();
//		double y = blockpos.getY() + direction_vec3i.getY();
//		double z = blockpos.getZ() + direction_vec3i.getZ();
////		if
////		(
////			(X >= x - 0.5D && X <= x + 0.5D) &&
////			(Y >= y - 5.5D && Y <= y + 5.5D) &&
////			(Z >= z - 0.5D && Z <= z + 0.5D)
////		)
////		{
//////			Nali.warn("1");
////			return true;
////		}

		//		cir.setReturnValue(false);
		Minecraft minecraft = Minecraft.getMinecraft();
//		EnumFacing enumfacing = side;
		EntityPlayerSP entityplayersp = minecraft.player;
		if (entityplayersp.getHorizontalFacing() == enumfacing)
		{
			return false;
		}
//		float entityplayersp_rotationpitch = ((entityplayersp.rotationPitch + 90) % 180 + 180) % 180 - 90;
//		if (entityplayersp_rotationpitch > 0)
//		{
//			if (enumfacing == EnumFacing.UP)
//			{
//				return false;
//			}
//		}
//		else if (entityplayersp_rotationpitch < 0)
//		{
//			if (enumfacing == EnumFacing.DOWN)
//			{
//				return false;
//			}
//		}
		Vec3i direction_vec3i = enumfacing.getDirectionVec();
		double x = blockpos.getX() - direction_vec3i.getX() - entityplayersp.posX;
		double y = blockpos.getY() - direction_vec3i.getY() - entityplayersp.posY;
		double z = blockpos.getZ() - direction_vec3i.getZ() - entityplayersp.posZ;
//		x -= entityplayersp.posX;
//		y -= entityplayersp.posY;
//		z -= entityplayersp.posZ;
//		double x = blockpos.getX() + 0.5D - entityplayersp.posX;
//		double y = blockpos.getY() + 0.5D - entityplayersp.posY;
//		double z = blockpos.getZ() + 0.5D - entityplayersp.posZ;
//		double x = blockpos.getX();
//		double y = blockpos.getY();
//		double z = blockpos.getZ();
//		double x = entityplayersp.posX - blockpos.getX() + 0.5D;
//		double y = entityplayersp.posY - blockpos.getY() + 0.5D;
//		double z = entityplayersp.posZ - blockpos.getZ() + 0.5D;

		//		switch (enumfacing)
		//		{
		//			case NORTH:
		//				break;
		//			case WEST:
		//				break;
		//			case SOUTH:
		//				break;
		//			case EAST:
		//				break;
		//			case DOWN:
		//				break;
		//			case UP:
		//				break;
		//		}

		double yaw = Math.toDegrees(Math.atan2(-x, z));
//		float yaw = EntityMath.normalize((float)Math.toDegrees(Math.atan2(-x, z)), 360.0F);
		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));//Math.asin

		float entityplayersp_rotationyaw = ((entityplayersp.rotationYaw + 180) % 360 + 360) % 360 - 180;
		float entityplayersp_rotationpitch = ((entityplayersp.rotationPitch + 90) % 180 + 180) % 180 - 90;
//		float entityplayersp_rotationyaw = EntityMath.normalize(entityplayersp.rotationYaw, 360.0F);
//		float entityplayersp_rotationpitch = EntityMath.normalize(entityplayersp.rotationPitch, 180.0F);

		x = Math.abs(entityplayersp_rotationyaw - yaw);
		y = Math.abs(entityplayersp_rotationpitch - pitch);

		if (x > 180)
		{
			x = 360 - x;
		}

//		if (y > 90)
//		{
//			y = 180 - y;
//		}

		if (x > EPSILON || y > EPSILON)
		{
			return false;
		}

		if (!(block instanceof BlockDoor) && !(block instanceof BlockTrapDoor))
		{
//			Block block = blockAccess.getBlockState(pos.offset(side)).getBlock();
//			if (block == (Object)this/* || block instanceof BlockLeaves*/ || (((Object)this instanceof BlockLeaves) && block != Blocks.AIR))
//	//		if (blockAccess.getBlockState(pos.offset(side)).getBlock() != Blocks.AIR)
			if (iblockaccess.getBlockState(blockpos.offset(enumfacing)).getBlock() == block)
			{
				return false;
			}
		}

		return true;
	}
}
