package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import javax.annotation.Nullable;

public abstract class DefinedStructureProcessor {

    public DefinedStructureProcessor() {}

    @Nullable
    public abstract DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo);

    protected abstract DefinedStructureStructureProcessorType a();

    protected abstract <T> Dynamic<T> a(DynamicOps<T> dynamicops);

    public <T> Dynamic<T> b(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.mergeInto(this.a(dynamicops).getValue(), dynamicops.createString("processor_type"), dynamicops.createString(IRegistry.STRUCTURE_PROCESSOR.getKey(this.a()).toString())));
    }
}
