package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorRaidReset extends Behavior<EntityLiving> {

    public BehaviorRaidReset() {
        super(ImmutableMap.of());
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return worldserver.random.nextInt(20) == 0;
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Raid raid = worldserver.c_(new BlockPosition(entityliving));

        if (raid == null || raid.d() || raid.f()) {
            behaviorcontroller.b(Activity.IDLE);
            behaviorcontroller.a(worldserver.getDayTime(), worldserver.getTime());
        }

    }
}
