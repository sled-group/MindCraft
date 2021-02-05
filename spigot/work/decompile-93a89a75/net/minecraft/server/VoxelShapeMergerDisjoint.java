package net.minecraft.server;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleList;

public class VoxelShapeMergerDisjoint extends AbstractDoubleList implements VoxelShapeMerger {

    private final DoubleList a;
    private final DoubleList b;
    private final boolean c;

    public VoxelShapeMergerDisjoint(DoubleList doublelist, DoubleList doublelist1, boolean flag) {
        this.a = doublelist;
        this.b = doublelist1;
        this.c = flag;
    }

    public int size() {
        return this.a.size() + this.b.size();
    }

    @Override
    public boolean a(VoxelShapeMerger.a voxelshapemerger_a) {
        return this.c ? this.b((i, j, k) -> {
            return voxelshapemerger_a.merge(j, i, k);
        }) : this.b(voxelshapemerger_a);
    }

    private boolean b(VoxelShapeMerger.a voxelshapemerger_a) {
        int i = this.a.size() - 1;

        int j;

        for (j = 0; j < i; ++j) {
            if (!voxelshapemerger_a.merge(j, -1, j)) {
                return false;
            }
        }

        if (!voxelshapemerger_a.merge(i, -1, i)) {
            return false;
        } else {
            for (j = 0; j < this.b.size(); ++j) {
                if (!voxelshapemerger_a.merge(i, j, i + 1 + j)) {
                    return false;
                }
            }

            return true;
        }
    }

    public double getDouble(int i) {
        return i < this.a.size() ? this.a.getDouble(i) : this.b.getDouble(i - this.a.size());
    }

    @Override
    public DoubleList a() {
        return this;
    }
}
