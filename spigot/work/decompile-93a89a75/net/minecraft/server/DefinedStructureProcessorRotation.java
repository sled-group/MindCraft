package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;
import javax.annotation.Nullable;

public class DefinedStructureProcessorRotation extends DefinedStructureProcessor {

    private final float a;

    public DefinedStructureProcessorRotation(float f) {
        this.a = f;
    }

    public DefinedStructureProcessorRotation(Dynamic<?> dynamic) {
        this(dynamic.get("integrity").asFloat(1.0F));
    }

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        Random random = definedstructureinfo.b(definedstructure_blockinfo1.a);

        return this.a < 1.0F && random.nextFloat() > this.a ? null : definedstructure_blockinfo1;
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.c;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("integrity"), dynamicops.createFloat(this.a))));
    }
}
