package net.minecraft.server;

import java.util.Random;

public class BlockBeetroot extends BlockCrops {

    public static final BlockStateInteger a = BlockProperties.aa;
    private static final VoxelShape[] c = new VoxelShape[]{Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)};

    public BlockBeetroot(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public BlockStateInteger d() {
        return BlockBeetroot.a;
    }

    @Override
    public int e() {
        return 3;
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        if (random.nextInt(3) != 0) {
            super.tick(iblockdata, world, blockposition, random);
        }

    }

    @Override
    protected int a(World world) {
        return super.a(world) / 3;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockBeetroot.a);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockBeetroot.c[(Integer) iblockdata.get(this.d())];
    }
}
