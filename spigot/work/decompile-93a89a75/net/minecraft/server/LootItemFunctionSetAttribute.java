package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;

public class LootItemFunctionSetAttribute extends LootItemFunctionConditional {

    private final List<LootItemFunctionSetAttribute.b> a;

    private LootItemFunctionSetAttribute(LootItemCondition[] alootitemcondition, List<LootItemFunctionSetAttribute.b> list) {
        super(alootitemcondition);
        this.a = ImmutableList.copyOf(list);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        Random random = loottableinfo.b();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            LootItemFunctionSetAttribute.b lootitemfunctionsetattribute_b = (LootItemFunctionSetAttribute.b) iterator.next();
            UUID uuid = lootitemfunctionsetattribute_b.e;

            if (uuid == null) {
                uuid = UUID.randomUUID();
            }

            EnumItemSlot enumitemslot = lootitemfunctionsetattribute_b.f[random.nextInt(lootitemfunctionsetattribute_b.f.length)];

            itemstack.a(lootitemfunctionsetattribute_b.b, new AttributeModifier(uuid, lootitemfunctionsetattribute_b.a, (double) lootitemfunctionsetattribute_b.d.b(random), lootitemfunctionsetattribute_b.c), enumitemslot);
        }

        return itemstack;
    }

    static class b {

        private final String a;
        private final String b;
        private final AttributeModifier.Operation c;
        private final LootValueBounds d;
        @Nullable
        private final UUID e;
        private final EnumItemSlot[] f;

        private b(String s, String s1, AttributeModifier.Operation attributemodifier_operation, LootValueBounds lootvaluebounds, EnumItemSlot[] aenumitemslot, @Nullable UUID uuid) {
            this.a = s;
            this.b = s1;
            this.c = attributemodifier_operation;
            this.d = lootvaluebounds;
            this.e = uuid;
            this.f = aenumitemslot;
        }

        public JsonObject a(JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("name", this.a);
            jsonobject.addProperty("attribute", this.b);
            jsonobject.addProperty("operation", a(this.c));
            jsonobject.add("amount", jsonserializationcontext.serialize(this.d));
            if (this.e != null) {
                jsonobject.addProperty("id", this.e.toString());
            }

            if (this.f.length == 1) {
                jsonobject.addProperty("slot", this.f[0].getSlotName());
            } else {
                JsonArray jsonarray = new JsonArray();
                EnumItemSlot[] aenumitemslot = this.f;
                int i = aenumitemslot.length;

                for (int j = 0; j < i; ++j) {
                    EnumItemSlot enumitemslot = aenumitemslot[j];

                    jsonarray.add(new JsonPrimitive(enumitemslot.getSlotName()));
                }

                jsonobject.add("slot", jsonarray);
            }

            return jsonobject;
        }

        public static LootItemFunctionSetAttribute.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            String s = ChatDeserializer.h(jsonobject, "name");
            String s1 = ChatDeserializer.h(jsonobject, "attribute");
            AttributeModifier.Operation attributemodifier_operation = a(ChatDeserializer.h(jsonobject, "operation"));
            LootValueBounds lootvaluebounds = (LootValueBounds) ChatDeserializer.a(jsonobject, "amount", jsondeserializationcontext, LootValueBounds.class);
            UUID uuid = null;
            EnumItemSlot[] aenumitemslot;

            if (ChatDeserializer.a(jsonobject, "slot")) {
                aenumitemslot = new EnumItemSlot[]{EnumItemSlot.fromName(ChatDeserializer.h(jsonobject, "slot"))};
            } else {
                if (!ChatDeserializer.d(jsonobject, "slot")) {
                    throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
                }

                JsonArray jsonarray = ChatDeserializer.u(jsonobject, "slot");

                aenumitemslot = new EnumItemSlot[jsonarray.size()];
                int i = 0;

                JsonElement jsonelement;

                for (Iterator iterator = jsonarray.iterator(); iterator.hasNext(); aenumitemslot[i++] = EnumItemSlot.fromName(ChatDeserializer.a(jsonelement, "slot"))) {
                    jsonelement = (JsonElement) iterator.next();
                }

                if (aenumitemslot.length == 0) {
                    throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
                }
            }

            if (jsonobject.has("id")) {
                String s2 = ChatDeserializer.h(jsonobject, "id");

                try {
                    uuid = UUID.fromString(s2);
                } catch (IllegalArgumentException illegalargumentexception) {
                    throw new JsonSyntaxException("Invalid attribute modifier id '" + s2 + "' (must be UUID format, with dashes)");
                }
            }

            return new LootItemFunctionSetAttribute.b(s, s1, attributemodifier_operation, lootvaluebounds, aenumitemslot, uuid);
        }

        private static String a(AttributeModifier.Operation attributemodifier_operation) {
            switch (attributemodifier_operation) {
                case ADDITION:
                    return "addition";
                case MULTIPLY_BASE:
                    return "multiply_base";
                case MULTIPLY_TOTAL:
                    return "multiply_total";
                default:
                    throw new IllegalArgumentException("Unknown operation " + attributemodifier_operation);
            }
        }

        private static AttributeModifier.Operation a(String s) {
            byte b0 = -1;

            switch (s.hashCode()) {
                case -1226589444:
                    if (s.equals("addition")) {
                        b0 = 0;
                    }
                    break;
                case -78229492:
                    if (s.equals("multiply_base")) {
                        b0 = 1;
                    }
                    break;
                case 1886894441:
                    if (s.equals("multiply_total")) {
                        b0 = 2;
                    }
            }

            switch (b0) {
                case 0:
                    return AttributeModifier.Operation.ADDITION;
                case 1:
                    return AttributeModifier.Operation.MULTIPLY_BASE;
                case 2:
                    return AttributeModifier.Operation.MULTIPLY_TOTAL;
                default:
                    throw new JsonSyntaxException("Unknown attribute modifier operation " + s);
            }
        }
    }

    public static class d extends LootItemFunctionConditional.c<LootItemFunctionSetAttribute> {

        public d() {
            super(new MinecraftKey("set_attributes"), LootItemFunctionSetAttribute.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetAttribute lootitemfunctionsetattribute, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetattribute, jsonserializationcontext);
            JsonArray jsonarray = new JsonArray();
            Iterator iterator = lootitemfunctionsetattribute.a.iterator();

            while (iterator.hasNext()) {
                LootItemFunctionSetAttribute.b lootitemfunctionsetattribute_b = (LootItemFunctionSetAttribute.b) iterator.next();

                jsonarray.add(lootitemfunctionsetattribute_b.a(jsonserializationcontext));
            }

            jsonobject.add("modifiers", jsonarray);
        }

        @Override
        public LootItemFunctionSetAttribute b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            JsonArray jsonarray = ChatDeserializer.u(jsonobject, "modifiers");
            List<LootItemFunctionSetAttribute.b> list = Lists.newArrayListWithExpectedSize(jsonarray.size());
            Iterator iterator = jsonarray.iterator();

            while (iterator.hasNext()) {
                JsonElement jsonelement = (JsonElement) iterator.next();

                list.add(LootItemFunctionSetAttribute.b.a(ChatDeserializer.m(jsonelement, "modifier"), jsondeserializationcontext));
            }

            if (list.isEmpty()) {
                throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
            } else {
                return new LootItemFunctionSetAttribute(alootitemcondition, list);
            }
        }
    }
}
