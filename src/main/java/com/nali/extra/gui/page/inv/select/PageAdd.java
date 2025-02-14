package com.nali.extra.gui.page.inv.select;

import com.nali.extra.gui.page.inv.PageInv;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaInvSelectAdd;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageAdd extends PageEdit
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte ST;//enter client init

	@Override
	public void init()
	{
		super.init();

		InventoryPlayer inventoryplayer = Minecraft.getMinecraft().player.inventory;
		byte inventoryplayer_size = (byte)inventoryplayer.getSizeInventory();
		byte index = 0;
		this.boxtextall_array = new BoxTextAll[2 + inventoryplayer_size + 2];
		this.boxtextall_array[index++] = new BoxTextAll("SELECT-ADD".toCharArray());
		this.boxtextall_array[index++] = new BoxTextAll("ITEM".toCharArray());
		for (byte i = 0; i < inventoryplayer_size; ++i)
		{
			ItemStack itemstack = inventoryplayer.getStackInSlot(i);
			this.boxtextall_array[index++] = new BoxTextAll(this.getChar(i + " " + itemstack.getDisplayName()));
		}
		this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = index;
			this.fl |= BF_SET_SELECT;
		}

		this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		byte new_index = (byte)(index - 2);
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[new_index / 8] |= 1 << new_index % 8;

//		for (BoxTextAll boxtextall : this.boxtextall_array)
//		{
//			Nali.warn("" + boxtextall);
//			Nali.warn("" + boxtextall.char_array);
//		}
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		if (this.select == this.boxtextall_array.length - 1)
		{
			this.back();
		}
		else
		{
			byte[] byte_array = new byte[1 + 1 + 4 + 1];
			byte_array[0] = SPageDa.ID;
			byte_array[1] = SDaInvSelectAdd.ID;
			ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 2);
			byte_array[2+4] = (byte)(this.select - 2);
			NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
		}
//			STATE &= 255-1;
//		}
	}

	@Override
	public void draw()
	{
		if ((ST & B_DRAW) == B_DRAW)
		{
			this.fl &= 255 - BF_SET_SELECT;
			this.clear();
			this.init();

			this.gen();
			ST &= 255 - (B_LOCK_DRAW + B_DRAW);
		}
		super.draw();
	}
}