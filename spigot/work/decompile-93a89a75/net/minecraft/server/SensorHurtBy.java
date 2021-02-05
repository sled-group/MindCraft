package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class SensorHurtBy extends Sensor<EntityLiving> {

    public SensorHurtBy() {}

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        if (entityliving.cE() != null) {
            behaviorcontroller.setMemory(MemoryModuleType.HURT_BY, (Object) entityliving.cE());
            Entity entity = ((DamageSource) behaviorcontroller.getMemory(MemoryModuleType.HURT_BY).get()).getEntity();

            if (entity instanceof EntityLiving) {
                behaviorcontroller.setMemory(MemoryModuleType.HURT_BY_ENTITY, (Object) ((EntityLiving) entity));
            }
        } else {
            behaviorcontroller.removeMemory(MemoryModuleType.HURT_BY);
        }

    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY);
    }
}
