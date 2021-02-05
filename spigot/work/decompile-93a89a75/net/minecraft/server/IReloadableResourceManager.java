package net.minecraft.server;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public interface IReloadableResourceManager extends IResourceManager {

    CompletableFuture<Unit> a(Executor executor, Executor executor1, List<IResourcePack> list, CompletableFuture<Unit> completablefuture);

    void a(IReloadListener ireloadlistener);
}
