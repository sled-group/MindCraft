package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.util.Pair;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class BehaviorController<E extends EntityLiving> implements MinecraftSerializable {

    private final Map<MemoryModuleType<?>, Optional<?>> memories = Maps.newHashMap();
    private final Map<SensorType<? extends Sensor<? super E>>, Sensor<? super E>> sensors = Maps.newLinkedHashMap();
    private final Map<Integer, Map<Activity, Set<Behavior<? super E>>>> c = Maps.newTreeMap();
    private Schedule schedule;
    private final Map<Activity, Set<Pair<MemoryModuleType<?>, MemoryStatus>>> e;
    private Set<Activity> f;
    private final Set<Activity> g;
    private Activity h;
    private long i;

    public <T> BehaviorController(Collection<MemoryModuleType<?>> collection, Collection<SensorType<? extends Sensor<? super E>>> collection1, Dynamic<T> dynamic) {
        this.schedule = Schedule.EMPTY;
        this.e = Maps.newHashMap();
        this.f = Sets.newHashSet();
        this.g = Sets.newHashSet();
        this.h = Activity.IDLE;
        this.i = -9999L;
        collection.forEach((memorymoduletype) -> {
            Optional optional = (Optional) this.memories.put(memorymoduletype, Optional.empty());
        });
        collection1.forEach((sensortype) -> {
            Sensor sensor = (Sensor) this.sensors.put(sensortype, sensortype.a());
        });
        this.sensors.values().forEach((sensor) -> {
            Iterator iterator = sensor.a().iterator();

            while (iterator.hasNext()) {
                MemoryModuleType<?> memorymoduletype = (MemoryModuleType) iterator.next();

                this.memories.put(memorymoduletype, Optional.empty());
            }

        });
        Iterator iterator = dynamic.get("memories").asMap(Function.identity(), Function.identity()).entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Dynamic<T>, Dynamic<T>> entry = (Entry) iterator.next();

            this.a((MemoryModuleType) IRegistry.MEMORY_MODULE_TYPE.get(new MinecraftKey(((Dynamic) entry.getKey()).asString(""))), (Dynamic) entry.getValue());
        }

    }

    public boolean hasMemory(MemoryModuleType<?> memorymoduletype) {
        return this.a(memorymoduletype, MemoryStatus.VALUE_PRESENT);
    }

    private <T, U> void a(MemoryModuleType<U> memorymoduletype, Dynamic<T> dynamic) {
        this.setMemory(memorymoduletype, ((Function) memorymoduletype.getSerializer().orElseThrow(RuntimeException::new)).apply(dynamic));
    }

    public <U> void removeMemory(MemoryModuleType<U> memorymoduletype) {
        this.setMemory(memorymoduletype, Optional.empty());
    }

    public <U> void setMemory(MemoryModuleType<U> memorymoduletype, @Nullable U u0) {
        this.setMemory(memorymoduletype, Optional.ofNullable(u0));
    }

    public <U> void setMemory(MemoryModuleType<U> memorymoduletype, Optional<U> optional) {
        if (this.memories.containsKey(memorymoduletype)) {
            if (optional.isPresent() && this.a(optional.get())) {
                this.removeMemory(memorymoduletype);
            } else {
                this.memories.put(memorymoduletype, optional);
            }
        }

    }

    public <U> Optional<U> getMemory(MemoryModuleType<U> memorymoduletype) {
        return (Optional) this.memories.get(memorymoduletype);
    }

    public boolean a(MemoryModuleType<?> memorymoduletype, MemoryStatus memorystatus) {
        Optional<?> optional = (Optional) this.memories.get(memorymoduletype);

        return optional == null ? false : memorystatus == MemoryStatus.REGISTERED || memorystatus == MemoryStatus.VALUE_PRESENT && optional.isPresent() || memorystatus == MemoryStatus.VALUE_ABSENT && !optional.isPresent();
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void a(Set<Activity> set) {
        this.f = set;
    }

    @Deprecated
    public Stream<Behavior<? super E>> d() {
        return this.c.values().stream().flatMap((map) -> {
            return map.values().stream();
        }).flatMap(Collection::stream).filter((behavior) -> {
            return behavior.a() == Behavior.Status.RUNNING;
        });
    }

    public void a(Activity activity) {
        this.g.clear();
        this.g.addAll(this.f);
        boolean flag = this.e.keySet().contains(activity) && this.d(activity);

        this.g.add(flag ? activity : this.h);
    }

    public void a(long i, long j) {
        if (j - this.i > 20L) {
            this.i = j;
            Activity activity = this.getSchedule().a((int) (i % 24000L));

            if (!this.g.contains(activity)) {
                this.a(activity);
            }
        }

    }

    public void b(Activity activity) {
        this.h = activity;
    }

    public void a(Activity activity, ImmutableList<Pair<Integer, ? extends Behavior<? super E>>> immutablelist) {
        this.a(activity, immutablelist, (Set) ImmutableSet.of());
    }

    public void a(Activity activity, ImmutableList<Pair<Integer, ? extends Behavior<? super E>>> immutablelist, Set<Pair<MemoryModuleType<?>, MemoryStatus>> set) {
        this.e.put(activity, set);
        immutablelist.forEach((pair) -> {
            ((Set) ((Map) this.c.computeIfAbsent(pair.getFirst(), (integer) -> {
                return Maps.newHashMap();
            })).computeIfAbsent(activity, (activity1) -> {
                return Sets.newLinkedHashSet();
            })).add(pair.getSecond());
        });
    }

    public boolean c(Activity activity) {
        return this.g.contains(activity);
    }

    public BehaviorController<E> f() {
        BehaviorController<E> behaviorcontroller = new BehaviorController<>(this.memories.keySet(), this.sensors.keySet(), new Dynamic(DynamicOpsNBT.a, new NBTTagCompound()));

        this.memories.forEach((memorymoduletype, optional) -> {
            optional.ifPresent((object) -> {
                Optional optional1 = (Optional) behaviorcontroller.memories.put(memorymoduletype, Optional.of(object));
            });
        });
        return behaviorcontroller;
    }

    public void a(WorldServer worldserver, E e0) {
        this.c(worldserver, e0);
        this.d(worldserver, e0);
        this.e(worldserver, e0);
    }

    public void b(WorldServer worldserver, E e0) {
        long i = e0.world.getTime();

        this.d().forEach((behavior) -> {
            behavior.e(worldserver, e0, i);
        });
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        T t0 = dynamicops.createMap((Map) this.memories.entrySet().stream().filter((entry) -> {
            return ((MemoryModuleType) entry.getKey()).getSerializer().isPresent() && ((Optional) entry.getValue()).isPresent();
        }).map((entry) -> {
            return Pair.of(dynamicops.createString(IRegistry.MEMORY_MODULE_TYPE.getKey(entry.getKey()).toString()), ((MinecraftSerializable) ((Optional) entry.getValue()).get()).a(dynamicops));
        }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));

        return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("memories"), t0));
    }

    private void c(WorldServer worldserver, E e0) {
        this.sensors.values().forEach((sensor) -> {
            sensor.b(worldserver, e0);
        });
    }

    private void d(WorldServer worldserver, E e0) {
        long i = worldserver.getTime();

        this.c.values().stream().flatMap((map) -> {
            return map.entrySet().stream();
        }).filter((entry) -> {
            return this.g.contains(entry.getKey());
        }).map(Entry::getValue).flatMap(Collection::stream).filter((behavior) -> {
            return behavior.a() == Behavior.Status.STOPPED;
        }).forEach((behavior) -> {
            behavior.b(worldserver, e0, i);
        });
    }

    private void e(WorldServer worldserver, E e0) {
        long i = worldserver.getTime();

        this.d().forEach((behavior) -> {
            behavior.c(worldserver, e0, i);
        });
    }

    private boolean d(Activity activity) {
        return ((Set) this.e.get(activity)).stream().allMatch((pair) -> {
            MemoryModuleType<?> memorymoduletype = (MemoryModuleType) pair.getFirst();
            MemoryStatus memorystatus = (MemoryStatus) pair.getSecond();

            return this.a(memorymoduletype, memorystatus);
        });
    }

    private boolean a(Object object) {
        return object instanceof Collection && ((Collection) object).isEmpty();
    }
}
