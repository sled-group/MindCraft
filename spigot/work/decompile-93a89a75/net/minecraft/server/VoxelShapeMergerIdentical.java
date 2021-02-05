package net.minecraft.server;

import it.unimi.dsi.fastutil.doubles.DoubleList;

public class VoxelShapeMergerIdentical implements VoxelShapeMerger {

    private final DoubleList a;

    public VoxelShapeMergerIdentical(DoubleList doublelist) {
        this.a = doublelist;
    }

    @Override
    public boolean a(VoxelShapeMerger.a voxelshapemerger_a) {
        for (int i = 0; i <= this.a.size(); ++i) {
            if (!voxelshapemerger_a.merge(i, i, i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public DoubleList a() {
        return this.a;
    }
}
