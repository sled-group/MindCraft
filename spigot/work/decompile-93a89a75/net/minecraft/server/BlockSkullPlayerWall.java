package net.minecraft.server;

import java.util.List;
import javax.annotation.Nullable;

public class BlockSkullPlayerWall extends BlockSkullWall {

    protected BlockSkullPlayerWall(Block.Info block_info) {
        super(BlockSkull.Type.PLAYER, block_info);
    }

    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
        Blocks.PLAYER_HEAD.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
    }

    @Override
    public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
        return Blocks.PLAYER_HEAD.a(iblockdata, loottableinfo_builder);
    }
}
