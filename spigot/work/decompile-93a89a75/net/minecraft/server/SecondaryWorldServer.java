package net.minecraft.server;

import java.util.concurrent.Executor;

public class SecondaryWorldServer extends WorldServer {

    public SecondaryWorldServer(WorldServer worldserver, MinecraftServer minecraftserver, Executor executor, WorldNBTStorage worldnbtstorage, DimensionManager dimensionmanager, GameProfilerFiller gameprofilerfiller, WorldLoadListener worldloadlistener) {
        super(minecraftserver, executor, worldnbtstorage, new SecondaryWorldData(worldserver.getWorldData()), dimensionmanager, gameprofilerfiller, worldloadlistener);
        worldserver.getWorldBorder().a((IWorldBorderListener) (new IWorldBorderListener.a(this.getWorldBorder())));
    }

    @Override
    protected void a() {}
}
