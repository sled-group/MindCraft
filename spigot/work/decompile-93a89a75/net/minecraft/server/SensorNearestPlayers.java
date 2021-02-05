package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SensorNearestPlayers extends Sensor<EntityLiving> {

    public SensorNearestPlayers() {}

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {
        Stream stream = worldserver.getPlayers().stream().filter(IEntitySelector.f).filter((entityplayer) -> {
            return entityliving.h((Entity) entityplayer) < 256.0D;
        });

        entityliving.getClass();
        List<EntityHuman> list = (List) stream.sorted(Comparator.comparingDouble(entityliving::h)).collect(Collectors.toList());
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        behaviorcontroller.setMemory(MemoryModuleType.NEAREST_PLAYERS, (Object) list);
        MemoryModuleType memorymoduletype = MemoryModuleType.NEAREST_VISIBLE_PLAYER;
        Stream stream1 = list.stream();

        entityliving.getClass();
        behaviorcontroller.setMemory(memorymoduletype, stream1.filter(entityliving::hasLineOfSight).findFirst());
    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER);
    }
}
