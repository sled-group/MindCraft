package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockStonecutter extends Block {

    private static final ChatMessage c = new ChatMessage("container.stonecutter", new Object[0]);
    public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
    protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public BlockStonecutter(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockStonecutter.a, EnumDirection.NORTH));
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockStonecutter.a, blockactioncontext.f().opposite());
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        entityhuman.openContainer(iblockdata.b(world, blockposition));
        entityhuman.a(StatisticList.INTERACT_WITH_STONECUTTER);
        return true;
    }

    @Nullable
    @Override
    public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return new TileInventory((i, playerinventory, entityhuman) -> {
            return new ContainerStonecutter(i, playerinventory, ContainerAccess.at(world, blockposition));
        }, BlockStonecutter.c);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockStonecutter.b;
    }

    @Override
    public boolean n(IBlockData iblockdata) {
        return true;
    }

    @Override
    public boolean f(IBlockData iblockdata) {
        return true;
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.MODEL;
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockStonecutter.a, enumblockrotation.a((EnumDirection) iblockdata.get(BlockStonecutter.a)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockStonecutter.a)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockStonecutter.a);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
