package net.minecraft.server;

import io.netty.handler.codec.EncoderException;

public class SkipEncodeException extends EncoderException {

    public SkipEncodeException(Throwable throwable) {
        super(throwable);
    }
}
