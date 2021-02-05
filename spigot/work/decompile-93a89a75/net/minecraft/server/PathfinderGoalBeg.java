package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalBeg extends PathfinderGoal {

    private final EntityWolf a;
    private EntityHuman b;
    private final World c;
    private final float d;
    private int e;
    private final PathfinderTargetCondition f;

    public PathfinderGoalBeg(EntityWolf entitywolf, float f) {
        this.a = entitywolf;
        this.c = entitywolf.world;
        this.d = f;
        this.f = (new PathfinderTargetCondition()).a((double) f).a().b().d();
        this.a(EnumSet.of(PathfinderGoal.Type.LOOK));
    }

    @Override
    public boolean a() {
        this.b = this.c.a(this.f, (EntityLiving) this.a);
        return this.b == null ? false : this.a(this.b);
    }

    @Override
    public boolean b() {
        return !this.b.isAlive() ? false : (this.a.h((Entity) this.b) > (double) (this.d * this.d) ? false : this.e > 0 && this.a(this.b));
    }

    @Override
    public void c() {
        this.a.v(true);
        this.e = 40 + this.a.getRandom().nextInt(40);
    }

    @Override
    public void d() {
        this.a.v(false);
        this.b = null;
    }

    @Override
    public void e() {
        this.a.getControllerLook().a(this.b.locX, this.b.locY + (double) this.b.getHeadHeight(), this.b.locZ, 10.0F, (float) this.a.M());
        --this.e;
    }

    private boolean a(EntityHuman entityhuman) {
        EnumHand[] aenumhand = EnumHand.values();
        int i = aenumhand.length;

        for (int j = 0; j < i; ++j) {
            EnumHand enumhand = aenumhand[j];
            ItemStack itemstack = entityhuman.b(enumhand);

            if (this.a.isTamed() && itemstack.getItem() == Items.BONE) {
                return true;
            }

            if (this.a.i(itemstack)) {
                return true;
            }
        }

        return false;
    }
}
