package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class LootSelectorLootTable extends LootSelectorEntry {

    private final MinecraftKey c;

    private LootSelectorLootTable(MinecraftKey minecraftkey, int i, int j, LootItemCondition[] alootitemcondition, LootItemFunction[] alootitemfunction) {
        super(i, j, alootitemcondition, alootitemfunction);
        this.c = minecraftkey;
    }

    @Override
    public void a(Consumer<ItemStack> consumer, LootTableInfo loottableinfo) {
        LootTable loottable = loottableinfo.a().getLootTable(this.c);

        loottable.populateLootDirect(loottableinfo, consumer);
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        if (set.contains(this.c)) {
            lootcollector.a("Table " + this.c + " is recursively called");
        } else {
            super.a(lootcollector, function, set, lootcontextparameterset);
            LootTable loottable = (LootTable) function.apply(this.c);

            if (loottable == null) {
                lootcollector.a("Unknown loot table called " + this.c);
            } else {
                Set<MinecraftKey> set1 = ImmutableSet.builder().addAll(set).add(this.c).build();

                loottable.a(lootcollector.b("->{" + this.c + "}"), function, set1, lootcontextparameterset);
            }

        }
    }

    public static LootSelectorEntry.a<?> a(MinecraftKey minecraftkey) {
        return a((i, j, alootitemcondition, alootitemfunction) -> {
            return new LootSelectorLootTable(minecraftkey, i, j, alootitemcondition, alootitemfunction);
        });
    }

    public static class a extends LootSelectorEntry.e<LootSelectorLootTable> {

        public a() {
            super(new MinecraftKey("loot_table"), LootSelectorLootTable.class);
        }

        public void a(JsonObject jsonobject, LootSelectorLootTable lootselectorloottable, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootSelectorEntry) lootselectorloottable, jsonserializationcontext);
            jsonobject.addProperty("name", lootselectorloottable.c.toString());
        }

        @Override
        protected LootSelectorLootTable b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, int i, int j, LootItemCondition[] alootitemcondition, LootItemFunction[] alootitemfunction) {
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "name"));

            return new LootSelectorLootTable(minecraftkey, i, j, alootitemcondition, alootitemfunction);
        }
    }
}
