package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalInteract extends PathfinderGoalLookAtPlayer {

    public PathfinderGoalInteract(EntityInsentient entityinsentient, Class<? extends EntityLiving> oclass, float f, float f1) {
        super(entityinsentient, oclass, f, f1);
        this.a(EnumSet.of(PathfinderGoal.Type.LOOK, PathfinderGoal.Type.MOVE));
    }
}
