package net.minecraft.server;

import java.util.EnumSet;

public class PathfinderGoalCatSitOnBed extends PathfinderGoalGotoTarget {

    private final EntityCat g;

    public PathfinderGoalCatSitOnBed(EntityCat entitycat, double d0, int i) {
        super(entitycat, d0, i, 6);
        this.g = entitycat;
        this.f = -2;
        this.a(EnumSet.of(PathfinderGoal.Type.JUMP, PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        return this.g.isTamed() && !this.g.isSitting() && !this.g.eg() && super.a();
    }

    @Override
    public void c() {
        super.c();
        this.g.getGoalSit().setSitting(false);
    }

    @Override
    protected int a(EntityCreature entitycreature) {
        return 40;
    }

    @Override
    public void d() {
        super.d();
        this.g.u(false);
    }

    @Override
    public void e() {
        super.e();
        this.g.getGoalSit().setSitting(false);
        if (!this.k()) {
            this.g.u(false);
        } else if (!this.g.eg()) {
            this.g.u(true);
        }

    }

    @Override
    protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
        return iworldreader.isEmpty(blockposition.up()) && iworldreader.getType(blockposition).getBlock().a(TagsBlock.BEDS);
    }
}
