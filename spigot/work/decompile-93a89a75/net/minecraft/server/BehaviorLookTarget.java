package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.function.Predicate;

public class BehaviorLookTarget extends Behavior<EntityLiving> {

    private final Predicate<EntityLiving> a;
    private final float b;

    public BehaviorLookTarget(EnumCreatureType enumcreaturetype, float f) {
        this((entityliving) -> {
            return enumcreaturetype.equals(entityliving.getEntityType().e());
        }, f);
    }

    public BehaviorLookTarget(EntityTypes<?> entitytypes, float f) {
        this((entityliving) -> {
            return entitytypes.equals(entityliving.getEntityType());
        }, f);
    }

    public BehaviorLookTarget(Predicate<EntityLiving> predicate, float f) {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
        this.a = predicate;
        this.b = f * f;
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return ((List) entityliving.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_MOBS).get()).stream().anyMatch(this.a);
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        behaviorcontroller.getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent((list) -> {
            list.stream().filter(this.a).filter((entityliving1) -> {
                return entityliving1.h((Entity) entityliving) <= (double) this.b;
            }).findFirst().ifPresent((entityliving1) -> {
                behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorPositionEntity(entityliving1)));
            });
        });
    }
}
