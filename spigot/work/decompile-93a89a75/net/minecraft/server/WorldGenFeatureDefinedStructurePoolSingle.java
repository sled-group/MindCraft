package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenFeatureDefinedStructurePoolSingle extends WorldGenFeatureDefinedStructurePoolStructure {

    protected final MinecraftKey a;
    protected final ImmutableList<DefinedStructureProcessor> b;

    @Deprecated
    public WorldGenFeatureDefinedStructurePoolSingle(String s, List<DefinedStructureProcessor> list) {
        this(s, list, WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
    }

    public WorldGenFeatureDefinedStructurePoolSingle(String s, List<DefinedStructureProcessor> list, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super(worldgenfeaturedefinedstructurepooltemplate_matching);
        this.a = new MinecraftKey(s);
        this.b = ImmutableList.copyOf(list);
    }

    @Deprecated
    public WorldGenFeatureDefinedStructurePoolSingle(String s) {
        this(s, ImmutableList.of());
    }

    public WorldGenFeatureDefinedStructurePoolSingle(Dynamic<?> dynamic) {
        super(dynamic);
        this.a = new MinecraftKey(dynamic.get("location").asString(""));
        this.b = ImmutableList.copyOf(dynamic.get("processors").asList((dynamic1) -> {
            return (DefinedStructureProcessor) DynamicDeserializer.a(dynamic1, IRegistry.STRUCTURE_PROCESSOR, "processor_type", DefinedStructureProcessorNop.a);
        }));
    }

    public List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, boolean flag) {
        DefinedStructure definedstructure = definedstructuremanager.a(this.a);
        List<DefinedStructure.BlockInfo> list = definedstructure.a(blockposition, (new DefinedStructureInfo()).a(enumblockrotation), Blocks.STRUCTURE_BLOCK, flag);
        List<DefinedStructure.BlockInfo> list1 = Lists.newArrayList();
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            DefinedStructure.BlockInfo definedstructure_blockinfo = (DefinedStructure.BlockInfo) iterator.next();

            if (definedstructure_blockinfo.c != null) {
                BlockPropertyStructureMode blockpropertystructuremode = BlockPropertyStructureMode.valueOf(definedstructure_blockinfo.c.getString("mode"));

                if (blockpropertystructuremode == BlockPropertyStructureMode.DATA) {
                    list1.add(definedstructure_blockinfo);
                }
            }
        }

        return list1;
    }

    @Override
    public List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random) {
        DefinedStructure definedstructure = definedstructuremanager.a(this.a);
        List<DefinedStructure.BlockInfo> list = definedstructure.a(blockposition, (new DefinedStructureInfo()).a(enumblockrotation), Blocks.JIGSAW, true);

        Collections.shuffle(list, random);
        return list;
    }

    @Override
    public StructureBoundingBox a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation) {
        DefinedStructure definedstructure = definedstructuremanager.a(this.a);

        return definedstructure.b((new DefinedStructureInfo()).a(enumblockrotation), blockposition);
    }

    @Override
    public boolean a(DefinedStructureManager definedstructuremanager, GeneratorAccess generatoraccess, BlockPosition blockposition, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random) {
        DefinedStructure definedstructure = definedstructuremanager.a(this.a);
        DefinedStructureInfo definedstructureinfo = this.a(enumblockrotation, structureboundingbox);

        if (!definedstructure.a(generatoraccess, blockposition, definedstructureinfo, 18)) {
            return false;
        } else {
            List<DefinedStructure.BlockInfo> list = DefinedStructure.a(generatoraccess, blockposition, definedstructureinfo, this.a(definedstructuremanager, blockposition, enumblockrotation, false));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                DefinedStructure.BlockInfo definedstructure_blockinfo = (DefinedStructure.BlockInfo) iterator.next();

                this.a(generatoraccess, definedstructure_blockinfo, blockposition, enumblockrotation, random, structureboundingbox);
            }

            return true;
        }
    }

    protected DefinedStructureInfo a(EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox) {
        DefinedStructureInfo definedstructureinfo = new DefinedStructureInfo();

        definedstructureinfo.a(structureboundingbox);
        definedstructureinfo.a(enumblockrotation);
        definedstructureinfo.c(true);
        definedstructureinfo.a(false);
        definedstructureinfo.a((DefinedStructureProcessor) DefinedStructureProcessorBlockIgnore.c);
        definedstructureinfo.a((DefinedStructureProcessor) DefinedStructureProcessorJigsawReplacement.a);
        this.b.forEach(definedstructureinfo::a);
        this.c().b().forEach(definedstructureinfo::a);
        return definedstructureinfo;
    }

    @Override
    public WorldGenFeatureDefinedStructurePools a() {
        return WorldGenFeatureDefinedStructurePools.b;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("location"), dynamicops.createString(this.a.toString()), dynamicops.createString("processors"), dynamicops.createList(this.b.stream().map((definedstructureprocessor) -> {
            return definedstructureprocessor.b(dynamicops).getValue();
        })))));
    }

    public String toString() {
        return "Single[" + this.a + "]";
    }
}
