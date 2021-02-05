package net.minecraft.server;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.Arrays;

public final class VoxelShapeArray extends VoxelShape {

    private final DoubleList b;
    private final DoubleList c;
    private final DoubleList d;

    protected VoxelShapeArray(VoxelShapeDiscrete voxelshapediscrete, double[] adouble, double[] adouble1, double[] adouble2) {
        this(voxelshapediscrete, (DoubleList) DoubleArrayList.wrap(Arrays.copyOf(adouble, voxelshapediscrete.b() + 1)), (DoubleList) DoubleArrayList.wrap(Arrays.copyOf(adouble1, voxelshapediscrete.c() + 1)), (DoubleList) DoubleArrayList.wrap(Arrays.copyOf(adouble2, voxelshapediscrete.d() + 1)));
    }

    VoxelShapeArray(VoxelShapeDiscrete voxelshapediscrete, DoubleList doublelist, DoubleList doublelist1, DoubleList doublelist2) {
        super(voxelshapediscrete);
        int i = voxelshapediscrete.b() + 1;
        int j = voxelshapediscrete.c() + 1;
        int k = voxelshapediscrete.d() + 1;

        if (i == doublelist.size() && j == doublelist1.size() && k == doublelist2.size()) {
            this.b = doublelist;
            this.c = doublelist1;
            this.d = doublelist2;
        } else {
            throw new IllegalArgumentException("Lengths of point arrays must be consistent with the size of the VoxelShape.");
        }
    }

    @Override
    protected DoubleList a(EnumDirection.EnumAxis enumdirection_enumaxis) {
        switch (enumdirection_enumaxis) {
            case X:
                return this.b;
            case Y:
                return this.c;
            case Z:
                return this.d;
            default:
                throw new IllegalArgumentException();
        }
    }
}
