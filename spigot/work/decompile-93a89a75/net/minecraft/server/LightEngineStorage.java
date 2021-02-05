package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import javax.annotation.Nullable;

public abstract class LightEngineStorage<M extends LightEngineStorageArray<M>> extends LightEngineGraphSection {

    protected static final NibbleArray a = new NibbleArray();
    private static final EnumDirection[] k = EnumDirection.values();
    private final EnumSkyBlock l;
    private final ILightAccess m;
    protected final LongSet b = new LongOpenHashSet();
    protected final LongSet c = new LongOpenHashSet();
    protected final LongSet d = new LongOpenHashSet();
    protected volatile M e;
    protected final M f;
    protected final LongSet g = new LongOpenHashSet();
    protected final LongSet h = new LongOpenHashSet();
    protected final Long2ObjectMap<NibbleArray> i = new Long2ObjectOpenHashMap();
    private final LongSet n = new LongOpenHashSet();
    private final LongSet o = new LongOpenHashSet();
    protected volatile boolean j;

    protected LightEngineStorage(EnumSkyBlock enumskyblock, ILightAccess ilightaccess, M m0) {
        super(3, 16, 256);
        this.l = enumskyblock;
        this.m = ilightaccess;
        this.f = m0;
        this.e = m0.b();
        this.e.d();
    }

    protected boolean g(long i) {
        return this.a(i, true) != null;
    }

    @Nullable
    protected NibbleArray a(long i, boolean flag) {
        return this.a(flag ? this.f : this.e, i);
    }

    @Nullable
    protected NibbleArray a(M m0, long i) {
        return m0.c(i);
    }

    @Nullable
    public NibbleArray h(long i) {
        NibbleArray nibblearray = (NibbleArray) this.i.get(i);

        return nibblearray != null ? nibblearray : this.a(i, false);
    }

    protected abstract int d(long i);

    protected int i(long i) {
        long j = SectionPosition.e(i);
        NibbleArray nibblearray = this.a(j, true);

        return nibblearray.a(SectionPosition.b(BlockPosition.b(i)), SectionPosition.b(BlockPosition.c(i)), SectionPosition.b(BlockPosition.d(i)));
    }

    protected void b(long i, int j) {
        long k = SectionPosition.e(i);

        if (this.g.add(k)) {
            this.f.a(k);
        }

        NibbleArray nibblearray = this.a(k, true);

        nibblearray.a(SectionPosition.b(BlockPosition.b(i)), SectionPosition.b(BlockPosition.c(i)), SectionPosition.b(BlockPosition.d(i)), j);

        for (int l = -1; l <= 1; ++l) {
            for (int i1 = -1; i1 <= 1; ++i1) {
                for (int j1 = -1; j1 <= 1; ++j1) {
                    this.h.add(SectionPosition.e(BlockPosition.a(i, i1, j1, l)));
                }
            }
        }

    }

    @Override
    protected int c(long i) {
        return i == Long.MAX_VALUE ? 2 : (this.b.contains(i) ? 0 : (!this.o.contains(i) && this.f.b(i) ? 1 : 2));
    }

    @Override
    protected int b(long i) {
        return this.c.contains(i) ? 2 : (!this.b.contains(i) && !this.d.contains(i) ? 2 : 0);
    }

    @Override
    protected void a(long i, int j) {
        int k = this.c(i);

        if (k != 0 && j == 0) {
            this.b.add(i);
            this.d.remove(i);
        }

        if (k == 0 && j != 0) {
            this.b.remove(i);
            this.c.remove(i);
        }

        if (k >= 2 && j != 2) {
            if (this.o.contains(i)) {
                this.o.remove(i);
            } else {
                this.f.a(i, this.j(i));
                this.g.add(i);
                this.k(i);

                for (int l = -1; l <= 1; ++l) {
                    for (int i1 = -1; i1 <= 1; ++i1) {
                        for (int j1 = -1; j1 <= 1; ++j1) {
                            this.h.add(SectionPosition.e(BlockPosition.a(i, i1, j1, l)));
                        }
                    }
                }
            }
        }

        if (k != 2 && j >= 2) {
            this.o.add(i);
        }

        this.j = !this.o.isEmpty();
    }

    protected NibbleArray j(long i) {
        NibbleArray nibblearray = (NibbleArray) this.i.get(i);

        return nibblearray != null ? nibblearray : new NibbleArray();
    }

