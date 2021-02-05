package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.authlib.GameProfile;
import java.util.Set;

public class LootItemFunctionFillPlayerHead extends LootItemFunctionConditional {

    private final LootTableInfo.EntityTarget a;

    public LootItemFunctionFillPlayerHead(LootItemCondition[] alootitemcondition, LootTableInfo.EntityTarget loottableinfo_entitytarget) {
        super(alootitemcondition);
        this.a = loottableinfo_entitytarget;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(this.a.a());
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.getItem() == Items.PLAYER_HEAD) {
            Entity entity = (Entity) loottableinfo.getContextParameter(this.a.a());

            if (entity instanceof EntityHuman) {
                GameProfile gameprofile = ((EntityHuman) entity).getProfile();

                itemstack.getOrCreateTag().set("SkullOwner", GameProfileSerializer.serialize(new NBTTagCompound(), gameprofile));
            }
        }

        return itemstack;
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionFillPlayerHead> {

        public a() {
            super(new MinecraftKey("fill_player_head"), LootItemFunctionFillPlayerHead.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionFillPlayerHead lootitemfunctionfillplayerhead, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionfillplayerhead, jsonserializationcontext);
            jsonobject.add("entity", jsonserializationcontext.serialize(lootitemfunctionfillplayerhead.a));
        }

        @Override
        public LootItemFunctionFillPlayerHead b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootTableInfo.EntityTarget loottableinfo_entitytarget = (LootTableInfo.EntityTarget) ChatDeserializer.a(jsonobject, "entity", jsondeserializationcontext, LootTableInfo.EntityTarget.class);

            return new LootItemFunctionFillPlayerHead(alootitemcondition, loottableinfo_entitytarget);
        }
    }
}
