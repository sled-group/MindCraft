package net.minecraft.server;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class BlockStateList<O, S extends IBlockDataHolder<S>> {

    private static final Pattern a = Pattern.compile("^[a-z0-9_]+$");
    private final O b;
    private final ImmutableSortedMap<String, IBlockState<?>> c;
    private final ImmutableList<S> d;

    protected <A extends BlockDataAbstract<O, S>> BlockStateList(O o0, BlockStateList.b<O, S, A> blockstatelist_b, Map<String, IBlockState<?>> map) {
        this.b = o0;
        this.c = ImmutableSortedMap.copyOf(map);
        Map<Map<IBlockState<?>, Comparable<?>>, A> map1 = Maps.newLinkedHashMap();
        List<A> list = Lists.newArrayList();
        Stream<List<Comparable<?>>> stream = Stream.of(Collections.emptyList());

        IBlockState iblockstate;

        for (UnmodifiableIterator unmodifiableiterator = this.c.values().iterator(); unmodifiableiterator.hasNext();stream = stream.flatMap((list1) -> {
            return iblockstate.getValues().stream().map((comparable) -> {
                List<Comparable<?>> list2 = Lists.newArrayList(list1);

                list2.add(comparable);
                return list2;
            });
        })) {
            iblockstate = (IBlockState) unmodifiableiterator.next();
        }

        stream.forEach((list1) -> {
            Map<IBlockState<?>, Comparable<?>> map2 = MapGeneratorUtils.b(this.c.values(), list1);
            A a0 = blockstatelist_b.create(o0, ImmutableMap.copyOf(map2));

            map1.put(map2, a0);
            list.add(a0);
        });
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            A a0 = (BlockDataAbstract) iterator.next();

            a0.a((Map) map1);
        }

        this.d = ImmutableList.copyOf(list);
    }

    public ImmutableList<S> a() {
        return this.d;
    }

    public S getBlockData() {
        return (IBlockDataHolder) this.d.get(0);
    }

    public O getBlock() {
        return this.b;
    }

    public Collection<IBlockState<?>> d() {
        return this.c.values();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("block", this.b).add("properties", this.c.values().stream().map(IBlockState::a).collect(Collectors.toList())).toString();
    }

    @Nullable
    public IBlockState<?> a(String s) {
        return (IBlockState) this.c.get(s);
    }

    public static class a<O, S extends IBlockDataHolder<S>> {

        private final O a;
        private final Map<String, IBlockState<?>> b = Maps.newHashMap();

        public a(O o0) {
            this.a = o0;
        }

        public BlockStateList.a<O, S> a(IBlockState<?>... aiblockstate) {
            IBlockState[] aiblockstate1 = aiblockstate;
            int i = aiblockstate.length;

            for (int j = 0; j < i; ++j) {
                IBlockState<?> iblockstate = aiblockstate1[j];

                this.a(iblockstate);
                this.b.put(iblockstate.a(), iblockstate);
            }

            return this;
        }

        private <T extends Comparable<T>> void a(IBlockState<T> iblockstate) {
            String s = iblockstate.a();

            if (!BlockStateList.a.matcher(s).matches()) {
                throw new IllegalArgumentException(this.a + " has invalidly named property: " + s);
            } else {
                Collection<T> collection = iblockstate.getValues();

                if (collection.size() <= 1) {
                    throw new IllegalArgumentException(this.a + " attempted use property " + s + " with <= 1 possible values");
                } else {
                    Iterator iterator = collection.iterator();

                    String s1;

                    do {
                        if (!iterator.hasNext()) {
                            if (this.b.containsKey(s)) {
                                throw new IllegalArgumentException(this.a + " has duplicate property: " + s);
                            }

                            return;
                        }

                        T t0 = (Comparable) iterator.next();

                        s1 = iblockstate.a(t0);
                    } while (BlockStateList.a.matcher(s1).matches());

                    throw new IllegalArgumentException(this.a + " has property: " + s + " with invalidly named value: " + s1);
                }
            }
        }

        public <A extends BlockDataAbstract<O, S>> BlockStateList<O, S> a(BlockStateList.b<O, S, A> blockstatelist_b) {
            return new BlockStateList<>(this.a, blockstatelist_b, this.b);
        }
    }

    public interface b<O, S extends IBlockDataHolder<S>, A extends BlockDataAbstract<O, S>> {

        A create(O o0, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap);
    }
}
