package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class LootItemFunctionCopyNBT extends LootItemFunctionConditional {

    private final LootItemFunctionCopyNBT.Source a;
    private final List<LootItemFunctionCopyNBT.b> c;
    private static final Function<Entity, NBTBase> d = CriterionConditionNBT::b;
    private static final Function<TileEntity, NBTBase> e = (tileentity) -> {
        return tileentity.save(new NBTTagCompound());
    };

    private LootItemFunctionCopyNBT(LootItemCondition[] alootitemcondition, LootItemFunctionCopyNBT.Source lootitemfunctioncopynbt_source, List<LootItemFunctionCopyNBT.b> list) {
        super(alootitemcondition);
        this.a = lootitemfunctioncopynbt_source;
        this.c = ImmutableList.copyOf(list);
    }

    private static ArgumentNBTKey.h b(String s) {
        try {
            return (new ArgumentNBTKey()).parse(new StringReader(s));
        } catch (CommandSyntaxException commandsyntaxexception) {
            throw new IllegalArgumentException("Failed to parse path " + s, commandsyntaxexception);
        }
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(this.a.f);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        NBTBase nbtbase = (NBTBase) this.a.g.apply(loottableinfo);

        if (nbtbase != null) {
            this.c.forEach((lootitemfunctioncopynbt_b) -> {
                lootitemfunctioncopynbt_b.a(itemstack::getOrCreateTag, nbtbase);
            });
        }

        return itemstack;
    }

    public static LootItemFunctionCopyNBT.a a(LootItemFunctionCopyNBT.Source lootitemfunctioncopynbt_source) {
        return new LootItemFunctionCopyNBT.a(lootitemfunctioncopynbt_source);
    }

    public static class e extends LootItemFunctionConditional.c<LootItemFunctionCopyNBT> {

        public e() {
            super(new MinecraftKey("copy_nbt"), LootItemFunctionCopyNBT.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionCopyNBT lootitemfunctioncopynbt, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctioncopynbt, jsonserializationcontext);
            jsonobject.addProperty("source", lootitemfunctioncopynbt.a.e);
            JsonArray jsonarray = new JsonArray();

            lootitemfunctioncopynbt.c.stream().map(LootItemFunctionCopyNBT.b::a).forEach(jsonarray::add);
            jsonobject.add("ops", jsonarray);
        }

        @Override
        public LootItemFunctionCopyNBT b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootItemFunctionCopyNBT.Source lootitemfunctioncopynbt_source = LootItemFunctionCopyNBT.Source.a(ChatDeserializer.h(jsonobject, "source"));
            List<LootItemFunctionCopyNBT.b> list = Lists.newArrayList();
            JsonArray jsonarray = ChatDeserializer.u(jsonobject, "ops");
            Iterator iterator = jsonarray.iterator();

            while (iterator.hasNext()) {
                JsonElement jsonelement = (JsonElement) iterator.next();
                JsonObject jsonobject1 = ChatDeserializer.m(jsonelement, "op");

                list.add(LootItemFunctionCopyNBT.b.a(jsonobject1));
            }

            return new LootItemFunctionCopyNBT(alootitemcondition, lootitemfunctioncopynbt_source, list);
        }
    }

    public static enum Source {

        THIS("this", LootContextParameters.THIS_ENTITY, LootItemFunctionCopyNBT.d), KILLER("killer", LootContextParameters.KILLER_ENTITY, LootItemFunctionCopyNBT.d), KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER, LootItemFunctionCopyNBT.d), BLOCK_ENTITY("block_entity", LootContextParameters.BLOCK_ENTITY, LootItemFunctionCopyNBT.e);

        public final String e;
        public final LootContextParameter<?> f;
        public final Function<LootTableInfo, NBTBase> g;

        private <T> Source(String s, LootContextParameter<T> lootcontextparameter, Function<? super T, NBTBase> function) {
            this.e = s;
            this.f = lootcontextparameter;
            this.g = (loottableinfo) -> {
                T t0 = loottableinfo.getContextParameter(lootcontextparameter);

                return t0 != null ? (NBTBase) function.apply(t0) : null;
            };
        }

        public static LootItemFunctionCopyNBT.Source a(String s) {
            LootItemFunctionCopyNBT.Source[] alootitemfunctioncopynbt_source = values();
            int i = alootitemfunctioncopynbt_source.length;

            for (int j = 0; j < i; ++j) {
                LootItemFunctionCopyNBT.Source lootitemfunctioncopynbt_source = alootitemfunctioncopynbt_source[j];

                if (lootitemfunctioncopynbt_source.e.equals(s)) {
                    return lootitemfunctioncopynbt_source;
                }
            }

            throw new IllegalArgumentException("Invalid tag source " + s);
        }
    }

    public static enum Action {

        REPLACE("replace") {
            @Override
            public void a(NBTBase nbtbase, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException {
                NBTBase nbtbase1 = (NBTBase) Iterables.getLast(list);

                argumentnbtkey_h.b(nbtbase, nbtbase1::clone);
            }
        },
        APPEND("append") {
            @Override
            public void a(NBTBase nbtbase, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException {
                List<NBTBase> list1 = argumentnbtkey_h.a(nbtbase, NBTTagList::new);

                list1.forEach((nbtbase1) -> {
                    if (nbtbase1 instanceof NBTTagList) {
                        list.forEach((nbtbase2) -> {
                            ((NBTTagList) nbtbase1).add(nbtbase2.clone());
                        });
                    }

                });
            }
        },
        MERGE("merge") {
            @Override
            public void a(NBTBase nbtbase, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException {
                List<NBTBase> list1 = argumentnbtkey_h.a(nbtbase, NBTTagCompound::new);

                list1.forEach((nbtbase1) -> {
                    if (nbtbase1 instanceof NBTTagCompound) {
                        list.forEach((nbtbase2) -> {
                            if (nbtbase2 instanceof NBTTagCompound) {
                                ((NBTTagCompound) nbtbase1).a((NBTTagCompound) nbtbase2);
                            }

                        });
                    }

                });
            }
        };

        private final String d;

        public abstract void a(NBTBase nbtbase, ArgumentNBTKey.h argumentnbtkey_h, List<NBTBase> list) throws CommandSyntaxException;

        private Action(String s) {
            this.d = s;
        }

        public static LootItemFunctionCopyNBT.Action a(String s) {
            LootItemFunctionCopyNBT.Action[] alootitemfunctioncopynbt_action = values();
            int i = alootitemfunctioncopynbt_action.length;

            for (int j = 0; j < i; ++j) {
                LootItemFunctionCopyNBT.Action lootitemfunctioncopynbt_action = alootitemfunctioncopynbt_action[j];

                if (lootitemfunctioncopynbt_action.d.equals(s)) {
                    return lootitemfunctioncopynbt_action;
                }
            }

            throw new IllegalArgumentException("Invalid merge strategy" + s);
        }
    }

    public static class a extends LootItemFunctionConditional.a<LootItemFunctionCopyNBT.a> {

        private final LootItemFunctionCopyNBT.Source a;
        private final List<LootItemFunctionCopyNBT.b> b;

        private a(LootItemFunctionCopyNBT.Source lootitemfunctioncopynbt_source) {
            this.b = Lists.newArrayList();
            this.a = lootitemfunctioncopynbt_source;
        }

        public LootItemFunctionCopyNBT.a a(String s, String s1, LootItemFunctionCopyNBT.Action lootitemfunctioncopynbt_action) {
            this.b.add(new LootItemFunctionCopyNBT.b(s, s1, lootitemfunctioncopynbt_action));
            return this;
        }

        public LootItemFunctionCopyNBT.a a(String s, String s1) {
            return this.a(s, s1, LootItemFunctionCopyNBT.Action.REPLACE);
        }

        @Override
        protected LootItemFunctionCopyNBT.a d() {
            return this;
        }

        @Override
        public LootItemFunction b() {
            return new LootItemFunctionCopyNBT(this.g(), this.a, this.b);
        }
    }

    static class b {

        private final String a;
        private final ArgumentNBTKey.h b;
        private final String c;
        private final ArgumentNBTKey.h d;
        private final LootItemFunctionCopyNBT.Action e;

        private b(String s, String s1, LootItemFunctionCopyNBT.Action lootitemfunctioncopynbt_action) {
            this.a = s;
            this.b = LootItemFunctionCopyNBT.b(s);
            this.c = s1;
            this.d = LootItemFunctionCopyNBT.b(s1);
            this.e = lootitemfunctioncopynbt_action;
        }

        public void a(Supplier<NBTBase> supplier, NBTBase nbtbase) {
            try {
                List<NBTBase> list = this.b.a(nbtbase);

                if (!list.isEmpty()) {
                    this.e.a((NBTBase) supplier.get(), this.d, list);
                }
            } catch (CommandSyntaxException commandsyntaxexception) {
                ;
            }

        }

        public JsonObject a() {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("source", this.a);
            jsonobject.addProperty("target", this.c);
            jsonobject.addProperty("op", this.e.d);
            return jsonobject;
        }

        public static LootItemFunctionCopyNBT.b a(JsonObject jsonobject) {
            String s = ChatDeserializer.h(jsonobject, "source");
            String s1 = ChatDeserializer.h(jsonobject, "target");
            LootItemFunctionCopyNBT.Action lootitemfunctioncopynbt_action = LootItemFunctionCopyNBT.Action.a(ChatDeserializer.h(jsonobject, "op"));

            return new LootItemFunctionCopyNBT.b(s, s1, lootitemfunctioncopynbt_action);
        }
    }
}
