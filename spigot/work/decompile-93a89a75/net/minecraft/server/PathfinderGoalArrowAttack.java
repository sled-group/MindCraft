package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalArrowAttack extends PathfinderGoal {

    private final EntityInsentient a;
    private final IRangedEntity b;
    private EntityLiving c;
    private int d;
    private final double e;
    private int f;
    private final int g;
    private final int h;
    private final float i;
    private final float j;

    public PathfinderGoalArrowAttack(IRangedEntity irangedentity, double d0, int i, float f) {
        this(irangedentity, d0, i, i, f);
    }

    public PathfinderGoalArrowAttack(IRangedEntity irangedentity, double d0, int i, int j, float f) {
        this.d = -1;
        if (!(irangedentity instanceof EntityLiving)) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        } else {
            this.b = irangedentity;
            this.a = (EntityInsentient) irangedentity;
            this.e = d0;
            this.g = i;
            this.h = j;
            this.i = f;
            this.j = f * f;
            this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
        }
    }

    @Override
    public boolean a() {
        EntityLiving entityliving = this.a.getGoalTarget();

        if (entityliving != null && entityliving.isAlive()) {
            this.c = entityliving;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean b() {
        return this.a() || !this.a.getNavigation().n();
    }

    @Override
    public void d() {
        this.c = null;
        this.f = 0;
        this.d = -1;
    }

    @Override
    public void e() {
        double d0 = this.a.e(this.c.locX, this.c.getBoundingBox().minY, this.c.locZ);
        boolean flag = this.a.getEntitySenses().a(this.c);

        if (flag) {
            ++this.f;
        } else {
            this.f = 0;
        }

        if (d0 <= (double) this.j && this.f >= 5) {
            this.a.getNavigation().o();
        } else {
            this.a.getNavigation().a((Entity) this.c, this.e);
        }

        this.a.getControllerLook().a(this.c, 30.0F, 30.0F);
        float f;

        if (--this.d == 0) {
            if (!flag) {
                return;
            }

            f = MathHelper.sqrt(d0) / this.i;
            float f1 = MathHelper.a(f, 0.1F, 1.0F);

            this.b.a(this.c, f1);
            this.d = MathHelper.d(f * (float) (this.h - this.g) + (float) this.g);
        } else if (this.d < 0) {
            f = MathHelper.sqrt(d0) / this.i;
            this.d = MathHelper.d(f * (float) (this.h - this.g) + (float) this.g);
        }

    }
}
