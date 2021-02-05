package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;

public class BehaviorWalkAwayBlock extends Behavior<EntityVillager> {

    private final MemoryModuleType<GlobalPos> a;
    private final float b;
    private final int c;
    private final int d;
    private final int e;

    public BehaviorWalkAwayBlock(MemoryModuleType<GlobalPos> memorymoduletype, float f, int i, int j, int k) {
        super(ImmutableMap.of(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, memorymoduletype, MemoryStatus.VALUE_PRESENT));
        this.a = memorymoduletype;
        this.b = f;
        this.c = i;
        this.d = j;
        this.e = k;
    }

    private void a(EntityVillager entityvillager, long i) {
        BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

        entityvillager.a(this.a);
        behaviorcontroller.removeMemory(this.a);
        behaviorcontroller.setMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, (Object) i);
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

        behaviorcontroller.getMemory(this.a).ifPresent((globalpos) -> {
            if (this.a(worldserver, entityvillager)) {
                this.a(entityvillager, i);
            } else if (this.a(worldserver, entityvillager, globalpos)) {
                Vec3D vec3d = null;
                int j = 0;

                for (boolean flag = true; j < 1000 && (vec3d == null || this.a(worldserver, entityvillager, GlobalPos.create(entityvillager.dimension, new BlockPosition(vec3d)))); ++j) {
                    vec3d = RandomPositionGenerator.a(entityvillager, 15, 7, new Vec3D(globalpos.getBlockPosition()));
                }

                if (j == 1000) {
                    this.a(entityvillager, i);
                    return;
                }

                behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(vec3d, this.b, this.c)));
            } else if (!this.b(worldserver, entityvillager, globalpos)) {
                behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(globalpos.getBlockPosition(), this.b, this.c)));
            }

        });
    }

    private boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        Optional<Long> optional = entityvillager.getBehaviorController().getMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);

        return optional.isPresent() ? worldserver.getTime() - (Long) optional.get() > (long) this.e : false;
    }

    private boolean a(WorldServer worldserver, EntityVillager entityvillager, GlobalPos globalpos) {
        return globalpos.getDimensionManager() != worldserver.getWorldProvider().getDimensionManager() || globalpos.getBlockPosition().n(new BlockPosition(entityvillager)) > this.d;
    }

    private boolean b(WorldServer worldserver, EntityVillager entityvillager, GlobalPos globalpos) {
        return globalpos.getDimensionManager() == worldserver.getWorldProvider().getDimensionManager() && globalpos.getBlockPosition().n(new BlockPosition(entityvillager)) <= this.c;
    }
}
