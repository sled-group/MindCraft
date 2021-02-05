package net.minecraft.server;

public abstract class NBTNumber implements NBTBase {

    protected NBTNumber() {}

    public abstract long asLong();

    public abstract int asInt();

    public abstract short asShort();

    public abstract byte asByte();

    public abstract double asDouble();

    public abstract float asFloat();

    public abstract Number j();
}
