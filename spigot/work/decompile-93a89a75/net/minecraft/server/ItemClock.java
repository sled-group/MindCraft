package net.minecraft.server;

public class ItemClock extends Item {

    public ItemClock(Item.Info item_info) {
        super(item_info);
        this.a(new MinecraftKey("time"), new IDynamicTexture() {
        });
    }
}
