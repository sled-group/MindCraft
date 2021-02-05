package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class VillagePlaceSection implements MinecraftSerializable {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Short2ObjectMap<VillagePlaceRecord> b = new Short2ObjectOpenHashMap();
    private final Map<VillagePlaceType, Set<VillagePlaceRecord>> c = Maps.newHashMap();
    private final Runnable d;
    private boolean e;

    public VillagePlaceSection(Runnable runnable) {
        this.d = runnable;
        this.e = true;
    }

    public <T> VillagePlaceSection(Runnable runnable, Dynamic<T> dynamic) {
        this.d = runnable;

        try {
            this.e = dynamic.get("Valid").asBoolean(false);
            dynamic.get("Records").asStream().forEach((dynamic1) -> {
                this.a(new VillagePlaceRecord(dynamic1, runnable));
            });
        } catch (Exception exception) {
            VillagePlaceSection.LOGGER.error("Failed to load POI chunk", exception);
            this.a();
            this.e = false;
        }

    }

    public Stream<VillagePlaceRecord> a(Predicate<VillagePlaceType> predicate, VillagePlace.Occupancy villageplace_occupancy) {
        return this.c.entrySet().stream().filter((entry) -> {
            return predicate.test(entry.getKey());
        }).flatMap((entry) -> {
            return ((Set) entry.getValue()).stream();
        }).filter(villageplace_occupancy.a());
    }

    public void a(BlockPosition blockposition, VillagePlaceType villageplacetype) {
        if (this.a(new VillagePlaceRecord(blockposition, villageplacetype, this.d))) {
            VillagePlaceSection.LOGGER.debug("Added POI of type {} @ {}", new Supplier[]{() -> {
                        return villageplacetype;
                    }, () -> {
                        return blockposition;
                    }});
            this.d.run();
        }

    }

    private boolean a(VillagePlaceRecord villageplacerecord) {
        BlockPosition blockposition = villageplacerecord.f();
        VillagePlaceType villageplacetype = villageplacerecord.g();
        short short0 = SectionPosition.b(blockposition);
        VillagePlaceRecord villageplacerecord1 = (VillagePlaceRecord) this.b.get(short0);

        if (villageplacerecord1 != null) {
            if (villageplacetype.equals(villageplacerecord1.g())) {
                return false;
            } else {
                throw new IllegalStateException("POI data mismatch: already registered at " + blockposition);
            }
        } else {
            this.b.put(short0, villageplacerecord);
            ((Set) this.c.computeIfAbsent(villageplacetype, (villageplacetype1) -> {
                return Sets.newHashSet();
            })).add(villageplacerecord);
            return true;
        }
    }

    public void a(BlockPosition blockposition) {
        VillagePlaceRecord villageplacerecord = (VillagePlaceRecord) this.b.remove(SectionPosition.b(blockposition));

        if (villageplacerecord == null) {
            VillagePlaceSection.LOGGER.error("POI data mismatch: never registered at " + blockposition);
        } else {
            ((Set) this.c.get(villageplacerecord.g())).remove(villageplacerecord);
            VillagePlaceSection.LOGGER.debug("Removed POI of type {} @ {}", new Supplier[]{villageplacerecord::g, villageplacerecord::f});
            this.d.run();
        }
    }

    public boolean c(BlockPosition blockposition) {
        VillagePlaceRecord villageplacerecord = (VillagePlaceRecord) this.b.get(SectionPosition.b(blockposition));

        if (villageplacerecord == null) {
            throw new IllegalStateException("POI never registered at " + blockposition);
        } else {
            boolean flag = villageplacerecord.c();

            this.d.run();
            return flag;
        }
    }

    public boolean a(BlockPosition blockposition, Predicate<VillagePlaceType> predicate) {
        short short0 = SectionPosition.b(blockposition);
        VillagePlaceRecord villageplacerecord = (VillagePlaceRecord) this.b.get(short0);

        return villageplacerecord != null && predicate.test(villageplacerecord.g());
    }

    public Optional<VillagePlaceType> d(BlockPosition blockposition) {
        short short0 = SectionPosition.b(blockposition);
        VillagePlaceRecord villageplacerecord = (VillagePlaceRecord) this.b.get(short0);

        return villageplacerecord != null ? Optional.of(villageplacerecord.g()) : Optional.empty();
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        T t0 = dynamicops.createList(this.b.values().stream().map((villageplacerecord) -> {
            return villageplacerecord.a(dynamicops);
        }));

        return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("Records"), t0, dynamicops.createString("Valid"), dynamicops.createBoolean(this.e)));
    }

    public void a(Consumer<BiConsumer<BlockPosition, VillagePlaceType>> consumer) {
        if (!this.e) {
            Short2ObjectMap<VillagePlaceRecord> short2objectmap = new Short2ObjectOpenHashMap(this.b);

            this.a();
            consumer.accept((blockposition, villageplacetype) -> {
                short short0 = SectionPosition.b(blockposition);
                VillagePlaceRecord villageplacerecord = (VillagePlaceRecord) short2objectmap.computeIfAbsent(short0, (i) -> {
                    return new VillagePlaceRecord(blockposition, villageplacetype, this.d);
                });

                this.a(villageplacerecord);
            });
            this.e = true;
            this.d.run();
        }

    }

    private void a() {
        this.b.clear();
        this.c.clear();
    }
}
