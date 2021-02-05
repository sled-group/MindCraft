package net.minecraft.server;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;

public class NonNullList<E> extends AbstractList<E> {

    private final List<E> a;
    private final E b;

    public static <E> NonNullList<E> a() {
        return new NonNullList<>();
    }

    public static <E> NonNullList<E> a(int i, E e0) {
        Validate.notNull(e0);
        Object[] aobject = new Object[i];

        Arrays.fill(aobject, e0);
        return new NonNullList<>(Arrays.asList(aobject), e0);
    }

    @SafeVarargs
    public static <E> NonNullList<E> a(E e0, E... ae) {
        return new NonNullList<>(Arrays.asList(ae), e0);
    }

    protected NonNullList() {
        this(new ArrayList(), (Object) null);
    }

    protected NonNullList(List<E> list, @Nullable E e0) {
        this.a = list;
        this.b = e0;
    }

    @Nonnull
    public E get(int i) {
        return this.a.get(i);
    }

    public E set(int i, E e0) {
        Validate.notNull(e0);
        return this.a.set(i, e0);
    }

    public void add(int i, E e0) {
        Validate.notNull(e0);
        this.a.add(i, e0);
    }

    public E remove(int i) {
        return this.a.remove(i);
    }

    public int size() {
        return this.a.size();
    }

    public void clear() {
        if (this.b == null) {
            super.clear();
        } else {
            for (int i = 0; i < this.size(); ++i) {
                this.set(i, this.b);
            }
        }

    }
}
