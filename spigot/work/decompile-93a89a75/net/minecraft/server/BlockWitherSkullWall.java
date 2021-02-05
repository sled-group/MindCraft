package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockWitherSkullWall extends BlockSkullWall {

    protected BlockWitherSkullWall(Block.Info block_info) {
        super(BlockSkull.Type.WITHER_SKELETON, block_info);
    }

    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
        Blocks.WITHER_SKELETON_SKULL.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
    }
}
