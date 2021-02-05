package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockLantern extends Block {

    public static final BlockStateBoolean a = BlockProperties.j;
    protected static final VoxelShape b = VoxelShapes.a(Block.a(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), Block.a(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
    protected static final VoxelShape c = VoxelShapes.a(Block.a(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.a(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));

    public BlockLantern(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockLantern.a, false));
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        EnumDirection[] aenumdirection = blockactioncontext.e();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
                IBlockData iblockdata = (IBlockData) this.getBlockData().set(BlockLantern.a, enumdirection == EnumDirection.UP);

                if (iblockdata.canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
                    return iblockdata;
                }
            }
        }

        return null;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return (Boolean) iblockdata.get(BlockLantern.a) ? BlockLantern.c : BlockLantern.b;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockLantern.a);
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        EnumDirection enumdirection = j(iblockdata).opposite();

        return Block.a(iworldreader, blockposition.shift(enumdirection), enumdirection.opposite());
    }

    protected static EnumDirection j(IBlockData iblockdata) {
        return (Boolean) iblockdata.get(BlockLantern.a) ? EnumDirection.DOWN : EnumDirection.UP;
    }

    @Override
    public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
        return EnumPistonReaction.DESTROY;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return j(iblockdata).opposite() == enumdirection && !iblockdata.canPlace(generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
