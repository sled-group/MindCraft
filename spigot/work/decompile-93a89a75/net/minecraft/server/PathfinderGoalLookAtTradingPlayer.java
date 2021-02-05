package net.minecraft.server;

public class PathfinderGoalLookAtTradingPlayer extends PathfinderGoalLookAtPlayer {

    private final EntityVillagerAbstract f;

    public PathfinderGoalLookAtTradingPlayer(EntityVillagerAbstract entityvillagerabstract) {
        super(entityvillagerabstract, EntityHuman.class, 8.0F);
        this.f = entityvillagerabstract;
    }

    @Override
    public boolean a() {
        if (this.f.dY()) {
            this.b = this.f.getTrader();
            return true;
        } else {
            return false;
        }
    }
}
