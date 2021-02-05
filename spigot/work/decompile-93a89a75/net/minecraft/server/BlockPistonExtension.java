package net.minecraft.server;

public class BlockPistonExtension extends BlockDirectional {

    public static final BlockStateEnum<BlockPropertyPistonType> TYPE = BlockProperties.aB;
    public static final BlockStateBoolean SHORT = BlockProperties.x;
    protected static final VoxelShape d = Block.a(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
    protected static final VoxelShape f = Block.a(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape g = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape h = Block.a(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape i = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    protected static final VoxelShape j = Block.a(6.0D, -4.0D, 6.0D, 10.0D, 12.0D, 10.0D);
    protected static final VoxelShape k = Block.a(6.0D, 4.0D, 6.0D, 10.0D, 20.0D, 10.0D);
    protected static final VoxelShape w = Block.a(6.0D, 6.0D, -4.0D, 10.0D, 10.0D, 12.0D);
    protected static final VoxelShape x = Block.a(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 20.0D);
    protected static final VoxelShape y = Block.a(-4.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
    protected static final VoxelShape z = Block.a(4.0D, 6.0D, 6.0D, 20.0D, 10.0D, 10.0D);
    protected static final VoxelShape A = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
    protected static final VoxelShape B = Block.a(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape C = Block.a(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D);
    protected static final VoxelShape D = Block.a(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape E = Block.a(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
    protected static final VoxelShape F = Block.a(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);

    public BlockPistonExtension(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockPistonExtension.FACING, EnumDirection.NORTH)).set(BlockPistonExtension.TYPE, BlockPropertyPistonType.DEFAULT)).set(BlockPistonExtension.SHORT, false));
    }

    private VoxelShape j(IBlockData iblockdata) {
        switch ((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)) {
            case DOWN:
            default:
                return BlockPistonExtension.i;
            case UP:
                return BlockPistonExtension.h;
            case NORTH:
                return BlockPistonExtension.g;
            case SOUTH:
                return BlockPistonExtension.f;
            case WEST:
                return BlockPistonExtension.e;
            case EAST:
                return BlockPistonExtension.d;
        }
    }

    @Override
    public boolean n(IBlockData iblockdata) {
        return true;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return VoxelShapes.a(this.j(iblockdata), this.q(iblockdata));
    }

    private VoxelShape q(IBlockData iblockdata) {
        boolean flag = (Boolean) iblockdata.get(BlockPistonExtension.SHORT);

        switch ((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)) {
            case DOWN:
            default:
                return flag ? BlockPistonExtension.B : BlockPistonExtension.k;
            case UP:
                return flag ? BlockPistonExtension.A : BlockPistonExtension.j;
            case NORTH:
                return flag ? BlockPistonExtension.D : BlockPistonExtension.x;
            case SOUTH:
                return flag ? BlockPistonExtension.C : BlockPistonExtension.w;
            case WEST:
                return flag ? BlockPistonExtension.F : BlockPistonExtension.z;
            case EAST:
                return flag ? BlockPistonExtension.E : BlockPistonExtension.y;
        }
    }

    @Override
    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        if (!world.isClientSide && entityhuman.abilities.canInstantlyBuild) {
            BlockPosition blockposition1 = blockposition.shift(((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).opposite());
            Block block = world.getType(blockposition1).getBlock();

            if (block == Blocks.PISTON || block == Blocks.STICKY_PISTON) {
                world.a(blockposition1, false);
            }
        }

        super.a(world, blockposition, iblockdata, entityhuman);
    }

    @Override
    public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
        if (iblockdata.getBlock() != iblockdata1.getBlock()) {
            super.remove(iblockdata, world, blockposition, iblockdata1, flag);
            EnumDirection enumdirection = ((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).opposite();

            blockposition = blockposition.shift(enumdirection);
            IBlockData iblockdata2 = world.getType(blockposition);

            if ((iblockdata2.getBlock() == Blocks.PISTON || iblockdata2.getBlock() == Blocks.STICKY_PISTON) && (Boolean) iblockdata2.get(BlockPiston.EXTENDED)) {
                c(iblockdata2, world, blockposition);
                world.a(blockposition, false);
            }

        }
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return enumdirection.opposite() == iblockdata.get(BlockPistonExtension.FACING) && !iblockdata.canPlace(generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        Block block = iworldreader.getType(blockposition.shift(((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).opposite())).getBlock();

        return block == Blocks.PISTON || block == Blocks.STICKY_PISTON || block == Blocks.MOVING_PISTON;
    }

    @Override
    public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
        if (iblockdata.canPlace(world, blockposition)) {
            BlockPosition blockposition2 = blockposition.shift(((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).opposite());

            world.getType(blockposition2).doPhysics(world, blockposition2, block, blockposition1, false);
        }

    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockPistonExtension.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockPistonExtension.FACING, BlockPistonExtension.TYPE, BlockPistonExtension.SHORT);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
