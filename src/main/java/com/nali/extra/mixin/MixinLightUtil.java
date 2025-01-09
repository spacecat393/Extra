package com.nali.extra.mixin;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.LightUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove light
@Mixin(LightUtil.class)
public abstract class MixinLightUtil
{
	@Overwrite
	public static float diffuseLight(float x, float y, float z)
	{
		return 1.0F;
	}

	@Overwrite
	public static float diffuseLight(EnumFacing side)
	{
		return 1.0F;
	}
}
