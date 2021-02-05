package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BlockStateDirection extends BlockStateEnum<EnumDirection> {

    protected BlockStateDirection(String s, Collection<EnumDirection> collection) {
        super(s, EnumDirection.class, collection);
    }

    public static BlockStateDirection a(String s, Predicate<EnumDirection> predicate) {
        return a(s, (Collection) Arrays.stream(EnumDirection.values()).filter(predicate).collect(Collectors.toList()));
    }

    public static BlockStateDirection a(String s, EnumDirection... aenumdirection) {
        return a(s, (Collection) Lists.newArrayList(aenumdirection));
    }

    public static BlockStateDirection a(String s, Collection<EnumDirection> collection) {
        return new BlockStateDirection(s, collection);
    }
}
