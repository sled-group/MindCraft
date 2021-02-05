package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalOfferFlower extends PathfinderGoal {

    private static final PathfinderTargetCondition a = (new PathfinderTargetCondition()).a(6.0D).b().a();
    private final EntityIronGolem b;
    private EntityVillager c;
    private int d;

    public PathfinderGoalOfferFlower(EntityIronGolem entityirongolem) {
        this.b = entityirongolem;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK));
    }

    @Override
    public boolean a() {
        if (!this.b.world.J()) {
            return false;
        } else if (this.b.getRandom().nextInt(8000) != 0) {
            return false;
        } else {
            this.c = (EntityVillager) this.b.world.a(EntityVillager.class, PathfinderGoalOfferFlower.a, this.b, this.b.locX, this.b.locY, this.b.locZ, this.b.getBoundingBox().grow(6.0D, 2.0D, 6.0D));
            return this.c != null;
        }
    }

    @Override
    public boolean b() {
        return this.d > 0;
    }

    @Override
    public void c() {
        this.d = 400;
        this.b.r(true);
    }

    @Override
    public void d() {
        this.b.r(false);
        this.c = null;
    }

    @Override
    public void e() {
        this.b.getControllerLook().a(this.c, 30.0F, 30.0F);
        --this.d;
    }
}
