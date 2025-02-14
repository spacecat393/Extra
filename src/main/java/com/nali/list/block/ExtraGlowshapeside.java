package com.nali.list.block;

import com.nali.da.IBothDaO;
import com.nali.extra.block.client.ClientGlowshapeside;
import com.nali.list.da.BothDaExtraGlowshapeside;
import com.nali.list.render.RenderExtraGlowshapeside;
import com.nali.small.mix.block.BlockB;
import com.nali.small.mix.block.BlockRegistry;
import com.nali.small.mix.item.ItemB;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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

public class ExtraGlowshapeside extends BlockB implements ITileEntityProvider
{
	public static int ID;
	public final static AxisAlignedBB AXISALIGNEDBB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	public final static PropertyDirection FACING = BlockHorizontal.FACING;

//	public IBothB ibothb;

	public ExtraGlowshapeside(String[] string_array)
	{
		super(string_array, Material.ROCK);
		this.setResistance(2000.0F);
		this.setHardness(50.0F);
		this.setSoundType(SoundType.STONE);
		this.setLightLevel(0.1F);
//		this.Ninit();
//		this.ibothb.init(this, string_array[0], string_array[1]);
//		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		EnumFacing facingDirection = placer.getHorizontalFacing();
		return this.getDefaultState().withProperty(FACING, facingDirection);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}

	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
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
//				IBlockState iblockstate = worldIn.getBlockState(pos);
//				EnumFacing enumfacing = iblockstate.getValue(ExtraGlowshapeside.FACING);
				worldIn.setBlockState(pos, BlockRegistry.BLOCK_ARRAY[ExtraGlowshapesideb.ID].getDefaultState().withProperty(ExtraGlowshapeside.FACING, playerIn.getHorizontalFacing()));
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
		RenderExtraGlowshapeside r = new RenderExtraGlowshapeside();
		this.ibothb = new ClientGlowshapeside(r, this);
	}

	@Override
	public IBothDaO getBD()
	{
		return BothDaExtraGlowshapeside.IDA;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new com.nali.list.block.tile.ExtraGlowshapeside();
	}

	public Item getNewItem()
	{
		return new ItemB(this);
	}

//	@Override
//	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
//	{
//		return this.getB().doesSideBlockRendering(face);
//	}
//
//	@Override
//	public void updateLight(World world, BlockPos blockpos)
//	{
//		this.ibothb.updateLight(world, blockpos);
//	}
//
//	@Override
//	public void light()
//	{
//		this.ibothb.light();
//	}
//
//	@Override
//	public void newS()
//	{
//		this.ibothb = new ServerB();
//	}
//
//	@Override
//	public IBothN getB()
//	{
//		return this.ibothb;
//	}
//
//	@Override
//	public Object getE()
//	{
//		return this;
//	}
//
//	@Override
//	public void render(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
//	{
//		this.ibothb.render(tileEntity, x, y, z, partialTicks, destroyStage, alpha);
//	}
//
//	@SideOnly(Side.CLIENT)
//	@Override
//	public void render()
//	{
//		this.ibothb.render();
//	}
}
