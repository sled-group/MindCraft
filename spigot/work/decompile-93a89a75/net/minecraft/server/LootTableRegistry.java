package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootTableRegistry extends ResourceDataJson {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson b = (new GsonBuilder()).registerTypeAdapter(LootValueBounds.class, new LootValueBounds.a()).registerTypeAdapter(LootValueBinomial.class, new LootValueBinomial.a()).registerTypeAdapter(LootValueConstant.class, new LootValueConstant.a()).registerTypeAdapter(LootIntegerLimit.class, new LootIntegerLimit.a()).registerTypeAdapter(LootSelector.class, new LootSelector.b()).registerTypeAdapter(LootTable.class, new LootTable.b()).registerTypeHierarchyAdapter(LootEntryAbstract.class, new LootEntries.a()).registerTypeHierarchyAdapter(LootItemFunction.class, new LootItemFunctions.a()).registerTypeHierarchyAdapter(LootItemCondition.class, new LootItemConditions.a()).registerTypeHierarchyAdapter(LootTableInfo.EntityTarget.class, new LootTableInfo.EntityTarget.a()).create();
    private Map<MinecraftKey, LootTable> c = ImmutableMap.of();

    public LootTableRegistry() {
        super(LootTableRegistry.b, "loot_tables");
    }

    public LootTable getLootTable(MinecraftKey minecraftkey) {
        return (LootTable) this.c.getOrDefault(minecraftkey, LootTable.a);
    }

    protected void a(Map<MinecraftKey, JsonObject> map, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller) {
        Builder<MinecraftKey, LootTable> builder = ImmutableMap.builder();
        JsonObject jsonobject = (JsonObject) map.remove(LootTables.a);

        if (jsonobject != null) {
            LootTableRegistry.LOGGER.warn("Datapack tried to redefine {} loot table, ignoring", LootTables.a);
        }

        map.forEach((minecraftkey, jsonobject1) -> {
            try {
                LootTable loottable = (LootTable) LootTableRegistry.b.fromJson(jsonobject1, LootTable.class);

                builder.put(minecraftkey, loottable);
            } catch (Exception exception) {
                LootTableRegistry.LOGGER.error("Couldn't parse loot table {}", minecraftkey, exception);
            }

        });
        builder.put(LootTables.a, LootTable.a);
        ImmutableMap<MinecraftKey, LootTable> immutablemap = builder.build();
        LootCollector lootcollector = new LootCollector();

        immutablemap.forEach((minecraftkey, loottable) -> {
            a(lootcollector, minecraftkey, loottable, immutablemap::get);
        });
        lootcollector.a().forEach((s, s1) -> {
            LootTableRegistry.LOGGER.warn("Found validation problem in " + s + ": " + s1);
        });
        this.c = immutablemap;
    }

    public static void a(LootCollector lootcollector, MinecraftKey minecraftkey, LootTable loottable, Function<MinecraftKey, LootTable> function) {
        Set<MinecraftKey> set = ImmutableSet.of(minecraftkey);

        loottable.a(lootcollector.b("{" + minecraftkey.toString() + "}"), function, set, loottable.getLootContextParameterSet());
    }

    public static JsonElement a(LootTable loottable) {
        return LootTableRegistry.b.toJsonTree(loottable);
    }

    public Set<MinecraftKey> a() {
        return this.c.keySet();
    }
}
