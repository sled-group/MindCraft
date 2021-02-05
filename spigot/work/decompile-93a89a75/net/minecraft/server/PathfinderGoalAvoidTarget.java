package net.minecraft.server;

import java.util.EnumSet;
import java.util.function.Predicate;

public class PathfinderGoalAvoidTarget<T extends EntityLiving> extends PathfinderGoal {

    protected final EntityCreature a;
    private final double i;
    private final double j;
    protected T b;
    protected final float c;
    protected PathEntity d;
    protected final NavigationAbstract e;
    protected final Class<T> f;
    protected final Predicate<EntityLiving> g;
    protected final Predicate<EntityLiving> h;
    private final PathfinderTargetCondition k;

    public PathfinderGoalAvoidTarget(EntityCreature entitycreature, Class<T> oclass, float f, double d0, double d1) {
        Predicate predicate = (entityliving) -> {
            return true;
        };
        Predicate predicate1 = IEntitySelector.e;

        this(entitycreature, oclass, predicate, f, d0, d1, predicate1::test);
    }

    public PathfinderGoalAvoidTarget(EntityCreature entitycreature, Class<T> oclass, Predicate<EntityLiving> predicate, float f, double d0, double d1, Predicate<EntityLiving> predicate1) {
        this.a = entitycreature;
        this.f = oclass;
        this.g = predicate;
        this.c = f;
        this.i = d0;
        this.j = d1;
        this.h = predicate1;
        this.e = entitycreature.getNavigation();
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
        this.k = (new PathfinderTargetCondition()).a((double) f).a(predicate1.and(predicate));
    }

    public PathfinderGoalAvoidTarget(EntityCreature entitycreature, Class<T> oclass, float f, double d0, double d1, Predicate<EntityLiving> predicate) {
        this(entitycreature, oclass, (entityliving) -> {
            return true;
        }, f, d0, d1, predicate);
    }

    @Override
    public boolean a() {
        this.b = this.a.world.b(this.f, this.k, this.a, this.a.locX, this.a.locY, this.a.locZ, this.a.getBoundingBox().grow((double) this.c, 3.0D, (double) this.c));
        if (this.b == null) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.c(this.a, 16, 7, new Vec3D(this.b.locX, this.b.locY, this.b.locZ));

            if (vec3d == null) {
                return false;
            } else if (this.b.e(vec3d.x, vec3d.y, vec3d.z) < this.b.h((Entity) this.a)) {
                return false;
            } else {
                this.d = this.e.a(vec3d.x, vec3d.y, vec3d.z, 0);
                return this.d != null;
            }
        }
    }

    @Override
    public boolean b() {
        return !this.e.n();
    }

    @Override
    public void c() {
        this.e.a(this.d, this.i);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (this.a.h((Entity) this.b) < 49.0D) {
            this.a.getNavigation().a(this.j);
        } else {
            this.a.getNavigation().a(this.i);
        }

    }
}
