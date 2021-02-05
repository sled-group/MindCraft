package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OptionalDynamic;
import com.mojang.datafixers.types.DynamicOps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegionFileSection<R extends MinecraftSerializable> extends RegionFileCache {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Long2ObjectMap<Optional<R>> b = new Long2ObjectOpenHashMap();
    private final LongLinkedOpenHashSet d = new LongLinkedOpenHashSet();
    private final BiFunction<Runnable, Dynamic<?>, R> e;
    private final Function<Runnable, R> f;
    private final DataFixer g;
    private final DataFixTypes h;

    public RegionFileSection(File file, BiFunction<Runnable, Dynamic<?>, R> bifunction, Function<Runnable, R> function, DataFixer datafixer, DataFixTypes datafixtypes) {
        super(file);
        this.e = bifunction;
        this.f = function;
        this.g = datafixer;
        this.h = datafixtypes;
    }

    protected void a(BooleanSupplier booleansupplier) {
        while (!this.d.isEmpty() && booleansupplier.getAsBoolean()) {
            ChunkCoordIntPair chunkcoordintpair = SectionPosition.a(this.d.firstLong()).u();

            this.d(chunkcoordintpair);
        }

    }

    @Nullable
    protected Optional<R> c(long i) {
        return (Optional) this.b.get(i);
    }

    protected Optional<R> d(long i) {
        SectionPosition sectionposition = SectionPosition.a(i);

        if (this.b(sectionposition)) {
            return Optional.empty();
        } else {
            Optional<R> optional = this.c(i);

            if (optional != null) {
                return optional;
            } else {
                this.b(sectionposition.u());
                optional = this.c(i);
                if (optional == null) {
                    throw new IllegalStateException();
                } else {
                    return optional;
                }
            }
        }
    }

    protected boolean b(SectionPosition sectionposition) {
        return World.b(SectionPosition.c(sectionposition.b()));
    }

    protected R e(long i) {
        Optional<R> optional = this.d(i);

        if (optional.isPresent()) {
            return (MinecraftSerializable) optional.get();
        } else {
            R r0 = (MinecraftSerializable) this.f.apply(() -> {
                this.a(i);
            });

            this.b.put(i, Optional.of(r0));
            return r0;
        }
    }

    private void b(ChunkCoordIntPair chunkcoordintpair) {
        this.a(chunkcoordintpair, DynamicOpsNBT.a, this.c(chunkcoordintpair));
    }

    @Nullable
    private NBTTagCompound c(ChunkCoordIntPair chunkcoordintpair) {
        try {
            return this.read(chunkcoordintpair);
        } catch (IOException ioexception) {
            RegionFileSection.LOGGER.error("Error reading chunk {} data from disk", chunkcoordintpair, ioexception);
            return null;
        }
    }

    private <T> void a(ChunkCoordIntPair chunkcoordintpair, DynamicOps<T> dynamicops, @Nullable T t0) {
        if (t0 == null) {
            for (int i = 0; i < 16; ++i) {
                this.b.put(SectionPosition.a(chunkcoordintpair, i).v(), Optional.empty());
            }
        } else {
            Dynamic<T> dynamic = new Dynamic(dynamicops, t0);
            int j = a(dynamic);
            int k = SharedConstants.a().getWorldVersion();
            boolean flag = j != k;
            Dynamic<T> dynamic1 = this.g.update(this.h.a(), dynamic, j, k);
            OptionalDynamic<T> optionaldynamic = dynamic1.get("Sections");

            for (int l = 0; l < 16; ++l) {
                long i1 = SectionPosition.a(chunkcoordintpair, l).v();
                Optional<R> optional = optionaldynamic.get(Integer.toString(l)).get().map((dynamic2) -> {
                    return (MinecraftSerializable) this.e.apply(() -> {
                        this.a(i1);
                    }, dynamic2);
                });

                this.b.put(i1, optional);
                optional.ifPresent((minecraftserializable) -> {
                    this.b(i1);
                    if (flag) {
                        this.a(i1);
                    }

                });
            }
        }

    }

    private void d(ChunkCoordIntPair chunkcoordintpair) {
        Dynamic<NBTBase> dynamic = this.a(chunkcoordintpair, DynamicOpsNBT.a);
        NBTBase nbtbase = (NBTBase) dynamic.getValue();

        if (nbtbase instanceof NBTTagCompound) {
            try {
                this.write(chunkcoordintpair, (NBTTagCompound) nbtbase);
            } catch (IOException ioexception) {
                RegionFileSection.LOGGER.error("Error writing data to disk", ioexception);
            }
        } else {
            RegionFileSection.LOGGER.error("Expected compound tag, got {}", nbtbase);
        }

    }

    private <T> Dynamic<T> a(ChunkCoordIntPair chunkcoordintpair, DynamicOps<T> dynamicops) {
        Map<T, T> map = Maps.newHashMap();

        for (int i = 0; i < 16; ++i) {
            long j = SectionPosition.a(chunkcoordintpair, i).v();

            this.d.remove(j);
            Optional<R> optional = (Optional) this.b.get(j);

            if (optional != null && optional.isPresent()) {
                map.put(dynamicops.createString(Integer.toString(i)), ((MinecraftSerializable) optional.get()).a(dynamicops));
            }
        }

        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("Sections"), dynamicops.createMap(map), dynamicops.createString("DataVersion"), dynamicops.createInt(SharedConstants.a().getWorldVersion()))));
    }

    protected void b(long i) {}

    protected void a(long i) {
        Optional<R> optional = (Optional) this.b.get(i);

        if (optional != null && optional.isPresent()) {
            this.d.add(i);
        } else {
            RegionFileSection.LOGGER.warn("No data for position: {}", SectionPosition.a(i));
        }
    }

    private static int a(Dynamic<?> dynamic) {
        return ((Number) dynamic.get("DataVersion").asNumber().orElse(1945)).intValue();
    }

    public void a(ChunkCoordIntPair chunkcoordintpair) {
        if (!this.d.isEmpty()) {
            for (int i = 0; i < 16; ++i) {
                long j = SectionPosition.a(chunkcoordintpair, i).v();

                if (this.d.contains(j)) {
                    this.d(chunkcoordintpair);
                    return;
                }
            }
        }

    }
}
