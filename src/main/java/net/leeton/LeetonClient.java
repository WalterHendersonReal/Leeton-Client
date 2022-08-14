package net.leeton;

import net.minecraft.client.MinecraftClient;

import net.leeton.event.EventManager;
import net.leeton.command.CmdProcessor;
import net.leeton.events.ChatOutputListener;

public enum LeetonClient 
{
    INSTANCE;

    public static final MinecraftClient mc = MinecraftClient.getInstance();
	// public static final IMinecraftClient IMC = (IMinecraftClient)MC;
        
    public static final String VERSION = "7.27.1";
	public static final String MC_VERSION = "1.19.2";

    private boolean enabled = true;

    private EventManager eventManager;
    private CmdProcessor cmdProcessor;

    public void initialize() 
    {
        
        eventManager = new EventManager(this);
        System.out.println("Initiated Event Manager");
        
        cmdProcessor = new CmdProcessor();
        eventManager.add(ChatOutputListener.class,cmdProcessor);
        System.out.println("Initiated Command Processor!");
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public EventManager getEventManager()
    {
        return eventManager;
    }
}

