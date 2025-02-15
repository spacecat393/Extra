package com.nali.extra;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Extra.ID)
public class Extra
{
	public final static String ID = "extra";

	@SideOnly(Side.CLIENT)
	public static byte FP;

	@SideOnly(Side.CLIENT)
	public static float
		YAW,
		PITCH,

		P_YAW,
		P_PITCH;

	@SideOnly(Side.CLIENT)
	public static Entity POINT_ENTITY;

//	@Mod.EventHandler
//	public void onFMLServerStartedEvent(FMLServerStartedEvent event)
//	{
////		WorldServer[] worldserver_array = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
////		File world_file = worldserver_array[0].getSaveHandler().getWorldDirectory();
//		File world_file = DimensionManager.getCurrentSaveRootDirectory();
//		PlayerDataInv.read(world_file);
//	}
//
//	@Mod.EventHandler
//	public void onFMLServerStoppingEvent(FMLServerStoppingEvent event)
//	{
//		File world_file = DimensionManager.getCurrentSaveRootDirectory();
//		PlayerDataInv.write(world_file);
//	}
}
