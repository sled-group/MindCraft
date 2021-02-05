package net.minecraft.server;

import com.google.common.base.Joiner;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;

public abstract class CriterionConditionEntityType {

    public static final CriterionConditionEntityType a = new CriterionConditionEntityType() {
        @Override
        public boolean a(EntityTypes<?> entitytypes) {
            return true;
        }

        @Override
        public JsonElement a() {
            return JsonNull.INSTANCE;
        }
    };
    private static final Joiner b = Joiner.on(", ");

    public CriterionConditionEntityType() {}

    public abstract boolean a(EntityTypes<?> entitytypes);

    public abstract JsonElement a();

    public static CriterionConditionEntityType a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            String s = ChatDeserializer.a(jsonelement, "type");
            MinecraftKey minecraftkey;

            if (s.startsWith("#")) {
                minecraftkey = new MinecraftKey(s.substring(1));
                Tag<EntityTypes<?>> tag = TagsEntity.a().b(minecraftkey);

                return new CriterionConditionEntityType.a(tag);
            } else {
                minecraftkey = new MinecraftKey(s);
                EntityTypes<?> entitytypes = (EntityTypes) IRegistry.ENTITY_TYPE.getOptional(minecraftkey).orElseThrow(() -> {
                    return new JsonSyntaxException("Unknown entity type '" + minecraftkey + "', valid types are: " + CriterionConditionEntityType.b.join(IRegistry.ENTITY_TYPE.keySet()));
                });

                return new CriterionConditionEntityType.b(entitytypes);
            }
        } else {
            return CriterionConditionEntityType.a;
        }
    }

    public static CriterionConditionEntityType b(EntityTypes<?> entitytypes) {
        return new CriterionConditionEntityType.b(entitytypes);
    }

    public static CriterionConditionEntityType a(Tag<EntityTypes<?>> tag) {
        return new CriterionConditionEntityType.a(tag);
    }

    static class a extends CriterionConditionEntityType {

        private final Tag<EntityTypes<?>> b;

        public a(Tag<EntityTypes<?>> tag) {
            this.b = tag;
        }

        @Override
        public boolean a(EntityTypes<?> entitytypes) {
            return this.b.isTagged(entitytypes);
        }

        @Override
        public JsonElement a() {
            return new JsonPrimitive("#" + this.b.c().toString());
        }
    }

    static class b extends CriterionConditionEntityType {

        private final EntityTypes<?> b;

        public b(EntityTypes<?> entitytypes) {
            this.b = entitytypes;
        }

        @Override
        public boolean a(EntityTypes<?> entitytypes) {
            return this.b == entitytypes;
        }

        @Override
        public JsonElement a() {
            return new JsonPrimitive(IRegistry.ENTITY_TYPE.getKey(this.b).toString());
        }
    }
}
