package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockScaffolding extends Block implements IBlockWaterlogged {

    private static final VoxelShape d;
    private static final VoxelShape e;
    private static final VoxelShape f = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape g = VoxelShapes.b().a(0.0D, -1.0D, 0.0D);
    public static final BlockStateInteger a = BlockProperties.au;
    public static final BlockStateBoolean b = BlockProperties.C;
    public static final BlockStateBoolean c = BlockProperties.b;

    protected BlockScaffolding(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockScaffolding.a, 7)).set(BlockScaffolding.b, false)).set(BlockScaffolding.c, false));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockScaffolding.a, BlockScaffolding.b, BlockScaffolding.c);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return !voxelshapecollision.a(iblockdata.getBlock().getItem()) ? ((Boolean) iblockdata.get(BlockScaffolding.c) ? BlockScaffolding.e : BlockScaffolding.d) : VoxelShapes.b();
    }

    @Override
    public VoxelShape i(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return VoxelShapes.b();
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }

    @Override
    public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
        return blockactioncontext.getItemStack().getItem() == this.getItem();
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        World world = blockactioncontext.getWorld();
        int i = a((IBlockAccess) world, blockposition);

        return (IBlockData) ((IBlockData) ((IBlockData) this.getBlockData().set(BlockScaffolding.b, world.getFluid(blockposition).getType() == FluidTypes.WATER)).set(BlockScaffolding.a, i)).set(BlockScaffolding.c, this.a(world, blockposition, i));
    }

    @Override
    public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
        if (!world.isClientSide) {
            world.getBlockTickList().a(blockposition, this, 1);
        }

    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if ((Boolean) iblockdata.get(BlockScaffolding.b)) {
            generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a((IWorldReader) generatoraccess));
        }

        if (!generatoraccess.e()) {
            generatoraccess.getBlockTickList().a(blockposition, this, 1);
        }

        return iblockdata;
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        int i = a((IBlockAccess) world, blockposition);
        IBlockData iblockdata1 = (IBlockData) ((IBlockData) iblockdata.set(BlockScaffolding.a, i)).set(BlockScaffolding.c, this.a(world, blockposition, i));

        if ((Integer) iblockdata1.get(BlockScaffolding.a) == 7) {
            if ((Integer) iblockdata.get(BlockScaffolding.a) == 7) {
                world.addEntity(new EntityFallingBlock(world, (double) blockposition.getX() + 0.5D, (double) blockposition.getY(), (double) blockposition.getZ() + 0.5D, (IBlockData) iblockdata1.set(BlockScaffolding.b, false)));
            } else {
                world.b(blockposition, true);
            }
        } else if (iblockdata != iblockdata1) {
            world.setTypeAndData(blockposition, iblockdata1, 3);
        }

    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return a((IBlockAccess) iworldreader, blockposition) < 7;
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return voxelshapecollision.a(VoxelShapes.b(), blockposition, true) && !voxelshapecollision.b() ? BlockScaffolding.d : ((Integer) iblockdata.get(BlockScaffolding.a) != 0 && (Boolean) iblockdata.get(BlockScaffolding.c) && voxelshapecollision.a(BlockScaffolding.g, blockposition, true) ? BlockScaffolding.f : VoxelShapes.a());
    }

    @Override
    public Fluid g(IBlockData iblockdata) {
        return (Boolean) iblockdata.get(BlockScaffolding.b) ? FluidTypes.WATER.a(false) : super.g(iblockdata);
    }

    private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, int i) {
        return i > 0 && iblockaccess.getType(blockposition.down()).getBlock() != this;
    }

    public static int a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = (new BlockPosition.MutableBlockPosition(blockposition)).c(EnumDirection.DOWN);
        IBlockData iblockdata = iblockaccess.getType(blockposition_mutableblockposition);
        int i = 7;

        if (iblockdata.getBlock() == Blocks.SCAFFOLDING) {
            i = (Integer) iblockdata.get(BlockScaffolding.a);
        } else if (iblockdata.d(iblockaccess, blockposition_mutableblockposition, EnumDirection.UP)) {
            return 0;
        }

        Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();
            IBlockData iblockdata1 = iblockaccess.getType(blockposition_mutableblockposition.g(blockposition).c(enumdirection));

            if (iblockdata1.getBlock() == Blocks.SCAFFOLDING) {
                i = Math.min(i, (Integer) iblockdata1.get(BlockScaffolding.a) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return i;
    }

    static {
        VoxelShape voxelshape = Block.a(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape voxelshape1 = Block.a(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape voxelshape2 = Block.a(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape voxelshape3 = Block.a(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape voxelshape4 = Block.a(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);

        d = VoxelShapes.a(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);
        VoxelShape voxelshape5 = Block.a(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape voxelshape6 = Block.a(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape7 = Block.a(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape8 = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);

        e = VoxelShapes.a(BlockScaffolding.f, BlockScaffolding.d, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
    }
}
