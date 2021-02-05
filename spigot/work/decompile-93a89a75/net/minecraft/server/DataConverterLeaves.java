package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List.ListType;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import javax.annotation.Nullable;

public class DataConverterLeaves extends DataFix {

    private static final int[][] a = new int[][]{{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    private static final Object2IntMap<String> b = (Object2IntMap) DataFixUtils.make(new Object2IntOpenHashMap(), (object2intopenhashmap) -> {
        object2intopenhashmap.put("minecraft:acacia_leaves", 0);
        object2intopenhashmap.put("minecraft:birch_leaves", 1);
        object2intopenhashmap.put("minecraft:dark_oak_leaves", 2);
        object2intopenhashmap.put("minecraft:jungle_leaves", 3);
        object2intopenhashmap.put("minecraft:oak_leaves", 4);
        object2intopenhashmap.put("minecraft:spruce_leaves", 5);
    });
    private static final Set<String> c = ImmutableSet.of("minecraft:acacia_bark", "minecraft:birch_bark", "minecraft:dark_oak_bark", "minecraft:jungle_bark", "minecraft:oak_bark", "minecraft:spruce_bark", new String[]{"minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log", "minecraft:stripped_acacia_log", "minecraft:stripped_birch_log", "minecraft:stripped_dark_oak_log", "minecraft:stripped_jungle_log", "minecraft:stripped_oak_log", "minecraft:stripped_spruce_log"});

    public DataConverterLeaves(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.c);
        OpticFinder<?> opticfinder = type.findField("Level");
        OpticFinder<?> opticfinder1 = opticfinder.type().findField("Sections");
        Type<?> type1 = opticfinder1.type();

        if (!(type1 instanceof ListType)) {
            throw new IllegalStateException("Expecting sections to be a list.");
        } else {
            Type<?> type2 = ((ListType) type1).getElement();
            OpticFinder<?> opticfinder2 = DSL.typeFinder(type2);

            return this.fixTypeEverywhereTyped("Leaves fix", type, (typed) -> {
                return typed.updateTyped(opticfinder, (typed1) -> {
                    int[] aint = new int[]{0};
                    Typed<?> typed2 = typed1.updateTyped(opticfinder1, (typed3) -> {
                        Int2ObjectMap<DataConverterLeaves.a> int2objectmap = new Int2ObjectOpenHashMap((Map) typed3.getAllTyped(opticfinder2).stream().map((typed4) -> {
                            return new DataConverterLeaves.a(typed4, this.getInputSchema());
                        }).collect(Collectors.toMap(DataConverterLeaves.b::c, (dataconverterleaves_a) -> {
                            return dataconverterleaves_a;
                        })));

                        if (int2objectmap.values().stream().allMatch(DataConverterLeaves.b::b)) {
                            return typed3;
                        } else {
                            List<IntSet> list = Lists.newArrayList();

                            int i;

                            for (i = 0; i < 7; ++i) {
                                list.add(new IntOpenHashSet());
                            }

                            ObjectIterator objectiterator = int2objectmap.values().iterator();

                            int j;
                            int k;

                            while (objectiterator.hasNext()) {
                                DataConverterLeaves.a dataconverterleaves_a = (DataConverterLeaves.a) objectiterator.next();

                                if (!dataconverterleaves_a.b()) {
                                    for (int l = 0; l < 4096; ++l) {
                                        int i1 = dataconverterleaves_a.c(l);

                                        if (dataconverterleaves_a.a(i1)) {
                                            ((IntSet) list.get(0)).add(dataconverterleaves_a.c() << 12 | l);
                                        } else if (dataconverterleaves_a.b(i1)) {
                                            j = this.a(l);
                                            k = this.c(l);
                                            aint[0] |= a(j == 0, j == 15, k == 0, k == 15);
                                        }
                                    }
                                }
                            }

                            for (i = 1; i < 7; ++i) {
                                IntSet intset = (IntSet) list.get(i - 1);
                                IntSet intset1 = (IntSet) list.get(i);
                                IntIterator intiterator = intset.iterator();

                                while (intiterator.hasNext()) {
                                    j = intiterator.nextInt();
                                    k = this.a(j);
                                    int j1 = this.b(j);
                                    int k1 = this.c(j);
                                    int[][] aint1 = DataConverterLeaves.a;
                                    int l1 = aint1.length;

                                    for (int i2 = 0; i2 < l1; ++i2) {
                                        int[] aint2 = aint1[i2];
                                        int j2 = k + aint2[0];
                                        int k2 = j1 + aint2[1];
                                        int l2 = k1 + aint2[2];

                                        if (j2 >= 0 && j2 <= 15 && l2 >= 0 && l2 <= 15 && k2 >= 0 && k2 <= 255) {
                                            DataConverterLeaves.a dataconverterleaves_a1 = (DataConverterLeaves.a) int2objectmap.get(k2 >> 4);

                                            if (dataconverterleaves_a1 != null && !dataconverterleaves_a1.b()) {
                                                int i3 = a(j2, k2 & 15, l2);
                                                int j3 = dataconverterleaves_a1.c(i3);

                                                if (dataconverterleaves_a1.b(j3)) {
                                                    int k3 = dataconverterleaves_a1.d(j3);

                                                    if (k3 > i) {
                                                        dataconverterleaves_a1.a(i3, j3, i);
                                                        intset1.add(a(j2, k2, l2));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            return typed3.updateTyped(opticfinder2, (typed4) -> {
                                return ((DataConverterLeaves.a) int2objectmap.get(((Dynamic) typed4.get(DSL.remainderFinder())).get("Y").asInt(0))).a(typed4);
                            });
                        }
                    });

                    if (aint[0] != 0) {
                        typed2 = typed2.update(DSL.remainderFinder(), (dynamic) -> {
                            Dynamic<?> dynamic1 = (Dynamic) DataFixUtils.orElse(dynamic.get("UpgradeData").get(), dynamic.emptyMap());

                            return dynamic.set("UpgradeData", dynamic1.set("Sides", dynamic.createByte((byte) (dynamic1.get("Sides").asByte((byte) 0) | aint[0]))));
                        });
                    }

                    return typed2;
                });
            });
        }
    }

    public static int a(int i, int j, int k) {
        return j << 8 | k << 4 | i;
    }

    private int a(int i) {
        return i & 15;
    }

    private int b(int i) {
        return i >> 8 & 255;
    }

    private int c(int i) {
        return i >> 4 & 15;
    }

    public static int a(boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        int i = 0;

        if (flag2) {
            if (flag1) {
                i |= 2;
            } else if (flag) {
                i |= 128;
            } else {
                i |= 1;
            }
        } else if (flag3) {
            if (flag) {
                i |= 32;
            } else if (flag1) {
                i |= 8;
            } else {
                i |= 16;
            }
        } else if (flag1) {
            i |= 4;
        } else if (flag) {
            i |= 64;
        }

        return i;
    }

    public static final class a extends DataConverterLeaves.b {

        @Nullable
        private IntSet e;
        @Nullable
        private IntSet f;
        @Nullable
        private Int2IntMap g;

        public a(Typed<?> typed, Schema schema) {
            super(typed, schema);
        }

        @Override
        protected boolean a() {
            this.e = new IntOpenHashSet();
            this.f = new IntOpenHashSet();
            this.g = new Int2IntOpenHashMap();

            for (int i = 0; i < this.b.size(); ++i) {
                Dynamic<?> dynamic = (Dynamic) this.b.get(i);
                String s = dynamic.get("Name").asString("");

                if (DataConverterLeaves.b.containsKey(s)) {
                    boolean flag = Objects.equals(dynamic.get("Properties").get("decayable").asString(""), "false");

                    this.e.add(i);
                    this.g.put(this.a(s, flag, 7), i);
                    this.b.set(i, this.a(dynamic, s, flag, 7));
                }

                if (DataConverterLeaves.c.contains(s)) {
                    this.f.add(i);
                }
            }

            return this.e.isEmpty() && this.f.isEmpty();
        }

        private Dynamic<?> a(Dynamic<?> dynamic, String s, boolean flag, int i) {
            Dynamic<?> dynamic1 = dynamic.emptyMap();

            dynamic1 = dynamic1.set("persistent", dynamic1.createString(flag ? "true" : "false"));
            dynamic1 = dynamic1.set("distance", dynamic1.createString(Integer.toString(i)));
            Dynamic<?> dynamic2 = dynamic.emptyMap();

            dynamic2 = dynamic2.set("Properties", dynamic1);
            dynamic2 = dynamic2.set("Name", dynamic2.createString(s));
            return dynamic2;
        }

        public boolean a(int i) {
            return this.f.contains(i);
        }

        public boolean b(int i) {
            return this.e.contains(i);
        }

        private int d(int i) {
            return this.a(i) ? 0 : Integer.parseInt(((Dynamic) this.b.get(i)).get("Properties").get("distance").asString(""));
        }

        private void a(int i, int j, int k) {
            Dynamic<?> dynamic = (Dynamic) this.b.get(j);
            String s = dynamic.get("Name").asString("");
            boolean flag = Objects.equals(dynamic.get("Properties").get("persistent").asString(""), "true");
            int l = this.a(s, flag, k);
            int i1;

            if (!this.g.containsKey(l)) {
                i1 = this.b.size();
                this.e.add(i1);
                this.g.put(l, i1);
                this.b.add(this.a(dynamic, s, flag, k));
            }

            i1 = this.g.get(l);
            if (1 << this.d.c() <= i1) {
                DataBits databits = new DataBits(this.d.c() + 1, 4096);

                for (int j1 = 0; j1 < 4096; ++j1) {
                    databits.b(j1, this.d.a(j1));
                }

                this.d = databits;
            }

            this.d.b(i, i1);
        }
    }

    public abstract static class b {

        private final Type<Pair<String, Dynamic<?>>> e;
        protected final OpticFinder<List<Pair<String, Dynamic<?>>>> a;
        protected final List<Dynamic<?>> b;
        protected final int c;
        @Nullable
        protected DataBits d;

        public b(Typed<?> typed, Schema schema) {
            this.e = DSL.named(DataConverterTypes.m.typeName(), DSL.remainderType());
            this.a = DSL.fieldFinder("Palette", DSL.list(this.e));
            if (!Objects.equals(schema.getType(DataConverterTypes.m), this.e)) {
                throw new IllegalStateException("Block state type is not what was expected.");
            } else {
                Optional<List<Pair<String, Dynamic<?>>>> optional = typed.getOptional(this.a);

                this.b = (List) optional.map((list) -> {
                    return (List) list.stream().map(Pair::getSecond).collect(Collectors.toList());
                }).orElse(ImmutableList.of());
                Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());

                this.c = dynamic.get("Y").asInt(0);
                this.a(dynamic);
            }
        }

        protected void a(Dynamic<?> dynamic) {
            if (this.a()) {
                this.d = null;
            } else {
                long[] along = ((LongStream) dynamic.get("BlockStates").asLongStreamOpt().get()).toArray();
                int i = Math.max(4, DataFixUtils.ceillog2(this.b.size()));

                this.d = new DataBits(i, 4096, along);
            }

        }

        public Typed<?> a(Typed<?> typed) {
            return this.b() ? typed : typed.update(DSL.remainderFinder(), (dynamic) -> {
                return dynamic.set("BlockStates", dynamic.createLongList(Arrays.stream(this.d.a())));
            }).set(this.a, this.b.stream().map((dynamic) -> {
                return Pair.of(DataConverterTypes.m.typeName(), dynamic);
            }).collect(Collectors.toList()));
        }

        public boolean b() {
            return this.d == null;
        }

        public int c(int i) {
            return this.d.a(i);
        }

        protected int a(String s, boolean flag, int i) {
            return DataConverterLeaves.b.get(s) << 5 | (flag ? 16 : 0) | i;
        }

        int c() {
            return this.c;
        }

        protected abstract boolean a();
    }
}
