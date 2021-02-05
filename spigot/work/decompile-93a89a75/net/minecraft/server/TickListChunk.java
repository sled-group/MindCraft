package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class TickListChunk<T> implements TickList<T> {

    private final Set<NextTickListEntry<T>> a;
    private final Function<T, MinecraftKey> b;

    public TickListChunk(Function<T, MinecraftKey> function, List<NextTickListEntry<T>> list) {
        this(function, (Set) Sets.newHashSet(list));
    }

    private TickListChunk(Function<T, MinecraftKey> function, Set<NextTickListEntry<T>> set) {
        this.a = set;
        this.b = function;
    }

    @Override
    public boolean a(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {
        this.a.add(new NextTickListEntry<>(blockposition, t0, (long) i, ticklistpriority));
    }

    @Override
    public boolean b(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(Stream<NextTickListEntry<T>> stream) {
        Set set = this.a;

        this.a.getClass();
        stream.forEach(set::add);
    }

    public Stream<NextTickListEntry<T>> b() {
        return this.a.stream();
    }

    public NBTTagList a(long i) {
        return TickListServer.a(this.b, this.a, i);
    }

    public static <T> TickListChunk<T> a(NBTTagList nbttaglist, Function<T, MinecraftKey> function, Function<MinecraftKey, T> function1) {
        Set<NextTickListEntry<T>> set = Sets.newHashSet();

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
            T t0 = function1.apply(new MinecraftKey(nbttagcompound.getString("i")));

            if (t0 != null) {
                set.add(new NextTickListEntry<>(new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z")), t0, (long) nbttagcompound.getInt("t"), TickListPriority.a(nbttagcompound.getInt("p"))));
            }
        }

        return new TickListChunk<>(function, set);
    }
}
