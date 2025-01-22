package com.nali.extra.mixin;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

//force remove light
@Mixin(DefaultVertexFormats.class)
public abstract class MixinDefaultVertexFormats
{
	@Mutable
	@Shadow @Final public static VertexFormat BLOCK;

	@Mutable
	@Shadow @Final public static VertexFormat ITEM;

	@Mutable
	@Shadow @Final public static VertexFormat OLDMODEL_POSITION_TEX_NORMAL;

	@Mutable
	@Shadow @Final public static VertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_COLOR;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_TEX;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_NORMAL;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_TEX_COLOR;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_TEX_NORMAL;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_TEX_LMAP_COLOR;

	@Mutable
	@Shadow @Final public static VertexFormat POSITION_TEX_COLOR_NORMAL;

	@Mutable
	@Shadow @Final public static VertexFormatElement POSITION_3F;

	@Mutable
	@Shadow @Final public static VertexFormatElement COLOR_4UB;

	@Mutable
	@Shadow @Final public static VertexFormatElement TEX_2F;

	@Mutable
	@Shadow @Final public static VertexFormatElement TEX_2S;

	@Mutable
	@Shadow @Final public static VertexFormatElement NORMAL_3B;

	@Mutable
	@Shadow @Final public static VertexFormatElement PADDING_1B;

	static
	{
		BLOCK = new VertexFormat();
		ITEM = new VertexFormat();
		OLDMODEL_POSITION_TEX_NORMAL = new VertexFormat();
		PARTICLE_POSITION_TEX_COLOR_LMAP = new VertexFormat();
		POSITION = new VertexFormat();
		POSITION_COLOR = new VertexFormat();
		POSITION_TEX = new VertexFormat();
		POSITION_NORMAL = new VertexFormat();
		POSITION_TEX_COLOR = new VertexFormat();
		POSITION_TEX_NORMAL = new VertexFormat();
		POSITION_TEX_LMAP_COLOR = new VertexFormat();
		POSITION_TEX_COLOR_NORMAL = new VertexFormat();
		POSITION_3F = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3);
		COLOR_4UB = new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR, 4);
		TEX_2F = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2);
		TEX_2S = new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV, 2);
		NORMAL_3B = null;
		PADDING_1B = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.PADDING, 4);

		BLOCK.addElement(POSITION_3F);
		BLOCK.addElement(COLOR_4UB);
		BLOCK.addElement(TEX_2F);
		BLOCK.addElement(TEX_2S);
		ITEM.addElement(POSITION_3F);
		ITEM.addElement(COLOR_4UB);
		ITEM.addElement(TEX_2F);
//		ITEM.addElement(NORMAL_3B);
		ITEM.addElement(PADDING_1B);
		OLDMODEL_POSITION_TEX_NORMAL.addElement(POSITION_3F);
		OLDMODEL_POSITION_TEX_NORMAL.addElement(TEX_2F);
//		OLDMODEL_POSITION_TEX_NORMAL.addElement(NORMAL_3B);
		OLDMODEL_POSITION_TEX_NORMAL.addElement(PADDING_1B);
		PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(POSITION_3F);
		PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2F);
		PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(COLOR_4UB);
		PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2S);
		POSITION.addElement(POSITION_3F);
		POSITION_COLOR.addElement(POSITION_3F);
		POSITION_COLOR.addElement(COLOR_4UB);
		POSITION_TEX.addElement(POSITION_3F);
		POSITION_TEX.addElement(TEX_2F);
		POSITION_NORMAL.addElement(POSITION_3F);
//		POSITION_NORMAL.addElement(NORMAL_3B);
		POSITION_NORMAL.addElement(PADDING_1B);
		POSITION_TEX_COLOR.addElement(POSITION_3F);
		POSITION_TEX_COLOR.addElement(TEX_2F);
		POSITION_TEX_COLOR.addElement(COLOR_4UB);
		POSITION_TEX_NORMAL.addElement(POSITION_3F);
		POSITION_TEX_NORMAL.addElement(TEX_2F);
//		POSITION_TEX_NORMAL.addElement(NORMAL_3B);
		POSITION_TEX_NORMAL.addElement(PADDING_1B);
		POSITION_TEX_LMAP_COLOR.addElement(POSITION_3F);
		POSITION_TEX_LMAP_COLOR.addElement(TEX_2F);
		POSITION_TEX_LMAP_COLOR.addElement(TEX_2S);
		POSITION_TEX_LMAP_COLOR.addElement(COLOR_4UB);
		POSITION_TEX_COLOR_NORMAL.addElement(POSITION_3F);
		POSITION_TEX_COLOR_NORMAL.addElement(TEX_2F);
		POSITION_TEX_COLOR_NORMAL.addElement(COLOR_4UB);
//		POSITION_TEX_COLOR_NORMAL.addElement(NORMAL_3B);
		POSITION_TEX_COLOR_NORMAL.addElement(PADDING_1B);
	}
}
