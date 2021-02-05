package net.minecraft.server;

public class PathfinderGoalDoorOpen extends PathfinderGoalDoorInteract {

    private final boolean a;
    private int b;

    public PathfinderGoalDoorOpen(EntityInsentient entityinsentient, boolean flag) {
        super(entityinsentient);
        this.entity = entityinsentient;
        this.a = flag;
    }

    @Override
    public boolean b() {
        return this.a && this.b > 0 && super.b();
    }

    @Override
    public void c() {
        this.b = 20;
        this.a(true);
    }

    @Override
    public void d() {
        this.a(false);
    }

    @Override
    public void e() {
        --this.b;
        super.e();
    }
}
