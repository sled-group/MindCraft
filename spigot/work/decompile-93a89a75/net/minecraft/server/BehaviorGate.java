package net.minecraft.server;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BehaviorGate<E extends EntityLiving> extends Behavior<E> {

    private final Set<MemoryModuleType<?>> a;
    private final BehaviorGate.Order b;
    private final BehaviorGate.Execution c;
    private final WeightedList<Behavior<? super E>> d = new WeightedList<>();

    public BehaviorGate(Map<MemoryModuleType<?>, MemoryStatus> map, Set<MemoryModuleType<?>> set, BehaviorGate.Order behaviorgate_order, BehaviorGate.Execution behaviorgate_execution, List<Pair<Behavior<? super E>, Integer>> list) {
        super(map);
        this.a = set;
        this.b = behaviorgate_order;
        this.c = behaviorgate_execution;
        list.forEach((pair) -> {
            this.d.a(pair.getFirst(), (Integer) pair.getSecond());
        });
    }

    @Override
    protected boolean g(WorldServer worldserver, E e0, long i) {
        return this.d.b().filter((behavior) -> {
            return behavior.a() == Behavior.Status.RUNNING;
        }).anyMatch((behavior) -> {
            return behavior.g(worldserver, e0, i);
        });
    }

    @Override
    protected boolean a(long i) {
        return false;
    }

    @Override
    protected void a(WorldServer worldserver, E e0, long i) {
        this.b.a(this.d);
        this.c.a(this.d, worldserver, e0, i);
    }

    @Override
    protected void d(WorldServer worldserver, E e0, long i) {
        this.d.b().filter((behavior) -> {
            return behavior.a() == Behavior.Status.RUNNING;
        }).forEach((behavior) -> {
            behavior.c(worldserver, e0, i);
        });
    }

    @Override
    protected void f(WorldServer worldserver, E e0, long i) {
        this.d.b().filter((behavior) -> {
            return behavior.a() == Behavior.Status.RUNNING;
        }).forEach((behavior) -> {
            behavior.e(worldserver, e0, i);
        });
        Set set = this.a;
        BehaviorController behaviorcontroller = e0.getBehaviorController();

        set.forEach(behaviorcontroller::removeMemory);
    }

    @Override
    public String toString() {
        Set<? extends Behavior<? super E>> set = (Set) this.d.b().filter((behavior) -> {
            return behavior.a() == Behavior.Status.RUNNING;
        }).collect(Collectors.toSet());

        return "(" + this.getClass().getSimpleName() + "): " + set;
    }

    static enum Execution {

        RUN_ONE {
            @Override
            public <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> weightedlist, WorldServer worldserver, E e0, long i) {
                weightedlist.b().filter((behavior) -> {
                    return behavior.a() == Behavior.Status.STOPPED;
                }).filter((behavior) -> {
                    return behavior.b(worldserver, e0, i);
                }).findFirst();
            }
        },
        TRY_ALL {
            @Override
            public <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> weightedlist, WorldServer worldserver, E e0, long i) {
                weightedlist.b().filter((behavior) -> {
                    return behavior.a() == Behavior.Status.STOPPED;
                }).forEach((behavior) -> {
                    behavior.b(worldserver, e0, i);
                });
            }
        };

        private Execution() {}

        public abstract <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> weightedlist, WorldServer worldserver, E e0, long i);
    }

    static enum Order {

        ORDERED((weightedlist) -> {
        }), SHUFFLED(WeightedList::a);

        private final Consumer<WeightedList<?>> c;

        private Order(Consumer consumer) {
            this.c = consumer;
        }

        public void a(WeightedList<?> weightedlist) {
            this.c.accept(weightedlist);
        }
    }
}
