package com.nali.list.world.gen;

import com.nali.small.mix.block.BlockRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ExtraCloud implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
//		Biome biome = world.getBiome(blockpos);
//		float temperature = biome.getTemperature(blockpos);
//		float humidity = biome.getRainfall();

		if (world.provider.getDimension() == 0)
		{
//			if (random.nextInt(10) == 0)
			if (random.nextBoolean())
			{
				int x = chunkX * 16 + 8;
				int z = chunkZ * 16 + 8;
				int y = 120 + random.nextInt(120);

				BlockPos blockpos = new BlockPos(x, y, z);
//				this.genCloud((byte)(y < 140 ? 0 : y < 160 ? 2 : y < 180 ? 1 : 3), world, blockpos, random);
				this.genCloud(world, blockpos, random);
			}

//			if (random.nextBoolean())
//			{
//				int x = chunkX * 16;
//				int z = chunkZ * 16;
//				int y = 120;
//				this.genCloudLayer(world, new BlockPos(x, y, z));
//			}
		}
	}

	public void genCloud(World world, BlockPos blockpos, Random random)
	{
		int radius = 4 + random.nextInt(5);
//		int id = random.nextInt(20);
		IBlockState iblockstate;
//		switch (id)
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
		iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();

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

	public void genCloudLayer(World world, BlockPos blockpos)
	{
		IBlockState iblockstate = BlockRegistry.BLOCK_ARRAY[com.nali.list.block.ExtraCloud.ID].getDefaultState();
		for (int dx = 0; dx < 16; ++dx)
		{
			for (int dy = 0; dy < 3; ++dy)
			{
				for (int dz = 0; dz < 16; ++dz)
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
