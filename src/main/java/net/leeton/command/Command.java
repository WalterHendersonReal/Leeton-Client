package net.leeton.command;

import java.util.Objects;

import net.leeton.util.ChatUtils;
import net.leeton.Category;

public abstract class Command
{
    private final String name;
    private final String description;
    private final String[] syntax;
    private Category category;

    public Command(String name, String description, String... syntax)
    {
        this.name = Objects.requireNonNull(name);
		this.description = Objects.requireNonNull(description);
		
		Objects.requireNonNull(syntax);
		if(syntax.length > 0)
			syntax[0] = "Syntax: " + syntax[0];
		this.syntax = syntax;
    }

    public abstract void call(String[] args) throws CmdException;

    public final String getName()
	{
		return "!" + name;
	}

    public String getPrimaryAction()
	{
		return "";
	}

    public final String getDescription()
	{
		String description = this.description;
		
		if(syntax.length > 0)
			description += "\n";
		
		for(String line : syntax)
			description += "\n" + line;
		
		return description;
	}

    public final String[] getSyntax()
	{
		return syntax;
	}
	
	public final void printHelp()
	{
		for(String line : description.split("\n"))
			ChatUtils.message(line);
		
		for(String line : syntax)
			ChatUtils.message(line);
	}

    public final Category getCategory()
	{
		return category;
	}
	
	protected final void setCategory(Category category)
	{
		this.category = category;
	}
}