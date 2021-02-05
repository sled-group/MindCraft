package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class BlockSeaPickle extends BlockPlant implements IBlockFragilePlantElement, IBlockWaterlogged {

    public static final BlockStateInteger a = BlockProperties.ar;
    public static final BlockStateBoolean b = BlockProperties.C;
    protected static final VoxelShape c = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    protected static final VoxelShape d = Block.a(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
    protected static final VoxelShape e = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    protected static final VoxelShape f = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

    protected BlockSeaPickle(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockSeaPickle.a, 1)).set(BlockSeaPickle.b, true));
    }

    @Override
    public int a(IBlockData iblockdata) {
        return this.j(iblockdata) ? 0 : super.a(iblockdata) + 3 * (Integer) iblockdata.get(BlockSeaPickle.a);
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition());

        if (iblockdata.getBlock() == this) {
            return (IBlockData) iblockdata.set(BlockSeaPickle.a, Math.min(4, (Integer) iblockdata.get(BlockSeaPickle.a) + 1));
        } else {
            Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
            boolean flag = fluid.a(TagsFluid.WATER) && fluid.g() == 8;

            return (IBlockData) super.getPlacedState(blockactioncontext).set(BlockSeaPickle.b, flag);
        }
    }

    private boolean j(IBlockData iblockdata) {
        return !(Boolean) iblockdata.get(BlockSeaPickle.b);
    }

    @Override
    protected boolean a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return !iblockdata.getCollisionShape(iblockaccess, blockposition).a(EnumDirection.UP).isEmpty();
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        BlockPosition blockposition1 = blockposition.down();

        return this.a_(iworldreader.getType(blockposition1), iworldreader, blockposition1);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if (!iblockdata.canPlace(generatoraccess, blockposition)) {
            return Blocks.AIR.getBlockData();
        } else {
            if ((Boolean) iblockdata.get(BlockSeaPickle.b)) {
                generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
            }

            return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
        }
    }

    @Override
    public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
        return blockactioncontext.getItemStack().getItem() == this.getItem() && (Integer) iblockdata.get(BlockSeaPickle.a) < 4 ? true : super.a(iblockdata, blockactioncontext);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        switch ((Integer) iblockdata.get(BlockSeaPickle.a)) {
            case 1:
            default:
                return BlockSeaPickle.c;
            case 2:
                return BlockSeaPickle.d;
            case 3:
                return BlockSeaPickle.e;
            case 4:
                return BlockSeaPickle.f;
        }
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return (Boolean) iblockdata.get(BlockSeaPickle.b) ? FluidTypes.WATER.a(false) : super.g(iblockdata);
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockSeaPickle.a, BlockSeaPickle.b);
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
        if (!this.j(iblockdata) && world.getType(blockposition.down()).a(TagsBlock.CORAL_BLOCKS)) {
            boolean flag = true;
            int i = 1;
            boolean flag1 = true;
            int j = 0;
            int k = blockposition.getX() - 2;
            int l = 0;

            for (int i1 = 0; i1 < 5; ++i1) {
                for (int j1 = 0; j1 < i; ++j1) {
                    int k1 = 2 + blockposition.getY() - 1;

                    for (int l1 = k1 - 2; l1 < k1; ++l1) {
                        BlockPosition blockposition1 = new BlockPosition(k + i1, l1, blockposition.getZ() - l + j1);

                        if (blockposition1 != blockposition && random.nextInt(6) == 0 && world.getType(blockposition1).getBlock() == Blocks.WATER) {
                            IBlockData iblockdata1 = world.getType(blockposition1.down());

                            if (iblockdata1.a(TagsBlock.CORAL_BLOCKS)) {
                                world.setTypeAndData(blockposition1, (IBlockData) Blocks.SEA_PICKLE.getBlockData().set(BlockSeaPickle.a, random.nextInt(4) + 1), 3);
                            }
                        }
                    }
                }

                if (j < 2) {
                    i += 2;
                    ++l;
                } else {
                    i -= 2;
                    --l;
                }

                ++j;
            }

            world.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockSeaPickle.a, 4), 2);
        }

    }
}
