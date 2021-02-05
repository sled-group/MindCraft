package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorRaid extends Behavior<EntityLiving> {

    public BehaviorRaid() {
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

        if (raid != null) {
            if (raid.c() && !raid.b()) {
                behaviorcontroller.b(Activity.RAID);
                behaviorcontroller.a(Activity.RAID);
            } else {
                behaviorcontroller.b(Activity.PRE_RAID);
                behaviorcontroller.a(Activity.PRE_RAID);
            }
        }

    }
}
