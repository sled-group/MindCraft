package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalFloat extends PathfinderGoal {

    private final EntityInsentient a;

    public PathfinderGoalFloat(EntityInsentient entityinsentient) {
        this.a = entityinsentient;
        this.a(EnumSet.of(PathfinderGoal.Type.JUMP));
        entityinsentient.getNavigation().d(true);
    }

    @Override
    public boolean a() {
        double d0 = (double) this.a.getHeadHeight() < 0.4D ? 0.2D : 0.4D;

        return this.a.isInWater() && this.a.cf() > d0 || this.a.aD();
    }

    @Override
    public void e() {
        if (this.a.getRandom().nextFloat() < 0.8F) {
            this.a.getControllerJump().jump();
        }

    }
}
