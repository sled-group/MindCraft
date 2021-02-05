package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class AdvancementRewards {

    public static final AdvancementRewards a = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], CustomFunction.a.a);
    private final int b;
    private final MinecraftKey[] c;
    private final MinecraftKey[] d;
    private final CustomFunction.a e;

    public AdvancementRewards(int i, MinecraftKey[] aminecraftkey, MinecraftKey[] aminecraftkey1, CustomFunction.a customfunction_a) {
        this.b = i;
        this.c = aminecraftkey;
        this.d = aminecraftkey1;
        this.e = customfunction_a;
    }

    public void a(EntityPlayer entityplayer) {
        entityplayer.giveExp(this.b);
        LootTableInfo loottableinfo = (new LootTableInfo.Builder(entityplayer.getWorldServer())).set(LootContextParameters.THIS_ENTITY, entityplayer).set(LootContextParameters.POSITION, new BlockPosition(entityplayer)).a(entityplayer.getRandom()).build(LootContextParameterSets.ADVANCEMENT_REWARD);
        boolean flag = false;
        MinecraftKey[] aminecraftkey = this.c;
        int i = aminecraftkey.length;

        for (int j = 0; j < i; ++j) {
            MinecraftKey minecraftkey = aminecraftkey[j];
            Iterator iterator = entityplayer.server.getLootTableRegistry().getLootTable(minecraftkey).populateLoot(loottableinfo).iterator();

            while (iterator.hasNext()) {
                ItemStack itemstack = (ItemStack) iterator.next();

                if (entityplayer.g(itemstack)) {
                    entityplayer.world.playSound((EntityHuman) null, entityplayer.locX, entityplayer.locY, entityplayer.locZ, SoundEffects.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((entityplayer.getRandom().nextFloat() - entityplayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    flag = true;
                } else {
                    EntityItem entityitem = entityplayer.drop(itemstack, false);

                    if (entityitem != null) {
                        entityitem.o();
                        entityitem.setOwner(entityplayer.getUniqueID());
                    }
                }
            }
        }

        if (flag) {
            entityplayer.defaultContainer.c();
        }

        if (this.d.length > 0) {
            entityplayer.a(this.d);
        }

        MinecraftServer minecraftserver = entityplayer.server;

        this.e.a(minecraftserver.getFunctionData()).ifPresent((customfunction) -> {
            minecraftserver.getFunctionData().a(customfunction, entityplayer.getCommandListener().a().a(2));
        });
    }

    public String toString() {
        return "AdvancementRewards{experience=" + this.b + ", loot=" + Arrays.toString(this.c) + ", recipes=" + Arrays.toString(this.d) + ", function=" + this.e + '}';
    }

    public JsonElement b() {
        if (this == AdvancementRewards.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            if (this.b != 0) {
                jsonobject.addProperty("experience", this.b);
            }

            JsonArray jsonarray;
            MinecraftKey[] aminecraftkey;
            int i;
            MinecraftKey minecraftkey;
            int j;

            if (this.c.length > 0) {
                jsonarray = new JsonArray();
                aminecraftkey = this.c;
                i = aminecraftkey.length;

                for (j = 0; j < i; ++j) {
                    minecraftkey = aminecraftkey[j];
                    jsonarray.add(minecraftkey.toString());
                }

                jsonobject.add("loot", jsonarray);
            }

            if (this.d.length > 0) {
                jsonarray = new JsonArray();
                aminecraftkey = this.d;
                i = aminecraftkey.length;

                for (j = 0; j < i; ++j) {
                    minecraftkey = aminecraftkey[j];
                    jsonarray.add(minecraftkey.toString());
                }

                jsonobject.add("recipes", jsonarray);
            }

            if (this.e.a() != null) {
                jsonobject.addProperty("function", this.e.a().toString());
            }

            return jsonobject;
        }
    }

    public static class a {

        private int a;
        private final List<MinecraftKey> b = Lists.newArrayList();
        private final List<MinecraftKey> c = Lists.newArrayList();
        @Nullable
        private MinecraftKey d;

        public a() {}

        public static AdvancementRewards.a a(int i) {
            return (new AdvancementRewards.a()).b(i);
        }

        public AdvancementRewards.a b(int i) {
            this.a += i;
            return this;
        }

        public static AdvancementRewards.a c(MinecraftKey minecraftkey) {
            return (new AdvancementRewards.a()).d(minecraftkey);
        }

        public AdvancementRewards.a d(MinecraftKey minecraftkey) {
            this.c.add(minecraftkey);
            return this;
        }

        public AdvancementRewards a() {
            return new AdvancementRewards(this.a, (MinecraftKey[]) this.b.toArray(new MinecraftKey[0]), (MinecraftKey[]) this.c.toArray(new MinecraftKey[0]), this.d == null ? CustomFunction.a.a : new CustomFunction.a(this.d));
        }
    }

    public static class b implements JsonDeserializer<AdvancementRewards> {

        public b() {}

        public AdvancementRewards deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "rewards");
            int i = ChatDeserializer.a(jsonobject, "experience", (int) 0);
            JsonArray jsonarray = ChatDeserializer.a(jsonobject, "loot", new JsonArray());
            MinecraftKey[] aminecraftkey = new MinecraftKey[jsonarray.size()];

            for (int j = 0; j < aminecraftkey.length; ++j) {
                aminecraftkey[j] = new MinecraftKey(ChatDeserializer.a(jsonarray.get(j), "loot[" + j + "]"));
            }

            JsonArray jsonarray1 = ChatDeserializer.a(jsonobject, "recipes", new JsonArray());
            MinecraftKey[] aminecraftkey1 = new MinecraftKey[jsonarray1.size()];

            for (int k = 0; k < aminecraftkey1.length; ++k) {
                aminecraftkey1[k] = new MinecraftKey(ChatDeserializer.a(jsonarray1.get(k), "recipes[" + k + "]"));
            }

            CustomFunction.a customfunction_a;

            if (jsonobject.has("function")) {
                customfunction_a = new CustomFunction.a(new MinecraftKey(ChatDeserializer.h(jsonobject, "function")));
            } else {
                customfunction_a = CustomFunction.a.a;
            }

            return new AdvancementRewards(i, aminecraftkey, aminecraftkey1, customfunction_a);
        }
    }
}
