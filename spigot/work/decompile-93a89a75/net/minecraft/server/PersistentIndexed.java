package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

public class PersistentIndexed extends PersistentBase {

    private LongSet a = new LongOpenHashSet();
    private LongSet b = new LongOpenHashSet();

    public PersistentIndexed(String s) {
        super(s);
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.a = new LongOpenHashSet(nbttagcompound.getLongArray("All"));
        this.b = new LongOpenHashSet(nbttagcompound.getLongArray("Remaining"));
    }

    @Override
    public NBTTagCompound b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("All", this.a.toLongArray());
        nbttagcompound.a("Remaining", this.b.toLongArray());
        return nbttagcompound;
    }

    public void a(long i) {
        this.a.add(i);
        this.b.add(i);
    }

    public boolean b(long i) {
        return this.a.contains(i);
    }

    public boolean c(long i) {
        return this.b.contains(i);
    }

    public void d(long i) {
        this.b.remove(i);
    }

    public LongSet a() {
        return this.a;
    }
}
