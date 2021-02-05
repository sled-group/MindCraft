package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class LootItemConditionBlockStateProperty implements LootItemCondition {

    private final Block a;
    private final Map<IBlockState<?>, Object> b;
    private final Predicate<IBlockData> c;

    private LootItemConditionBlockStateProperty(Block block, Map<IBlockState<?>, Object> map) {
        this.a = block;
        this.b = ImmutableMap.copyOf(map);
        this.c = a(block, map);
    }

    private static Predicate<IBlockData> a(Block block, Map<IBlockState<?>, Object> map) {
        int i = map.size();

        if (i == 0) {
            return (iblockdata) -> {
                return iblockdata.getBlock() == block;
            };
        } else if (i == 1) {
            Entry<IBlockState<?>, Object> entry = (Entry) map.entrySet().iterator().next();
            IBlockState<?> iblockstate = (IBlockState) entry.getKey();
            Object object = entry.getValue();

            return (iblockdata) -> {
                return iblockdata.getBlock() == block && object.equals(iblockdata.get(iblockstate));
            };
        } else {
            Predicate<IBlockData> predicate = (iblockdata) -> {
                return iblockdata.getBlock() == block;
            };

            IBlockState iblockstate1;
            Object object1;

            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();predicate = predicate.and((iblockdata) -> {
                return object1.equals(iblockdata.get(iblockstate1));
            })) {
                Entry<IBlockState<?>, Object> entry1 = (Entry) iterator.next();

                iblockstate1 = (IBlockState) entry1.getKey();
                object1 = entry1.getValue();
            }

            return predicate;
        }
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.BLOCK_STATE);
    }

    public boolean test(LootTableInfo loottableinfo) {
        IBlockData iblockdata = (IBlockData) loottableinfo.getContextParameter(LootContextParameters.BLOCK_STATE);

        return iblockdata != null && this.c.test(iblockdata);
    }

    public static LootItemConditionBlockStateProperty.a a(Block block) {
        return new LootItemConditionBlockStateProperty.a(block);
    }

    public static class b extends LootItemCondition.b<LootItemConditionBlockStateProperty> {

        private static <T extends Comparable<T>> String a(IBlockState<T> iblockstate, Object object) {
            return iblockstate.a((Comparable) object);
        }

        protected b() {
            super(new MinecraftKey("block_state_property"), LootItemConditionBlockStateProperty.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionBlockStateProperty lootitemconditionblockstateproperty, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("block", IRegistry.BLOCK.getKey(lootitemconditionblockstateproperty.a).toString());
            JsonObject jsonobject1 = new JsonObject();

            lootitemconditionblockstateproperty.b.forEach((iblockstate, object) -> {
                jsonobject1.addProperty(iblockstate.a(), a(iblockstate, object));
            });
            jsonobject.add("properties", jsonobject1);
        }

        @Override
        public LootItemConditionBlockStateProperty b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "block"));
            Block block = (Block) IRegistry.BLOCK.getOptional(minecraftkey).orElseThrow(() -> {
                return new IllegalArgumentException("Can't find block " + minecraftkey);
            });
            BlockStateList<Block, IBlockData> blockstatelist = block.getStates();
            Map<IBlockState<?>, Object> map = Maps.newHashMap();

            if (jsonobject.has("properties")) {
                JsonObject jsonobject1 = ChatDeserializer.t(jsonobject, "properties");

                jsonobject1.entrySet().forEach((entry) -> {
                    String s = (String) entry.getKey();
                    IBlockState<?> iblockstate = blockstatelist.a(s);

                    if (iblockstate == null) {
                        throw new IllegalArgumentException("Block " + IRegistry.BLOCK.getKey(block) + " does not have property '" + s + "'");
                    } else {
                        String s1 = ChatDeserializer.a((JsonElement) entry.getValue(), "value");
                        Object object = iblockstate.b(s1).orElseThrow(() -> {
                            return new IllegalArgumentException("Block " + IRegistry.BLOCK.getKey(block) + " property '" + s + "' does not have value '" + s1 + "'");
                        });

                        map.put(iblockstate, object);
                    }
                });
            }

            return new LootItemConditionBlockStateProperty(block, map);
        }
    }

    public static class a implements LootItemCondition.a {

        private final Block a;
        private final Set<IBlockState<?>> b;
        private final Map<IBlockState<?>, Object> c = Maps.newHashMap();

        public a(Block block) {
            this.a = block;
            this.b = Sets.newIdentityHashSet();
            this.b.addAll(block.getStates().d());
        }

        public <T extends Comparable<T>> LootItemConditionBlockStateProperty.a a(IBlockState<T> iblockstate, T t0) {
            if (!this.b.contains(iblockstate)) {
                throw new IllegalArgumentException("Block " + IRegistry.BLOCK.getKey(this.a) + " does not have property '" + iblockstate + "'");
            } else if (!iblockstate.getValues().contains(t0)) {
                throw new IllegalArgumentException("Block " + IRegistry.BLOCK.getKey(this.a) + " property '" + iblockstate + "' does not have value '" + t0 + "'");
            } else {
                this.c.put(iblockstate, t0);
                return this;
            }
        }

        @Override
        public LootItemCondition build() {
            return new LootItemConditionBlockStateProperty(this.a, this.c);
        }
    }
}
