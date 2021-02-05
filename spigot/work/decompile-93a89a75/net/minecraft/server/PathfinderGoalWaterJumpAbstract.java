package net.minecraft.server;

import java.util.EnumSet;

public abstract class PathfinderGoalWaterJumpAbstract extends PathfinderGoal {

    public PathfinderGoalWaterJumpAbstract() {
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.JUMP));
    }

    protected float a(float f, float f1, float f2) {
        float f3;

        for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F) {
            ;
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        return f + f2 * f3;
    }
}
