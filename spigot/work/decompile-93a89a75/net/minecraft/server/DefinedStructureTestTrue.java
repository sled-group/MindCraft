package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class DefinedStructureTestTrue extends DefinedStructureRuleTest {

    public static final DefinedStructureTestTrue a = new DefinedStructureTestTrue();

    private DefinedStructureTestTrue() {}

    @Override
    public boolean a(IBlockData iblockdata, Random random) {
        return true;
    }

    @Override
    protected DefinedStructureRuleTestType a() {
        return DefinedStructureRuleTestType.b;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }
}
