package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class SensorDummy extends Sensor<EntityLiving> {

    public SensorDummy() {}

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving) {}

    @Override
    public Set<MemoryModuleType<?>> a() {
        return ImmutableSet.of();
    }
}
