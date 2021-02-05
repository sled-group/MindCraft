package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestRandomBlockState extends DefinedStructureRuleTest {

    private final IBlockData a;
    private final float b;

    public DefinedStructureTestRandomBlockState(IBlockData iblockdata, float f) {
        this.a = iblockdata;
        this.b = f;
    }

    public <T> DefinedStructureTestRandomBlockState(Dynamic<T> dynamic) {
        this(IBlockData.a(dynamic.get("blockstate").orElseEmptyMap()), dynamic.get("probability").asFloat(1.0F));
    }

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return iblockdata == this.a && random.nextFloat() < this.b;
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.g;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("blockstate"), IBlockData.a(dynamicops, this.a).getValue(), dynamicops.createString("probability"), dynamicops.createFloat(this.b))));
    }
}
