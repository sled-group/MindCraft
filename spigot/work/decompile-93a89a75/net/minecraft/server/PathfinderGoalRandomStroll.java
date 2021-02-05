package net.minecraft.server;

import java.util.EnumSet;
import javax.annotation.Nullable;

public class PathfinderGoalRandomStroll extends PathfinderGoal {

    protected final EntityCreature a;
    protected double b;
    protected double c;
    protected double d;
    protected final double e;
    protected int f;
    protected boolean g;

    public PathfinderGoalRandomStroll(EntityCreature entitycreature, double d0) {
        this(entitycreature, d0, 120);
    }

    public PathfinderGoalRandomStroll(EntityCreature entitycreature, double d0, int i) {
        this.a = entitycreature;
        this.e = d0;
        this.f = i;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        if (this.a.isVehicle()) {
            return false;
        } else {
            if (!this.g) {
                if (this.a.cw() >= 100) {
                    return false;
                }

                if (this.a.getRandom().nextInt(this.f) != 0) {
                    return false;
                }
            }

            Vec3D vec3d = this.g();

            if (vec3d == null) {
                return false;
            } else {
                this.b = vec3d.x;
                this.c = vec3d.y;
                this.d = vec3d.z;
                this.g = false;
                return true;
            }
        }
    }

    @Nullable
    protected Vec3D g() {
        return RandomPositionGenerator.a(this.a, 10, 7);
    }

    @Override
    public boolean b() {
        return !this.a.getNavigation().n();
    }

    @Override
    public void c() {
        this.a.getNavigation().a(this.b, this.c, this.d, this.e);
    }

    public void h() {
        this.g = true;
    }

    public void setTimeBetweenMovement(int i) {
        this.f = i;
    }
}
