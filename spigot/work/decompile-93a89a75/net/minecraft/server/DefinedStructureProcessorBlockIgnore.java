package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import javax.annotation.Nullable;

public class DefinedStructureProcessorBlockIgnore extends DefinedStructureProcessor {

    public static final DefinedStructureProcessorBlockIgnore a = new DefinedStructureProcessorBlockIgnore(ImmutableList.of(Blocks.STRUCTURE_BLOCK));
    public static final DefinedStructureProcessorBlockIgnore b = new DefinedStructureProcessorBlockIgnore(ImmutableList.of(Blocks.AIR));
    public static final DefinedStructureProcessorBlockIgnore c = new DefinedStructureProcessorBlockIgnore(ImmutableList.of(Blocks.AIR, Blocks.STRUCTURE_BLOCK));
    private final ImmutableList<Block> d;

    public DefinedStructureProcessorBlockIgnore(List<Block> list) {
        this.d = ImmutableList.copyOf(list);
    }

    public DefinedStructureProcessorBlockIgnore(Dynamic<?> dynamic) {
        this(dynamic.get("blocks").asList((dynamic1) -> {
            return IBlockData.a(dynamic1).getBlock();
        }));
    }

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        return this.d.contains(definedstructure_blockinfo1.b.getBlock()) ? null : definedstructure_blockinfo1;
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.b;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("blocks"), dynamicops.createList(this.d.stream().map((block) -> {
            return IBlockData.a(dynamicops, block.getBlockData()).getValue();
        })))));
    }
}