    protected void a(LightEngineLayer<?, ?> lightenginelayer, long i) {
        int j = SectionPosition.c(SectionPosition.b(i));
        int k = SectionPosition.c(SectionPosition.c(i));
        int l = SectionPosition.c(SectionPosition.d(i));

        for (int i1 = 0; i1 < 16; ++i1) {
            for (int j1 = 0; j1 < 16; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    long l1 = BlockPosition.a(j + i1, k + j1, l + k1);

                    lightenginelayer.e(l1);
                }
            }
        }

    }

    protected boolean a() {
        return this.j;
    }

    protected void a(LightEngineLayer<M, ?> lightenginelayer, boolean flag, boolean flag1) {
        if (this.a() || !this.i.isEmpty()) {
            LongIterator longiterator = this.o.iterator();

            long i;
            NibbleArray nibblearray;

            while (longiterator.hasNext()) {
                i = (Long) longiterator.next();
                this.a(lightenginelayer, i);
                NibbleArray nibblearray1 = (NibbleArray) this.i.remove(i);

                nibblearray = this.f.d(i);
                if (this.n.contains(SectionPosition.f(i))) {
                    if (nibblearray1 != null) {
                        this.i.put(i, nibblearray1);
                    } else if (nibblearray != null) {
                        this.i.put(i, nibblearray);
                    }
                }
            }

            this.f.c();
            longiterator = this.o.iterator();

            while (longiterator.hasNext()) {
                i = (Long) longiterator.next();
                this.l(i);
            }

            this.o.clear();
            this.j = false;
            ObjectIterator objectiterator = this.i.long2ObjectEntrySet().iterator();

            Entry entry;
            long j;

            while (objectiterator.hasNext()) {
                entry = (Entry) objectiterator.next();
                j = entry.getLongKey();
                if (this.g(j)) {
                    nibblearray = (NibbleArray) entry.getValue();
                    if (this.f.c(j) != nibblearray) {
                        this.a(lightenginelayer, j);
                        this.f.a(j, nibblearray);
                        this.g.add(j);
                    }
                }
            }

            this.f.c();
            if (!flag1) {
                longiterator = this.i.keySet().iterator();

                while (longiterator.hasNext()) {
                    i = (Long) longiterator.next();
                    if (this.g(i)) {
                        int k = SectionPosition.c(SectionPosition.b(i));
                        int l = SectionPosition.c(SectionPosition.c(i));
                        int i1 = SectionPosition.c(SectionPosition.d(i));
                        EnumDirection[] aenumdirection = LightEngineStorage.k;
                        int j1 = aenumdirection.length;

                        for (int k1 = 0; k1 < j1; ++k1) {
                            EnumDirection enumdirection = aenumdirection[k1];
                            long l1 = SectionPosition.a(i, enumdirection);

                            if (!this.i.containsKey(l1) && this.g(l1)) {
                                for (int i2 = 0; i2 < 16; ++i2) {
                                    for (int j2 = 0; j2 < 16; ++j2) {
                                        long k2;
                                        long l2;

                                        switch (enumdirection) {
                                            case DOWN:
                                                k2 = BlockPosition.a(k + j2, l, i1 + i2);
                                                l2 = BlockPosition.a(k + j2, l - 1, i1 + i2);
                                                break;
                                            case UP:
                                                k2 = BlockPosition.a(k + j2, l + 16 - 1, i1 + i2);
                                                l2 = BlockPosition.a(k + j2, l + 16, i1 + i2);
                                                break;
                                            case NORTH:
                                                k2 = BlockPosition.a(k + i2, l + j2, i1);
                                                l2 = BlockPosition.a(k + i2, l + j2, i1 - 1);
                                                break;
                                            case SOUTH:
                                                k2 = BlockPosition.a(k + i2, l + j2, i1 + 16 - 1);
                                                l2 = BlockPosition.a(k + i2, l + j2, i1 + 16);
                                                break;
                                            case WEST:
                                                k2 = BlockPosition.a(k, l + i2, i1 + j2);
                                                l2 = BlockPosition.a(k - 1, l + i2, i1 + j2);
                                                break;
                                            default:
                                                k2 = BlockPosition.a(k + 16 - 1, l + i2, i1 + j2);
                                                l2 = BlockPosition.a(k + 16, l + i2, i1 + j2);
                                        }

                                        lightenginelayer.a(k2, l2, lightenginelayer.b(k2, l2, lightenginelayer.c(k2)), false);
                                        lightenginelayer.a(l2, k2, lightenginelayer.b(l2, k2, lightenginelayer.c(l2)), false);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            objectiterator = this.i.long2ObjectEntrySet().iterator();

            while (objectiterator.hasNext()) {
                entry = (Entry) objectiterator.next();
                j = entry.getLongKey();
                if (this.g(j)) {
                    objectiterator.remove();
                }
            }

        }
    }

    protected void k(long i) {}

    protected void l(long i) {}

    protected void b(long i, boolean flag) {}

    public void c(long i, boolean flag) {
        if (flag) {
            this.n.add(i);
        } else {
            this.n.remove(i);
        }

    }

    protected void a(long i, @Nullable NibbleArray nibblearray) {
        if (nibblearray != null) {
            this.i.put(i, nibblearray);
        } else {
            this.i.remove(i);
        }

    }

    protected void d(long i, boolean flag) {
        boolean flag1 = this.b.contains(i);

        if (!flag1 && !flag) {
            this.d.add(i);
            this.a(Long.MAX_VALUE, i, 0, true);
        }

        if (flag1 && flag) {
            this.c.add(i);
            this.a(Long.MAX_VALUE, i, 2, false);
        }

    }

    protected void c() {
        if (this.b()) {
            this.b(Integer.MAX_VALUE);
        }

    }

    protected void d() {
        if (!this.g.isEmpty()) {
            M m0 = this.f.b();

            m0.d();
            this.e = m0;
            this.g.clear();
        }

        if (!this.h.isEmpty()) {
            LongIterator longiterator = this.h.iterator();

            while (longiterator.hasNext()) {
                long i = longiterator.nextLong();

                this.m.a(this.l, SectionPosition.a(i));
            }

            this.h.clear();
        }

    }
}
