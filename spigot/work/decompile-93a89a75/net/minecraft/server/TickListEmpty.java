package net.minecraft.server;

import java.util.stream.Stream;

public class TickListEmpty<T> implements TickList<T> {

    private static final TickListEmpty<Object> a = new TickListEmpty<>();

    public TickListEmpty() {}

    public static <T> TickListEmpty<T> b() {
        return TickListEmpty.a;
    }

    @Override
    public boolean a(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(BlockPosition blockposition, T t0, int i) {}

    @Override
    public void a(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {}

    @Override
    public boolean b(BlockPosition blockposition, T t0) {
        return false;
    }

    @Override
    public void a(Stream<NextTickListEntry<T>> stream) {}
}
