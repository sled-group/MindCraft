package net.minecraft.server;

public class BehaviorHomeRaid extends BehaviorHome {

    public BehaviorHomeRaid(int i, float f) {
        super(i, f, 1);
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        Raid raid = worldserver.c_(new BlockPosition(entityliving));

        return super.a(worldserver, entityliving) && raid != null && raid.v() && !raid.e() && !raid.f();
    }
}
