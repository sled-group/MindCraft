package net.minecraft.server;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Either;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkTaskQueueSorter implements AutoCloseable, PlayerChunk.c {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<Mailbox<?>, ChunkTaskQueue<? extends Function<Mailbox<Unit>, ?>>> b;
    private final Set<Mailbox<?>> c;
    private final ThreadedMailbox<PairedQueue.b> d;

    public ChunkTaskQueueSorter(List<Mailbox<?>> list, Executor executor, int i) {
        this.b = (Map) list.stream().collect(Collectors.toMap(Function.identity(), (mailbox) -> {
            return new ChunkTaskQueue<>(mailbox.bf() + "_queue", i);
        }));
        this.c = Sets.newHashSet(list);
        this.d = new ThreadedMailbox<>(new PairedQueue.a(4), executor, "sorter");
    }

    public static ChunkTaskQueueSorter.a<Runnable> a(Runnable runnable, long i, IntSupplier intsupplier) {
        return new ChunkTaskQueueSorter.a<>((mailbox) -> {
            return () -> {
                runnable.run();
                mailbox.a((Object) Unit.INSTANCE);
            };
        }, i, intsupplier);
    }

    public static ChunkTaskQueueSorter.a<Runnable> a(PlayerChunk playerchunk, Runnable runnable) {
        long i = playerchunk.i().pair();

        playerchunk.getClass();
        return a(runnable, i, playerchunk::k);
    }

    public static ChunkTaskQueueSorter.b a(Runnable runnable, long i, boolean flag) {
        return new ChunkTaskQueueSorter.b(runnable, i, flag);
    }

    public <T> Mailbox<ChunkTaskQueueSorter.a<T>> a(Mailbox<T> mailbox, boolean flag) {
        return (Mailbox) this.d.a((mailbox1) -> {
            return new PairedQueue.b(0, () -> {
                this.b(mailbox);
                mailbox1.a((Object) Mailbox.a("chunk priority sorter around " + mailbox.bf(), (chunktaskqueuesorter_a) -> {
                    this.a(mailbox, chunktaskqueuesorter_a.a, chunktaskqueuesorter_a.b, chunktaskqueuesorter_a.c, flag);
                }));
            });
        }).join();
    }

    public Mailbox<ChunkTaskQueueSorter.b> a(Mailbox<Runnable> mailbox) {
        return (Mailbox) this.d.a((mailbox1) -> {
            return new PairedQueue.b(0, () -> {
                mailbox1.a((Object) Mailbox.a("chunk priority sorter around " + mailbox.bf(), (chunktaskqueuesorter_b) -> {
                    this.a(mailbox, chunktaskqueuesorter_b.b, chunktaskqueuesorter_b.a, chunktaskqueuesorter_b.c);
                }));
            });
        }).join();
    }

    @Override
    public void a(ChunkCoordIntPair chunkcoordintpair, IntSupplier intsupplier, int i, IntConsumer intconsumer) {
        this.d.a((Object) (new PairedQueue.b(0, () -> {
            int j = intsupplier.getAsInt();

            this.b.values().forEach((chunktaskqueue) -> {
                chunktaskqueue.a(j, chunkcoordintpair, i);
            });
            intconsumer.accept(i);
        })));
    }

    private <T> void a(Mailbox<T> mailbox, long i, Runnable runnable, boolean flag) {
        this.d.a((Object) (new PairedQueue.b(1, () -> {
            ChunkTaskQueue<Function<Mailbox<Unit>, T>> chunktaskqueue = this.b(mailbox);

            chunktaskqueue.a(i, flag);
            if (this.c.remove(mailbox)) {
                this.a(chunktaskqueue, mailbox);
            }

            runnable.run();
        })));
    }

    private <T> void a(Mailbox<T> mailbox, Function<Mailbox<Unit>, T> function, long i, IntSupplier intsupplier, boolean flag) {
        this.d.a((Object) (new PairedQueue.b(2, () -> {
            ChunkTaskQueue<Function<Mailbox<Unit>, T>> chunktaskqueue = this.b(mailbox);
            int j = intsupplier.getAsInt();

            chunktaskqueue.a(Optional.of(function), i, j);
            if (flag) {
                chunktaskqueue.a(Optional.empty(), i, j);
            }

            if (this.c.remove(mailbox)) {
                this.a(chunktaskqueue, mailbox);
            }

        })));
    }

    private <T> void a(ChunkTaskQueue<Function<Mailbox<Unit>, T>> chunktaskqueue, Mailbox<T> mailbox) {
        this.d.a((Object) (new PairedQueue.b(3, () -> {
            Stream<Either<Function<Mailbox<Unit>, T>, Runnable>> stream = chunktaskqueue.a();

            if (stream == null) {
                this.c.add(mailbox);
            } else {
                SystemUtils.b((List) stream.map((either) -> {
                    return (CompletableFuture) either.map(mailbox::a, (runnable) -> {
                        runnable.run();
                        return CompletableFuture.completedFuture(Unit.INSTANCE);
                    });
                }).collect(Collectors.toList())).thenAccept((list) -> {
                    this.a(chunktaskqueue, mailbox);
                });
            }

        })));
    }

    private <T> ChunkTaskQueue<Function<Mailbox<Unit>, T>> b(Mailbox<T> mailbox) {
        ChunkTaskQueue<? extends Function<Mailbox<Unit>, ?>> chunktaskqueue = (ChunkTaskQueue) this.b.get(mailbox);

        if (chunktaskqueue == null) {
            throw new IllegalArgumentException("No queue for: " + mailbox);
        } else {
            return chunktaskqueue;
        }
    }

    @VisibleForTesting
    public String a() {
        return (String) this.b.entrySet().stream().map((entry) -> {
            return ((Mailbox) entry.getKey()).bf() + "=[" + (String) ((ChunkTaskQueue) entry.getValue()).b().stream().map((olong) -> {
                return olong + ":" + new ChunkCoordIntPair(olong);
            }).collect(Collectors.joining(",")) + "]";
        }).collect(Collectors.joining(",")) + ", s=" + this.c.size();
    }

    public void close() {
        this.b.keySet().forEach(Mailbox::close);
    }

    public static final class b {

        private final Runnable a;
        private final long b;
        private final boolean c;

        private b(Runnable runnable, long i, boolean flag) {
            this.a = runnable;
            this.b = i;
            this.c = flag;
        }
    }

    public static final class a<T> {

        private final Function<Mailbox<Unit>, T> a;
        private final long b;
        private final IntSupplier c;

        private a(Function<Mailbox<Unit>, T> function, long i, IntSupplier intsupplier) {
            this.a = function;
            this.b = i;
            this.c = intsupplier;
        }
    }
}
