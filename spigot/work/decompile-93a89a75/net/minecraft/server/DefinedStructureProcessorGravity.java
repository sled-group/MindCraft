package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import javax.annotation.Nullable;

public class DefinedStructureProcessorGravity extends DefinedStructureProcessor {

    private final HeightMap.Type a;
    private final int b;

    public DefinedStructureProcessorGravity(HeightMap.Type heightmap_type, int i) {
        this.a = heightmap_type;
        this.b = i;
    }

    public DefinedStructureProcessorGravity(Dynamic<?> dynamic) {
        this(HeightMap.Type.a(dynamic.get("heightmap").asString(HeightMap.Type.WORLD_SURFACE_WG.a())), dynamic.get("offset").asInt(0));
    }

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        int i = iworldreader.a(this.a, definedstructure_blockinfo1.a.getX(), definedstructure_blockinfo1.a.getZ()) + this.b;
        int j = definedstructure_blockinfo.a.getY();

        return new DefinedStructure.BlockInfo(new BlockPosition(definedstructure_blockinfo1.a.getX(), i + j, definedstructure_blockinfo1.a.getZ()), definedstructure_blockinfo1.b, definedstructure_blockinfo1.c);
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.d;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("heightmap"), dynamicops.createString(this.a.a()), dynamicops.createString("offset"), dynamicops.createInt(this.b))));
    }
}
