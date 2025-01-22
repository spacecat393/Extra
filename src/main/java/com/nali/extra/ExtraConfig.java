//package com.nali.extra;
//
//import net.minecraftforge.common.config.Config;
//import net.minecraftforge.common.config.ConfigManager;
//import net.minecraftforge.fml.client.event.ConfigChangedEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//@Config(modid = Extra.ID)
//public class ExtraConfig
//{
//	@Config.Name("LeftClick")
//	@Config.Comment("Delay")
//	public static int LEFTCLICK_DELAY = 0;
//
//	@Config.Name("RightClick")
//	@Config.Comment("Delay")
//	public static int RIGHTCLICK_DELAY = 20;
//
//	@Mod.EventBusSubscriber(modid = Extra.ID, value = Side.CLIENT)
//	public static class ConfigEvent
//	{
//		@SubscribeEvent
//		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
//		{
//			if (event.getModID().equals(Extra.ID))
//			{
//				ConfigManager.sync(Extra.ID, Config.Type.INSTANCE);
//			}
//		}
//	}
//}
