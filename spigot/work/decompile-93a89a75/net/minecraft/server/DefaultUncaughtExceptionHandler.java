package net.minecraft.server;

import java.lang.Thread.UncaughtExceptionHandler;
import org.apache.logging.log4j.Logger;

public class DefaultUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private final Logger a;

    public DefaultUncaughtExceptionHandler(Logger logger) {
        this.a = logger;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        this.a.error("Caught previously unhandled exception :", throwable);
    }
}
