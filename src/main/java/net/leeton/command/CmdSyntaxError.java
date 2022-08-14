package net.leeton.command;

import net.leeton.util.ChatUtils;

public final class CmdSyntaxError extends CmdException
{
	public CmdSyntaxError()
	{}
	
	public CmdSyntaxError(String message)
	{
		super(message);
	}
	
	@Override
	public void printToChat(Command cmd)
	{
		String message = getMessage();
		if(message != null)
			ChatUtils.syntaxError(message);
		
		for(String line : cmd.getSyntax())
			ChatUtils.message(line);
	}
}