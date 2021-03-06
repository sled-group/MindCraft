package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

public class NBTTagIntArray extends NBTList<NBTTagInt> {

    private int[] data;

    NBTTagIntArray() {}

    public NBTTagIntArray(int[] aint) {
        this.data = aint;
    }

    public NBTTagIntArray(List<Integer> list) {
        this(a(list));
    }

    private static int[] a(List<Integer> list) {
        int[] aint = new int[list.size()];

        for (int i = 0; i < list.size(); ++i) {
            Integer integer = (Integer) list.get(i);

            aint[i] = integer == null ? 0 : integer;
        }

        return aint;
    }

    @Override
    public void write(DataOutput dataoutput) throws IOException {
        dataoutput.writeInt(this.data.length);
        int[] aint = this.data;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int k = aint[j];

            dataoutput.writeInt(k);
        }

    }

    @Override
    public void load(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
        nbtreadlimiter.a(192L);
        int j = datainput.readInt();

        nbtreadlimiter.a((long) (32 * j));
        this.data = new int[j];

        for (int k = 0; k < j; ++k) {
            this.data[k] = datainput.readInt();
        }

    }

    @Override
    public byte getTypeId() {
        return 11;
    }

    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder("[I;");

        for (int i = 0; i < this.data.length; ++i) {
            if (i != 0) {
                stringbuilder.append(',');
            }

            stringbuilder.append(this.data[i]);
        }

        return stringbuilder.append(']').toString();
    }

    @Override
    public NBTTagIntArray clone() {
        int[] aint = new int[this.data.length];

        System.arraycopy(this.data, 0, aint, 0, this.data.length);
        return new NBTTagIntArray(aint);
    }

    public boolean equals(Object object) {
        return this == object ? true : object instanceof NBTTagIntArray && Arrays.equals(this.data, ((NBTTagIntArray) object).data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public int[] getInts() {
        return this.data;
    }

    @Override
    public IChatBaseComponent a(String s, int i) {
        IChatBaseComponent ichatbasecomponent = (new ChatComponentText("I")).a(NBTTagIntArray.e);
        IChatBaseComponent ichatbasecomponent1 = (new ChatComponentText("[")).addSibling(ichatbasecomponent).a(";");

        for (int j = 0; j < this.data.length; ++j) {
            ichatbasecomponent1.a(" ").addSibling((new ChatComponentText(String.valueOf(this.data[j]))).a(NBTTagIntArray.d));
            if (j != this.data.length - 1) {
                ichatbasecomponent1.a(",");
            }
        }

        ichatbasecomponent1.a("]");
        return ichatbasecomponent1;
    }

    public int size() {
        return this.data.length;
    }

    public NBTTagInt get(int i) {
        return new NBTTagInt(this.data[i]);
    }

    public NBTTagInt set(int i, NBTTagInt nbttagint) {
        int j = this.data[i];

        this.data[i] = nbttagint.asInt();
        return new NBTTagInt(j);
    }

    public void add(int i, NBTTagInt nbttagint) {
        this.data = ArrayUtils.add(this.data, i, nbttagint.asInt());
    }

    @Override
    public boolean a(int i, NBTBase nbtbase) {
        if (nbtbase instanceof NBTNumber) {
            this.data[i] = ((NBTNumber) nbtbase).asInt();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean b(int i, NBTBase nbtbase) {
        if (nbtbase instanceof NBTNumber) {
            this.data = ArrayUtils.add(this.data, i, ((NBTNumber) nbtbase).asInt());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NBTTagInt remove(int i) {
        int j = this.data[i];

        this.data = ArrayUtils.remove(this.data, i);
        return new NBTTagInt(j);
    }

    public void clear() {
        this.data = new int[0];
    }
}
