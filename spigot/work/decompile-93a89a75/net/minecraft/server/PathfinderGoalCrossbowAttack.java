package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalCrossbowAttack<T extends EntityMonster & IRangedEntity & ICrossbow> extends PathfinderGoal {

    private final T a;
    private PathfinderGoalCrossbowAttack.State b;
    private final double c;
    private final float d;
    private int e;
    private int f;

    public PathfinderGoalCrossbowAttack(T t0, double d0, float f) {
        this.b = PathfinderGoalCrossbowAttack.State.UNCHARGED;
        this.a = t0;
        this.c = d0;
        this.d = f * f;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
    }

    @Override
    public boolean a() {
        return this.h() && this.g();
    }

    private boolean g() {
        return this.a.a(Items.CROSSBOW);
    }

    @Override
    public boolean b() {
        return this.h() && (this.a() || !this.a.getNavigation().n()) && this.g();
    }

    private boolean h() {
        return this.a.getGoalTarget() != null && this.a.getGoalTarget().isAlive();
    }

    @Override
    public void d() {
        super.d();
        this.a.q(false);
        this.a.setGoalTarget((EntityLiving) null);
        this.e = 0;
        if (this.a.isHandRaised()) {
            this.a.dp();
            ((ICrossbow) this.a).a(false);
            ItemCrossbow.a(this.a.dl(), false);
        }

    }

    @Override
    public void e() {
        EntityLiving entityliving = this.a.getGoalTarget();

        if (entityliving != null) {
            boolean flag = this.a.getEntitySenses().a(entityliving);
            boolean flag1 = this.e > 0;

            if (flag != flag1) {
                this.e = 0;
            }

            if (flag) {
                ++this.e;
            } else {
                --this.e;
            }

            double d0 = this.a.h((Entity) entityliving);
            boolean flag2 = (d0 > (double) this.d || this.e < 5) && this.f == 0;

            if (flag2) {
                this.a.getNavigation().a((Entity) entityliving, this.j() ? this.c : this.c * 0.5D);
            } else {
                this.a.getNavigation().o();
            }

            this.a.getControllerLook().a(entityliving, 30.0F, 30.0F);
            if (this.b == PathfinderGoalCrossbowAttack.State.UNCHARGED) {
                if (!flag2) {
                    this.a.c(ProjectileHelper.a(this.a, Items.CROSSBOW));
                    this.b = PathfinderGoalCrossbowAttack.State.CHARGING;
                    ((ICrossbow) this.a).a(true);
                }
            } else if (this.b == PathfinderGoalCrossbowAttack.State.CHARGING) {
                if (!this.a.isHandRaised()) {
                    this.b = PathfinderGoalCrossbowAttack.State.UNCHARGED;
                }

                int i = this.a.dn();
                ItemStack itemstack = this.a.dl();

                if (i >= ItemCrossbow.e(itemstack)) {
                    this.a.clearActiveItem();
                    this.b = PathfinderGoalCrossbowAttack.State.CHARGED;
                    this.f = 20 + this.a.getRandom().nextInt(20);
                    ((ICrossbow) this.a).a(false);
                }
            } else if (this.b == PathfinderGoalCrossbowAttack.State.CHARGED) {
                --this.f;
                if (this.f == 0) {
                    this.b = PathfinderGoalCrossbowAttack.State.READY_TO_ATTACK;
                }
            } else if (this.b == PathfinderGoalCrossbowAttack.State.READY_TO_ATTACK && flag) {
                ((IRangedEntity) this.a).a(entityliving, 1.0F);
                ItemStack itemstack1 = this.a.b(ProjectileHelper.a(this.a, Items.CROSSBOW));

                ItemCrossbow.a(itemstack1, false);
                this.b = PathfinderGoalCrossbowAttack.State.UNCHARGED;
            }

        }
    }

    private boolean j() {
        return this.b == PathfinderGoalCrossbowAttack.State.UNCHARGED;
    }

    static enum State {

        UNCHARGED, CHARGING, CHARGED, READY_TO_ATTACK;

        private State() {}
    }
}
