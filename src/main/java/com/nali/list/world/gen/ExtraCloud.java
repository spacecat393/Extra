package com.nali.list.world.gen;

import com.nali.Nali;
import com.nali.small.entity.memo.server.si.path.PathMath;
import com.nali.small.mix.block.BlockRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class ExtraCloud implements IWorldGenerator
{
	public final static byte B_EMPTY = 0;
	public final static byte B_CLOUD = 1;
//	public final static byte B_CLOUD_FLAT = 2;
//	public final static byte B_CLOUD_BIG = 3;
	public final static byte B_CLOUD_EMPTY = 2;
//	public static byte LOCK;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.getDimension() == 0/* && (LOCK & 1) == 0*/)
		{
//			LOCK |= 1;
//			((WorldServer)world).addScheduledTask(() ->
//			{
			File nali_file = new File(world.getSaveHandler().getWorldDirectory(), "nali");
			nali_file.mkdir();
			File world_file = new File(nali_file, "world");
			world_file.mkdir();

			byte chance = (byte)random.nextInt(3);
			if (chance != B_EMPTY)
			{
				chance = this.get(world_file, chunkX, chunkZ, chance);

				//			File xz_file = new File(world_file, "" + ((long)chunkZ << 32 | chunkX));
				File xz_file = new File(world_file, chunkX + " " + chunkZ);
//				if (xz_file.isFile())
//				{
//					Nali.error("same");
//				}
				try
				{
					Files.write(xz_file.toPath(), new byte[]{chance});
				}
				catch (IOException e)
				{
					Nali.error(e);
				}
			}

//		Biome biome = world.getBiome(blockpos);
//		float temperature = biome.getTemperature(blockpos);
//		float humidity = biome.getRainfall();

//			if (random.nextInt(10) == 0)
			if (chance == B_CLOUD)
			{
//				if (random.nextBoolean())
//				{
				int x = chunkX * 16 + 8;
				int z = chunkZ * 16 + 8;
				int y = 120 + random.nextInt(120);
//				int y = 120;

//				world.setBlockState(new BlockPos(x, y, z), BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloudwater.ID].getDefaultState(), 2);
//				this.genCloud((byte)(y < 140 ? 0 : y < 160 ? 2 : y < 180 ? 1 : 3), world, blockpos, random);
				this.genCloud(world, new BlockPos(x, y, z), random);
//				}
			}
//			else if (chance == B_CLOUD_FLAT)
//			{
////				if (random.nextBoolean())
////				{
//				int x = chunkX * 16;
//				int z = chunkZ * 16;
//				int y = 120;
////				world.setBlockState(new BlockPos(x, y, z), BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState(), 2);
//
//				if (random.nextBoolean())
//				{
//					this.genCloudLayer(world, new BlockPos(x, y, z));
//				}
////				}
//			}
//			else if (chance == B_CLOUD_BIG)
//			{
//				int x = chunkX * 16;
//				int z = chunkZ * 16;
//				int y = 120/* + 16 + random.nextInt(16)*/;
//				this.genCloudBig(world, new BlockPos(x, y, z), random);
//			}
//			});
//			LOCK &= 255-1;
		}
	}

	public void genCloud(World world, BlockPos blockpos, Random random)
	{
		int radius = 4 + random.nextInt(5);
//		int id = random.nextInt(20);
//		IBlockState iblockstate;
//		switch (random.nextInt(40))
//		{
//			case 1:
//				iblockstate = BlockRegistry.BLOCK_ARRAY[ExtraCloudsnow.ID].getDefaultState();
//				break;
//			case 2:
//				iblockstate = BlockRegistry.BLOCK_ARRAY[ExtraCloudwater.ID].getDefaultState();
//				break;
//			case 3:
//				iblockstate = BlockRegistry.BLOCK_ARRAY[ExtraCloudthunder.ID].getDefaultState();
//				break;
//			default:
//				iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();
//		}
		IBlockState iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();

//		world.setBlockState(blockpos, iblockstate, 2);

//		for (int dx = -radius; dx <= radius; dx++)
//		{
//			for (int dy = -2; dy <= 2; dy++)
//			{
//				for (int dz = -radius; dz <= radius; dz++)
//				{
//					BlockPos new_blockpos = blockpos.add(dx, dy, dz);
//					if (world.isAirBlock(new_blockpos))
//					{
//						world.setBlockState(new_blockpos, iblockstate, 2);
//					}
//				}
//			}
//		}

//		for (int dx = 0; dx <= radius; dx++)
//		{
//			for (int dy = 0; dy <= radius / 4; dy++)
//			{
//				for (int dz = 0; dz <= radius; dz++)
//				{
//					if (dx * dx + dy * dy * 2 + dz * dz <= radius * radius)
//					{
//						BlockPos new_blockpos = blockpos.add(dx, dy, dz);
//						if (world.isAirBlock(new_blockpos))
//						{
//							world.setBlockState(new_blockpos, iblockstate, 2);
//						}
//					}
//				}
//			}
//		}
		for (int dx = -radius; dx < radius; dx++)
		{
			for (int dy = -radius / 2; dy < radius / 2; dy++)
			{
				for (int dz = -radius; dz < radius; dz++)
				{
					if (dx * dx + dy * dy * 2 + dz * dz < radius * radius)
					{
						BlockPos new_blockpos = blockpos.add(dx, dy, dz);
						if (world.isAirBlock(new_blockpos))
						{
							world.setBlockState(new_blockpos, iblockstate, 2);
						}
					}
				}
			}
		}
	}
