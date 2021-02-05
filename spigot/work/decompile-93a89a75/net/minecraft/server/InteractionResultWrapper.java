package net.minecraft.server;

public class InteractionResultWrapper<T> {

    private final EnumInteractionResult a;
    private final T b;

    public InteractionResultWrapper(EnumInteractionResult enuminteractionresult, T t0) {
        this.a = enuminteractionresult;
        this.b = t0;
    }

    public EnumInteractionResult a() {
        return this.a;
    }

    public T b() {
        return this.b;
    }
}
