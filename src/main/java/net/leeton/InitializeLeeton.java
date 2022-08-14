package net.leeton;

import net.fabricmc.api.ModInitializer;

public final class InitializeLeeton implements ModInitializer
{
    private static boolean initialized = false;

    @Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		

		if(initialized)
			throw new RuntimeException(
				"Leeton Initializer ran twice!");
		
		LeetonClient.INSTANCE.initialize();
		initialized = true;
		System.out.println("Initiated Leeeton!");
	}
}