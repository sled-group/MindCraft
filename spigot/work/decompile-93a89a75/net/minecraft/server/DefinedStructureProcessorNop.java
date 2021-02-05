package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import javax.annotation.Nullable;

public class DefinedStructureProcessorNop extends DefinedStructureProcessor {

    public static final DefinedStructureProcessorNop a = new DefinedStructureProcessorNop();

    private DefinedStructureProcessorNop() {}

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        return definedstructure_blockinfo1;
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.g;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }
}
