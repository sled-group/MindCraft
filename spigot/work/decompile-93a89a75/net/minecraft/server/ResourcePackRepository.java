package net.minecraft.server;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class ResourcePackRepository<T extends ResourcePackLoader> implements AutoCloseable {

    private final Set<ResourcePackSource> a = Sets.newHashSet();
    private final Map<String, T> b = Maps.newLinkedHashMap();
    private final List<T> c = Lists.newLinkedList();
    private final ResourcePackLoader.b<T> d;

    public ResourcePackRepository(ResourcePackLoader.b<T> resourcepackloader_b) {
        this.d = resourcepackloader_b;
    }

    public void a() {
        this.close();
        Set<String> set = (Set) this.c.stream().map(ResourcePackLoader::e).collect(Collectors.toCollection(LinkedHashSet::new));

        this.b.clear();
        this.c.clear();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            ResourcePackSource resourcepacksource = (ResourcePackSource) iterator.next();

            resourcepacksource.a(this.b, this.d);
        }

        this.e();
        List list = this.c;
        Stream stream = set.stream();
        Map map = this.b;

        this.b.getClass();
        list.addAll((Collection) stream.map(map::get).filter(Objects::nonNull).collect(Collectors.toCollection(LinkedHashSet::new)));
        iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            T t0 = (ResourcePackLoader) iterator.next();

            if (t0.f() && !this.c.contains(t0)) {
                t0.h().a(this.c, t0, Functions.identity(), false);
            }
        }

    }

    private void e() {
        List<Entry<String, T>> list = Lists.newArrayList(this.b.entrySet());

        this.b.clear();
        list.stream().sorted(Entry.comparingByKey()).forEachOrdered((entry) -> {
            ResourcePackLoader resourcepackloader = (ResourcePackLoader) this.b.put(entry.getKey(), entry.getValue());
        });
    }

    public void a(Collection<T> collection) {
        this.c.clear();
        this.c.addAll(collection);
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            T t0 = (ResourcePackLoader) iterator.next();

            if (t0.f() && !this.c.contains(t0)) {
                t0.h().a(this.c, t0, Functions.identity(), false);
            }
        }

    }

    public Collection<T> b() {
        return this.b.values();
    }

    public Collection<T> c() {
        Collection<T> collection = Lists.newArrayList(this.b.values());

        collection.removeAll(this.c);
        return collection;
    }

    public Collection<T> d() {
        return this.c;
    }

    @Nullable
    public T a(String s) {
        return (ResourcePackLoader) this.b.get(s);
    }

    public void a(ResourcePackSource resourcepacksource) {
        this.a.add(resourcepacksource);
    }

    public void close() {
        this.b.values().forEach(ResourcePackLoader::close);
    }
}
