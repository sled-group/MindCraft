package net.minecraft.server;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class ResourceDataAbstract<T> implements IReloadListener {

    public ResourceDataAbstract() {}

    @Override
    public final CompletableFuture<Void> a(IReloadListener.a ireloadlistener_a, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller, GameProfilerFiller gameprofilerfiller1, Executor executor, Executor executor1) {
        CompletableFuture completablefuture = CompletableFuture.supplyAsync(() -> {
            return this.b(iresourcemanager, gameprofilerfiller);
        }, executor);

        ireloadlistener_a.getClass();
        return completablefuture.thenCompose(ireloadlistener_a::a).thenAcceptAsync((object) -> {
            this.a(object, iresourcemanager, gameprofilerfiller1);
        }, executor1);
    }

    protected abstract T b(IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller);

    protected abstract void a(T t0, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller);
}
