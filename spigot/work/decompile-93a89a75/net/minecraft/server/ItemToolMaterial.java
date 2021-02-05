package net.minecraft.server;

public class ItemToolMaterial extends Item {

    private final ToolMaterial a;

    public ItemToolMaterial(ToolMaterial toolmaterial, Item.Info item_info) {
        super(item_info.b(toolmaterial.a()));
        this.a = toolmaterial;
    }

    public ToolMaterial e() {
        return this.a;
    }

    @Override
    public int c() {
        return this.a.e();
    }

    @Override
    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.a.f().test(itemstack1) || super.a(itemstack, itemstack1);
    }
}
