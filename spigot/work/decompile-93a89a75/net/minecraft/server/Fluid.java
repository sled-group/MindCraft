package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public interface Fluid extends IBlockDataHolder<Fluid> {

    FluidType getType();

    default boolean isSource() {
        return this.getType().c(this);
    }

    default boolean isEmpty() {
        return this.getType().c();
    }

    default float getHeight(IBlockAccess iblockaccess, BlockPosition blockposition) {
        return this.getType().a(this, iblockaccess, blockposition);
    }

    default float f() {
        return this.getType().a(this);
    }

    default int g() {
        return this.getType().d(this);
    }

    default void a(World world, BlockPosition blockposition) {
        this.getType().a(world, blockposition, this);
    }

    default boolean h() {
        return this.getType().k();
    }

    default void b(World world, BlockPosition blockposition, Random random) {
        this.getType().b(world, blockposition, this, random);
    }

    default Vec3D c(IBlockAccess iblockaccess, BlockPosition blockposition) {
        return this.getType().a(iblockaccess, blockposition, this);
    }

    default IBlockData getBlockData() {
        return this.getType().b(this);
    }

    default boolean a(Tag<FluidType> tag) {
        return this.getType().a(tag);
    }

    default float l() {
        return this.getType().d();
    }

    default boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection) {
        return this.getType().a(this, iblockaccess, blockposition, fluidtype, enumdirection);
    }

    static <T> Dynamic<T> a(DynamicOps<T> dynamicops, Fluid fluid) {
        ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap = fluid.getStateMap();
        Object object;

        if (immutablemap.isEmpty()) {
            object = dynamicops.createMap(ImmutableMap.of(dynamicops.createString("Name"), dynamicops.createString(IRegistry.FLUID.getKey(fluid.getType()).toString())));
        } else {
            object = dynamicops.createMap(ImmutableMap.of(dynamicops.createString("Name"), dynamicops.createString(IRegistry.FLUID.getKey(fluid.getType()).toString()), dynamicops.createString("Properties"), dynamicops.createMap((Map) immutablemap.entrySet().stream().map((entry) -> {
                return Pair.of(dynamicops.createString(((IBlockState) entry.getKey()).a()), dynamicops.createString(IBlockDataHolder.b((IBlockState) entry.getKey(), (Comparable) entry.getValue())));
            }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)))));
        }

        return new Dynamic(dynamicops, object);
    }

    static <T> Fluid a(Dynamic<T> dynamic) {
        RegistryBlocks registryblocks = IRegistry.FLUID;
        Optional optional = dynamic.getElement("Name");
        DynamicOps dynamicops = dynamic.getOps();

        dynamicops.getClass();
        FluidType fluidtype = (FluidType) registryblocks.get(new MinecraftKey((String) optional.flatMap(dynamicops::getStringValue).orElse("minecraft:empty")));
        Map<String, String> map = dynamic.get("Properties").asMap((dynamic1) -> {
            return dynamic1.asString("");
        }, (dynamic1) -> {
            return dynamic1.asString("");
        });
        Fluid fluid = fluidtype.i();
        BlockStateList<FluidType, Fluid> blockstatelist = fluidtype.h();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, String> entry = (Entry) iterator.next();
            String s = (String) entry.getKey();
            IBlockState<?> iblockstate = blockstatelist.a(s);

            if (iblockstate != null) {
                fluid = (Fluid) IBlockDataHolder.a(fluid, iblockstate, s, dynamic.toString(), (String) entry.getValue());
            }
        }

        return fluid;
    }

    default VoxelShape d(IBlockAccess iblockaccess, BlockPosition blockposition) {
        return this.getType().b(this, iblockaccess, blockposition);
    }
}
