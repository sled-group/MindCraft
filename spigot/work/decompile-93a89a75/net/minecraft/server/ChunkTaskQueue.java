package net.minecraft.server;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public class ChunkTaskQueue<T> {

    public static final int a = PlayerChunkMap.GOLDEN_TICKET + 2;
    private final List<Long2ObjectLinkedOpenHashMap<List<Optional<T>>>> b;
    private volatile int c;
    private final String d;
    private final LongSet e;
    private final int f;

    public ChunkTaskQueue(String s, int i) {
        this.b = (List) IntStream.range(0, ChunkTaskQueue.a).mapToObj((j) -> {
            return new Long2ObjectLinkedOpenHashMap();
        }).collect(Collectors.toList());
        this.c = ChunkTaskQueue.a;
        this.e = new LongOpenHashSet();
        this.d = s;
        this.f = i;
    }

    protected void a(int i, ChunkCoordIntPair chunkcoordintpair, int j) {
        if (i < ChunkTaskQueue.a) {
            Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = (Long2ObjectLinkedOpenHashMap) this.b.get(i);
            List<Optional<T>> list = (List) long2objectlinkedopenhashmap.remove(chunkcoordintpair.pair());

            if (i == this.c) {
                while (this.c < ChunkTaskQueue.a && ((Long2ObjectLinkedOpenHashMap) this.b.get(this.c)).isEmpty()) {
                    ++this.c;
                }
            }

            if (list != null && !list.isEmpty()) {
                ((List) ((Long2ObjectLinkedOpenHashMap) this.b.get(j)).computeIfAbsent(chunkcoordintpair.pair(), (k) -> {
                    return Lists.newArrayList();
                })).addAll(list);
                this.c = Math.min(this.c, j);
            }

        }
    }

    protected void a(Optional<T> optional, long i, int j) {
        ((List) ((Long2ObjectLinkedOpenHashMap) this.b.get(j)).computeIfAbsent(i, (k) -> {
            return Lists.newArrayList();
        })).add(optional);
        this.c = Math.min(this.c, j);
    }

    protected void a(long i, boolean flag) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = (Long2ObjectLinkedOpenHashMap) iterator.next();
            List<Optional<T>> list = (List) long2objectlinkedopenhashmap.get(i);

            if (list != null) {
                if (flag) {
                    list.clear();
                } else {
                    list.removeIf((optional) -> {
                        return !optional.isPresent();
                    });
                }

                if (list.isEmpty()) {
                    long2objectlinkedopenhashmap.remove(i);
                }
            }
        }

        while (this.c < ChunkTaskQueue.a && ((Long2ObjectLinkedOpenHashMap) this.b.get(this.c)).isEmpty()) {
            ++this.c;
        }

        this.e.remove(i);
    }

    private Runnable a(long i) {
        return () -> {
            this.e.add(i);
        };
    }

    @Nullable
    public Stream<Either<T, Runnable>> a() {
        if (this.e.size() >= this.f) {
            return null;
        } else if (this.c >= ChunkTaskQueue.a) {
            return null;
        } else {
            int i = this.c;
            Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = (Long2ObjectLinkedOpenHashMap) this.b.get(i);
            long j = long2objectlinkedopenhashmap.firstLongKey();

            List list;

            for (list = (List) long2objectlinkedopenhashmap.removeFirst(); this.c < ChunkTaskQueue.a && ((Long2ObjectLinkedOpenHashMap) this.b.get(this.c)).isEmpty(); ++this.c) {
                ;
            }

            return list.stream().map((optional) -> {
                return (Either) optional.map(Either::left).orElseGet(() -> {
                    return Either.right(this.a(j));
                });
            });
        }
    }

    public String toString() {
        return this.d + " " + this.c + "...";
    }

    @VisibleForTesting
    LongSet b() {
        return new LongOpenHashSet(this.e);
    }
}
