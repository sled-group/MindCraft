package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Map;

public class ItemHoe extends ItemToolMaterial {

    private final float b;
    protected static final Map<Block, IBlockData> a = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getBlockData(), Blocks.GRASS_PATH, Blocks.FARMLAND.getBlockData(), Blocks.DIRT, Blocks.FARMLAND.getBlockData(), Blocks.COARSE_DIRT, Blocks.DIRT.getBlockData()));

    public ItemHoe(ToolMaterial toolmaterial, float f, Item.Info item_info) {
        super(toolmaterial, item_info);
        this.b = f;
    }

    @Override
    public EnumInteractionResult a(ItemActionContext itemactioncontext) {
        World world = itemactioncontext.getWorld();
        BlockPosition blockposition = itemactioncontext.getClickPosition();

        if (itemactioncontext.getClickedFace() != EnumDirection.DOWN && world.getType(blockposition.up()).isAir()) {
            IBlockData iblockdata = (IBlockData) ItemHoe.a.get(world.getType(blockposition).getBlock());

            if (iblockdata != null) {
                EntityHuman entityhuman = itemactioncontext.getEntity();

                world.playSound(entityhuman, blockposition, SoundEffects.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClientSide) {
                    world.setTypeAndData(blockposition, iblockdata, 11);
                    if (entityhuman != null) {
                        itemactioncontext.getItemStack().damage(1, entityhuman, (entityhuman1) -> {
                            entityhuman1.d(itemactioncontext.n());
                        });
                    }
                }

                return EnumInteractionResult.SUCCESS;
            }
        }

        return EnumInteractionResult.PASS;
    }

    @Override
    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1, (entityliving2) -> {
            entityliving2.c(EnumItemSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        Multimap<String, AttributeModifier> multimap = super.a(enumitemslot);

        if (enumitemslot == EnumItemSlot.MAINHAND) {
            multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ItemHoe.g, "Weapon modifier", 0.0D, AttributeModifier.Operation.ADDITION));
            multimap.put(GenericAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ItemHoe.h, "Weapon modifier", (double) this.b, AttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }
}
