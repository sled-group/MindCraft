package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalLeapAtTarget extends PathfinderGoal {

    private final EntityInsentient a;
    private EntityLiving b;
    private final float c;

    public PathfinderGoalLeapAtTarget(EntityInsentient entityinsentient, float f) {
        this.a = entityinsentient;
        this.c = f;
        this.a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        if (this.a.isVehicle()) {
            return false;
        } else {
            this.b = this.a.getGoalTarget();
            if (this.b == null) {
                return false;
            } else {
                double d0 = this.a.h((Entity) this.b);

                return d0 >= 4.0D && d0 <= 16.0D ? (!this.a.onGround ? false : this.a.getRandom().nextInt(5) == 0) : false;
            }
        }
    }

    @Override
    public boolean b() {
        return !this.a.onGround;
    }

    @Override
    public void c() {
        Vec3D vec3d = this.a.getMot();
        Vec3D vec3d1 = new Vec3D(this.b.locX - this.a.locX, 0.0D, this.b.locZ - this.a.locZ);

        if (vec3d1.g() > 1.0E-7D) {
            vec3d1 = vec3d1.d().a(0.4D).e(vec3d.a(0.2D));
        }

        this.a.setMot(vec3d1.x, (double) this.c, vec3d1.z);
    }
}
