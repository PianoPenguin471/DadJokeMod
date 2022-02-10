package me.PianoPenguin471.dadjokemod.commands;

import me.PianoPenguin471.dadjokemod.DadJokeMod;
import net.minecraft.block.BlockCauldron;
import net.minecraft.command.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.ChatComponentText;

import static me.PianoPenguin471.dadjokemod.DadJokeMod.mc;

public class LoadItemCommand extends ClientCommand {
	public LoadItemCommand() {
		super("loaditem");
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (DadJokeMod.savedItem != null) {
			EntityArrow
			mc.thePlayer.inventory.setInventorySlotContents(3, DadJokeMod.savedItem);
		}
	}
}
