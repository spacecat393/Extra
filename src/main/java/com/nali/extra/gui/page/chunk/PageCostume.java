package com.nali.extra.gui.page.chunk;

import com.nali.extra.ExtraCostume;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaCostume;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageCostume extends PageSelect
{
	public final static byte F_HEAD = 1;
	public final static byte F_CHEST = 2;
	public final static byte F_LEGS = 4;
	public final static byte F_FEET = 8;

	public static long REDRAW;

	public PageCostume()
	{
		this.select = 12;
	}

	@Override
	public void init()
	{
		EntityDataManager entitydatamanager = Minecraft.getMinecraft().player.getDataManager();
		byte inv = entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER);
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("COSTUME".toCharArray()),
			new BoxTextAll("FLAG".toCharArray()),
			new BoxTextAll(("HEAD " + (byte)Math.signum(inv & F_HEAD)).toCharArray()),
			new BoxTextAll(("CHEST " + (byte)Math.signum(inv & F_CHEST)).toCharArray()),
			new BoxTextAll(("LEGS " + (byte)Math.signum(inv & F_LEGS)).toCharArray()),
			new BoxTextAll(("FEET " + (byte)Math.signum(inv & F_FEET)).toCharArray()),
			new BoxTextAll("SELECT".toCharArray()),
			new BoxTextAll(this.getChar("HEAD " + entitydatamanager.get(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER).getDisplayName())),
			new BoxTextAll(this.getChar("CHEST " + entitydatamanager.get(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER).getDisplayName())),
			new BoxTextAll(this.getChar("LEGS " + entitydatamanager.get(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER).getDisplayName())),
			new BoxTextAll(this.getChar("FEET " + entitydatamanager.get(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER).getDisplayName())),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[5 / 8] |= 1 << 5 % 8;
		this.group_byte_array[10 / 8] |= 1 << 10 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				this.sendNet(SDaCostume.B_SET_HEAD);
				break;
			case 3:
				this.sendNet(SDaCostume.B_SET_CHEST);
				break;
			case 4:
				this.sendNet(SDaCostume.B_SET_LEGS);
				break;
			case 5:
				this.sendNet(SDaCostume.B_SET_FEET);
				break;
			case 7:
				this.sendNet(SDaCostume.B_FAKE_HEAD);
				break;
			case 8:
				this.sendNet(SDaCostume.B_FAKE_CHEST);
				break;
			case 9:
				this.sendNet(SDaCostume.B_FAKE_LEGS);
				break;
			case 10:
				this.sendNet(SDaCostume.B_FAKE_FEET);
				break;
			case 12:
				this.back();
		}
	}

	@Override
	public void draw()
	{
		if (REDRAW != 0)
		{
			long time = Minecraft.getSystemTime();
			if (time - REDRAW >= 1000)
			{
				this.fl &= 255 - BF_SET_SELECT;
				this.clear();
				this.init();

				this.gen();
				REDRAW = 0;
			}
		}
		super.draw();
	}

	public void sendNet(byte b2)
	{
		NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SPageDa.ID, SDaCostume.ID, b2}));
		REDRAW = Minecraft.getSystemTime();
	}
}
