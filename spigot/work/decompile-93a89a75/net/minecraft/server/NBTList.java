package net.minecraft.server;

import java.util.AbstractList;

public abstract class NBTList<T extends NBTBase> extends AbstractList<T> implements NBTBase {

    public NBTList() {}

    public abstract T set(int i, T t0);

    public abstract void add(int i, T t0);

    public abstract T remove(int i);

    public abstract boolean a(int i, NBTBase nbtbase);

    public abstract boolean b(int i, NBTBase nbtbase);
}
