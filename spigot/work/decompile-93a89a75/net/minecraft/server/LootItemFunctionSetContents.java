package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class LootItemFunctionSetContents extends LootItemFunctionConditional {

    private final List<LootEntryAbstract> a;

    private LootItemFunctionSetContents(LootItemCondition[] alootitemcondition, List<LootEntryAbstract> list) {
        super(alootitemcondition);
        this.a = ImmutableList.copyOf(list);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.isEmpty()) {
            return itemstack;
        } else {
            NonNullList<ItemStack> nonnulllist = NonNullList.a();

            this.a.forEach((lootentryabstract) -> {
                lootentryabstract.expand(loottableinfo, (lootentry) -> {
                    nonnulllist.getClass();
                    lootentry.a(LootTable.a(nonnulllist::add), loottableinfo);
                });
            });
            NBTTagCompound nbttagcompound = new NBTTagCompound();

            ContainerUtil.a(nbttagcompound, nonnulllist);
            NBTTagCompound nbttagcompound1 = itemstack.getOrCreateTag();

            nbttagcompound1.set("BlockEntityTag", nbttagcompound.a(nbttagcompound1.getCompound("BlockEntityTag")));
            return itemstack;
        }
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        super.a(lootcollector, function, set, lootcontextparameterset);

        for (int i = 0; i < this.a.size(); ++i) {
            ((LootEntryAbstract) this.a.get(i)).a(lootcollector.b(".entry[" + i + "]"), function, set, lootcontextparameterset);
        }

    }

    public static LootItemFunctionSetContents.a b() {
        return new LootItemFunctionSetContents.a();
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionSetContents> {

        protected b() {
            super(new MinecraftKey("set_contents"), LootItemFunctionSetContents.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetContents lootitemfunctionsetcontents, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetcontents, jsonserializationcontext);
            jsonobject.add("entries", jsonserializationcontext.serialize(lootitemfunctionsetcontents.a));
        }

        @Override
        public LootItemFunctionSetContents b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootEntryAbstract[] alootentryabstract = (LootEntryAbstract[]) ChatDeserializer.a(jsonobject, "entries", jsondeserializationcontext, LootEntryAbstract[].class);

            return new LootItemFunctionSetContents(alootitemcondition, Arrays.asList(alootentryabstract));
        }
    }

    public static class a extends LootItemFunctionConditional.a<LootItemFunctionSetContents.a> {

        private final List<LootEntryAbstract> a = Lists.newArrayList();

        public a() {}

        @Override
        protected LootItemFunctionSetContents.a d() {
            return this;
        }

        public LootItemFunctionSetContents.a a(LootEntryAbstract.a<?> lootentryabstract_a) {
            this.a.add(lootentryabstract_a.b());
            return this;
        }

        @Override
        public LootItemFunction b() {
            return new LootItemFunctionSetContents(this.g(), this.a);
        }
    }
}
