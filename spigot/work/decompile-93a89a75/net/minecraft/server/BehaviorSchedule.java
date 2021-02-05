package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorSchedule extends Behavior<EntityLiving> {

    public BehaviorSchedule() {
        super(ImmutableMap.of());
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        entityliving.getBehaviorController().a(worldserver.getDayTime(), worldserver.getTime());
    }
}
