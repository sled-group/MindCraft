package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class LootItemFunctionSetTag extends LootItemFunctionConditional {

    private final NBTTagCompound a;

    private LootItemFunctionSetTag(LootItemCondition[] alootitemcondition, NBTTagCompound nbttagcompound) {
        super(alootitemcondition);
        this.a = nbttagcompound;
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        itemstack.getOrCreateTag().a(this.a);
        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> a(NBTTagCompound nbttagcompound) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionSetTag(alootitemcondition, nbttagcompound);
        });
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionSetTag> {

        public a() {
            super(new MinecraftKey("set_nbt"), LootItemFunctionSetTag.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetTag lootitemfunctionsettag, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsettag, jsonserializationcontext);
            jsonobject.addProperty("tag", lootitemfunctionsettag.a.toString());
        }

        @Override
        public LootItemFunctionSetTag b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            try {
                NBTTagCompound nbttagcompound = MojangsonParser.parse(ChatDeserializer.h(jsonobject, "tag"));

                return new LootItemFunctionSetTag(alootitemcondition, nbttagcompound);
            } catch (CommandSyntaxException commandsyntaxexception) {
                throw new JsonSyntaxException(commandsyntaxexception.getMessage());
            }
        }
    }
}
