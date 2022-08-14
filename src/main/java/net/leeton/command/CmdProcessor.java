package net.leeton.command;

import java.util.Arrays;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;

import net.leeton.LeetonClient;
import net.leeton.util.ChatUtils;
import net.leeton.events.ChatOutputListener;
import net.leeton.command.Command;

public final class CmdProcessor implements ChatOutputListener
{

	private final CmdList cmds;
	
	public CmdProcessor(CmdList cmds)
	{
		this.cmds = cmds;
	}

    @Override
	public void onSentMessage(ChatOutputEvent event)
	{
		System.out.println("Message sent! from leeton!");

		if(!LeetonClient.INSTANCE.isEnabled())
			return;
		
		String message = event.getOriginalMessage().trim();
		if(!message.startsWith("!"))
			return;
		
		event.cancel();
		process(message.substring(1));
	}

    public void process(String input)
	{
		try
		{
			Command cmd = parseCmd(input);

			runCmd(cmd, input);
		}catch(CmdNotFoundException e)
		{
			e.printToChat();
		}

			
	}

	private Command parseCmd(String input) throws CmdNotFoundException
	{
		String cmdName = input.split(" ")[0];
		Command cmd = cmds.getCmdByName(cmdName);
		
		if(cmd == null)
			throw new CmdNotFoundException(input);
		
		return cmd;
	}

	private Command runCmd(Command cmd, String input)
	{
		String[] args = input.split(" ");
		args = Arrays.copyOfRange(args, 1, args.length);
		
		try
		{
			cmd.call(args);
			
		}catch(CmdException e)
		{
			e.printToChat(cmd);
			
		}catch(Throwable e)
		{
			CrashReport report = CrashReport.create(e, "Running a Leeton Command...");
			CrashReportSection section = report.addElement("Affected command");
			section.add("Command input", () -> input);
			throw new CrashException(report);
		}
	}

	private static class CmdNotFoundException extends Exception
	{
		private final String input;
		
		public CmdNotFoundException(String input)
		{
			this.input = input;
		}
		
		public void printToChat()
		{
			String cmdName = input.split(" ")[0];
			ChatUtils.error("Command not found: ." + cmdName);
			
			StringBuilder helpMsg = new StringBuilder();
			
			if(input.startsWith("/"))
			{
				helpMsg.append("Do \"!say " + input + "\"");
				helpMsg.append(" to send it in chat.");
				
			}else
			{
				helpMsg.append("Type \"!help\" to get a list of commands or");
				helpMsg.append("\"!say ." + input + "\"");
				helpMsg.append(" to say it in chat.");
			}
			
			ChatUtils.message(helpMsg.toString());
		}
	}
}