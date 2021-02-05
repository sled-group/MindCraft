package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;

public class BehaviorGateSingle<E extends EntityLiving> extends BehaviorGate<E> {

    public BehaviorGateSingle(List<Pair<Behavior<? super E>, Integer>> list) {
        this(ImmutableMap.of(), list);
    }

    public BehaviorGateSingle(Map<MemoryModuleType<?>, MemoryStatus> map, List<Pair<Behavior<? super E>, Integer>> list) {
        super(map, ImmutableSet.of(), BehaviorGate.Order.SHUFFLED, BehaviorGate.Execution.RUN_ONE, list);
    }
}
