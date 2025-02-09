package com.nali.extra;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ExtraView
{
	public static double X, Y, Z;
	public static float YAW, PITCH;
	public static int TEMP_YAW, TEMP_PITCH;
	public static long TIME;

//	public final static float BLOCK_DISTANCE_EPSILON = 4.0F;
	public final static float YAW_BLOCK_EPSILON = 80.0F;//75.0F
	public final static float PITCH_BLOCK_EPSILON = 80.0F;//45.0F
	public final static float CHUNK_EPSILON = 45.0F;//90.0F

	public static boolean check(Block block, BlockPos blockpos, IBlockAccess iblockaccess, IBlockState iblockstate, EnumFacing enumfacing)
	{
//		if (true)
//		{
//			return true;
//		}
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
//		Vec3i direction_vec3i = enumfacing.getDirectionVec();
//		double x = blockpos.getX() + direction_vec3i.getX() - entityplayersp.posX;
//		double y = blockpos.getY() + direction_vec3i.getY() - entityplayersp.posY;
//		double z = blockpos.getZ() + direction_vec3i.getZ() - entityplayersp.posZ;

//		double x = blockpos.getX() - entityplayersp.posX;
//		double y = blockpos.getY() - entityplayersp.posY;
//		double z = blockpos.getZ() - entityplayersp.posZ;
//		switch (enumfacing)
//		{
//			case NORTH:
//				y += 0.5D;
//				z -= 1.0D;
//				break;
//			case WEST:
//				y += 0.5D;
//				x -= 1.0D;
//				break;
//			case SOUTH:
//				y += 0.5D;
//				z += 1.0D;
//				break;
//			case EAST:
//				y += 0.5D;
//				x += 1.0D;
//				break;
//			case DOWN:
//				x += 0.5D;
//				z += 0.5D;
//				y -= 1.0D;
//				break;
//			case UP:
//				x += 0.5D;
//				z += 0.5D;
//				y += 1.0D;
//		}

//		double x = blockpos.getX() - direction_vec3i.getX() - entityplayersp.posX;
//		double y = blockpos.getY() - direction_vec3i.getY() - entityplayersp.posY;
//		double z = blockpos.getZ() - direction_vec3i.getZ() - entityplayersp.posZ;
//		x -= entityplayersp.posX;
//		y -= entityplayersp.posY;
//		z -= entityplayersp.posZ;
		double x = blockpos.getX() + 0.5D - entityplayersp.posX;
		double y = blockpos.getY() + 0.5D - entityplayersp.posY;
		double z = blockpos.getZ() + 0.5D - entityplayersp.posZ;
//		double x = blockpos.getX();
//		double y = blockpos.getY();
//		double z = blockpos.getZ();
//		double x = entityplayersp.posX - blockpos.getX() + 0.5D;
//		double y = entityplayersp.posY - blockpos.getY() + 0.5D;
//		double z = entityplayersp.posZ - blockpos.getZ() + 0.5D;

//		double d = x * x + y * y + z * z;

		double yaw = Math.toDegrees(Math.atan2(-x, z));
//		float yaw = EntityMath.normalize((float)Math.toDegrees(Math.atan2(-x, z)), 360.0F);
		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));//Math.asin

//		float entityplayersp_rotationyaw = ((entityplayersp.rotationYaw + 180) % 360 + 360) % 360 - 180;
//		float entityplayersp_rotationpitch = ((entityplayersp.rotationPitch /*+ 90 */+ 90) % 180 + 180) % 180 - 90;
////		float entityplayersp_rotationyaw = EntityMath.normalize(entityplayersp.rotationYaw, 360.0F);
////		float entityplayersp_rotationpitch = EntityMath.normalize(entityplayersp.rotationPitch, 180.0F);

//		x = Math.abs(entityplayersp_rotationyaw - yaw);
//		y = Math.abs(entityplayersp_rotationpitch - pitch);
//		if (PITCH == -90 && pitch == -90)
//		{
//			return true;
//		}
//		else if (PITCH == 90 && pitch == 90)
//		{
//			return true;
//		}
		x = Math.abs(YAW - yaw);
		y = Math.abs(PITCH - pitch);
//		Nali.warn("pitch " + pitch);

		if (x > 180)
		{
			x = 360 - x;
		}

//		if (y > 90)
//		{
//			y = 180 - y;
//		}

//		Nali.warn("enumfacing " + enumfacing);
//		Nali.warn("entityplayersp_rotationyaw " + entityplayersp_rotationyaw);
//		Nali.warn("entityplayersp_rotationpitch " + entityplayersp_rotationpitch);
//		Nali.warn("yaw " + yaw);
//		Nali.warn("pitch " + pitch);
//		Nali.warn("x " + x);
//		Nali.warn("y " + y);

//		if (d - (X * X + Y * Y + Z * Z) > BLOCK_DISTANCE_EPSILON)
//		{
		if (x > YAW_BLOCK_EPSILON || y > PITCH_BLOCK_EPSILON)
		{
			return false;
		}
//		}

//		if (!(block instanceof BlockDoor) && !(block instanceof BlockTrapDoor) && !(block instanceof BlockSnow))
		if (iblockstate.isFullCube())
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

//	public static boolean check(double x, double y, double z, float entityplayersp_rotationyaw, float entityplayersp_rotationpitch)
//	{
//		double yaw = Math.toDegrees(Math.atan2(-x, z));
//		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));
//
//		x = Math.abs(entityplayersp_rotationyaw - yaw);
//		y = Math.abs(entityplayersp_rotationpitch - pitch);
//
//		if (x > 180)
//		{
//			x = 360 - x;
//		}
//
////		if (y > 90)
////		{
////			y = 180 - y;
////		}
//
//		return x <= CHUNK_EPSILON && y <= CHUNK_EPSILON;
//	}
//
//	public static boolean check(double x, double z, float entityplayersp_rotationyaw)
//	{
//		double yaw = Math.toDegrees(Math.atan2(-x, z));
//
//		x = Math.abs(entityplayersp_rotationyaw - yaw);
//
//		if (x > 180)
//		{
//			x = 360 - x;
//		}
//
//		return x <= CHUNK_EPSILON;
//	}

	public static boolean check(double x, double y, double z)
	{
		double yaw = Math.toDegrees(Math.atan2(-x, z));
		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));

		x = Math.abs(YAW - yaw);
		y = Math.abs(PITCH - pitch);

		if (x > 180)
		{
			x = 360 - x;
		}

		return x <= CHUNK_EPSILON && y <= CHUNK_EPSILON;
	}

	public static boolean check(double x, double z)
	{
		double yaw = Math.toDegrees(Math.atan2(-x, z));

		x = Math.abs(YAW - yaw);

		if (x > 180)
		{
			x = 360 - x;
		}

		return x <= CHUNK_EPSILON;
	}
}
