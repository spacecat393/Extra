package com.nali.extra.mixin;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.client.model.pipeline.BlockInfo;
import net.minecraftforge.client.model.pipeline.QuadGatheringTransformer;
import net.minecraftforge.client.model.pipeline.VertexLighterFlat;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//force remove light
@Mixin(VertexLighterFlat.class)
public abstract class MixinVertexLighterFlat extends QuadGatheringTransformer
{
	@Shadow protected int posIndex;

	@Shadow protected int lightmapIndex;

	@Shadow protected int colorIndex;

	@Shadow private int tint;

	@Shadow @Final protected BlockInfo blockInfo;

	@Shadow protected abstract void updateColor(float[] normal, float[] color, float x, float y, float z, float tint, int multiplier);

	@Overwrite(remap = false)
	protected void processQuad()
	{
		float[][] position = quadData[posIndex];
		float[][] lightmap = quadData[lightmapIndex];
		float[][] color = quadData[colorIndex];

		int multiplier = -1;
		if(tint != -1)
		{
			multiplier = blockInfo.getColorMultiplier(tint);
		}

		VertexFormat format = parent.getVertexFormat();
		int count = format.getElementCount();

		for(int v = 0; v < 4; v++)
		{
			position[v][0] += blockInfo.getShx();
			position[v][1] += blockInfo.getShy();
			position[v][2] += blockInfo.getShz();

			float x = position[v][0] - .5f;
			float y = position[v][1] - .5f;
			float z = position[v][2] - .5f;

//			float blockLight = lightmap[v][0], skyLight = lightmap[v][1];
//			updateLightmap(normal[v], lightmap[v], x, y, z);
//			if(dataLength[lightmapIndex] > 1)
//			{
//				if(blockLight > lightmap[v][0]) lightmap[v][0] = blockLight;
//				if(skyLight > lightmap[v][1]) lightmap[v][1] = skyLight;
//			}
			updateColor(null, color[v], x, y, z, tint, multiplier);
//			if(diffuse)
//			{
//				float d = LightUtil.diffuseLight(normal[v][0], normal[v][1], normal[v][2]);
//				for(int i = 0; i < 3; i++)
//				{
//					color[v][i] *= d;
//				}
//			}
//			if(EntityRenderer.anaglyphEnable)
//			{
//				applyAnaglyph(color[v]);
//			}

			for(int e = 0; e < count; e++)
			{
				VertexFormatElement element = format.getElement(e);
				switch(element.getUsage())
				{
					case POSITION:
						parent.put(e, position[v]);
						break;
					case COLOR:
						parent.put(e, color[v]);
						break;
					case UV:
						if(element.getIndex() == 1)
						{
							parent.put(e, lightmap[v]);
							break;
						}
						// else fallthrough to default
					default:
						parent.put(e, quadData[e][v]);
				}
			}
		}
		tint = -1;
	}
}
