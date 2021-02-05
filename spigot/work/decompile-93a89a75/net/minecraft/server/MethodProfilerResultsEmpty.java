package net.minecraft.server;

import java.io.File;

public class MethodProfilerResultsEmpty implements MethodProfilerResults {

    public static final MethodProfilerResultsEmpty a = new MethodProfilerResultsEmpty();

    private MethodProfilerResultsEmpty() {}

    @Override
    public boolean a(File file) {
        return false;
    }

    @Override
    public long a() {
        return 0L;
    }

    @Override
    public int b() {
        return 0;
    }

    @Override
    public long c() {
        return 0L;
    }

    @Override
    public int d() {
        return 0;
    }

    @Override
    public String e() {
        return "";
    }
}
