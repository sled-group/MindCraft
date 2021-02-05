package net.minecraft.server;

import java.util.function.Supplier;

public interface GameProfilerFillerActive extends GameProfilerFiller {

    @Override
    void enter(String s);

    @Override
    void a(Supplier<String> supplier);

    @Override
    void exit();

    @Override
    void exitEnter(String s);

    MethodProfilerResults d();
}
