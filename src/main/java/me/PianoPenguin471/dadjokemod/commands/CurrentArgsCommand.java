package me.PianoPenguin471.dadjokemod.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class CurrentArgsCommand extends ClientCommand {
	public CurrentArgsCommand() {
		super("getargs");
	}
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		for (String arg : runtimeMXBean.getInputArguments()) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + arg));
		}
	}
}
