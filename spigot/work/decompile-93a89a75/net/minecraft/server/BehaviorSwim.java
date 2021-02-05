package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorSwim extends Behavior<EntityInsentient> {

    private final float a;
    private final float b;

    public BehaviorSwim(float f, float f1) {
        super(ImmutableMap.of());
        this.a = f;
        this.b = f1;
    }

    protected boolean a(WorldServer worldserver, EntityInsentient entityinsentient) {
        return entityinsentient.isInWater() && entityinsentient.cf() > (double) this.a || entityinsentient.aD();
    }

    protected boolean g(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        return this.a(worldserver, entityinsentient);
    }

    protected void d(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        if (entityinsentient.getRandom().nextFloat() < this.b) {
            entityinsentient.getControllerJump().jump();
        }

    }
}
