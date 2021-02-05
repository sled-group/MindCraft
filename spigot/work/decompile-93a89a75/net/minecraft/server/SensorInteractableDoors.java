package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;

public class SensorInteractableDoors extends Sensor<EntityLiving> {

    public SensorInteractableDoors() {}

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {
        DimensionManager dimensionmanager = worldserver.getWorldProvider().getDimensionManager();
        BlockPosition blockposition = new BlockPosition(entityliving);
        List<GlobalPos> list = Lists.newArrayList();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    BlockPosition blockposition1 = blockposition.b(i, j, k);

                    if (worldserver.getType(blockposition1).a(TagsBlock.WOODEN_DOORS)) {
                        list.add(GlobalPos.create(dimensionmanager, blockposition1));
                    }
                }
            }
        }

        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        if (!list.isEmpty()) {
            behaviorcontroller.setMemory(MemoryModuleType.INTERACTABLE_DOORS, (Object) list);
        } else {
            behaviorcontroller.removeMemory(MemoryModuleType.INTERACTABLE_DOORS);
        }

    }

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of(MemoryModuleType.INTERACTABLE_DOORS);
    }
}
