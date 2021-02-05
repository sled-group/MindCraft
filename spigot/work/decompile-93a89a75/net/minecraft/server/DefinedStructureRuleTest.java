package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public abstract class DefinedStructureRuleTest {

    public DefinedStructureRuleTest() {}

    public abstract boolean a(IBlockData iblockdata, Random random);

    public <T> Dynamic<T> b(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.mergeInto(this.a(dynamicops).getValue(), dynamicops.createString("predicate_type"), dynamicops.createString(IRegistry.RULE_TEST.getKey(this.a()).toString())));
    }

    protected abstract DefinedStructureRuleTestType a();

    protected abstract <T> Dynamic<T> a(DynamicOps<T> dynamicops);
}
