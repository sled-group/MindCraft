package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalOwnerHurtTarget extends PathfinderGoalTarget {

    private final EntityTameableAnimal a;
    private EntityLiving b;
    private int c;

    public PathfinderGoalOwnerHurtTarget(EntityTameableAnimal entitytameableanimal) {
        super(entitytameableanimal, false);
        this.a = entitytameableanimal;
        this.a(EnumSet.of(PathfinderGoal.Type.TARGET));
    }

    @Override
    public boolean a() {
        if (this.a.isTamed() && !this.a.isSitting()) {
            EntityLiving entityliving = this.a.getOwner();

            if (entityliving == null) {
                return false;
            } else {
                this.b = entityliving.cu();
                int i = entityliving.cv();

                return i != this.c && this.a(this.b, PathfinderTargetCondition.a) && this.a.a(this.b, entityliving);
            }
        } else {
            return false;
        }
    }

    @Override
    public void c() {
        this.e.setGoalTarget(this.b);
        EntityLiving entityliving = this.a.getOwner();

        if (entityliving != null) {
            this.c = entityliving.cv();
        }

        super.c();
    }
}
