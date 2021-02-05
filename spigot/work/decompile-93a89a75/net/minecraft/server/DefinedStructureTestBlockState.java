package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestBlockState extends DefinedStructureRuleTest {

    private final IBlockData a;

    public DefinedStructureTestBlockState(IBlockData iblockdata) {
        this.a = iblockdata;
    }

    public <T> DefinedStructureTestBlockState(Dynamic<T> dynamic) {
        this(IBlockData.a(dynamic.get("blockstate").orElseEmptyMap()));
    }

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return iblockdata == this.a;
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.d;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("blockstate"), IBlockData.a(dynamicops, this.a).getValue())));
    }
}
