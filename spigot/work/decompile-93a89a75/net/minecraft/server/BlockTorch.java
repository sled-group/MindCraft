package net.minecraft.server;

public class BlockTorch extends Block {

    protected static final VoxelShape d = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    protected BlockTorch(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockTorch.d;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return enumdirection == EnumDirection.DOWN && !this.canPlace(iblockdata, generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return a(iworldreader, blockposition.down(), EnumDirection.UP);
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }
}
