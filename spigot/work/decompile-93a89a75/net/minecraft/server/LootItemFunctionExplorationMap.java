package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Locale;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootItemFunctionExplorationMap extends LootItemFunctionConditional {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final MapIcon.Type a = MapIcon.Type.MANSION;
    private final String d;
    private final MapIcon.Type e;
    private final byte f;
    private final int g;
    private final boolean h;

    private LootItemFunctionExplorationMap(LootItemCondition[] alootitemcondition, String s, MapIcon.Type mapicon_type, byte b0, int i, boolean flag) {
        super(alootitemcondition);
        this.d = s;
        this.e = mapicon_type;
        this.f = b0;
        this.g = i;
        this.h = flag;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.POSITION);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.getItem() != Items.MAP) {
            return itemstack;
        } else {
            BlockPosition blockposition = (BlockPosition) loottableinfo.getContextParameter(LootContextParameters.POSITION);

            if (blockposition != null) {
                WorldServer worldserver = loottableinfo.d();
                BlockPosition blockposition1 = worldserver.a(this.d, blockposition, this.g, this.h);

                if (blockposition1 != null) {
                    ItemStack itemstack1 = ItemWorldMap.createFilledMapView(worldserver, blockposition1.getX(), blockposition1.getZ(), this.f, true, true);

                    ItemWorldMap.applySepiaFilter(worldserver, itemstack1);
                    WorldMap.decorateMap(itemstack1, blockposition1, "+", this.e);
                    itemstack1.a((IChatBaseComponent) (new ChatMessage("filled_map." + this.d.toLowerCase(Locale.ROOT), new Object[0])));
                    return itemstack1;
                }
            }

            return itemstack;
        }
    }

    public static LootItemFunctionExplorationMap.a b() {
        return new LootItemFunctionExplorationMap.a();
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionExplorationMap> {

        protected b() {
            super(new MinecraftKey("exploration_map"), LootItemFunctionExplorationMap.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionExplorationMap lootitemfunctionexplorationmap, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionexplorationmap, jsonserializationcontext);
            if (!lootitemfunctionexplorationmap.d.equals("Buried_Treasure")) {
                jsonobject.add("destination", jsonserializationcontext.serialize(lootitemfunctionexplorationmap.d));
            }

            if (lootitemfunctionexplorationmap.e != LootItemFunctionExplorationMap.a) {
                jsonobject.add("decoration", jsonserializationcontext.serialize(lootitemfunctionexplorationmap.e.toString().toLowerCase(Locale.ROOT)));
            }

            if (lootitemfunctionexplorationmap.f != 2) {
                jsonobject.addProperty("zoom", lootitemfunctionexplorationmap.f);
            }

            if (lootitemfunctionexplorationmap.g != 50) {
                jsonobject.addProperty("search_radius", lootitemfunctionexplorationmap.g);
            }

            if (!lootitemfunctionexplorationmap.h) {
                jsonobject.addProperty("skip_existing_chunks", lootitemfunctionexplorationmap.h);
            }

        }

        @Override
        public LootItemFunctionExplorationMap b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            String s = jsonobject.has("destination") ? ChatDeserializer.h(jsonobject, "destination") : "Buried_Treasure";

            s = WorldGenerator.aP.containsKey(s.toLowerCase(Locale.ROOT)) ? s : "Buried_Treasure";
            String s1 = jsonobject.has("decoration") ? ChatDeserializer.h(jsonobject, "decoration") : "mansion";
            MapIcon.Type mapicon_type = LootItemFunctionExplorationMap.a;

            try {
                mapicon_type = MapIcon.Type.valueOf(s1.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException illegalargumentexception) {
                LootItemFunctionExplorationMap.LOGGER.error("Error while parsing loot table decoration entry. Found {}. Defaulting to " + LootItemFunctionExplorationMap.a, s1);
            }

            byte b0 = ChatDeserializer.a(jsonobject, "zoom", (byte) 2);
            int i = ChatDeserializer.a(jsonobject, "search_radius", (int) 50);
            boolean flag = ChatDeserializer.a(jsonobject, "skip_existing_chunks", true);

            return new LootItemFunctionExplorationMap(alootitemcondition, s, mapicon_type, b0, i, flag);
        }
    }

    public static class a extends LootItemFunctionConditional.a<LootItemFunctionExplorationMap.a> {

        private String a = "Buried_Treasure";
        private MapIcon.Type b;
        private byte c;
        private int d;
        private boolean e;

        public a() {
            this.b = LootItemFunctionExplorationMap.a;
            this.c = 2;
            this.d = 50;
            this.e = true;
        }

        @Override
        protected LootItemFunctionExplorationMap.a d() {
            return this;
        }

        public LootItemFunctionExplorationMap.a a(String s) {
            this.a = s;
            return this;
        }

        public LootItemFunctionExplorationMap.a a(MapIcon.Type mapicon_type) {
            this.b = mapicon_type;
            return this;
        }

        public LootItemFunctionExplorationMap.a a(byte b0) {
            this.c = b0;
            return this;
        }

        public LootItemFunctionExplorationMap.a a(boolean flag) {
            this.e = flag;
            return this;
        }

        @Override
        public LootItemFunction b() {
            return new LootItemFunctionExplorationMap(this.g(), this.a, this.b, this.c, this.d, this.e);
        }
    }
}
