package com.nali.list.gui.da.server;

import com.nali.list.entity.si.SIEInv;
import com.nali.list.gui.da.client.CDaInvSelectAdd;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SDaInvSelectAdd
{
	public static byte ID;

	public static byte[] BYTE_ARRAY;
	public static EntityPlayerMP ENTITYPLAYERMP;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		//should support over than long?
//		SDaInvSelect.RUNNABLE_LIST.add(() ->
//		{
		if ((SIEInv.ST & SIEInv.BS_LOCK) == 0)
		{
			SIEInv.ST |= SIEInv.BS_LOCK;
//			int length = servermessage.data.length;
//			BYTE_ARRAY = new byte[length];
//			System.arraycopy(servermessage.data, 0, BYTE_ARRAY, 0, length);
			BYTE_ARRAY = servermessage.data;
			ENTITYPLAYERMP = entityplayermp;
		}
//		});
	}

	public static void add()
	{
		if (SDaInvSelectAdd.BYTE_ARRAY != null)
		{
			int inv = ByteReader.getInt(SDaInvSelectAdd.BYTE_ARRAY, 2);
//			File inv_file = new File(ENTITYPLAYERMP.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + inv);

			byte item_slot = SDaInvSelectAdd.BYTE_ARRAY[2+4];

			ItemStack itemstack = ENTITYPLAYERMP.inventory.getStackInSlot(item_slot);
//			if (!itemstack.isEmpty())
//			{
//				int item_id = Item.getIdFromItem(itemstack.getItem());
//				File inv_n_file = new File(inv_file, "" + item_id);
//				//			inv_n_file.mkdir();
//
//				long count = itemstack.getCount();
//				byte[] count_byte_array = new byte[Long.BYTES];
//				//			long max_count;
//				//			long max_count_file;
//
//				//			try (Stream<Path> path_stream = Files.list(inv_file.toPath()))
//				//			{
//				//				max_count_file = path_stream.count();
//				//			}
//				//			catch (IOException e)
//				//			{
//				//				Nali.warn(e);
//				//			}
//				if (inv_n_file.exists())
//				{
//					try
//					{
//						byte[] byte_array = Files.readAllBytes(inv_n_file.toPath());
//						count += ByteReader.getLong(byte_array, 0);
//						if ((count & (1L << 63)) != 0)
//						{
//							SDaInv.ST &= 255 - SDaInv.BS_LOCK;
//							return;
//						}
//					}
//					catch (Exception e)
//					{
//						Nali.warn(e);
//						inv_n_file.delete();
//					}
//					//				max_count =;
//				}
//				try
//				{
//					ByteWriter.set(count_byte_array, count, 0);
//					Files.write(/*Paths.get(*/inv_n_file/* + "/" +*/.toPath(), count_byte_array);
//				}
//				catch (IOException e)
//				{
//					Nali.warn(e);
//				}
//
//				NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//				if (nbttagcompound != null)
//				{
//					byte[] nbt_byte_array = ByteWriter.serializeNBT(nbttagcompound);
//
//					File nbt_n_file = new File(inv_file, "nbt/" + item_id);
//					nbt_n_file.mkdir();
//					//				File[] nbt_i_file_array = nbt_n_file.listFiles();
//					//				boolean new_file = true;
//					//				for (File nbt_i_file : nbt_i_file_array)
//					//				{
//					//					try
//					//					{
//					//						byte[] byte_array = Files.readAllBytes(nbt_i_file.toPath());
//					//						if (Arrays.equals(nbt_byte_array, byte_array))
//					//						{
//					//							File nbt_n_size_file = new File(nbt_i_file + "s");
//					//							byte_array = Files.readAllBytes(nbt_n_size_file.toPath());
//					////							long nbt_count = ByteReader.getLong(byte_array, 0) + 1;
//					////							if ((count & (1L << 63)) != 0)
//					////							{
//					////								SDataInvSelect.STATE &= 255-1;
//					////								return;
//					////							}
//					//							ByteWriter.set(byte_array, ByteReader.getLong(byte_array, 0) + 1, 0);
//					//							new_file = false;
//					//							break;
//					//						}
//					//					}
//					//					catch (Exception e)
//					//					{
//					//						Nali.warn(e);
//					//						nbt_i_file.delete();
//					//					}
//					//				}
//
//					//				if (new_file)
//					//				{
//					try
//					{
//						long file_index = 0;
//						File nbt_i_file = new File(nbt_n_file, "" + file_index);
//						while (nbt_i_file.exists())
//						{
//							++file_index;
//							nbt_i_file = new File(nbt_n_file, "" + file_index);
//						}
//
//						Files.write(nbt_i_file.toPath(), nbt_byte_array);
//					}
//					catch (IOException e)
//					{
//						Nali.warn(e);
//					}
//				}
//				//			}
//				ENTITYPLAYERMP.inventory.removeStackFromSlot(item_slot);
//
//				NetworkRegistry.I.sendTo(new ClientMessage(new byte[]
//				{
//					CPageDa.ID,
//					CDaInvSelectAdd.ID
//				}), ENTITYPLAYERMP);
//			}

			if (SIEInv.invAdd(ENTITYPLAYERMP.world.getSaveHandler().getWorldDirectory(), inv, itemstack))
			{
				ENTITYPLAYERMP.inventory.removeStackFromSlot(item_slot);

				NetworkRegistry.I.sendTo(new ClientMessage(new byte[]
				{
					CPageDa.ID,
					CDaInvSelectAdd.ID
				}), ENTITYPLAYERMP);
			}

			BYTE_ARRAY = null;
			ENTITYPLAYERMP = null;
			SIEInv.ST &= 255 - SIEInv.BS_LOCK;
		}
	}
}
