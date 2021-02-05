package net.minecraft.server;

public class ChatMessageException extends IllegalArgumentException {

    public ChatMessageException(ChatMessage chatmessage, String s) {
        super(String.format("Error parsing: %s: %s", chatmessage, s));
    }

    public ChatMessageException(ChatMessage chatmessage, int i) {
        super(String.format("Invalid index %d requested for %s", i, chatmessage));
    }

    public ChatMessageException(ChatMessage chatmessage, Throwable throwable) {
        super(String.format("Error while parsing: %s", chatmessage), throwable);
    }
}
