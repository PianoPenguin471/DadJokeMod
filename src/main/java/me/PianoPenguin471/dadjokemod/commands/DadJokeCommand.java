package me.PianoPenguin471.dadjokemod.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import static me.PianoPenguin471.dadjokemod.DadJokeMod.*;

public class DadJokeCommand extends ClientCommand {
	public DadJokeCommand() {
		super("dadjoke");
	}
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		String message;
		try {message = getDadJoke();} catch (Exception e) {throw new CommandException("commands.generic.exception");}
		IChatComponent component = new ChatComponentText(EnumChatFormatting.GREEN + message);
		component.setChatStyle(component.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, message)));
		sender.addChatMessage(component);
	}
}