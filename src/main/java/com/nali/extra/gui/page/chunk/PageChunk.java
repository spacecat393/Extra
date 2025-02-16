package com.nali.extra.gui.page.chunk;

import com.nali.Nali;
import com.nali.extra.ExtraView;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageEdit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SideOnly(Side.CLIENT)
public class PageChunk extends PageEdit
{
	@Override
	public void init()
	{
		super.init();
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("CHUNK".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			new BoxTextAll("CHUNK-LIST".toCharArray()),
			new BoxTextAll("CHUNK-MAP".toCharArray()),
			new BoxTextAll("VIEW".toCharArray()),
			new BoxTextAll(this.getChar("YAW_BLOCK_EPSILON " + ExtraView.YAW_BLOCK_EPSILON)),
			new BoxTextAll(this.getChar("PITCH_BLOCK_EPSILON " + ExtraView.PITCH_BLOCK_EPSILON)),
			new BoxTextAll(this.getChar("CHUNK_EPSILON " + ExtraView.CHUNK_EPSILON)),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 9;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		try
		{
			switch (this.select)
			{
				case 2:
					PAGE_LIST.add(this);
					KEY_LIST.add(Key.KEY);
					this.set(new PageList(), new KeySelect());
					break;
				case 3:
					PAGE_LIST.add(this);
					KEY_LIST.add(Key.KEY);
					this.set(new PageMap(), new KeySelect());
					break;
				case 5:
					if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
					{
						ExtraView.YAW_BLOCK_EPSILON = Float.parseFloat(this.input_stringbuilder.toString());
					}
					else
					{
						this.input_stringbuilder.setLength(0);
						this.input_stringbuilder.append(ExtraView.YAW_BLOCK_EPSILON);
						this.select_box = this.input_stringbuilder.length();
					}
					this.fl ^= BF_ENTER_MODE;
					this.scroll = 0;
					break;
				case 6:
					if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
					{
						ExtraView.PITCH_BLOCK_EPSILON = Float.parseFloat(this.input_stringbuilder.toString());
					}
					else
					{
						this.input_stringbuilder.setLength(0);
						this.input_stringbuilder.append(ExtraView.PITCH_BLOCK_EPSILON);
						this.select_box = this.input_stringbuilder.length();
					}
					this.fl ^= BF_ENTER_MODE;
					this.scroll = 0;
					break;
				case 7:
					if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
					{
						ExtraView.CHUNK_EPSILON = Float.parseFloat(this.input_stringbuilder.toString());
					}
					else
					{
						this.input_stringbuilder.setLength(0);
						this.input_stringbuilder.append(ExtraView.CHUNK_EPSILON);
						this.select_box = this.input_stringbuilder.length();
					}
					this.fl ^= BF_ENTER_MODE;
					this.scroll = 0;
					break;
				case 9:
					this.back();
					break;
			}
		}
		catch (Exception e)
		{
		}
	}

	@Override
	public void exit()
	{
		this.save();
		super.exit();
	}

	@Override
	public void back()
	{
		this.save();
		super.back();
	}

	public void save()
	{
		try
		{
			Files.write(Paths.get("nali/nali/tmp/config_extra_view"), ExtraView.getByteArray());
		}
		catch (IOException e)
		{
			Nali.error(e);
		}
	}
}
