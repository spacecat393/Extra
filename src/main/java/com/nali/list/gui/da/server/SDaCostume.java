package com.nali.list.gui.da.server;

import com.nali.extra.ExtraCostume;
import com.nali.list.network.message.ServerMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.EntityDataManager;

public class SDaCostume
{
	public static byte ID;

	public final static byte B_SET_HEAD = 0;
	public final static byte B_SET_CHEST = 1;
	public final static byte B_SET_LEGS = 2;
	public final static byte B_SET_FEET = 3;

	public final static byte B_FAKE_HEAD = 4;
	public final static byte B_FAKE_CHEST = 5;
	public final static byte B_FAKE_LEGS = 6;
	public final static byte B_FAKE_FEET = 7;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		EntityDataManager entitydatamanager = entityplayermp.getDataManager();
		switch (servermessage.data[2])
		{
			case B_SET_HEAD:
				entitydatamanager.set(ExtraCostume.INV_BYTE_DATAPARAMETER, (byte)(entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER) ^ 1));
				break;
			case B_SET_CHEST:
				entitydatamanager.set(ExtraCostume.INV_BYTE_DATAPARAMETER, (byte)(entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER) ^ 2));
				break;
			case B_SET_LEGS:
				entitydatamanager.set(ExtraCostume.INV_BYTE_DATAPARAMETER, (byte)(entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER) ^ 4));
				break;
			case B_SET_FEET:
				entitydatamanager.set(ExtraCostume.INV_BYTE_DATAPARAMETER, (byte)(entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER) ^ 8));
				break;

			case B_FAKE_HEAD:
				entitydatamanager.set(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER, entityplayermp.getHeldItem(entityplayermp.getActiveHand()).copy());
				break;
			case B_FAKE_CHEST:
				entitydatamanager.set(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER, entityplayermp.getHeldItem(entityplayermp.getActiveHand()).copy());
				break;
			case B_FAKE_LEGS:
				entitydatamanager.set(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER, entityplayermp.getHeldItem(entityplayermp.getActiveHand()).copy());
				break;
			case B_FAKE_FEET:
				entitydatamanager.set(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER, entityplayermp.getHeldItem(entityplayermp.getActiveHand()).copy());
		}
	}
}
