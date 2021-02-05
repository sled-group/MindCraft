package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WorldGenFeatureDefinedStructurePoolList extends WorldGenFeatureDefinedStructurePoolStructure {

    private final List<WorldGenFeatureDefinedStructurePoolStructure> a;

    @Deprecated
    public WorldGenFeatureDefinedStructurePoolList(List<WorldGenFeatureDefinedStructurePoolStructure> list) {
        this(list, WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
    }

    public WorldGenFeatureDefinedStructurePoolList(List<WorldGenFeatureDefinedStructurePoolStructure> list, WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super(worldgenfeaturedefinedstructurepooltemplate_matching);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Elements are empty");
        } else {
            this.a = list;
            this.b(worldgenfeaturedefinedstructurepooltemplate_matching);
        }
    }

    public WorldGenFeatureDefinedStructurePoolList(Dynamic<?> dynamic) {
        super(dynamic);
        List<WorldGenFeatureDefinedStructurePoolStructure> list = dynamic.get("elements").asList((dynamic1) -> {
            return (WorldGenFeatureDefinedStructurePoolStructure) DynamicDeserializer.a(dynamic1, IRegistry.STRUCTURE_POOL_ELEMENT, "element_type", WorldGenFeatureDefinedStructurePoolEmpty.a);
        });

        if (list.isEmpty()) {
            throw new IllegalArgumentException("Elements are empty");
        } else {
            this.a = list;
        }
    }

    @Override
    public List<DefinedStructure.BlockInfo> a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation, Random random) {
        return ((WorldGenFeatureDefinedStructurePoolStructure) this.a.get(0)).a(definedstructuremanager, blockposition, enumblockrotation, random);
    }

    @Override
    public StructureBoundingBox a(DefinedStructureManager definedstructuremanager, BlockPosition blockposition, EnumBlockRotation enumblockrotation) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure = (WorldGenFeatureDefinedStructurePoolStructure) iterator.next();
            StructureBoundingBox structureboundingbox1 = worldgenfeaturedefinedstructurepoolstructure.a(definedstructuremanager, blockposition, enumblockrotation);

            structureboundingbox.c(structureboundingbox1);
        }

        return structureboundingbox;
    }

    @Override
    public boolean a(DefinedStructureManager definedstructuremanager, GeneratorAccess generatoraccess, BlockPosition blockposition, EnumBlockRotation enumblockrotation, StructureBoundingBox structureboundingbox, Random random) {
        Iterator iterator = this.a.iterator();

        WorldGenFeatureDefinedStructurePoolStructure worldgenfeaturedefinedstructurepoolstructure;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            worldgenfeaturedefinedstructurepoolstructure = (WorldGenFeatureDefinedStructurePoolStructure) iterator.next();
        } while (worldgenfeaturedefinedstructurepoolstructure.a(definedstructuremanager, generatoraccess, blockposition, enumblockrotation, structureboundingbox, random));

        return false;
    }

    @Override
    public WorldGenFeatureDefinedStructurePools a() {
        return WorldGenFeatureDefinedStructurePools.c;
    }

    @Override
    public WorldGenFeatureDefinedStructurePoolStructure a(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        super.a(worldgenfeaturedefinedstructurepooltemplate_matching);
        this.b(worldgenfeaturedefinedstructurepooltemplate_matching);
        return this;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        T t0 = dynamicops.createList(this.a.stream().map((worldgenfeaturedefinedstructurepoolstructure) -> {
            return worldgenfeaturedefinedstructurepoolstructure.b(dynamicops).getValue();
        }));

        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("elements"), t0)));
    }

    public String toString() {
        return "List[" + (String) this.a.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
    }

    private void b(WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching) {
        this.a.forEach((worldgenfeaturedefinedstructurepoolstructure) -> {
            worldgenfeaturedefinedstructurepoolstructure.a(worldgenfeaturedefinedstructurepooltemplate_matching);
        });
    }
}
