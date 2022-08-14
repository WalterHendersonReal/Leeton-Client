package net.leeton.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import net.leeton.LeetonClient;

public enum ChatUtils
{
    ;

    private static final MinecraftClient mc = LeetonClient.mc;

    public static final String LEETON_PREFIX = "\u00a79[\u00a77\u00a7lLeeton\u00a79]\u00a7r ";
    public static final String WARNING_PREFIX = "\u00a79[\u00a76\u00a7lWARNING\u00a79]\u00a7r ";
    public static final String ERROR_PREFIX = "\u00a79[\u00a74\u00a7lERROR\u00a79]\u00a7r ";
    public static final String SYNTAX_ERROR_PREFIX = "\u00a7c\u00a7oSyntax Error\u00a7r : ";

    private static boolean enabled = true;

    private static void setEnabled(boolean enabled)
    {
        ChatUtils.enabled = enabled;
    }

    public static void component(Text component)
	{
		if(!enabled)
			return;
		
		ChatHud chatHud = mc.inGameHud.getChatHud();
		MutableText prefix = Text.literal(LEETON_PREFIX);
		chatHud.addMessage(prefix.append(component));
	}

    public static void message(String message)
	{
		component(Text.literal(message));
	}

    public static void warning(String message)
	{
		message(WARNING_PREFIX + message);
	}
	
	public static void error(String message)
	{
		message(ERROR_PREFIX + message);
	}
	
	public static void syntaxError(String message)
	{
		message(SYNTAX_ERROR_PREFIX + message);
	}


}