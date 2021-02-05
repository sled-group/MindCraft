package net.minecraft.server;

public class ItemHorseArmor extends Item {

    private final int a;
    private final String b;

    public ItemHorseArmor(int i, String s, Item.Info item_info) {
        super(item_info);
        this.a = i;
        this.b = "textures/entity/horse/armor/horse_armor_" + s + ".png";
    }

    public int e() {
        return this.a;
    }
}
