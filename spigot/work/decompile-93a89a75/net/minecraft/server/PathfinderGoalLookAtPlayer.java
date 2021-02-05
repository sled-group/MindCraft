package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalLookAtPlayer extends PathfinderGoal {

    protected final EntityInsentient a;
    protected Entity b;
    protected final float c;
    private int f;
    private final float g;
    protected final Class<? extends EntityLiving> d;
    protected final PathfinderTargetCondition e;

    public PathfinderGoalLookAtPlayer(EntityInsentient entityinsentient, Class<? extends EntityLiving> oclass, float f) {
        this(entityinsentient, oclass, f, 0.02F);
    }

    public PathfinderGoalLookAtPlayer(EntityInsentient entityinsentient, Class<? extends EntityLiving> oclass, float f, float f1) {
        this.a = entityinsentient;
        this.d = oclass;
        this.c = f;
        this.g = f1;
        this.a(EnumSet.of(PathfinderGoal.Type.LOOK));
        if (oclass == EntityHuman.class) {
            this.e = (new PathfinderTargetCondition()).a((double) f).b().a().d().a((entityliving) -> {
                return IEntitySelector.b(entityinsentient).test(entityliving);
            });
        } else {
            this.e = (new PathfinderTargetCondition()).a((double) f).b().a().d();
        }

    }

    @Override
    public boolean a() {
        if (this.a.getRandom().nextFloat() >= this.g) {
            return false;
        } else {
            if (this.a.getGoalTarget() != null) {
                this.b = this.a.getGoalTarget();
            }

            if (this.d == EntityHuman.class) {
                this.b = this.a.world.a(this.e, this.a, this.a.locX, this.a.locY + (double) this.a.getHeadHeight(), this.a.locZ);
            } else {
                this.b = this.a.world.b(this.d, this.e, this.a, this.a.locX, this.a.locY + (double) this.a.getHeadHeight(), this.a.locZ, this.a.getBoundingBox().grow((double) this.c, 3.0D, (double) this.c));
            }

            return this.b != null;
        }
    }

    @Override
    public boolean b() {
        return !this.b.isAlive() ? false : (this.a.h(this.b) > (double) (this.c * this.c) ? false : this.f > 0);
    }

    @Override
    public void c() {
        this.f = 40 + this.a.getRandom().nextInt(40);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        this.a.getControllerLook().a(this.b.locX, this.b.locY + (double) this.b.getHeadHeight(), this.b.locZ);
        --this.f;
    }
}
