package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IBlockDataHolder<C> {

    Logger LOGGER = LogManager.getLogger();

    <T extends Comparable<T>> T get(IBlockState<T> iblockstate);

    <T extends Comparable<T>, V extends T> C set(IBlockState<T> iblockstate, V v0);

    ImmutableMap<IBlockState<?>, Comparable<?>> getStateMap();

    static <T extends Comparable<T>> String b(IBlockState<T> iblockstate, Comparable<?> comparable) {
        return iblockstate.a(comparable);
    }

    static <S extends IBlockDataHolder<S>, T extends Comparable<T>> S a(S s0, IBlockState<T> iblockstate, String s, String s1, String s2) {
        Optional<T> optional = iblockstate.b(s2);

        if (optional.isPresent()) {
            return (IBlockDataHolder) s0.set(iblockstate, (Comparable) optional.get());
        } else {
            IBlockDataHolder.LOGGER.warn("Unable to read property: {} with value: {} for input: {}", s, s2, s1);
            return s0;
        }
    }
}
