package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorWake extends Behavior<EntityLiving> {

    public BehaviorWake() {
        super(ImmutableMap.of());
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return !entityliving.getBehaviorController().c(Activity.REST) && entityliving.isSleeping();
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        entityliving.dy();
    }
}
