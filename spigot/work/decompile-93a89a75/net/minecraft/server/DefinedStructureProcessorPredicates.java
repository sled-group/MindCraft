package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;
import javax.annotation.Nullable;

public class DefinedStructureProcessorPredicates {

    private final DefinedStructureRuleTest a;
    private final DefinedStructureRuleTest b;
    private final IBlockData c;
    @Nullable
    private final NBTTagCompound d;

    public DefinedStructureProcessorPredicates(DefinedStructureRuleTest definedstructureruletest, DefinedStructureRuleTest definedstructureruletest1, IBlockData iblockdata) {
        this(definedstructureruletest, definedstructureruletest1, iblockdata, (NBTTagCompound) null);
    }

    public DefinedStructureProcessorPredicates(DefinedStructureRuleTest definedstructureruletest, DefinedStructureRuleTest definedstructureruletest1, IBlockData iblockdata, @Nullable NBTTagCompound nbttagcompound) {
        this.a = definedstructureruletest;
        this.b = definedstructureruletest1;
        this.c = iblockdata;
        this.d = nbttagcompound;
    }

    public boolean a(IBlockData iblockdata, IBlockData iblockdata1, Random random) {
        return this.a.a(iblockdata, random) && this.b.a(iblockdata1, random);
    }

    public IBlockData a() {
        return this.c;
    }

    @Nullable
    public NBTTagCompound b() {
        return this.d;
    }

    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        T t0 = dynamicops.createMap(ImmutableMap.of(dynamicops.createString("input_predicate"), this.a.b(dynamicops).getValue(), dynamicops.createString("location_predicate"), this.b.b(dynamicops).getValue(), dynamicops.createString("output_state"), IBlockData.a(dynamicops, this.c).getValue()));

        return this.d == null ? new Dynamic(dynamicops, t0) : new Dynamic(dynamicops, dynamicops.mergeInto(t0, dynamicops.createString("output_nbt"), (new Dynamic(DynamicOpsNBT.a, this.d)).convert(dynamicops).getValue()));
    }

    public static <T> DefinedStructureProcessorPredicates a(Dynamic<T> dynamic) {
        Dynamic<T> dynamic1 = dynamic.get("input_predicate").orElseEmptyMap();
        Dynamic<T> dynamic2 = dynamic.get("location_predicate").orElseEmptyMap();
        DefinedStructureRuleTest definedstructureruletest = (DefinedStructureRuleTest) DynamicDeserializer.a(dynamic1, IRegistry.RULE_TEST, "predicate_type", DefinedStructureTestTrue.a);
        DefinedStructureRuleTest definedstructureruletest1 = (DefinedStructureRuleTest) DynamicDeserializer.a(dynamic2, IRegistry.RULE_TEST, "predicate_type", DefinedStructureTestTrue.a);
        IBlockData iblockdata = IBlockData.a(dynamic.get("output_state").orElseEmptyMap());
        NBTTagCompound nbttagcompound = (NBTTagCompound) dynamic.get("output_nbt").map((dynamic3) -> {
            return (NBTBase) dynamic3.convert(DynamicOpsNBT.a).getValue();
        }).orElse((Object) null);

        return new DefinedStructureProcessorPredicates(definedstructureruletest, definedstructureruletest1, iblockdata, nbttagcompound);
    }
}
