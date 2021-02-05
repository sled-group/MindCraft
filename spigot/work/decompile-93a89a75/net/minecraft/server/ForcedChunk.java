package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;

public class ForcedChunk extends PersistentBase {

    private LongSet a = new LongOpenHashSet();

    public ForcedChunk() {
        super("chunks");
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.a = new LongOpenHashSet(nbttagcompound.getLongArray("Forced"));
    }

    @Override
    public NBTTagCompound b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Forced", this.a.toLongArray());
        return nbttagcompound;
    }

    public LongSet a() {
        return this.a;
    }
}
