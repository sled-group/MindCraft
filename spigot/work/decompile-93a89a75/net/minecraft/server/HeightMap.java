package net.minecraft.server;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class HeightMap {

    private static final Predicate<IBlockData> a = (iblockdata) -> {
        return !iblockdata.isAir();
    };
    private static final Predicate<IBlockData> b = (iblockdata) -> {
        return iblockdata.getMaterial().isSolid();
    };
    private final DataBits c = new DataBits(9, 256);
    private final Predicate<IBlockData> d;
    private final IChunkAccess e;

    public HeightMap(IChunkAccess ichunkaccess, HeightMap.Type heightmap_type) {
        this.d = heightmap_type.d();
        this.e = ichunkaccess;
    }

    public static void a(IChunkAccess ichunkaccess, Set<HeightMap.Type> set) {
        int i = set.size();
        ObjectList<HeightMap> objectlist = new ObjectArrayList(i);
        ObjectListIterator<HeightMap> objectlistiterator = objectlist.iterator();
        int j = ichunkaccess.b() + 16;
        BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.r();
        Throwable throwable = null;

        try {
            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < 16; ++l) {
                    Iterator iterator = set.iterator();

                    while (iterator.hasNext()) {
                        HeightMap.Type heightmap_type = (HeightMap.Type) iterator.next();

                        objectlist.add(ichunkaccess.b(heightmap_type));
                    }

                    for (int i1 = j - 1; i1 >= 0; --i1) {
                        blockposition_pooledblockposition.d(k, i1, l);
                        IBlockData iblockdata = ichunkaccess.getType(blockposition_pooledblockposition);

                        if (iblockdata.getBlock() != Blocks.AIR) {
                            while (objectlistiterator.hasNext()) {
                                HeightMap heightmap = (HeightMap) objectlistiterator.next();

                                if (heightmap.d.test(iblockdata)) {
                                    heightmap.a(k, l, i1 + 1);
                                    objectlistiterator.remove();
                                }
                            }

                            if (objectlist.isEmpty()) {
                                break;
                            }

                            objectlistiterator.back(i);
                        }
                    }
                }
            }
        } catch (Throwable throwable1) {
            throwable = throwable1;
            throw throwable1;
        } finally {
            if (blockposition_pooledblockposition != null) {
                if (throwable != null) {
                    try {
                        blockposition_pooledblockposition.close();
                    } catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                } else {
                    blockposition_pooledblockposition.close();
                }
            }

        }

    }

    public boolean a(int i, int j, int k, IBlockData iblockdata) {
        int l = this.a(i, k);

        if (j <= l - 2) {
            return false;
        } else {
            if (this.d.test(iblockdata)) {
                if (j >= l) {
                    this.a(i, k, j + 1);
                    return true;
                }
            } else if (l - 1 == j) {
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (int i1 = j - 1; i1 >= 0; --i1) {
                    blockposition_mutableblockposition.d(i, i1, k);
                    if (this.d.test(this.e.getType(blockposition_mutableblockposition))) {
                        this.a(i, k, i1 + 1);
                        return true;
                    }
                }

                this.a(i, k, 0);
                return true;
            }

            return false;
        }
    }

    public int a(int i, int j) {
        return this.a(c(i, j));
    }

    private int a(int i) {
        return this.c.a(i);
    }

    private void a(int i, int j, int k) {
        this.c.b(c(i, j), k);
    }

    public void a(long[] along) {
        System.arraycopy(along, 0, this.c.a(), 0, along.length);
    }

    public long[] a() {
        return this.c.a();
    }

    private static int c(int i, int j) {
        return i + j * 16;
    }

    public static enum Type {

        WORLD_SURFACE_WG("WORLD_SURFACE_WG", HeightMap.Use.WORLDGEN, HeightMap.a), WORLD_SURFACE("WORLD_SURFACE", HeightMap.Use.CLIENT, HeightMap.a), OCEAN_FLOOR_WG("OCEAN_FLOOR_WG", HeightMap.Use.WORLDGEN, HeightMap.b), OCEAN_FLOOR("OCEAN_FLOOR", HeightMap.Use.LIVE_WORLD, HeightMap.b), MOTION_BLOCKING("MOTION_BLOCKING", HeightMap.Use.CLIENT, (iblockdata) -> {
            return iblockdata.getMaterial().isSolid() || !iblockdata.p().isEmpty();
        }), MOTION_BLOCKING_NO_LEAVES("MOTION_BLOCKING_NO_LEAVES", HeightMap.Use.LIVE_WORLD, (iblockdata) -> {
            return (iblockdata.getMaterial().isSolid() || !iblockdata.p().isEmpty()) && !(iblockdata.getBlock() instanceof BlockLeaves);
        });

        private final String g;
        private final HeightMap.Use h;
        private final Predicate<IBlockData> i;
        private static final Map<String, HeightMap.Type> j = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
            HeightMap.Type[] aheightmap_type = values();
            int i = aheightmap_type.length;

            for (int j = 0; j < i; ++j) {
                HeightMap.Type heightmap_type = aheightmap_type[j];

                hashmap.put(heightmap_type.g, heightmap_type);
            }

        });

        private Type(String s, HeightMap.Use heightmap_use, Predicate predicate) {
            this.g = s;
            this.h = heightmap_use;
            this.i = predicate;
        }

        public String a() {
            return this.g;
        }

        public boolean b() {
            return this.h == HeightMap.Use.CLIENT;
        }

        public static HeightMap.Type a(String s) {
            return (HeightMap.Type) HeightMap.Type.j.get(s);
        }

        public Predicate<IBlockData> d() {
            return this.i;
        }
    }

    public static enum Use {

        WORLDGEN, LIVE_WORLD, CLIENT;

        private Use() {}
    }
}
