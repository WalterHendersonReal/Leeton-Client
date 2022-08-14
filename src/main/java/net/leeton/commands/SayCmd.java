package net.leeton.commands;

import net.minecraft.client.MinecraftClient;

import net.leeton.command.Command;
import net.leeton.command.CmdException;
import net.leeton.command.CmdSyntaxError;



public final class SayCmd extends Command
{
    public static final MinecraftClient mc = MinecraftClient.getInstance();

	public SayCmd()
	{
		super("say",
			"Sends the given chat message, even if it starts with a\n" + "dot.",
			".say <message>");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length < 1)
			throw new CmdSyntaxError();
		
		String message = String.join(" ", args);
		if(message.startsWith("/"))
			mc.player.sendCommand(message.substring(1));
		else
			mc.player.sendChatMessage(message, null);
	}
}