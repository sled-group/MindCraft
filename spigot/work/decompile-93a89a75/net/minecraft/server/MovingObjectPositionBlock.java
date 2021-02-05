package net.minecraft.server;

public class MovingObjectPositionBlock extends MovingObjectPosition {

    private final EnumDirection b;
    private final BlockPosition c;
    private final boolean d;
    private final boolean e;

    public static MovingObjectPositionBlock a(Vec3D vec3d, EnumDirection enumdirection, BlockPosition blockposition) {
        return new MovingObjectPositionBlock(true, vec3d, enumdirection, blockposition, false);
    }

    public MovingObjectPositionBlock(Vec3D vec3d, EnumDirection enumdirection, BlockPosition blockposition, boolean flag) {
        this(false, vec3d, enumdirection, blockposition, flag);
    }

    private MovingObjectPositionBlock(boolean flag, Vec3D vec3d, EnumDirection enumdirection, BlockPosition blockposition, boolean flag1) {
        super(vec3d);
        this.d = flag;
        this.b = enumdirection;
        this.c = blockposition;
        this.e = flag1;
    }

    public MovingObjectPositionBlock a(EnumDirection enumdirection) {
        return new MovingObjectPositionBlock(this.d, this.pos, enumdirection, this.c, this.e);
    }

    public BlockPosition getBlockPosition() {
        return this.c;
    }

    public EnumDirection getDirection() {
        return this.b;
    }

    @Override
    public MovingObjectPosition.EnumMovingObjectType getType() {
        return this.d ? MovingObjectPosition.EnumMovingObjectType.MISS : MovingObjectPosition.EnumMovingObjectType.BLOCK;
    }

    public boolean d() {
        return this.e;
    }
}
