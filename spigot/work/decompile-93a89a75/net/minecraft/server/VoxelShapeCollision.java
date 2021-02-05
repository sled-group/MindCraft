package net.minecraft.server;

public interface VoxelShapeCollision {

    static VoxelShapeCollision a() {
        return VoxelShapeCollisionEntity.a;
    }

    static VoxelShapeCollision a(Entity entity) {
        return new VoxelShapeCollisionEntity(entity);
    }

    boolean b();

    boolean a(VoxelShape voxelshape, BlockPosition blockposition, boolean flag);

    boolean a(Item item);
}
