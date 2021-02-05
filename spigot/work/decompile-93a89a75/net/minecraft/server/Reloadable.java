package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class Reloadable<S> implements IReloadable {

    protected final IResourceManager a;
    protected final CompletableFuture<Unit> b = new CompletableFuture();
    protected final CompletableFuture<List<S>> c;
    private final Set<IReloadListener> d;
    private final int e;
    private int f;
    private int g;
    private final AtomicInteger h = new AtomicInteger();
    private final AtomicInteger i = new AtomicInteger();

    public static Reloadable<Void> a(IResourceManager iresourcemanager, List<IReloadListener> list, Executor executor, Executor executor1, CompletableFuture<Unit> completablefuture) {
        return new Reloadable<>(executor, executor1, iresourcemanager, list, (ireloadlistener_a, iresourcemanager1, ireloadlistener, executor2, executor3) -> {
            return ireloadlistener.a(ireloadlistener_a, iresourcemanager1, GameProfilerDisabled.a, GameProfilerDisabled.a, executor, executor3);
        }, completablefuture);
    }

    protected Reloadable(Executor executor, final Executor executor1, IResourceManager iresourcemanager, List<IReloadListener> list, Reloadable.a<S> reloadable_a, CompletableFuture<Unit> completablefuture) {
        this.a = iresourcemanager;
        this.e = list.size();
        this.h.incrementAndGet();
        AtomicInteger atomicinteger = this.i;

        this.i.getClass();
        completablefuture.thenRun(atomicinteger::incrementAndGet);
        List<CompletableFuture<S>> list1 = new ArrayList();
        final CompletableFuture<?> completablefuture1 = completablefuture;

        this.d = Sets.newHashSet(list);

        CompletableFuture completablefuture2;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); completablefuture1 = completablefuture2) {
            final IReloadListener ireloadlistener = (IReloadListener) iterator.next();

            completablefuture2 = reloadable_a.create(new IReloadListener.a() {
                @Override
                public <T> CompletableFuture<T> a(T t0) {
                    executor1.execute(() -> {
                        Reloadable.this.d.remove(ireloadlistener);
                        if (Reloadable.this.d.isEmpty()) {
                            Reloadable.this.b.complete(Unit.INSTANCE);
                        }

                    });
                    return Reloadable.this.b.thenCombine(completablefuture1, (unit, object) -> {
                        return t0;
                    });
                }
            }, iresourcemanager, ireloadlistener, (runnable) -> {
                this.h.incrementAndGet();
                executor.execute(() -> {
                    runnable.run();
                    this.i.incrementAndGet();
                });
            }, (runnable) -> {
                ++this.f;
                executor1.execute(() -> {
                    runnable.run();
                    ++this.g;
                });
            });
            list1.add(completablefuture2);
        }

        this.c = SystemUtils.b(list1);
    }

    @Override
    public CompletableFuture<Unit> a() {
        return this.c.thenApply((list) -> {
            return Unit.INSTANCE;
        });
    }

    public interface a<S> {

        CompletableFuture<S> create(IReloadListener.a ireloadlistener_a, IResourceManager iresourcemanager, IReloadListener ireloadlistener, Executor executor, Executor executor1);
    }
}
