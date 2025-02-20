//package com.nali.extra.asm;
//
//import net.minecraft.launchwrapper.IClassTransformer;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class ASMInfo implements IClassTransformer
//{
//	public static final Logger LOGGER = LogManager.getLogger("extra");
//
//	@Override
//	public byte[] transform(String name, String transformedName, byte[] basicClass)
//	{
//		LOGGER.warn("name " + name);
//		LOGGER.warn("transformedName " + transformedName);
//		return basicClass;
//	}
//}
