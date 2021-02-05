package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootItemFunctionSmelt extends LootItemFunctionConditional {

    private static final Logger LOGGER = LogManager.getLogger();

    private LootItemFunctionSmelt(LootItemCondition[] alootitemcondition) {
        super(alootitemcondition);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.isEmpty()) {
            return itemstack;
        } else {
            Optional<FurnaceRecipe> optional = loottableinfo.d().getCraftingManager().craft(Recipes.SMELTING, new InventorySubcontainer(new ItemStack[]{itemstack}), loottableinfo.d());

            if (optional.isPresent()) {
                ItemStack itemstack1 = ((FurnaceRecipe) optional.get()).c();

                if (!itemstack1.isEmpty()) {
                    ItemStack itemstack2 = itemstack1.cloneItemStack();

                    itemstack2.setCount(itemstack.getCount());
                    return itemstack2;
                }
            }

            LootItemFunctionSmelt.LOGGER.warn("Couldn't smelt {} because there is no smelting recipe", itemstack);
            return itemstack;
        }
    }

    public static LootItemFunctionConditional.a<?> b() {
        return a(LootItemFunctionSmelt::new);
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionSmelt> {

        protected a() {
            super(new MinecraftKey("furnace_smelt"), LootItemFunctionSmelt.class);
        }

        @Override
        public LootItemFunctionSmelt b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            return new LootItemFunctionSmelt(alootitemcondition);
        }
    }
}
