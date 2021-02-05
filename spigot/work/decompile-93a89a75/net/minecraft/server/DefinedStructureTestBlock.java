package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestBlock extends DefinedStructureRuleTest {

    private final Block a;

    public DefinedStructureTestBlock(Block block) {
        this.a = block;
    }

    public <T> DefinedStructureTestBlock(Dynamic<T> dynamic) {
        this((Block) IRegistry.BLOCK.get(new MinecraftKey(dynamic.get("block").asString(""))));
    }

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return iblockdata.getBlock() == this.a;
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.c;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("block"), dynamicops.createString(IRegistry.BLOCK.getKey(this.a).toString()))));
    }
}
