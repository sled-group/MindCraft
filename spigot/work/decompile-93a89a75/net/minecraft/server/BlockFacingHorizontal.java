package net.minecraft.server;

public abstract class BlockFacingHorizontal extends Block {

    public static final BlockStateDirection FACING = BlockProperties.N;

    protected BlockFacingHorizontal(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockFacingHorizontal.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockFacingHorizontal.FACING)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockFacingHorizontal.FACING)));
    }
}
