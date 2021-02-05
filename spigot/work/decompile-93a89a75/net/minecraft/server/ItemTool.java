package net.minecraft.server;

import com.google.common.collect.Multimap;
import java.util.Set;

public class ItemTool extends ItemToolMaterial {

    private final Set<Block> a;
    protected final float b;
    protected final float c;
    protected final float d;

    protected ItemTool(float f, float f1, ToolMaterial toolmaterial, Set<Block> set, Item.Info item_info) {
        super(toolmaterial, item_info);
        this.a = set;
        this.b = toolmaterial.b();
        this.c = f + toolmaterial.c();
        this.d = f1;
    }

    @Override
    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        return this.a.contains(iblockdata.getBlock()) ? this.b : 1.0F;
    }

    @Override
    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(2, entityliving1, (entityliving2) -> {
            entityliving2.c(EnumItemSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
        if (!world.isClientSide && iblockdata.f(world, blockposition) != 0.0F) {
            itemstack.damage(1, entityliving, (entityliving1) -> {
                entityliving1.c(EnumItemSlot.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        Multimap<String, AttributeModifier> multimap = super.a(enumitemslot);

        if (enumitemslot == EnumItemSlot.MAINHAND) {
            multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ItemTool.g, "Tool modifier", (double) this.c, AttributeModifier.Operation.ADDITION));
            multimap.put(GenericAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ItemTool.h, "Tool modifier", (double) this.d, AttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }
}
