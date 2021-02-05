package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockBell extends BlockTileEntity {

    public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
    private static final BlockStateEnum<BlockPropertyBellAttach> b = BlockProperties.P;
    private static final VoxelShape c = Block.a(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);
    private static final VoxelShape d = Block.a(4.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
    private static final VoxelShape e = Block.a(5.0D, 6.0D, 5.0D, 11.0D, 13.0D, 11.0D);
    private static final VoxelShape f = Block.a(4.0D, 4.0D, 4.0D, 12.0D, 6.0D, 12.0D);
    private static final VoxelShape g = VoxelShapes.a(BlockBell.f, BlockBell.e);
    private static final VoxelShape h = VoxelShapes.a(BlockBell.g, Block.a(7.0D, 13.0D, 0.0D, 9.0D, 15.0D, 16.0D));
    private static final VoxelShape i = VoxelShapes.a(BlockBell.g, Block.a(0.0D, 13.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    private static final VoxelShape j = VoxelShapes.a(BlockBell.g, Block.a(0.0D, 13.0D, 7.0D, 13.0D, 15.0D, 9.0D));
    private static final VoxelShape k = VoxelShapes.a(BlockBell.g, Block.a(3.0D, 13.0D, 7.0D, 16.0D, 15.0D, 9.0D));
    private static final VoxelShape w = VoxelShapes.a(BlockBell.g, Block.a(7.0D, 13.0D, 0.0D, 9.0D, 15.0D, 13.0D));
    private static final VoxelShape x = VoxelShapes.a(BlockBell.g, Block.a(7.0D, 13.0D, 3.0D, 9.0D, 15.0D, 16.0D));
    private static final VoxelShape y = VoxelShapes.a(BlockBell.g, Block.a(7.0D, 13.0D, 7.0D, 9.0D, 16.0D, 9.0D));

    public BlockBell(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockBell.a, EnumDirection.NORTH)).set(BlockBell.b, BlockPropertyBellAttach.FLOOR));
    }

    @Override
    public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, Entity entity) {
        if (entity instanceof EntityArrow) {
            Entity entity1 = ((EntityArrow) entity).getShooter();
            EntityHuman entityhuman = entity1 instanceof EntityHuman ? (EntityHuman) entity1 : null;

            this.a(world, iblockdata, world.getTileEntity(movingobjectpositionblock.getBlockPosition()), movingobjectpositionblock, entityhuman, true);
        }

    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        return this.a(world, iblockdata, world.getTileEntity(blockposition), movingobjectpositionblock, entityhuman, true);
    }

    public boolean a(World world, IBlockData iblockdata, @Nullable TileEntity tileentity, MovingObjectPositionBlock movingobjectpositionblock, @Nullable EntityHuman entityhuman, boolean flag) {
        EnumDirection enumdirection = movingobjectpositionblock.getDirection();
        BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
        boolean flag1 = !flag || this.a(iblockdata, enumdirection, movingobjectpositionblock.getPos().y - (double) blockposition.getY());

        if (!world.isClientSide && tileentity instanceof TileEntityBell && flag1) {
            ((TileEntityBell) tileentity).a(enumdirection);
            this.a(world, blockposition);
            if (entityhuman != null) {
                entityhuman.a(StatisticList.BELL_RING);
            }

            return true;
        } else {
            return true;
        }
    }

    private boolean a(IBlockData iblockdata, EnumDirection enumdirection, double d0) {
        if (enumdirection.k() != EnumDirection.EnumAxis.Y && d0 <= 0.8123999834060669D) {
            EnumDirection enumdirection1 = (EnumDirection) iblockdata.get(BlockBell.a);
            BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach) iblockdata.get(BlockBell.b);

            switch (blockpropertybellattach) {
                case FLOOR:
                    return enumdirection1.k() == enumdirection.k();
                case SINGLE_WALL:
                case DOUBLE_WALL:
                    return enumdirection1.k() != enumdirection.k();
                case CEILING:
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    private void a(World world, BlockPosition blockposition) {
        world.playSound((EntityHuman) null, blockposition, SoundEffects.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F);
    }

    private VoxelShape j(IBlockData iblockdata) {
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockBell.a);
        BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach) iblockdata.get(BlockBell.b);

        return blockpropertybellattach == BlockPropertyBellAttach.FLOOR ? (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH ? BlockBell.d : BlockBell.c) : (blockpropertybellattach == BlockPropertyBellAttach.CEILING ? BlockBell.y : (blockpropertybellattach == BlockPropertyBellAttach.DOUBLE_WALL ? (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH ? BlockBell.i : BlockBell.h) : (enumdirection == EnumDirection.NORTH ? BlockBell.w : (enumdirection == EnumDirection.SOUTH ? BlockBell.x : (enumdirection == EnumDirection.EAST ? BlockBell.k : BlockBell.j)))));
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return this.j(iblockdata);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return this.j(iblockdata);
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.MODEL;
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        EnumDirection enumdirection = blockactioncontext.getClickedFace();
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        World world = blockactioncontext.getWorld();
        EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.k();
        IBlockData iblockdata;

        if (enumdirection_enumaxis == EnumDirection.EnumAxis.Y) {
            iblockdata = (IBlockData) ((IBlockData) this.getBlockData().set(BlockBell.b, enumdirection == EnumDirection.DOWN ? BlockPropertyBellAttach.CEILING : BlockPropertyBellAttach.FLOOR)).set(BlockBell.a, blockactioncontext.f());
            if (iblockdata.canPlace(blockactioncontext.getWorld(), blockposition)) {
                return iblockdata;
            }
        } else {
            boolean flag = enumdirection_enumaxis == EnumDirection.EnumAxis.X && world.getType(blockposition.west()).d(world, blockposition.west(), EnumDirection.EAST) && world.getType(blockposition.east()).d(world, blockposition.east(), EnumDirection.WEST) || enumdirection_enumaxis == EnumDirection.EnumAxis.Z && world.getType(blockposition.north()).d(world, blockposition.north(), EnumDirection.SOUTH) && world.getType(blockposition.south()).d(world, blockposition.south(), EnumDirection.NORTH);

            iblockdata = (IBlockData) ((IBlockData) this.getBlockData().set(BlockBell.a, enumdirection.opposite())).set(BlockBell.b, flag ? BlockPropertyBellAttach.DOUBLE_WALL : BlockPropertyBellAttach.SINGLE_WALL);
            if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
                return iblockdata;
            }

            boolean flag1 = world.getType(blockposition.down()).d(world, blockposition.down(), EnumDirection.UP);

            iblockdata = (IBlockData) iblockdata.set(BlockBell.b, flag1 ? BlockPropertyBellAttach.FLOOR : BlockPropertyBellAttach.CEILING);
            if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
                return iblockdata;
            }
        }

        return null;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        BlockPropertyBellAttach blockpropertybellattach = (BlockPropertyBellAttach) iblockdata.get(BlockBell.b);
        EnumDirection enumdirection1 = q(iblockdata).opposite();

        if (enumdirection1 == enumdirection && !iblockdata.canPlace(generatoraccess, blockposition) && blockpropertybellattach != BlockPropertyBellAttach.DOUBLE_WALL) {
            return Blocks.AIR.getBlockData();
        } else {
            if (enumdirection.k() == ((EnumDirection) iblockdata.get(BlockBell.a)).k()) {
                if (blockpropertybellattach == BlockPropertyBellAttach.DOUBLE_WALL && !iblockdata1.d(generatoraccess, blockposition1, enumdirection)) {
                    return (IBlockData) ((IBlockData) iblockdata.set(BlockBell.b, BlockPropertyBellAttach.SINGLE_WALL)).set(BlockBell.a, enumdirection.opposite());
                }

                if (blockpropertybellattach == BlockPropertyBellAttach.SINGLE_WALL && enumdirection1.opposite() == enumdirection && iblockdata1.d(generatoraccess, blockposition1, (EnumDirection) iblockdata.get(BlockBell.a))) {
                    return (IBlockData) iblockdata.set(BlockBell.b, BlockPropertyBellAttach.DOUBLE_WALL);
                }
            }

            return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
        }
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return BlockAttachable.b(iworldreader, blockposition, q(iblockdata).opposite());
    }

    private static EnumDirection q(IBlockData iblockdata) {
        switch ((BlockPropertyBellAttach) iblockdata.get(BlockBell.b)) {
            case FLOOR:
                return EnumDirection.UP;
            case CEILING:
                return EnumDirection.DOWN;
            default:
                return ((EnumDirection) iblockdata.get(BlockBell.a)).opposite();
        }
    }

    @Override
    public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
        return EnumPistonReaction.DESTROY;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockBell.a, BlockBell.b);
    }

    @Nullable
    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntityBell();
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
