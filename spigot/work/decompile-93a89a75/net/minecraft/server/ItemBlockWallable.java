package net.minecraft.server;

import java.util.Map;
import javax.annotation.Nullable;

public class ItemBlockWallable extends ItemBlock {

    public final Block wallBlock;

    public ItemBlockWallable(Block block, Block block1, Item.Info item_info) {
        super(block, item_info);
        this.wallBlock = block1;
    }

    @Nullable
    @Override
    protected IBlockData c(BlockActionContext blockactioncontext) {
        IBlockData iblockdata = this.wallBlock.getPlacedState(blockactioncontext);
        IBlockData iblockdata1 = null;
        World world = blockactioncontext.getWorld();
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        EnumDirection[] aenumdirection = blockactioncontext.e();
        int i = aenumdirection.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection enumdirection = aenumdirection[j];

            if (enumdirection != EnumDirection.UP) {
                IBlockData iblockdata2 = enumdirection == EnumDirection.DOWN ? this.getBlock().getPlacedState(blockactioncontext) : iblockdata;

                if (iblockdata2 != null && iblockdata2.canPlace(world, blockposition)) {
                    iblockdata1 = iblockdata2;
                    break;
                }
            }
        }

        return iblockdata1 != null && world.a(iblockdata1, blockposition, VoxelShapeCollision.a()) ? iblockdata1 : null;
    }

    @Override
    public void a(Map<Block, Item> map, Item item) {
        super.a(map, item);
        map.put(this.wallBlock, item);
    }
}
