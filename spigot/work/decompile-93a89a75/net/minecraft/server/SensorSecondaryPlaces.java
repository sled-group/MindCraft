package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;

public class SensorSecondaryPlaces extends Sensor<EntityVillager> {

    public SensorSecondaryPlaces() {
        super(40);
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager) {
        DimensionManager dimensionmanager = worldserver.getWorldProvider().getDimensionManager();
        BlockPosition blockposition = new BlockPosition(entityvillager);
        List<GlobalPos> list = Lists.newArrayList();
        boolean flag = true;

        for (int i = -4; i <= 4; ++i) {
            for (int j = -2; j <= 2; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    BlockPosition blockposition1 = blockposition.b(i, j, k);

                    if (entityvillager.getVillagerData().getProfession().d().contains(worldserver.getType(blockposition1).getBlock())) {
                        list.add(GlobalPos.create(dimensionmanager, blockposition1));
                    }
                }
            }
        }

        BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

        if (!list.isEmpty()) {
            behaviorcontroller.setMemory(MemoryModuleType.SECONDARY_JOB_SITE, (Object) list);
        } else {
            behaviorcontroller.removeMemory(MemoryModuleType.SECONDARY_JOB_SITE);
        }

    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.SECONDARY_JOB_SITE);
    }
}
