package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.function.Predicate;

public class BehaviorLookInteract extends Behavior<EntityLiving> {

    private final EntityTypes<?> a;
    private final int b;
    private final Predicate<EntityLiving> c;
    private final Predicate<EntityLiving> d;

    public BehaviorLookInteract(EntityTypes<?> entitytypes, int i, Predicate<EntityLiving> predicate, Predicate<EntityLiving> predicate1) {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
        this.a = entitytypes;
        this.b = i * i;
        this.c = predicate1;
        this.d = predicate;
    }

    public BehaviorLookInteract(EntityTypes<?> entitytypes, int i) {
        this(entitytypes, i, (entityliving) -> {
            return true;
        }, (entityliving) -> {
            return true;
        });
    }

    @Override
    public boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return this.d.test(entityliving) && this.b(entityliving).stream().anyMatch(this::a);
    }

    @Override
    public void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        super.a(worldserver, entityliving, i);
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        behaviorcontroller.getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent((list) -> {
            list.stream().filter((entityliving1) -> {
                return entityliving1.h((Entity) entityliving) <= (double) this.b;
            }).filter(this::a).findFirst().ifPresent((entityliving1) -> {
                behaviorcontroller.setMemory(MemoryModuleType.INTERACTION_TARGET, (Object) entityliving1);
                behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorPositionEntity(entityliving1)));
            });
        });
    }

    private boolean a(EntityLiving entityliving) {
        return this.a.equals(entityliving.getEntityType()) && this.c.test(entityliving);
    }

    private List<EntityLiving> b(EntityLiving entityliving) {
        return (List) entityliving.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_MOBS).get();
    }
}
