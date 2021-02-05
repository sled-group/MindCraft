package net.minecraft.server;

import com.google.common.collect.Multimap;

public class ItemSword extends ItemToolMaterial {

    private final float a;
    private final float b;

    public ItemSword(ToolMaterial toolmaterial, int i, float f, Item.Info item_info) {
        super(toolmaterial, item_info);
        this.b = f;
        this.a = (float) i + toolmaterial.c();
    }

    public float d() {
        return this.a;
    }

    @Override
    public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
        return !entityhuman.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        if (block == Blocks.COBWEB) {
            return 15.0F;
        } else {
            Material material = iblockdata.getMaterial();

            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !iblockdata.a(TagsBlock.LEAVES) && material != Material.PUMPKIN ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1, (entityliving2) -> {
            entityliving2.c(EnumItemSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
        if (iblockdata.f(world, blockposition) != 0.0F) {
            itemstack.damage(2, entityliving, (entityliving1) -> {
                entityliving1.c(EnumItemSlot.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
        return iblockdata.getBlock() == Blocks.COBWEB;
    }

    @Override
    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        Multimap<String, AttributeModifier> multimap = super.a(enumitemslot);

        if (enumitemslot == EnumItemSlot.MAINHAND) {
            multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ItemSword.g, "Weapon modifier", (double) this.a, AttributeModifier.Operation.ADDITION));
            multimap.put(GenericAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ItemSword.h, "Weapon modifier", (double) this.b, AttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }
}
