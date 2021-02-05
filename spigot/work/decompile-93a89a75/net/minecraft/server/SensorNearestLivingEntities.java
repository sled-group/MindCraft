package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SensorNearestLivingEntities extends Sensor<EntityLiving> {

    private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).a(16.0D).b().d().c();

    public SensorNearestLivingEntities() {}

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {
        List<EntityLiving> list = worldserver.a(EntityLiving.class, entityliving.getBoundingBox().grow(16.0D, 16.0D, 16.0D), (entityliving1) -> {
            return entityliving1 != entityliving && entityliving1.isAlive();
        });

        entityliving.getClass();
        list.sort(Comparator.comparingDouble(entityliving::h));
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        behaviorcontroller.setMemory(MemoryModuleType.MOBS, (Object) list);
        MemoryModuleType memorymoduletype = MemoryModuleType.VISIBLE_MOBS;
        Stream stream = list.stream().filter((entityliving1) -> {
            return SensorNearestLivingEntities.a.a(entityliving, entityliving1);
        });

        entityliving.getClass();
        behaviorcontroller.setMemory(memorymoduletype, stream.filter(entityliving::hasLineOfSight).collect(Collectors.toList()));
    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS);
    }
}
