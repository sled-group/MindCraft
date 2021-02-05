package net.minecraft.server;

import java.util.function.Supplier;

public class LazyInitVar<T> {

    private Supplier<T> a;
    private T b;

    public LazyInitVar(Supplier<T> supplier) {
        this.a = supplier;
    }

    public T a() {
        Supplier<T> supplier = this.a;

        if (supplier != null) {
            this.b = supplier.get();
            this.a = null;
        }

        return this.b;
    }
}
