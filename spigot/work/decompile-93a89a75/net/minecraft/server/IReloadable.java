package net.minecraft.server;

import java.util.concurrent.CompletableFuture;

public interface IReloadable {

    CompletableFuture<Unit> a();
}
