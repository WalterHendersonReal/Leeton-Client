package net.leeton.events;

import java.util.ArrayList;
import java.util.Objects;

import net.leeton.event.Listener;
import net.leeton.event.CancellableEvent;

public interface ChatOutputListener extends Listener
{
    public void onSentMessage(ChatOutputEvent event);

    public static class ChatOutputEvent extends CancellableEvent<ChatOutputListener>
    {
        private final String originalMessage;
        private String message;

        

        public ChatOutputEvent(String message)
		{
			this.message = Objects.requireNonNull(message);
			originalMessage = message;
		}

        public String getMessage()
        {
            return message;
        }

        public String getOriginalMessage()
        {
            return originalMessage;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public boolean isModified()
        {
            return !originalMessage.equals(message);
        }

        @Override
		public void fire(ArrayList<ChatOutputListener> listeners)
		{
			for(ChatOutputListener listener : listeners)
			{
				listener.onSentMessage(this);
				
                System.out.println("Leeton sees a message!");

				if(isCancelled())
					break;
			}
		}

        @Override
		public Class<ChatOutputListener> getListenerType()
		{
			return ChatOutputListener.class;
		}
    }
}