package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.extra.ExtraView;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.BitSet;
import java.util.List;

//force chunk render
@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer
{
//	@Inject(method = "renderModel(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/renderer/BufferBuilder;ZJ)Z", at = @At("HEAD"), cancellable = true)
//	public void nali_extra_renderModel(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand, CallbackInfoReturnable<Boolean> cir)
//	{
//		ExtraView.check(stateIn.getBlock(), posIn, worldIn, stateIn, enumfacing) && stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing);
//		cir.setReturnValue(false);
//		cir.cancel();
//	}

	@Shadow @Final private BlockColors blockColors;

	@Overwrite
	public boolean renderModel(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
	{
//		if (checkSides == true)
//		{
//			return false;
//		}
//		return this.renderModelFlat(worldIn, modelIn, stateIn, posIn, buffer, true, rand);
//		boolean flag = Minecraft.isAmbientOcclusionEnabled() && stateIn.getLightValue(worldIn, posIn) == 0 && modelIn.isAmbientOcclusion(stateIn);

//		try
//		{
		return /*flag ? this.copyRenderModelSmooth(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand) : */this.copyRenderModelFlat(worldIn, modelIn, stateIn, posIn, buffer/*, checkSides*/, rand);
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

	private boolean copyRenderModelFlat(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer/*, boolean checkSides*/, long rand)
	{
//		return false;
		boolean flag = false;
		try
		{
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

				if (!list.isEmpty() && (/*!checkSides || */ExtraView.check(stateIn.getBlock(), posIn, worldIn, stateIn, enumfacing) && stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing)))
				{
	//				int i = stateIn.getPackedLightmapCoords(worldIn, posIn.offset(enumfacing));
	//				this.renderQuadsFlat(worldIn, stateIn, posIn, i, false, buffer, list, bitset);
					this.renderQuadsFlat(worldIn, stateIn, posIn, 0, false, buffer, list, bitset);
					flag = true;
				}
			}

			List<BakedQuad> list1 = modelIn.getQuads(stateIn, null, rand);

			if (!list1.isEmpty())
			{
				if (/*!checkSides || */ExtraView.check(stateIn.getBlock(), posIn, worldIn, stateIn, null))
				{
					this.renderQuadsFlat(worldIn, stateIn, posIn, -1, true, buffer, list1, bitset);
					flag = true;
				}
			}
		}
		catch (Exception e)
		{
			Nali.warn(e);
			Nali.warn("modelIn " + modelIn);
		}

		return flag;
	}

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

	@Overwrite
	private void renderQuadsFlat(IBlockAccess blockAccessIn, IBlockState stateIn, BlockPos posIn, int brightnessIn, boolean ownBrightness, BufferBuilder buffer, List<BakedQuad> list, BitSet bitSet)
	{
		Vec3d vec3d = stateIn.getOffset(blockAccessIn, posIn);
		double d0 = (double)posIn.getX() + vec3d.x;
		double d1 = (double)posIn.getY() + vec3d.y;
		double d2 = (double)posIn.getZ() + vec3d.z;
		int i = 0;

		for (int j = list.size(); i < j; ++i)
		{
			BakedQuad bakedquad = list.get(i);
//			if (ownBrightness)
//			{
//				this.fillQuadBounds(stateIn, bakedquad.getVertexData(), bakedquad.getFace(), (float[])null, bitSet);
//				BlockPos blockpos = bitSet.get(0) ? posIn.offset(bakedquad.getFace()) : posIn;
//				brightnessIn = stateIn.getPackedLightmapCoords(blockAccessIn, blockpos);
//			}

			buffer.addVertexData(bakedquad.getVertexData());
//			buffer.putBrightness4(brightnessIn, brightnessIn, brightnessIn, brightnessIn);
			buffer.putBrightness4(0, 0, 0, 0);

			if (bakedquad.hasTintIndex())
			{
				int k = this.blockColors.colorMultiplier(stateIn, blockAccessIn, posIn, bakedquad.getTintIndex());

//				if (EntityRenderer.anaglyphEnable)
//				{
//					k = TextureUtil.anaglyphColor(k);
//				}

				float f = (float)(k >> 16 & 255) / 255.0F;
				float f1 = (float)(k >> 8 & 255) / 255.0F;
				float f2 = (float)(k & 255) / 255.0F;
//				if(bakedquad.shouldApplyDiffuseLighting())
//				{
//					float diffuse = net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(bakedquad.getFace());
//					f *= diffuse;
//					f1 *= diffuse;
//					f2 *= diffuse;
//				}
				buffer.putColorMultiplier(f, f1, f2, 4);
				buffer.putColorMultiplier(f, f1, f2, 3);
				buffer.putColorMultiplier(f, f1, f2, 2);
				buffer.putColorMultiplier(f, f1, f2, 1);
			}
//			else if(bakedquad.shouldApplyDiffuseLighting())
//			{
//				float diffuse = net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(bakedquad.getFace());
//				buffer.putColorMultiplier(diffuse, diffuse, diffuse, 4);
//				buffer.putColorMultiplier(diffuse, diffuse, diffuse, 3);
//				buffer.putColorMultiplier(diffuse, diffuse, diffuse, 2);
//				buffer.putColorMultiplier(diffuse, diffuse, diffuse, 1);
//			}

			buffer.putPosition(d0, d1, d2);
		}
	}

//	@Overwrite
//	private void renderQuadsSmooth(IBlockAccess blockAccessIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, List<BakedQuad> list, float[] quadBounds, BitSet bitSet, BlockModelRenderer.AmbientOcclusionFace aoFace)
//	{
//
//	}
}
