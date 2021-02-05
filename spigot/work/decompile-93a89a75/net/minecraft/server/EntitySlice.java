package net.minecraft.server;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySlice<T> extends AbstractCollection<T> {

    private final Map<Class<?>, List<T>> a = Maps.newHashMap();
    private final Class<T> b;
    private final List<T> c = Lists.newArrayList();

    public EntitySlice(Class<T> oclass) {
        this.b = oclass;
        this.a.put(oclass, this.c);
    }

    public boolean add(T t0) {
        boolean flag = false;
        Iterator iterator = this.a.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Class<?>, List<T>> entry = (Entry) iterator.next();

            if (((Class) entry.getKey()).isInstance(t0)) {
                flag |= ((List) entry.getValue()).add(t0);
            }
        }

        return flag;
    }

    public boolean remove(Object object) {
        boolean flag = false;
        Iterator iterator = this.a.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<Class<?>, List<T>> entry = (Entry) iterator.next();

            if (((Class) entry.getKey()).isInstance(object)) {
                List<T> list = (List) entry.getValue();

                flag |= list.remove(object);
            }
        }

        return flag;
    }

    public boolean contains(Object object) {
        return this.a(object.getClass()).contains(object);
    }

    public <S> Collection<S> a(Class<S> oclass) {
        if (!this.b.isAssignableFrom(oclass)) {
            throw new IllegalArgumentException("Don't know how to search for " + oclass);
        } else {
            List<T> list = (List) this.a.computeIfAbsent(oclass, (oclass1) -> {
                Stream stream = this.c.stream();

                oclass1.getClass();
                return (List) stream.filter(oclass1::isInstance).collect(Collectors.toList());
            });

            return Collections.unmodifiableCollection(list);
        }
    }

    public Iterator<T> iterator() {
        return (Iterator) (this.c.isEmpty() ? Collections.emptyIterator() : Iterators.unmodifiableIterator(this.c.iterator()));
    }

    public int size() {
        return this.c.size();
    }
}
