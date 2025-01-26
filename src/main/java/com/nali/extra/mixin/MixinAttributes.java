//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.vertex.VertexFormat;
//import net.minecraft.client.renderer.vertex.VertexFormatElement;
//import net.minecraftforge.client.model.Attributes;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//
////force remove light
//@Mixin(Attributes.class)
//public abstract class MixinAttributes
//{
//	@Mutable
//	@Shadow @Final public static VertexFormat DEFAULT_BAKED_FORMAT;
//
//	static
//	{
//		DEFAULT_BAKED_FORMAT = new VertexFormat();
//		DEFAULT_BAKED_FORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
//		DEFAULT_BAKED_FORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR,    4));
//		DEFAULT_BAKED_FORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV,       2));
//		DEFAULT_BAKED_FORMAT.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE,  VertexFormatElement.EnumUsage.PADDING,  1));
//	}
//}
