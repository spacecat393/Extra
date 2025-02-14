package com.nali.list.block;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshapedown;
import com.nali.list.render.RenderExtraGlowshapedown;
import com.nali.small.mix.block.BlockB;
import com.nali.small.mix.block.BlockRegistry;
import com.nali.small.mix.item.ItemB;
import com.nali.small.mix.memo.client.ClientB;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ExtraGlowshapedown extends BlockB implements ITileEntityProvider
{
	public static int ID;
	public final static AxisAlignedBB AXISALIGNEDBB = new AxisAlignedBB(0.0D, 0.10D, 0.0D, 1.0D, 0.40D, 1.0D);

	public ExtraGlowshapedown(String[] string_array)
	{
		super(string_array, Material.ROCK);
		this.setResistance(0.0F);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.STONE);
		this.setLightLevel(0.1F);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			ItemStack itemstack = playerIn.getHeldItem(hand);
			if (itemstack.getItem() == Items.GLOWSTONE_DUST)
			{
				worldIn.setBlockState(pos, BlockRegistry.BLOCK_ARRAY[ExtraGlowshapedownb.ID].getDefaultState());
				itemstack.shrink(1);
			}
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AXISALIGNEDBB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void newC()
	{
		RenderExtraGlowshapedown r = new RenderExtraGlowshapedown();
		this.ibothb = new ClientB(r, this);
	}

	@Override
	public IBothDaO getBD()
	{
		return BothDaExtraGlowshapedown.IDA;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new com.nali.list.block.tile.ExtraGlowshapedown();
	}

	public Item getNewItem()
	{
		return new ItemB(this);
	}
}
