package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestTag extends DefinedStructureRuleTest {

    private final Tag<Block> a;

    public DefinedStructureTestTag(Tag<Block> tag) {
        this.a = tag;
    }

    public <T> DefinedStructureTestTag(Dynamic<T> dynamic) {
        this(TagsBlock.a().a(new MinecraftKey(dynamic.get("tag").asString(""))));
    }

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return iblockdata.a(this.a);
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.e;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("tag"), dynamicops.createString(this.a.c().toString()))));
    }
}
