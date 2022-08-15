package net.leeton.events;


import java.util.ArrayList;

import net.leeton.event.Event;
import net.leeton.event.Listener;



public interface UpdateListener extends Listener {

    public void onUpdate();

    public static class UpdateEvent extends Event<UpdateListener>
	{
		public static final UpdateEvent INSTANCE = new UpdateEvent();
		
		@Override
		public void fire(ArrayList<UpdateListener> listeners)
		{
			for(UpdateListener listener : listeners)
				listener.onUpdate();
		}
		
		@Override
		public Class<UpdateListener> getListenerType()
		{
			return UpdateListener.class;
		}
	}
}
