package net.minecraft.server;

import java.util.Random;

public class BlockBubbleColumn extends Block implements IFluidSource {

    public static final BlockStateBoolean a = BlockProperties.e;

    public BlockBubbleColumn(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockBubbleColumn.a, true));
    }

    @Override
    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
        IBlockData iblockdata1 = world.getType(blockposition.up());

        if (iblockdata1.isAir()) {
            entity.j((Boolean) iblockdata.get(BlockBubbleColumn.a));
            if (!world.isClientSide) {
                WorldServer worldserver = (WorldServer) world;

                for (int i = 0; i < 2; ++i) {
                    worldserver.a(Particles.SPLASH, (double) ((float) blockposition.getX() + world.random.nextFloat()), (double) (blockposition.getY() + 1), (double) ((float) blockposition.getZ() + world.random.nextFloat()), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    worldserver.a(Particles.BUBBLE, (double) ((float) blockposition.getX() + world.random.nextFloat()), (double) (blockposition.getY() + 1), (double) ((float) blockposition.getZ() + world.random.nextFloat()), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        } else {
            entity.k((Boolean) iblockdata.get(BlockBubbleColumn.a));
        }

    }

    @Override
    public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
        a(world, blockposition.up(), a((IBlockAccess) world, blockposition.down()));
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        a(world, blockposition.up(), a((IBlockAccess) world, blockposition));
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return FluidTypes.WATER.a(false);
    }

    public static void a(GeneratorAccess generatoraccess, BlockPosition blockposition, boolean flag) {
        if (a(generatoraccess, blockposition)) {
            generatoraccess.setTypeAndData(blockposition, (IBlockData) Blocks.BUBBLE_COLUMN.getBlockData().set(BlockBubbleColumn.a, flag), 2);
        }

    }

    public static boolean a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
        Fluid fluid = generatoraccess.getFluid(blockposition);

        return generatoraccess.getType(blockposition).getBlock() == Blocks.WATER && fluid.g() >= 8 && fluid.isSource();
    }

    private static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        Block block = iblockdata.getBlock();

        return block == Blocks.BUBBLE_COLUMN ? (Boolean) iblockdata.get(BlockBubbleColumn.a) : block != Blocks.SOUL_SAND;
    }

    @Override
    public int a(IWorldReader iworldreader) {
        return 5;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if (!iblockdata.canPlace(generatoraccess, blockposition)) {
            return Blocks.WATER.getBlockData();
        } else {
            if (enumdirection == EnumDirection.DOWN) {
                generatoraccess.setTypeAndData(blockposition, (IBlockData) Blocks.BUBBLE_COLUMN.getBlockData().set(BlockBubbleColumn.a, a((IBlockAccess) generatoraccess, blockposition1)), 2);
            } else if (enumdirection == EnumDirection.UP && iblockdata1.getBlock() != Blocks.BUBBLE_COLUMN && a(generatoraccess, blockposition1)) {
                generatoraccess.getBlockTickList().a(blockposition, this, this.a((IWorldReader) generatoraccess));
            }

            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
            return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
        }
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        Block block = iworldreader.getType(blockposition.down()).getBlock();

        return block == Blocks.BUBBLE_COLUMN || block == Blocks.MAGMA_BLOCK || block == Blocks.SOUL_SAND;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return VoxelShapes.a();
    }

    @Override
    public TextureType c() {
        return TextureType.TRANSLUCENT;
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.INVISIBLE;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockBubbleColumn.a);
    }

    @Override
    public FluidType removeFluid(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
        generatoraccess.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
        return FluidTypes.WATER;
    }
}
