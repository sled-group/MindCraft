package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTNumber {

    private long data;

    NBTTagLong() {}

    public NBTTagLong(long i) {
        this.data = i;
    }

    @Override
    public void write(DataOutput dataoutput) throws IOException {
        dataoutput.writeLong(this.data);
    }

    @Override
    public void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
        nbtreadlimiter.a(128L);
        this.data = datainput.readLong();
    }

    @Override
    public byte getTypeId() {
        return 4;
    }

    @Override
    public String toString() {
        return this.data + "L";
    }

    @Override
    public NBTTagLong clone() {
        return new NBTTagLong(this.data);
    }

    public boolean equals(Object object) {
        return this == object ? true : object instanceof NBTTagLong && this.data == ((NBTTagLong) object).data;
    }

    public int hashCode() {
        return (int) (this.data ^ this.data >>> 32);
    }

    @Override
    public IChatBaseComponent a(String s, int i) {
        IChatBaseComponent ichatbasecomponent = (new ChatComponentText("L")).a(NBTTagLong.e);

        return (new ChatComponentText(String.valueOf(this.data))).addSibling(ichatbasecomponent).a(NBTTagLong.d);
    }

    @Override
    public long asLong() {
        return this.data;
    }

    @Override
    public int asInt() {
        return (int) (this.data & -1L);
    }

    @Override
    public short asShort() {
        return (short) ((int) (this.data & 65535L));
    }

    @Override
    public byte asByte() {
        return (byte) ((int) (this.data & 255L));
    }

    @Override
    public double asDouble() {
        return (double) this.data;
    }

    @Override
    public float asFloat() {
        return (float) this.data;
    }

    @Override
    public Number j() {
        return this.data;
    }
}
