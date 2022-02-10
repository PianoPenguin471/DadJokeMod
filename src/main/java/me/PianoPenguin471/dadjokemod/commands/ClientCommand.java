package me.PianoPenguin471.dadjokemod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ClientCommand extends CommandBase {
	String commandName, usage;
	public ClientCommand(String name, String usage) {
		this.commandName = name;
		this.usage = usage;
	}
	public ClientCommand(String name) {this(name, "/" + name);}
	public String getCommandName() {return commandName;}
	public String getCommandUsage(ICommandSender sender) {return usage;}
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "This is a template command with no usage.")); }
	public boolean canCommandSenderUseCommand(ICommandSender sender) {return true;}
}
