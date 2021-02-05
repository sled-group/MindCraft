package net.minecraft.server;

public abstract class MovingObjectPosition {

    protected final Vec3D pos;

    protected MovingObjectPosition(Vec3D vec3d) {
        this.pos = vec3d;
    }

    public abstract MovingObjectPosition.EnumMovingObjectType getType();

    public Vec3D getPos() {
        return this.pos;
    }

    public static enum EnumMovingObjectType {

        MISS, BLOCK, ENTITY;

        private EnumMovingObjectType() {}
    }
}
