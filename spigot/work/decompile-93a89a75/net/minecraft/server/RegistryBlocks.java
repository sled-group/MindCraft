package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RegistryBlocks<T> extends RegistryMaterials<T> {

    private final MinecraftKey R;
    private T S;

    public RegistryBlocks(String s) {
        this.R = new MinecraftKey(s);
    }

    @Override
    public <V extends T> V a(int i, MinecraftKey minecraftkey, V v0) {
        if (this.R.equals(minecraftkey)) {
            this.S = v0;
        }

        return super.a(i, minecraftkey, v0);
    }

    @Override
    public int a(@Nullable T t0) {
        int i = super.a(t0);

        return i == -1 ? super.a(this.S) : i;
    }

    @Nonnull
    @Override
    public MinecraftKey getKey(T t0) {
        MinecraftKey minecraftkey = super.getKey(t0);

        return minecraftkey == null ? this.R : minecraftkey;
    }

    @Nonnull
    @Override
    public T get(@Nullable MinecraftKey minecraftkey) {
        T t0 = super.get(minecraftkey);

        return t0 == null ? this.S : t0;
    }

    @Nonnull
    @Override
    public T fromId(int i) {
        T t0 = super.fromId(i);

        return t0 == null ? this.S : t0;
    }

    @Nonnull
    @Override
    public T a(Random random) {
        T t0 = super.a(random);

        return t0 == null ? this.S : t0;
    }

    public MinecraftKey a() {
        return this.R;
    }
}
