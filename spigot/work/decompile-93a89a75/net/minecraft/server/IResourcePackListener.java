package net.minecraft.server;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface IResourcePackListener extends IReloadListener {

    @Override
    default CompletableFuture<Void> a(IReloadListener.a ireloadlistener_a, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller, GameProfilerFiller gameprofilerfiller1, Executor executor, Executor executor1) {
        return ireloadlistener_a.a(Unit.INSTANCE).thenRunAsync(() -> {
            this.a(iresourcemanager);
        }, executor1);
    }

    void a(IResourceManager iresourcemanager);
}
