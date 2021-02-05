package net.minecraft.server;

public abstract class IRegistryWritable<T> extends IRegistry<T> {

    public IRegistryWritable() {}

    public abstract <V extends T> V a(int i, MinecraftKey minecraftkey, V v0);

    public abstract <V extends T> V a(MinecraftKey minecraftkey, V v0);

    public abstract boolean c();
}
