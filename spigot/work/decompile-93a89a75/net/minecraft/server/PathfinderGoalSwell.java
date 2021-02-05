package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalSwell extends PathfinderGoal {

    private final EntityCreeper a;
    private EntityLiving b;

    public PathfinderGoalSwell(EntityCreeper entitycreeper) {
        this.a = entitycreeper;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        return this.a.dV() > 0 || entityliving != null && this.a.h((Entity) entityliving) < 9.0D;
    }

    @Override
    public void c() {
        this.a.getNavigation().o();
        this.b = this.a.getGoalTarget();
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (this.b == null) {
            this.a.a(-1);
        } else if (this.a.h((Entity) this.b) > 49.0D) {
            this.a.a(-1);
        } else if (!this.a.getEntitySenses().a(this.b)) {
            this.a.a(-1);
        } else {
            this.a.a(1);
        }
    }
}
