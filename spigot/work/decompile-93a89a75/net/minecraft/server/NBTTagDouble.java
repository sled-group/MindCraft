package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTNumber {

    private double data;

    NBTTagDouble() {}

    public NBTTagDouble(double d0) {
        this.data = d0;
    }

    @Override
    public void write(DataOutput dataoutput) throws IOException {
        dataoutput.writeDouble(this.data);
    }

    @Override
    public void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
        nbtreadlimiter.a(128L);
        this.data = datainput.readDouble();
    }

    @Override
    public byte getTypeId() {
        return 6;
    }

    @Override
    public String toString() {
        return this.data + "d";
    }

    @Override
    public NBTTagDouble clone() {
        return new NBTTagDouble(this.data);
    }

    public boolean equals(Object object) {
        return this == object ? true : object instanceof NBTTagDouble && this.data == ((NBTTagDouble) object).data;
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.data);

        return (int) (i ^ i >>> 32);
    }

    @Override
    public IChatBaseComponent a(String s, int i) {
        IChatBaseComponent ichatbasecomponent = (new ChatComponentText("d")).a(NBTTagDouble.e);

        return (new ChatComponentText(String.valueOf(this.data))).addSibling(ichatbasecomponent).a(NBTTagDouble.d);
    }

    @Override
    public long asLong() {
        return (long) Math.floor(this.data);
    }

    @Override
    public int asInt() {
        return MathHelper.floor(this.data);
    }

    @Override
    public short asShort() {
        return (short) (MathHelper.floor(this.data) & '\uffff');
    }

    @Override
    public byte asByte() {
        return (byte) (MathHelper.floor(this.data) & 255);
    }

    @Override
    public double asDouble() {
        return this.data;
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
