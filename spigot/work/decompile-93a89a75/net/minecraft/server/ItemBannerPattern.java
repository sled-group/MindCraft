package net.minecraft.server;

public class ItemBannerPattern extends Item {

    private final EnumBannerPatternType a;

    public ItemBannerPattern(EnumBannerPatternType enumbannerpatterntype, Item.Info item_info) {
        super(item_info);
        this.a = enumbannerpatterntype;
    }

    public EnumBannerPatternType b() {
        return this.a;
    }
}
