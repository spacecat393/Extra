package com.nali.extra.mixin;

import com.nali.extra.ExtraView;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.BitSet;
import java.util.List;

//force chunk render
@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer
{
	@Shadow protected abstract void renderQuadsFlat(IBlockAccess blockAccessIn, IBlockState stateIn, BlockPos posIn, int brightnessIn, boolean ownBrightness, BufferBuilder buffer, List<BakedQuad> list, BitSet bitSet);

	@Overwrite
	public boolean renderModel(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
	{
//		boolean flag = Minecraft.isAmbientOcclusionEnabled() && stateIn.getLightValue(worldIn, posIn) == 0 && modelIn.isAmbientOcclusion(stateIn);

//		try
//		{
		return /*flag ? this.copyRenderModelSmooth(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand) : */this.copyRenderModelFlat(worldIn, modelIn, stateIn, posIn, buffer, rand);
//			return false;
//		}
//		catch (Throwable throwable)
//		{
//			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Tesselating block model");
//			CrashReportCategory crashreportcategory = crashreport.makeCategory("Block model being tesselated");
//			CrashReportCategory.addBlockInfo(crashreportcategory, posIn, stateIn);
//			crashreportcategory.addCrashSection("Using AO", Boolean.valueOf(flag));
//			throw new ReportedException(crashreport);
//		}
	}

//	private boolean copyRenderModelSmooth(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
//	{
////		return false;
//		boolean flag = false;
//		float[] afloat = new float[EnumFacing.values().length * 2];
//		BitSet bitset = new BitSet(3);
//		BlockModelRenderer.AmbientOcclusionFace blockmodelrenderer$ambientocclusionface = new BlockModelRenderer.AmbientOcclusionFace();
//
//		for (EnumFacing enumfacing : EnumFacing.values())
//		{
//			List<BakedQuad> list = modelIn.getQuads(stateIn, enumfacing, rand);
//
//			if (!list.isEmpty() && (!checkSides || stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing)))
//			{
//				this.renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list, afloat, bitset, blockmodelrenderer$ambientocclusionface);
//				flag = true;
//			}
//		}
//
//		List<BakedQuad> list1 = modelIn.getQuads(stateIn, (EnumFacing)null, rand);
//
//		if (!list1.isEmpty())
//		{
//			this.renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list1, afloat, bitset, blockmodelrenderer$ambientocclusionface);
//			flag = true;
//		}
//
//		return flag;
//	}

	private boolean copyRenderModelFlat(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, long rand)
	{
//		return false;
		boolean flag = false;
		BitSet bitset = new BitSet(3);

		for (EnumFacing enumfacing : EnumFacing.values())
		{
			List<BakedQuad> list = modelIn.getQuads(stateIn, enumfacing, rand);
//			for (BakedQuad bakedquad : list)
//			{
//				if (bakedquad == null)
//				{
//					Nali.warn("list " + list);
//					Nali.error("bakedquad null");
//				}
//				else
//				{
//					EnumFacing enumfacing = bakedquad.getFace();
//					if (enumfacing == null)
//					{
//						Nali.warn("list " + list);
//						Nali.warn("bakedquad " + bakedquad);
//						Nali.error("bakedquad.getFace() null");
//					}
//				}
//			}

			if (!list.isEmpty() && ExtraView.check(stateIn.getBlock(), posIn, worldIn, stateIn, enumfacing) && stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing))
			{
				int i = stateIn.getPackedLightmapCoords(worldIn, posIn.offset(enumfacing));
				this.renderQuadsFlat(worldIn, stateIn, posIn, i, false, buffer, list, bitset);
				flag = true;
			}
		}

		List<BakedQuad> list1 = modelIn.getQuads(stateIn, null, rand);

		if (!list1.isEmpty())
		{
			this.renderQuadsFlat(worldIn, stateIn, posIn, -1, true, buffer, list1, bitset);
			flag = true;
		}

		return flag;
	}
	//s0-why it didn't work
//	@Overwrite(remap = false)
//	public boolean renderModelSmooth(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
//	{
//		Nali.warn("false");
//		return false;
//	}
//
//	@Overwrite(remap = false)
//	public boolean renderModelFlat(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
//	{
//		Nali.warn("false");
//		return false;
//	}
	//e0-why it didn't work

//	@ModifyVariable(method = "renderModelSmooth", at = @At("HEAD"), ordinal = 0)
//	private boolean nali_extra_renderModelSmooth(boolean checkSides)
//	{
//		return true;
//	}
//
//	@Redirect(method = "renderModelSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;shouldSideBeRendered(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z"))
//	private boolean nali_extra_renderModelSmooth(IBlockState instance, IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing)
//	{
//		return false;
////		return this.check(blockPos, iBlockAccess, enumFacing) && instance.shouldSideBeRendered(iBlockAccess, blockPos, enumFacing);
//	}
//
//	@ModifyVariable(method = "renderModelFlat", at = @At("HEAD"), ordinal = 0)
//	private boolean nali_extra_renderModelFlat(boolean checkSides)
//	{
//		return true;
//	}
//
//	@Redirect(method = "renderModelFlat", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;shouldSideBeRendered(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z"))
//	private boolean nali_extra_renderModelFlat(IBlockState instance, IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing)
//	{
//		return false;
////		return this.check(blockPos, iBlockAccess, enumFacing) && instance.shouldSideBeRendered(iBlockAccess, blockPos, enumFacing);
//	}
}
