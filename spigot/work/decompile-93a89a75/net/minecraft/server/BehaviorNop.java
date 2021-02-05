package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorNop extends Behavior<EntityLiving> {

    public BehaviorNop(int i, int j) {
        super(ImmutableMap.of(), i, j);
    }

    @Override
    protected boolean g(WorldServer worldserver, EntityLiving entityliving, long i) {
        return true;
    }
}
