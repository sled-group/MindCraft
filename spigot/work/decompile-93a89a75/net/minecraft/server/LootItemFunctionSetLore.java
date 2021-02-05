package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;

public class LootItemFunctionSetLore extends LootItemFunctionConditional {

    private final boolean a;
    private final List<IChatBaseComponent> c;
    @Nullable
    private final LootTableInfo.EntityTarget d;

    public LootItemFunctionSetLore(LootItemCondition[] alootitemcondition, boolean flag, List<IChatBaseComponent> list, @Nullable LootTableInfo.EntityTarget loottableinfo_entitytarget) {
        super(alootitemcondition);
        this.a = flag;
        this.c = ImmutableList.copyOf(list);
        this.d = loottableinfo_entitytarget;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return this.d != null ? ImmutableSet.of(this.d.a()) : ImmutableSet.of();
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        NBTTagList nbttaglist = this.a(itemstack, !this.c.isEmpty());

        if (nbttaglist != null) {
            if (this.a) {
                nbttaglist.clear();
            }

            UnaryOperator<IChatBaseComponent> unaryoperator = LootItemFunctionSetName.a(loottableinfo, this.d);

            this.c.stream().map(unaryoperator).map(IChatBaseComponent.ChatSerializer::a).map(NBTTagString::new).forEach(nbttaglist::add);
        }

        return itemstack;
    }

    @Nullable
    private NBTTagList a(ItemStack itemstack, boolean flag) {
        NBTTagCompound nbttagcompound;

        if (itemstack.hasTag()) {
            nbttagcompound = itemstack.getTag();
        } else {
            if (!flag) {
                return null;
            }

            nbttagcompound = new NBTTagCompound();
            itemstack.setTag(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1;

        if (nbttagcompound.hasKeyOfType("display", 10)) {
            nbttagcompound1 = nbttagcompound.getCompound("display");
        } else {
            if (!flag) {
                return null;
            }

            nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.set("display", nbttagcompound1);
        }

        if (nbttagcompound1.hasKeyOfType("Lore", 9)) {
            return nbttagcompound1.getList("Lore", 8);
        } else if (flag) {
            NBTTagList nbttaglist = new NBTTagList();

            nbttagcompound1.set("Lore", nbttaglist);
            return nbttaglist;
        } else {
            return null;
        }
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionSetLore> {

        public b() {
            super(new MinecraftKey("set_lore"), LootItemFunctionSetLore.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetLore lootitemfunctionsetlore, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetlore, jsonserializationcontext);
            jsonobject.addProperty("replace", lootitemfunctionsetlore.a);
            JsonArray jsonarray = new JsonArray();
            Iterator iterator = lootitemfunctionsetlore.c.iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) iterator.next();

                jsonarray.add(IChatBaseComponent.ChatSerializer.b(ichatbasecomponent));
            }

            jsonobject.add("lore", jsonarray);
            if (lootitemfunctionsetlore.d != null) {
                jsonobject.add("entity", jsonserializationcontext.serialize(lootitemfunctionsetlore.d));
            }

        }

        @Override
        public LootItemFunctionSetLore b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            boolean flag = ChatDeserializer.a(jsonobject, "replace", false);
            List<IChatBaseComponent> list = (List) Streams.stream(ChatDeserializer.u(jsonobject, "lore")).map(IChatBaseComponent.ChatSerializer::a).collect(ImmutableList.toImmutableList());
            LootTableInfo.EntityTarget loottableinfo_entitytarget = (LootTableInfo.EntityTarget) ChatDeserializer.a(jsonobject, "entity", (Object) null, jsondeserializationcontext, LootTableInfo.EntityTarget.class);

            return new LootItemFunctionSetLore(alootitemcondition, flag, list, loottableinfo_entitytarget);
        }
    }
}
