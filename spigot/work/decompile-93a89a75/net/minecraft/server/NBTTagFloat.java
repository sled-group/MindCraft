package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTNumber {

    private float data;

    NBTTagFloat() {}

    public NBTTagFloat(float f) {
        this.data = f;
    }

    @Override
    public void write(DataOutput dataoutput) throws IOException {
        dataoutput.writeFloat(this.data);
    }

    @Override
    public void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
        nbtreadlimiter.a(96L);
        this.data = datainput.readFloat();
    }

    @Override
    public byte getTypeId() {
        return 5;
    }

    @Override
    public String toString() {
        return this.data + "f";
    }

    @Override
    public NBTTagFloat clone() {
        return new NBTTagFloat(this.data);
    }

    public boolean equals(Object object) {
        return this == object ? true : object instanceof NBTTagFloat && this.data == ((NBTTagFloat) object).data;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.data);
    }

    @Override
    public IChatBaseComponent a(String s, int i) {
        IChatBaseComponent ichatbasecomponent = (new ChatComponentText("f")).a(NBTTagFloat.e);

        return (new ChatComponentText(String.valueOf(this.data))).addSibling(ichatbasecomponent).a(NBTTagFloat.d);
    }

    @Override
    public long asLong() {
        return (long) this.data;
    }

    @Override
    public int asInt() {
        return MathHelper.d(this.data);
    }

    @Override
    public short asShort() {
        return (short) (MathHelper.d(this.data) & '\uffff');
    }

    @Override
    public byte asByte() {
        return (byte) (MathHelper.d(this.data) & 255);
    }

    @Override
    public double asDouble() {
        return (double) this.data;
    }

    @Override
    public float asFloat() {
        return this.data;
    }

    @Override
    public Number j() {
        return this.data;
    }
}
