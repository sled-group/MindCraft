package net.minecraft.server;

public class PathfinderGoalRestrictSun extends PathfinderGoal {

    private final EntityCreature a;

    public PathfinderGoalRestrictSun(EntityCreature entitycreature) {
        this.a = entitycreature;
    }

    @Override
    public boolean a() {
        return this.a.world.J() && this.a.getEquipment(EnumItemSlot.HEAD).isEmpty() && this.a.getNavigation() instanceof Navigation;
    }

    @Override
    public void c() {
        ((Navigation) this.a.getNavigation()).c(true);
    }

    @Override
    public void d() {
        ((Navigation) this.a.getNavigation()).c(false);
    }
}
