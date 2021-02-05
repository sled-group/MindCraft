package net.minecraft.server;

public class PathfinderGoalPerch extends PathfinderGoal {

    private final EntityPerchable a;
    private EntityPlayer b;
    private boolean c;

    public PathfinderGoalPerch(EntityPerchable entityperchable) {
        this.a = entityperchable;
    }

    @Override
    public boolean a() {
        EntityPlayer entityplayer = (EntityPlayer) this.a.getOwner();
        boolean flag = entityplayer != null && !entityplayer.isSpectator() && !entityplayer.abilities.isFlying && !entityplayer.isInWater();

        return !this.a.isSitting() && flag && this.a.eh();
    }

    @Override
    public boolean C_() {
        return !this.c;
    }

    @Override
    public void c() {
        this.b = (EntityPlayer) this.a.getOwner();
        this.c = false;
    }

    @Override
    public void e() {
        if (!this.c && !this.a.isSitting() && !this.a.isLeashed()) {
            if (this.a.getBoundingBox().c(this.b.getBoundingBox())) {
                this.c = this.a.d(this.b);
            }

        }
    }
}
