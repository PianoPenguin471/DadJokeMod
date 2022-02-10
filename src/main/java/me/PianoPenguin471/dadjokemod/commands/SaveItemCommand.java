package me.PianoPenguin471.dadjokemod.commands;

import me.PianoPenguin471.dadjokemod.DadJokeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class SaveItemCommand extends ClientCommand {
	public SaveItemCommand() {
		super("saveitem");
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		DadJokeMod.savedItem = Minecraft.getMinecraft().thePlayer.getHeldItem().copy();
		sender.addChatMessage(new ChatComponentText(DadJokeMod.savedItem.serializeNBT().toString()));
		sender.addChatMessage(new ChatComponentText("Item Saved"));
	}
}
