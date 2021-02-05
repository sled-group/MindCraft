package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorBellAlert extends Behavior<EntityLiving> {

    public BehaviorBellAlert() {
        super(ImmutableMap.of(MemoryModuleType.HEARD_BELL_TIME, MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Raid raid = worldserver.c_(new BlockPosition(entityliving));

        if (raid == null) {
            behaviorcontroller.a(Activity.HIDE);
        }

    }
}
