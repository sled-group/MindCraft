package net.minecraft.server;

import org.apache.commons.lang3.Validate;

public class ItemBanner extends ItemBlockWallable {

    public ItemBanner(Block block, Block block1, Item.Info item_info) {
        super(block, block1, item_info);
        Validate.isInstanceOf(BlockBannerAbstract.class, block);
        Validate.isInstanceOf(BlockBannerAbstract.class, block1);
    }

    public EnumColor b() {
        return ((BlockBannerAbstract) this.getBlock()).getColor();
    }
}
