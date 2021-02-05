package net.minecraft.server;

public class BlockRotatable extends Block {

    public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockProperties.E;

    public BlockRotatable(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) this.getBlockData().set(BlockRotatable.AXIS, EnumDirection.EnumAxis.Y));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        switch (enumblockrotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((EnumDirection.EnumAxis) iblockdata.get(BlockRotatable.AXIS)) {
                    case X:
                        return (IBlockData) iblockdata.set(BlockRotatable.AXIS, EnumDirection.EnumAxis.Z);
                    case Z:
                        return (IBlockData) iblockdata.set(BlockRotatable.AXIS, EnumDirection.EnumAxis.X);
                    default:
                        return iblockdata;
                }
            default:
                return iblockdata;
        }
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockRotatable.AXIS);
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockRotatable.AXIS, blockactioncontext.getClickedFace().k());
    }
}
