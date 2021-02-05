package net.minecraft.server;

import it.unimi.dsi.fastutil.doubles.DoubleList;

public final class VoxelShapeCube extends VoxelShape {

    protected VoxelShapeCube(VoxelShapeDiscrete voxelshapediscrete) {
        super(voxelshapediscrete);
    }

    @Override
    protected DoubleList a(EnumDirection.EnumAxis enumdirection_enumaxis) {
        return new VoxelShapeCubePoint(this.a.c(enumdirection_enumaxis));
    }

    @Override
    protected int a(EnumDirection.EnumAxis enumdirection_enumaxis, double d0) {
        int i = this.a.c(enumdirection_enumaxis);

        return MathHelper.clamp(MathHelper.floor(d0 * (double) i), -1, i);
    }
}
