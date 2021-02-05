package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class LootItemFunctionSetStewEffect extends LootItemFunctionConditional {

    private final Map<MobEffectList, LootValueBounds> a;

    private LootItemFunctionSetStewEffect(LootItemCondition[] alootitemcondition, Map<MobEffectList, LootValueBounds> map) {
        super(alootitemcondition);
        this.a = ImmutableMap.copyOf(map);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.getItem() == Items.SUSPICIOUS_STEW && !this.a.isEmpty()) {
            Random random = loottableinfo.b();
            int i = random.nextInt(this.a.size());
            Entry<MobEffectList, LootValueBounds> entry = (Entry) Iterables.get(this.a.entrySet(), i);
            MobEffectList mobeffectlist = (MobEffectList) entry.getKey();
            int j = ((LootValueBounds) entry.getValue()).a(random);

            if (!mobeffectlist.isInstant()) {
                j *= 20;
            }

            ItemSuspiciousStew.a(itemstack, mobeffectlist, j);
            return itemstack;
        } else {
            return itemstack;
        }
    }

    public static LootItemFunctionSetStewEffect.a b() {
        return new LootItemFunctionSetStewEffect.a();
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionSetStewEffect> {

        public b() {
            super(new MinecraftKey("set_stew_effect"), LootItemFunctionSetStewEffect.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetStewEffect lootitemfunctionsetsteweffect, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetsteweffect, jsonserializationcontext);
            if (!lootitemfunctionsetsteweffect.a.isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                Iterator iterator = lootitemfunctionsetsteweffect.a.keySet().iterator();

                while (iterator.hasNext()) {
                    MobEffectList mobeffectlist = (MobEffectList) iterator.next();
                    JsonObject jsonobject1 = new JsonObject();
                    MinecraftKey minecraftkey = IRegistry.MOB_EFFECT.getKey(mobeffectlist);

                    if (minecraftkey == null) {
                        throw new IllegalArgumentException("Don't know how to serialize mob effect " + mobeffectlist);
                    }

                    jsonobject1.add("type", new JsonPrimitive(minecraftkey.toString()));
                    jsonobject1.add("duration", jsonserializationcontext.serialize(lootitemfunctionsetsteweffect.a.get(mobeffectlist)));
                    jsonarray.add(jsonobject1);
                }

                jsonobject.add("effects", jsonarray);
            }

        }

        @Override
        public LootItemFunctionSetStewEffect b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            Map<MobEffectList, LootValueBounds> map = Maps.newHashMap();

            if (jsonobject.has("effects")) {
                JsonArray jsonarray = ChatDeserializer.u(jsonobject, "effects");
                Iterator iterator = jsonarray.iterator();

                while (iterator.hasNext()) {
                    JsonElement jsonelement = (JsonElement) iterator.next();
                    String s = ChatDeserializer.h(jsonelement.getAsJsonObject(), "type");
                    MobEffectList mobeffectlist = (MobEffectList) IRegistry.MOB_EFFECT.getOptional(new MinecraftKey(s)).orElseThrow(() -> {
                        return new JsonSyntaxException("Unknown mob effect '" + s + "'");
                    });
                    LootValueBounds lootvaluebounds = (LootValueBounds) ChatDeserializer.a(jsonelement.getAsJsonObject(), "duration", jsondeserializationcontext, LootValueBounds.class);

                    map.put(mobeffectlist, lootvaluebounds);
                }
            }

            return new LootItemFunctionSetStewEffect(alootitemcondition, map);
        }
    }

    public static class a extends LootItemFunctionConditional.a<LootItemFunctionSetStewEffect.a> {

        private final Map<MobEffectList, LootValueBounds> a = Maps.newHashMap();

        public a() {}

        @Override
        protected LootItemFunctionSetStewEffect.a d() {
            return this;
        }

        public LootItemFunctionSetStewEffect.a a(MobEffectList mobeffectlist, LootValueBounds lootvaluebounds) {
            this.a.put(mobeffectlist, lootvaluebounds);
            return this;
        }

        @Override
        public LootItemFunction b() {
            return new LootItemFunctionSetStewEffect(this.g(), this.a);
        }
    }
}
