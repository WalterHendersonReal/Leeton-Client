package net.leeton.command;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.TreeMap;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.leeton.commands.*;

public final class CmdList
{
    public final SayCmd sayCmd = new SayCmd();




    private final TreeMap<String, Command> cmds =
		new TreeMap<>(String::compareToIgnoreCase);

    public CmdList()
	{
		try
		{
			for(Field field : CmdList.class.getDeclaredFields())
			{
				if(!field.getName().endsWith("Cmd"))
					continue;
				
				Command cmd = (Command)field.get(this);
				cmds.put(cmd.getName(), cmd);
			}
			
		}catch(Exception e)
		{
			String message = "Initializing Leeton commands";
			CrashReport report = CrashReport.create(e, message);
			throw new CrashException(report);
		}
	}

    public Command getCmdByName(String name)
	{
		return cmds.get("!" + name);
	}
	
	public Collection<Command> getAllCmds()
	{
		return cmds.values();
	}
	
	public int countCmds()
	{
		return cmds.size();
	}
}
