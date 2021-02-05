package net.minecraft.server;

import java.util.function.Function;
import javax.annotation.Nullable;

public class DataPaletteHash<T> implements DataPalette<T> {

    private final RegistryBlockID<T> a;
    private final RegistryID<T> b;
    private final DataPaletteExpandable<T> c;
    private final Function<NBTTagCompound, T> d;
    private final Function<T, NBTTagCompound> e;
    private final int f;

    public DataPaletteHash(RegistryBlockID<T> registryblockid, int i, DataPaletteExpandable<T> datapaletteexpandable, Function<NBTTagCompound, T> function, Function<T, NBTTagCompound> function1) {
        this.a = registryblockid;
        this.f = i;
        this.c = datapaletteexpandable;
        this.d = function;
        this.e = function1;
        this.b = new RegistryID<>(1 << i);
    }

    @Override
    public int a(T t0) {
        int i = this.b.getId(t0);

        if (i == -1) {
            i = this.b.c(t0);
            if (i >= 1 << this.f) {
                i = this.c.onResize(this.f + 1, t0);
            }
        }

        return i;
    }

    @Override
    public boolean b(T t0) {
        return this.b.getId(t0) != -1;
    }

    @Nullable
    @Override
    public T a(int i) {
        return this.b.fromId(i);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) {
        int i = this.b();

        packetdataserializer.d(i);

        for (int j = 0; j < i; ++j) {
            packetdataserializer.d(this.a.getId(this.b.fromId(j)));
        }

    }

    @Override
    public int a() {
        int i = PacketDataSerializer.a(this.b());

        for (int j = 0; j < this.b(); ++j) {
            i += PacketDataSerializer.a(this.a.getId(this.b.fromId(j)));
        }

        return i;
    }

    public int b() {
        return this.b.b();
    }

    @Override
    public void a(NBTTagList nbttaglist) {
        this.b.a();

        for (int i = 0; i < nbttaglist.size(); ++i) {
            this.b.c(this.d.apply(nbttaglist.getCompound(i)));
        }

    }

    public void b(NBTTagList nbttaglist) {
        for (int i = 0; i < this.b(); ++i) {
            nbttaglist.add(this.e.apply(this.b.fromId(i)));
        }

    }
}
