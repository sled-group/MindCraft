package net.minecraft.server;

public class BehaviorTarget implements BehaviorPosition {

    private final BlockPosition a;
    private final Vec3D b;

    public BehaviorTarget(BlockPosition blockposition) {
        this.a = blockposition;
        this.b = new Vec3D((double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D);
    }

    @Override
    public BlockPosition a() {
        return this.a;
    }

    @Override
    public Vec3D b() {
        return this.b;
    }

    @Override
    public boolean a(EntityLiving entityliving) {
        return true;
    }

    public String toString() {
        return "BlockPosWrapper{pos=" + this.a + ", lookAt=" + this.b + '}';
    }
}
