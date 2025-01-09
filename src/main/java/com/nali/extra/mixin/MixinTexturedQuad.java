package com.nali.extra.mixin;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//remove light
@Mixin(TexturedQuad.class)
public abstract class MixinTexturedQuad
{
//	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;begin(ILnet/minecraft/client/renderer/vertex/VertexFormat;)V"))
//	public void draw(BufferBuilder instance, int glMode, VertexFormat format)
//	{
//		instance.begin(7, DefaultVertexFormats.POSITION_TEX);
//	}
//
//	@Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BufferBuilder;normal(FFF)Lnet/minecraft/client/renderer/BufferBuilder;"))
//	public BufferBuilder draw(BufferBuilder instance, float x, float y, float z)
//	{
//		return instance;
//	}

	@Shadow public PositionTextureVertex[] vertexPositions;

	@Overwrite
	public void draw(BufferBuilder renderer, float scale)
	{
		renderer.begin(7, DefaultVertexFormats.POSITION_TEX);

		for (int i = 0; i < 4; ++i)
		{
			PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
			Vec3d vec3d = positiontexturevertex.vector3D;
			renderer.pos(vec3d.x * scale, vec3d.y * scale, vec3d.z * scale).tex(positiontexturevertex.texturePositionX, positiontexturevertex.texturePositionY).endVertex();
		}

		Tessellator.getInstance().draw();
	}
}
