package net.minecraft.server;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class PathfinderGoalFollowEntity extends PathfinderGoal {

    private final EntityInsentient a;
    private final Predicate<EntityInsentient> b;
    private EntityInsentient c;
    private final double d;
    private final NavigationAbstract e;
    private int f;
    private final float g;
    private float h;
    private final float i;

    public PathfinderGoalFollowEntity(EntityInsentient entityinsentient, double d0, float f, float f1) {
        this.a = entityinsentient;
        this.b = (entityinsentient1) -> {
            return entityinsentient1 != null && entityinsentient.getClass() != entityinsentient1.getClass();
        };
        this.d = d0;
        this.e = entityinsentient.getNavigation();
        this.g = f;
        this.i = f1;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
        if (!(entityinsentient.getNavigation() instanceof Navigation) && !(entityinsentient.getNavigation() instanceof NavigationFlying)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
        }
    }

    @Override
    public boolean a() {
        List<EntityInsentient> list = this.a.world.a(EntityInsentient.class, this.a.getBoundingBox().g((double) this.i), this.b);

        if (!list.isEmpty()) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityInsentient entityinsentient = (EntityInsentient) iterator.next();

                if (!entityinsentient.isInvisible()) {
                    this.c = entityinsentient;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean b() {
        return this.c != null && !this.e.n() && this.a.h((Entity) this.c) > (double) (this.g * this.g);
    }

    @Override
    public void c() {
        this.f = 0;
        this.h = this.a.a(PathType.WATER);
        this.a.a(PathType.WATER, 0.0F);
    }

    @Override
    public void d() {
        this.c = null;
        this.e.o();
        this.a.a(PathType.WATER, this.h);
    }

    @Override
    public void e() {
        if (this.c != null && !this.a.isLeashed()) {
            this.a.getControllerLook().a(this.c, 10.0F, (float) this.a.M());
            if (--this.f <= 0) {
                this.f = 10;
                double d0 = this.a.locX - this.c.locX;
                double d1 = this.a.locY - this.c.locY;
                double d2 = this.a.locZ - this.c.locZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > (double) (this.g * this.g)) {
                    this.e.a((Entity) this.c, this.d);
                } else {
                    this.e.o();
                    ControllerLook controllerlook = this.c.getControllerLook();

                    if (d3 <= (double) this.g || controllerlook.d() == this.a.locX && controllerlook.e() == this.a.locY && controllerlook.f() == this.a.locZ) {
                        double d4 = this.c.locX - this.a.locX;
                        double d5 = this.c.locZ - this.a.locZ;

                        this.e.a(this.a.locX - d4, this.a.locY, this.a.locZ - d5, this.d);
                    }

                }
            }
        }
    }
}
