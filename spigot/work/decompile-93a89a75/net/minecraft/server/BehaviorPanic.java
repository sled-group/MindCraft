package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorPanic extends Behavior<EntityVillager> {

    public BehaviorPanic() {
        super(ImmutableMap.of());
    }

    protected boolean g(WorldServer worldserver, EntityVillager entityvillager, long i) {
        return b(entityvillager) || a(entityvillager);
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        if (b(entityvillager) || a(entityvillager)) {
            BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

            if (!behaviorcontroller.c(Activity.PANIC)) {
                behaviorcontroller.removeMemory(MemoryModuleType.PATH);
                behaviorcontroller.removeMemory(MemoryModuleType.WALK_TARGET);
                behaviorcontroller.removeMemory(MemoryModuleType.LOOK_TARGET);
                behaviorcontroller.removeMemory(MemoryModuleType.BREED_TARGET);
                behaviorcontroller.removeMemory(MemoryModuleType.INTERACTION_TARGET);
            }

            behaviorcontroller.a(Activity.PANIC);
        }

    }

    protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
        if (i % 100L == 0L) {
            entityvillager.a(i, 3);
        }

    }

    public static boolean a(EntityLiving entityliving) {
        return entityliving.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_HOSTILE);
    }

    public static boolean b(EntityLiving entityliving) {
        return entityliving.getBehaviorController().hasMemory(MemoryModuleType.HURT_BY);
    }
}
