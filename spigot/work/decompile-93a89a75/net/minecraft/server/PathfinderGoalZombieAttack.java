package net.minecraft.server;

public class PathfinderGoalZombieAttack extends PathfinderGoalMeleeAttack {

    private final EntityZombie d;
    private int e;

    public PathfinderGoalZombieAttack(EntityZombie entityzombie, double d0, boolean flag) {
        super(entityzombie, d0, flag);
        this.d = entityzombie;
    }

    @Override
    public void c() {
        super.c();
        this.e = 0;
    }

    @Override
    public void d() {
        super.d();
        this.d.q(false);
    }

    @Override
    public void e() {
        super.e();
        ++this.e;
        if (this.e >= 5 && this.b < 10) {
            this.d.q(true);
        } else {
            this.d.q(false);
        }

    }
}
