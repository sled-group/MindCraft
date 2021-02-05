package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorCooldown extends Behavior<EntityVillager> {

    public BehaviorCooldown() {
        super(ImmutableMap.of());
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        boolean flag = BehaviorPanic.b(entityvillager) || BehaviorPanic.a(entityvillager) || a(entityvillager);

        if (!flag) {
            entityvillager.getBehaviorController().removeMemory(MemoryModuleType.HURT_BY);
            entityvillager.getBehaviorController().removeMemory(MemoryModuleType.HURT_BY_ENTITY);
            entityvillager.getBehaviorController().a(worldserver.getDayTime(), worldserver.getTime());
        }

    }

    private static boolean a(EntityVillager entityvillager) {
        return entityvillager.getBehaviorController().getMemory(MemoryModuleType.HURT_BY_ENTITY).filter((entityliving) -> {
            return entityliving.h((Entity) entityvillager) <= 36.0D;
        }).isPresent();
    }
}
