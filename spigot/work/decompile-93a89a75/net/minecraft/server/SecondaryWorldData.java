package net.minecraft.server;

import javax.annotation.Nullable;

public class SecondaryWorldData extends WorldData {

    private final WorldData b;

    public SecondaryWorldData(WorldData worlddata) {
        this.b = worlddata;
    }

    @Override
    public NBTTagCompound a(@Nullable NBTTagCompound nbttagcompound) {
        return this.b.a(nbttagcompound);
    }

    @Override
    public long getSeed() {
        return this.b.getSeed();
    }

    @Override
    public int b() {
        return this.b.b();
    }

    @Override
    public int c() {
        return this.b.c();
    }

    @Override
    public int d() {
        return this.b.d();
    }

    @Override
    public long getTime() {
        return this.b.getTime();
    }

    @Override
    public long getDayTime() {
        return this.b.getDayTime();
    }

    @Override
    public NBTTagCompound h() {
        return this.b.h();
    }

    @Override
    public String getName() {
        return this.b.getName();
    }

    @Override
    public int j() {
        return this.b.j();
    }

    @Override
    public boolean isThundering() {
        return this.b.isThundering();
    }

    @Override
    public int getThunderDuration() {
        return this.b.getThunderDuration();
    }

    @Override
    public boolean hasStorm() {
        return this.b.hasStorm();
    }

    @Override
    public int getWeatherDuration() {
        return this.b.getWeatherDuration();
    }

    @Override
    public EnumGamemode getGameType() {
        return this.b.getGameType();
    }

    @Override
    public void setTime(long i) {}

    @Override
    public void setDayTime(long i) {}

    @Override
    public void setSpawn(BlockPosition blockposition) {}

    @Override
    public void setName(String s) {}

    @Override
    public void d(int i) {}

    @Override
    public void setThundering(boolean flag) {}

    @Override
    public void setThunderDuration(int i) {}

    @Override
    public void setStorm(boolean flag) {}

    @Override
    public void setWeatherDuration(int i) {}

    @Override
    public boolean shouldGenerateMapFeatures() {
        return this.b.shouldGenerateMapFeatures();
    }

    @Override
    public boolean isHardcore() {
        return this.b.isHardcore();
    }

    @Override
    public WorldType getType() {
        return this.b.getType();
    }

    @Override
    public void a(WorldType worldtype) {}

    @Override
    public boolean t() {
        return this.b.t();
    }

    @Override
    public void c(boolean flag) {}

    @Override
    public boolean u() {
        return this.b.u();
    }

    @Override
    public void d(boolean flag) {}

    @Override
    public GameRules v() {
        return this.b.v();
    }

    @Override
    public EnumDifficulty getDifficulty() {
        return this.b.getDifficulty();
    }

    @Override
    public void setDifficulty(EnumDifficulty enumdifficulty) {}

    @Override
    public boolean isDifficultyLocked() {
        return this.b.isDifficultyLocked();
    }

    @Override
    public void e(boolean flag) {}

    @Override
    public CustomFunctionCallbackTimerQueue<MinecraftServer> y() {
        return this.b.y();
    }

    @Override
    public void a(DimensionManager dimensionmanager, NBTTagCompound nbttagcompound) {
        this.b.a(dimensionmanager, nbttagcompound);
    }

    @Override
    public NBTTagCompound a(DimensionManager dimensionmanager) {
        return this.b.a(dimensionmanager);
    }

    @Override
    public void a(CrashReportSystemDetails crashreportsystemdetails) {
        crashreportsystemdetails.a("Derived", (Object) true);
        this.b.a(crashreportsystemdetails);
    }
}
