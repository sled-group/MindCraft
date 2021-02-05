package net.minecraft.server;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;

public class FluidTypes {

    public static final FluidType EMPTY = a("empty", new FluidTypeEmpty());
    public static final FluidTypeFlowing FLOWING_WATER = (FluidTypeFlowing) a("flowing_water", new FluidTypeWater.a());
    public static final FluidTypeFlowing WATER = (FluidTypeFlowing) a("water", new FluidTypeWater.b());
    public static final FluidTypeFlowing FLOWING_LAVA = (FluidTypeFlowing) a("flowing_lava", new FluidTypeLava.a());
    public static final FluidTypeFlowing LAVA = (FluidTypeFlowing) a("lava", new FluidTypeLava.b());

    private static <T extends FluidType> T a(String s, T t0) {
        return (FluidType) IRegistry.a((IRegistry) IRegistry.FLUID, s, (Object) t0);
    }

    static {
        Iterator iterator = IRegistry.FLUID.iterator();

        while (iterator.hasNext()) {
            FluidType fluidtype = (FluidType) iterator.next();
            UnmodifiableIterator unmodifiableiterator = fluidtype.h().a().iterator();

            while (unmodifiableiterator.hasNext()) {
                Fluid fluid = (Fluid) unmodifiableiterator.next();

                FluidType.c.b(fluid);
            }
        }

    }
}
