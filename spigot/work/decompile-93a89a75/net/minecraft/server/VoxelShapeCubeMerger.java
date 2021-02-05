package net.minecraft.server;

import com.google.common.math.IntMath;
import it.unimi.dsi.fastutil.doubles.DoubleList;

public final class VoxelShapeCubeMerger implements VoxelShapeMerger {

    private final VoxelShapeCubePoint a;
    private final int b;
    private final int c;
    private final int d;

    VoxelShapeCubeMerger(int i, int j) {
        this.a = new VoxelShapeCubePoint((int) VoxelShapes.a(i, j));
        this.b = i;
        this.c = j;
        this.d = IntMath.gcd(i, j);
    }

    @Override
    public boolean a(VoxelShapeMerger.a voxelshapemerger_a) {
        int i = this.b / this.d;
        int j = this.c / this.d;

        for (int k = 0; k <= this.a.size(); ++k) {
            if (!voxelshapemerger_a.merge(k / j, k / i, k)) {
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
