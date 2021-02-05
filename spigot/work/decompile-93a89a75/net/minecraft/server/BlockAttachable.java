package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockAttachable extends BlockFacingHorizontal {

    public static final BlockStateEnum<BlockPropertyAttachPosition> FACE = BlockProperties.O;

    protected BlockAttachable(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return b(iworldreader, blockposition, j(iblockdata).opposite());
    }

    public static boolean b(IWorldReader iworldreader, BlockPosition blockposition, EnumDirection enumdirection) {
        BlockPosition blockposition1 = blockposition.shift(enumdirection);

        return iworldreader.getType(blockposition1).d(iworldreader, blockposition1, enumdirection.opposite());
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        EnumDirection[] aenumdirection = blockactioncontext.e();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];
            IBlockData iblockdata;

            if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
                iblockdata = (IBlockData) ((IBlockData) this.getBlockData().set(BlockAttachable.FACE, enumdirection == EnumDirection.UP ? BlockPropertyAttachPosition.CEILING : BlockPropertyAttachPosition.FLOOR)).set(BlockAttachable.FACING, blockactioncontext.f());
            } else {
                iblockdata = (IBlockData) ((IBlockData) this.getBlockData().set(BlockAttachable.FACE, BlockPropertyAttachPosition.WALL)).set(BlockAttachable.FACING, enumdirection.opposite());
            }

            if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
                return iblockdata;
            }
        }

        return null;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return j(iblockdata).opposite() == enumdirection && !iblockdata.canPlace(generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    protected static EnumDirection j(IBlockData iblockdata) {
        switch ((BlockPropertyAttachPosition) iblockdata.get(BlockAttachable.FACE)) {
            case CEILING:
                return EnumDirection.DOWN;
            case FLOOR:
                return EnumDirection.UP;
            default:
                return (EnumDirection) iblockdata.get(BlockAttachable.FACING);
        }
    }
}
