package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SensorGolemLastSeen extends Sensor<EntityLiving> {

    public SensorGolemLastSeen() {
        this(200);
    }

    public SensorGolemLastSeen(int i) {
        super(i);
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {
        a(worldserver.getTime(), entityliving);
    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.MOBS);
    }

    public static void a(long i, EntityLiving entityliving) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Optional<List<EntityLiving>> optional = behaviorcontroller.getMemory(MemoryModuleType.MOBS);

        if (optional.isPresent()) {
            boolean flag = ((List) optional.get()).stream().anyMatch((entityliving1) -> {
                return entityliving1.getEntityType().equals(EntityTypes.IRON_GOLEM);
            });

            if (flag) {
                behaviorcontroller.setMemory(MemoryModuleType.GOLEM_LAST_SEEN_TIME, (Object) i);
            }

        }
    }
}
