package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;

public class LootItemFunctionCopyName extends LootItemFunctionConditional {

    private final LootItemFunctionCopyName.Source a;

    private LootItemFunctionCopyName(LootItemCondition[] alootitemcondition, LootItemFunctionCopyName.Source lootitemfunctioncopyname_source) {
        super(alootitemcondition);
        this.a = lootitemfunctioncopyname_source;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(this.a.f);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        Object object = loottableinfo.getContextParameter(this.a.f);

        if (object instanceof INamableTileEntity) {
            INamableTileEntity inamabletileentity = (INamableTileEntity) object;

            if (inamabletileentity.hasCustomName()) {
                itemstack.a(inamabletileentity.getScoreboardDisplayName());
            }
        }

        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> a(LootItemFunctionCopyName.Source lootitemfunctioncopyname_source) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionCopyName(alootitemcondition, lootitemfunctioncopyname_source);
        });
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionCopyName> {

        public b() {
            super(new MinecraftKey("copy_name"), LootItemFunctionCopyName.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionCopyName lootitemfunctioncopyname, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctioncopyname, jsonserializationcontext);
            jsonobject.addProperty("source", lootitemfunctioncopyname.a.e);
        }

        @Override
        public LootItemFunctionCopyName b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootItemFunctionCopyName.Source lootitemfunctioncopyname_source = LootItemFunctionCopyName.Source.a(ChatDeserializer.h(jsonobject, "source"));

            return new LootItemFunctionCopyName(alootitemcondition, lootitemfunctioncopyname_source);
        }
    }

    public static enum Source {

        THIS("this", LootContextParameters.THIS_ENTITY), KILLER("killer", LootContextParameters.KILLER_ENTITY), KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER), BLOCK_ENTITY("block_entity", LootContextParameters.BLOCK_ENTITY);

        public final String e;
        public final LootContextParameter<?> f;

        private Source(String s, LootContextParameter lootcontextparameter) {
            this.e = s;
            this.f = lootcontextparameter;
        }

        public static LootItemFunctionCopyName.Source a(String s) {
            LootItemFunctionCopyName.Source[] alootitemfunctioncopyname_source = values();
            int i = alootitemfunctioncopyname_source.length;

            for (int j = 0; j < i; ++j) {
                LootItemFunctionCopyName.Source lootitemfunctioncopyname_source = alootitemfunctioncopyname_source[j];

                if (lootitemfunctioncopyname_source.e.equals(s)) {
                    return lootitemfunctioncopyname_source;
                }
            }

            throw new IllegalArgumentException("Invalid name source " + s);
        }
    }
}
