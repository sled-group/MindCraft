package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestRandomBlock extends DefinedStructureRuleTest {

    private final Block a;
    private final float b;

    public DefinedStructureTestRandomBlock(Block block, float f) {
        this.a = block;
        this.b = f;
    }

    public <T> DefinedStructureTestRandomBlock(Dynamic<T> dynamic) {
        this((Block) IRegistry.BLOCK.get(new MinecraftKey(dynamic.get("block").asString(""))), dynamic.get("probability").asFloat(1.0F));
    }

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return iblockdata.getBlock() == this.a && random.nextFloat() < this.b;
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.f;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("block"), dynamicops.createString(IRegistry.BLOCK.getKey(this.a).toString()), dynamicops.createString("probability"), dynamicops.createFloat(this.b))));
    }
}
