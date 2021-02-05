package net.minecraft.server;

public class ResourceKeyInvalidException extends RuntimeException {

    public ResourceKeyInvalidException(String s) {
        super(s);
    }

    public ResourceKeyInvalidException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
