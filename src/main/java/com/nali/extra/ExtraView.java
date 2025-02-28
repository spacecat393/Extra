package com.nali.extra;

import com.nali.Nali;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.nio.file.Files;

@SideOnly(Side.CLIENT)
public class ExtraView
{
	public static double X, Y, Z;
	public static float YAW, PITCH;
	public static int TEMP_YAW, TEMP_PITCH;
	public static long TIME;

//	public final static float BLOCK_DISTANCE_EPSILON = 4.0F;
//	public final static float YAW_BLOCK_EPSILON = 80.0F;//75.0F
//	public final static float PITCH_BLOCK_EPSILON = 80.0F;//45.0F
//	public final static float CHUNK_EPSILON = 45.0F;//90.0F
	public static float YAW_BLOCK_EPSILON = 80.0F;
	public static float PITCH_BLOCK_EPSILON = 80.0F;
	public static float CHUNK_EPSILON = 45.0F;

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


//		float x = (float)(blockpos.getX() - entityplayersp.posX);
//		float y = (float)(blockpos.getY() - entityplayersp.posY);
//		float z = (float)(blockpos.getZ() - entityplayersp.posZ);
//		return isOccluded(x, y, z, x + 1.0F, y + 1.0F, z + 1.0F);


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
		if (iblockstate.isFullCube() && enumfacing != null)
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

	public static boolean check(double minx, double miny, double minz, double maxx, double maxy, double maxz)
	{
		//		Nali.warn("x " + (maxx - minx));
//		Nali.warn("y " + (maxy - miny));
//		Nali.warn("z " + (maxz - minz));
		if
		(
//			(this.x >= minx && this.x <= maxx) &&
//			(this.y >= miny && this.y <= maxy) &&
//			(this.z >= minz && this.z <= maxz)
			(ExtraView.X >= minx && ExtraView.X <= maxx) &&
			(ExtraView.Y >= miny && ExtraView.Y <= maxy) &&
			(ExtraView.Z >= minz && ExtraView.Z <= maxz)
		)
		{
//			Nali.warn("1");
			return true;
		}

//		float epsilon = 45.0F;

//		minx -= this.x;
//		miny -= this.y;
//		minz -= this.z;
//		maxx -= this.x;
//		maxy -= this.y;
//		maxz -= this.z;
		minx -= ExtraView.X;
		miny -= ExtraView.Y;
		minz -= ExtraView.Z;
		maxx -= ExtraView.X;
		maxy -= ExtraView.Y;
		maxz -= ExtraView.Z;

//		double x_scalar = maxx - minx;
//		double y_scalar = maxy - miny;
//		double z_scalar = maxz - minz;
//
//		double box_center_x = minx + x_scalar / 2;
//		double box_center_y = miny + y_scalar / 2;
//		double box_center_z = minz + z_scalar / 2;
//
//		double x = box_center_x - this.x;
//		double y = box_center_y - this.y;
//		double z = box_center_z - this.z;

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}

		if (ExtraView.check(minx, miny, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, miny, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, miny, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, miny, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, maxy, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, maxy, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, maxy, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, maxy, maxz))
		{
			return true;
		}
		return false;
//		return this.clippingHelper.isBoxInFrustum(minx, miny, minz, maxx, maxy, maxz);
//		return true;
	}

	public static void init()
	{
		File file = new File("nali/nali/tmp/config_extra_view");

		if (file.isFile())
		{
			try
			{
				set(Files.readAllBytes(file.toPath()));
			}
			catch (Exception e)
			{
				Nali.error(e);
			}
		}
	}

	public static byte[] getByteArray()
	{
		byte[] byte_array = new byte[4 + 4 + 4];
		ByteWriter.set(byte_array, YAW_BLOCK_EPSILON, 0);
		ByteWriter.set(byte_array, PITCH_BLOCK_EPSILON, 4);
		ByteWriter.set(byte_array, CHUNK_EPSILON, 4+4);
		return byte_array;
	}

	public static void set(byte[] byte_array)
	{
		YAW_BLOCK_EPSILON = ByteReader.getFloat(byte_array, 0);
		PITCH_BLOCK_EPSILON = ByteReader.getFloat(byte_array, 4);
		CHUNK_EPSILON = ByteReader.getFloat(byte_array, 4+4);
	}

//	public static float[] PROJECT_M4X4_FLOAT_ARRAY;
//	public static float[] DEPTH_FLOAT_ARRAY;
//	public static float[] TEMP0_FLOAT_ARRAY;
//	static
//	{
//		TEMP0_FLOAT_ARRAY = new float[4];
//		TEMP0_FLOAT_ARRAY[3] = 1.0F;
//	}
//
//	public static boolean isOccluded(float minx, float miny, float minz, float maxx, float maxy, float maxz)
//	{
//		boolean outside = true;
//
//		for (int i = 0; i < 8; ++i)
//		{
//			TEMP0_FLOAT_ARRAY[0] = (i & 1) == 0 ? minx : maxx;
//			TEMP0_FLOAT_ARRAY[1] = (i & 2) == 0 ? miny : maxy;
//			TEMP0_FLOAT_ARRAY[2] = (i & 4) == 0 ? minz : maxz;
//
//			float[] transform_v4_float_array = M4x4.mV4M4x4(TEMP0_FLOAT_ARRAY, PROJECT_M4X4_FLOAT_ARRAY, 0);
//			float w = transform_v4_float_array[3];
//			if (w > 0)
//			{
//				float x = (transform_v4_float_array[0] / w) * 0.5F + 0.5F;
//				float y = (transform_v4_float_array[1] / w) * 0.5F + 0.5F;
//				float z = (transform_v4_float_array[2] / w);
//
//				int px = (int)(x * ExtraFBO.WIDTH);
//				int py = (int)(y * ExtraFBO.HEIGHT);
//
//				if (px >= 0 && px < ExtraFBO.WIDTH && py >= 0 && py < ExtraFBO.HEIGHT)
//				{
//					if (z < DEPTH_FLOAT_ARRAY[py * ExtraFBO.HEIGHT + px])
//					{
//						return false;
//					}
//				}
//				outside = false;
//			}
//		}
//
//		return !outside;
//	}
}
