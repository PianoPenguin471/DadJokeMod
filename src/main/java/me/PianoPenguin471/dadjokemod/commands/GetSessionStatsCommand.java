package me.PianoPenguin471.dadjokemod.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;
import static me.PianoPenguin471.dadjokemod.DadJokeMod.*;

public class GetSessionStatsCommand extends ClientCommand {
	public GetSessionStatsCommand() {
		super("sessionstats");
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Session session = mc.getSession();
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Username: " + session.getUsername()));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Token: " + session.getToken()));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Token: " + session.getSessionID()));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "UUID: " + session.getProfile().getId()));
		sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + session.getSessionID()));
	}
}
