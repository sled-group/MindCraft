package net.minecraft.server;

import java.util.Collection;
import java.util.Optional;

public interface IBlockState<T extends Comparable<T>> {

    String a();

    Collection<T> getValues();

    Class<T> b();

    Optional<T> b(String s);

    String a(T t0);
}
