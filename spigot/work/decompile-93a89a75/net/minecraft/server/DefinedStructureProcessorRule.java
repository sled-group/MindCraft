package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public class DefinedStructureProcessorRule extends DefinedStructureProcessor {

    private final ImmutableList<DefinedStructureProcessorPredicates> a;

    public DefinedStructureProcessorRule(List<DefinedStructureProcessorPredicates> list) {
        this.a = ImmutableList.copyOf(list);
    }

    public DefinedStructureProcessorRule(Dynamic<?> dynamic) {
        this(dynamic.get("rules").asList(DefinedStructureProcessorPredicates::a));
    }

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        Random random = new Random(MathHelper.a((BaseBlockPosition) definedstructure_blockinfo1.a));
        IBlockData iblockdata = iworldreader.getType(definedstructure_blockinfo1.a);
        UnmodifiableIterator unmodifiableiterator = this.a.iterator();

        DefinedStructureProcessorPredicates definedstructureprocessorpredicates;

        do {
            if (!unmodifiableiterator.hasNext()) {
                return definedstructure_blockinfo1;
            }

            definedstructureprocessorpredicates = (DefinedStructureProcessorPredicates) unmodifiableiterator.next();
        } while (!definedstructureprocessorpredicates.a(definedstructure_blockinfo1.b, iblockdata, random));

        return new DefinedStructure.BlockInfo(definedstructure_blockinfo1.a, definedstructureprocessorpredicates.a(), definedstructureprocessorpredicates.b());
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.f;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("rules"), dynamicops.createList(this.a.stream().map((definedstructureprocessorpredicates) -> {
            return definedstructureprocessorpredicates.a(dynamicops).getValue();
        })))));
    }
}
