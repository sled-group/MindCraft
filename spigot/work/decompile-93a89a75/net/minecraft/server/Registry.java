package net.minecraft.server;

import javax.annotation.Nullable;

public interface Registry<T> extends Iterable<T> {

    @Nullable
    T fromId(int i);
}
