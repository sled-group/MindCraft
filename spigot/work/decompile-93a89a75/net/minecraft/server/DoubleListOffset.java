package net.minecraft.server;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleList;

public class DoubleListOffset extends AbstractDoubleList {

    private final DoubleList a;
    private final double b;

    public DoubleListOffset(DoubleList doublelist, double d0) {
        this.a = doublelist;
        this.b = d0;
    }

    public double getDouble(int i) {
        return this.a.getDouble(i) + this.b;
    }

    public int size() {
        return this.a.size();
    }
}
