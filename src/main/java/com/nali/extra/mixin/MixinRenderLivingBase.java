package com.nali.extra.mixin;

import com.nali.extra.Extra;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

//fp
@Mixin(RenderLivingBase.class)
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T>
{
	@Shadow protected abstract boolean setBrightness(T entitylivingbaseIn, float partialTicks, boolean combineTextures);

	@Shadow protected abstract void unsetBrightness();

	@Shadow protected List<LayerRenderer<T>> layerRenderers;

	protected MixinRenderLivingBase(RenderManager renderManager)
	{
		super(renderManager);
	}

	@Overwrite
	protected void renderLayers(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn)
	{
		for (LayerRenderer<T> layerrenderer : this.layerRenderers)
		{
			if ((Extra.FP & 1) == 1)
			{
				if (!(layerrenderer instanceof LayerHeldItem) && !(layerrenderer instanceof LayerArrow) && !(layerrenderer instanceof LayerEntityOnShoulder) && !(layerrenderer instanceof LayerArmorBase))
				{
					continue;
				}
			}

			boolean flag = this.setBrightness(entitylivingbaseIn, partialTicks, layerrenderer.shouldCombineTextures());
			layerrenderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);

			if (flag)
			{
				this.unsetBrightness();
			}
		}
	}
}
