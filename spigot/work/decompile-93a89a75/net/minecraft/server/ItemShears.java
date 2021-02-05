package net.minecraft.server;

public class ItemShears extends Item {

    public ItemShears(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
        if (!world.isClientSide) {
            itemstack.damage(1, entityliving, (entityliving1) -> {
                entityliving1.c(EnumItemSlot.MAINHAND);
            });
        }

        Block block = iblockdata.getBlock();

        return !iblockdata.a(TagsBlock.LEAVES) && block != Blocks.COBWEB && block != Blocks.GRASS && block != Blocks.FERN && block != Blocks.DEAD_BUSH && block != Blocks.VINE && block != Blocks.TRIPWIRE && !block.a(TagsBlock.WOOL) ? super.a(itemstack, world, iblockdata, blockposition, entityliving) : true;
    }

    @Override
    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return block == Blocks.COBWEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE;
    }

    @Override
    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return block != Blocks.COBWEB && !iblockdata.a(TagsBlock.LEAVES) ? (block.a(TagsBlock.WOOL) ? 5.0F : super.getDestroySpeed(itemstack, iblockdata)) : 15.0F;
    }
}
