package net.minecraft.server;

public class ItemCompass extends Item {

    public ItemCompass(Item.Info item_info) {
        super(item_info);
        this.a(new MinecraftKey("angle"), new IDynamicTexture() {
        });
    }
}
