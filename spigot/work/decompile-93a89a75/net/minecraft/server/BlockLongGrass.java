package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockPlant implements IBlockFragilePlantElement {

    protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    protected BlockLongGrass(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockLongGrass.a;
    }

    @Override
    public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return true;
    }

    @Override
    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    @Override
    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        BlockTallPlant blocktallplant = (BlockTallPlant) ((BlockTallPlant) (this == Blocks.FERN ? Blocks.LARGE_FERN : Blocks.TALL_GRASS));

        if (blocktallplant.getBlockData().canPlace(world, blockposition) && world.isEmpty(blockposition.up())) {
            blocktallplant.a(world, blockposition, 2);
        }

    }

    @Override
    public Block.EnumRandomOffset R_() {
        return Block.EnumRandomOffset.XYZ;
    }
}
