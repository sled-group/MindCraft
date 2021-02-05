package net.minecraft.server;

public class BlockTallPlantShearable extends BlockTallPlant {

    public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> b = BlockTallPlant.HALF;

    public BlockTallPlantShearable(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
        boolean flag = super.a(iblockdata, blockactioncontext);

        return flag && blockactioncontext.getItemStack().getItem() == this.getItem() ? false : flag;
    }
}