//
//	public void genCloudLayer(World world, BlockPos blockpos)
//	{
//		IBlockState iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();
//		for (int dx = 0; dx < 16; ++dx)
//		{
//			for (int dy = 0; dy < 3; ++dy)
//			{
//				for (int dz = 0; dz < 16; ++dz)
//				{
//					BlockPos new_blockpos = blockpos.add(dx, dy, dz);
//					if (world.isAirBlock(new_blockpos))
//					{
//						world.setBlockState(new_blockpos, iblockstate, 2);
//					}
//				}
//			}
//		}
//	}

//	public void genCloudBig(World world, BlockPos blockpos, Random random)
//	{
//		IBlockState iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();
////		IBlockState iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloudthunder.ID].getDefaultState();
////		int y = 16 + random.nextInt(16);
////		for (int dx = 0; dx < 16; ++dx)
////		{
////			for (int dy = 0; dy < y; ++dy)
////			{
////				for (int dz = 0; dz < 16; ++dz)
////				{
////					BlockPos new_blockpos = blockpos.add(dx, dy, dz);
////					if (world.isAirBlock(new_blockpos) && random.nextBoolean())
////					{
////						world.setBlockState(new_blockpos, iblockstate, 2);
////					}
////				}
////			}
////		}
//		if (random.nextInt(40) == 0)
//		{
//			for (int dx = 0; dx < 16; ++dx)
//			{
//				for (int dz = 0; dz < 16; ++dz)
//				{
//					BlockPos new_blockpos = blockpos.add(dx, 0, dz);
//					if (world.isAirBlock(new_blockpos) && random.nextBoolean())
//					{
//						world.setBlockState(new_blockpos, iblockstate, 2);
//					}
//				}
//			}
//		}
//	}

	public byte get(File world_file, int chunkx, int chunkz, byte chance)
	{
		for (byte x : PathMath.PATH_BYTE_ARRAY)
		{
			for (byte z : PathMath.PATH_BYTE_ARRAY)
			{
				if (!(x == 0 && z == 0))
				{
//					File xz_file = new File(world_file, "" + ((long)chunkz + z << 32 | chunkx + x));
					File xz_file = new File(world_file, (chunkx + x) + " " + (chunkz + z));
					if (xz_file.isFile())
					{
						try
						{
							byte type = Files.readAllBytes(xz_file.toPath())[0];
//							if (type == 0)
//							{
//								Nali.error("0");
//							}
//							if (type != B_EMPTY)
//							{
//								Nali.warn("rewrite");
							return type;
//								return B_EMPTY;
//							}
						}
						catch (IOException e)
						{
							Nali.error(e);
						}
					}
				}
			}
		}

		return chance;
	}
}
