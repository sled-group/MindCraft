package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockAnvil extends BlockFalling {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    private static final VoxelShape b = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
    private static final VoxelShape c = Block.a(3.0D, 4.0D, 4.0D, 13.0D, 5.0D, 12.0D);
    private static final VoxelShape d = Block.a(4.0D, 5.0D, 6.0D, 12.0D, 10.0D, 10.0D);
    private static final VoxelShape e = Block.a(0.0D, 10.0D, 3.0D, 16.0D, 16.0D, 13.0D);
    private static final VoxelShape f = Block.a(4.0D, 4.0D, 3.0D, 12.0D, 5.0D, 13.0D);
    private static final VoxelShape g = Block.a(6.0D, 5.0D, 4.0D, 10.0D, 10.0D, 12.0D);
    private static final VoxelShape h = Block.a(3.0D, 10.0D, 0.0D, 13.0D, 16.0D, 16.0D);
    private static final VoxelShape i = VoxelShapes.a(BlockAnvil.b, BlockAnvil.c, BlockAnvil.d, BlockAnvil.e);
    private static final VoxelShape j = VoxelShapes.a(BlockAnvil.b, BlockAnvil.f, BlockAnvil.g, BlockAnvil.h);
    private static final ChatMessage k = new ChatMessage("container.repair", new Object[0]);

    public BlockAnvil(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockAnvil.FACING, EnumDirection.NORTH));
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockAnvil.FACING, blockactioncontext.f().e());
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        entityhuman.openContainer(iblockdata.b(world, blockposition));
        return true;
    }

    @Nullable
    @Override
    public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return new TileInventory((i, playerinventory, entityhuman) -> {
            return new ContainerAnvil(i, playerinventory, ContainerAccess.at(world, blockposition));
        }, BlockAnvil.k);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockAnvil.FACING);

        return enumdirection.k() == EnumDirection.EnumAxis.X ? BlockAnvil.i : BlockAnvil.j;
    }

    @Override
    protected void a(EntityFallingBlock entityfallingblock) {
        entityfallingblock.a(true);
    }

    @Override
    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {
        world.triggerEffect(1031, blockposition, 0);
    }

    @Override
    public void a(World world, BlockPosition blockposition) {
        world.triggerEffect(1029, blockposition, 0);
    }

    @Nullable
    public static IBlockData a_(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return block == Blocks.ANVIL ? (IBlockData) Blocks.CHIPPED_ANVIL.getBlockData().set(BlockAnvil.FACING, iblockdata.get(BlockAnvil.FACING)) : (block == Blocks.CHIPPED_ANVIL ? (IBlockData) Blocks.DAMAGED_ANVIL.getBlockData().set(BlockAnvil.FACING, iblockdata.get(BlockAnvil.FACING)) : null);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockAnvil.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockAnvil.FACING)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockAnvil.FACING);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
