package net.minecraft.server;

import java.util.function.Supplier;

public interface GameProfilerFiller {

    void a();

    void b();

    void enter(String s);

    void a(Supplier<String> supplier);

    void exit();

    void exitEnter(String s);
}
