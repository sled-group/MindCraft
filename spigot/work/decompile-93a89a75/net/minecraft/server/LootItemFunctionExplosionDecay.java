package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Random;

public class LootItemFunctionExplosionDecay extends LootItemFunctionConditional {

    private LootItemFunctionExplosionDecay(LootItemCondition[] alootitemcondition) {
        super(alootitemcondition);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        Float ofloat = (Float) loottableinfo.getContextParameter(LootContextParameters.EXPLOSION_RADIUS);

        if (ofloat != null) {
            Random random = loottableinfo.b();
            float f = 1.0F / ofloat;
            int i = itemstack.getCount();
            int j = 0;

            for (int k = 0; k < i; ++k) {
                if (random.nextFloat() <= f) {
                    ++j;
                }
            }

            itemstack.setCount(j);
        }

        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> b() {
        return a(LootItemFunctionExplosionDecay::new);
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionExplosionDecay> {

        protected a() {
            super(new MinecraftKey("explosion_decay"), LootItemFunctionExplosionDecay.class);
        }

        @Override
        public LootItemFunctionExplosionDecay b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            return new LootItemFunctionExplosionDecay(alootitemcondition);
        }
    }
}
