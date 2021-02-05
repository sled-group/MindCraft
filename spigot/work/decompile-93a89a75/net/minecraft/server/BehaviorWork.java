package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Objects;

public class BehaviorWork extends Behavior<EntityVillager> {

    private long a;

    public BehaviorWork() {
        super(ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED));
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        if (worldserver.getTime() - this.a < 300L) {
            return false;
        } else if (worldserver.random.nextInt(2) != 0) {
            return false;
        } else {
            this.a = worldserver.getTime();
            GlobalPos globalpos = (GlobalPos) entityvillager.getBehaviorController().getMemory(MemoryModuleType.JOB_SITE).get();

            return Objects.equals(globalpos.getDimensionManager(), worldserver.getWorldProvider().getDimensionManager()) && globalpos.getBlockPosition().a((IPosition) entityvillager.getPositionVector(), 1.73D);
        }
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        BehaviorController<EntityVillager> behaviorcontroller = entityvillager.getBehaviorController();

        behaviorcontroller.setMemory(MemoryModuleType.LAST_WORKED_AT_POI, (Object) MinecraftSerializableLong.a(i));
        behaviorcontroller.getMemory(MemoryModuleType.JOB_SITE).ifPresent((globalpos) -> {
            behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorTarget(globalpos.getBlockPosition())));
        });
        entityvillager.el();
        if (entityvillager.ek()) {
            entityvillager.ej();
        }

    }
}
