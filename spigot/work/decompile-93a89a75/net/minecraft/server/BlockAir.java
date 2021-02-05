package net.minecraft.server;

public class BlockAir extends Block {

    protected BlockAir(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.INVISIBLE;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return VoxelShapes.a();
    }

    @Override
    public boolean e(IBlockData iblockdata) {
        return true;
    }
}
