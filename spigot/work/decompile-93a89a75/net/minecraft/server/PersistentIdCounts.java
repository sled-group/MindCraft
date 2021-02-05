package net.minecraft.server;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.Iterator;

public class PersistentIdCounts extends PersistentBase {

    private final Object2IntMap<String> a = new Object2IntOpenHashMap();

    public PersistentIdCounts() {
        super("idcounts");
        this.a.defaultReturnValue(-1);
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.a.clear();
        Iterator iterator = nbttagcompound.getKeys().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            if (nbttagcompound.hasKeyOfType(s, 99)) {
                this.a.put(s, nbttagcompound.getInt(s));
            }
        }

    }

    @Override
    public NBTTagCompound b(NBTTagCompound nbttagcompound) {
        ObjectIterator objectiterator = this.a.object2IntEntrySet().iterator();

        while (objectiterator.hasNext()) {
            Entry<String> entry = (Entry) objectiterator.next();

            nbttagcompound.setInt((String) entry.getKey(), entry.getIntValue());
        }

        return nbttagcompound;
    }

    public int a() {
        int i = this.a.getInt("map") + 1;

        this.a.put("map", i);
        this.b();
        return i;
    }
}
