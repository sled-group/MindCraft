package net.minecraft.server;

import javax.annotation.Nullable;

public interface IAttribute {

    String getName();

    double a(double d0);

    double getDefault();

    boolean c();

    @Nullable
    IAttribute d();
}
