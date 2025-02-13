package com.nali.list.block;

public class ExtraCloudwater extends ExtraCloud
{
	public static int ID;

	public ExtraCloudwater(String[] string_array)
	{
		super(string_array);
	}

//	@SideOnly(Side.CLIENT)
//	@Override
//	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
//	{
//		super.randomDisplayTick(stateIn, worldIn, pos, rand);
//		worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, pos.getX() + rand.nextFloat(), pos.getY(), pos.getZ() + rand.nextFloat(), 0, -0.1D, 0);
//	}
}
