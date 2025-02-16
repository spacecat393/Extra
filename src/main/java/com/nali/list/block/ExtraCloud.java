package com.nali.list.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class ExtraCloud extends Block
{
	public final static Material CLOUD = (new Material(MapColor.CLOTH)
	{
		@Override
		public boolean isSolid()
		{
			return false;
		}

		@Override
		public boolean blocksLight()
		{
			return false;
		}

		@Override
		public boolean isOpaque()
		{
			return false;
		}

		@Override
		public boolean blocksMovement()
		{
			return false;
		}
	})/*.setReplaceable()*/;

	public static int ID;
//	public static byte
//		X/*, Y*/, Z,
//		STATE;
//	public final static byte B_UPDATE = 1;

	public ExtraCloud(String[] string_array)
	{
//		super(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT ? Material.CLOTH : Material.AIR);
		super(CLOUD);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.CLOTH);
		this.setTickRandomly(true);

		String name = string_array[0];
		String mod_id = string_array[1];
		this.setRegistryName(mod_id, name);
		this.setTranslationKey(mod_id + '.' + name);
	}

	public Item getNewItem()
	{
		return new ItemBlock(this);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);
		pos = pos.down();
		if (worldIn.isAirBlock(pos) && rand.nextInt(40) == 0)
		{
			Biome biome = worldIn.getBiome(pos);

			if ((biome.canRain() || biome.getEnableSnow()) && worldIn.isRaining())
			{
				if (worldIn.canSnowAt(pos, false))
				{
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, Blocks.SNOW_LAYER.getDefaultState());
//					entityfallingblock.motionY = -0.5D;
					entityfallingblock.fallTime = Integer.MIN_VALUE;
					worldIn.spawnEntity(entityfallingblock);
				}
				else
				{
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, Blocks.ICE.getDefaultState());
//					entityfallingblock.motionY = -0.5D;
					entityfallingblock.fallTime = Integer.MIN_VALUE;
					worldIn.spawnEntity(entityfallingblock);
				}
			}
		}
//		if ((STATE & B_UPDATE) == B_UPDATE)
//		{
//	//		Nali.warn("updateTick");
////			BlockPos new_blockpos = pos.add(X, Y, Z);
//			BlockPos new_blockpos = pos.add(X, 0, Z);
//			if (worldIn.isAirBlock(new_blockpos))
//			{
//				worldIn.setBlockState(new_blockpos, state);
//				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
//			}
//		}
	}

//	@SideOnly(Side.CLIENT)
//	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
//	{
//		pos = pos.down();
//		if (worldIn.isAirBlock(pos) && rand.nextInt(10) == 0)
//		{
//			if (worldIn.isRaining())
//			{
//				if (worldIn.canSnowAt(pos, false))
//				{
//					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, Blocks.SNOW_LAYER.getDefaultState());
//					entityfallingblock.motionY = -0.5D;
//					entityfallingblock.fallTime = Integer.MIN_VALUE;
//					worldIn.spawnEntity(entityfallingblock);
//				}
//				else
//				{
//					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, Blocks.ICE.getDefaultState());
//					entityfallingblock.motionY = -0.5D;
//					entityfallingblock.fallTime = Integer.MIN_VALUE;
//					worldIn.spawnEntity(entityfallingblock);
//				}
//			}
//		}
//	}

	@Override
	public boolean canEntitySpawn(IBlockState state, Entity entityIn)
	{
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	public boolean isTopSolid(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return null;
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
//		entityIn.fallDistance *= 0.25F;
		entityIn.fallDistance = Float.NaN;
		worldIn.destroyBlock(pos, false);
	}

	@Override
	public boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

//	@SideOnly(Side.CLIENT)
//	public static void registerBlockColors()
//	{
//		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, world, pos, tintIndex) ->
//		{
//			if (world != null && pos != null)
//			{
//				int y = pos.getY();
////				int whiteness = Math.min(255, y);
//				return (y << 16) | (y << 8) | y;
//			}
//			return 0xFFFFFF;
//		}, BlockRegistry.BLOCK_ARRAY[ID]);
//	}
}
