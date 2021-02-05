package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class FluidImpl extends BlockDataAbstract<FluidType, Fluid> implements Fluid {

    public FluidImpl(FluidType fluidtype, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap) {
        super(fluidtype, immutablemap);
    }

    @Override
    public FluidType getType() {
        return (FluidType) this.a;
    }
}
