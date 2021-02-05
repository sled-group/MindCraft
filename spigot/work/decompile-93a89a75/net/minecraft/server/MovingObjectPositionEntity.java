package net.minecraft.server;

public class MovingObjectPositionEntity extends MovingObjectPosition {

    private final Entity b;

    public MovingObjectPositionEntity(Entity entity) {
        this(entity, new Vec3D(entity.locX, entity.locY, entity.locZ));
    }

    public MovingObjectPositionEntity(Entity entity, Vec3D vec3d) {
        super(vec3d);
        this.b = entity;
    }

    public Entity getEntity() {
        return this.b;
    }

    @Override
    public MovingObjectPosition.EnumMovingObjectType getType() {
        return MovingObjectPosition.EnumMovingObjectType.ENTITY;
    }
}
