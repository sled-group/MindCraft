package net.minecraft.server;

import java.util.BitSet;

public final class VoxelShapeBitSet extends VoxelShapeDiscrete {

    private final BitSet d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;

    public VoxelShapeBitSet(int i, int j, int k) {
        this(i, j, k, i, j, k, 0, 0, 0);
    }

    public VoxelShapeBitSet(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
        super(i, j, k);
        this.d = new BitSet(i * j * k);
        this.e = l;
        this.f = i1;
        this.g = j1;
        this.h = k1;
        this.i = l1;
        this.j = i2;
    }

    public VoxelShapeBitSet(VoxelShapeDiscrete voxelshapediscrete) {
        super(voxelshapediscrete.a, voxelshapediscrete.b, voxelshapediscrete.c);
        if (voxelshapediscrete instanceof VoxelShapeBitSet) {
            this.d = (BitSet) ((VoxelShapeBitSet) voxelshapediscrete).d.clone();
        } else {
            this.d = new BitSet(this.a * this.b * this.c);

            for (int i = 0; i < this.a; ++i) {
                for (int j = 0; j < this.b; ++j) {
                    for (int k = 0; k < this.c; ++k) {
                        if (voxelshapediscrete.b(i, j, k)) {
                            this.d.set(this.a(i, j, k));
                        }
                    }
                }
            }
        }

        this.e = voxelshapediscrete.a(EnumDirection.EnumAxis.X);
        this.f = voxelshapediscrete.a(EnumDirection.EnumAxis.Y);
        this.g = voxelshapediscrete.a(EnumDirection.EnumAxis.Z);
        this.h = voxelshapediscrete.b(EnumDirection.EnumAxis.X);
        this.i = voxelshapediscrete.b(EnumDirection.EnumAxis.Y);
        this.j = voxelshapediscrete.b(EnumDirection.EnumAxis.Z);
    }

    protected int a(int i, int j, int k) {
        return (i * this.b + j) * this.c + k;
    }

    @Override
    public boolean b(int i, int j, int k) {
        return this.d.get(this.a(i, j, k));
    }

    @Override
    public void a(int i, int j, int k, boolean flag, boolean flag1) {
        this.d.set(this.a(i, j, k), flag1);
        if (flag && flag1) {
            this.e = Math.min(this.e, i);
            this.f = Math.min(this.f, j);
            this.g = Math.min(this.g, k);
            this.h = Math.max(this.h, i + 1);
            this.i = Math.max(this.i, j + 1);
            this.j = Math.max(this.j, k + 1);
        }

    }

    @Override
    public boolean a() {
        return this.d.isEmpty();
    }

    @Override
    public int a(EnumDirection.EnumAxis enumdirection_enumaxis) {
        return enumdirection_enumaxis.a(this.e, this.f, this.g);
    }

    @Override
    public int b(EnumDirection.EnumAxis enumdirection_enumaxis) {
        return enumdirection_enumaxis.a(this.h, this.i, this.j);
    }

    @Override
    protected boolean a(int i, int j, int k, int l) {
        return k >= 0 && l >= 0 && i >= 0 ? (k < this.a && l < this.b && j <= this.c ? this.d.nextClearBit(this.a(k, l, i)) >= this.a(k, l, j) : false) : false;
    }

    @Override
    protected void a(int i, int j, int k, int l, boolean flag) {
        this.d.set(this.a(k, l, i), this.a(k, l, j), flag);
    }

    static VoxelShapeBitSet a(VoxelShapeDiscrete voxelshapediscrete, VoxelShapeDiscrete voxelshapediscrete1, VoxelShapeMerger voxelshapemerger, VoxelShapeMerger voxelshapemerger1, VoxelShapeMerger voxelshapemerger2, OperatorBoolean operatorboolean) {
        VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(voxelshapemerger.a().size() - 1, voxelshapemerger1.a().size() - 1, voxelshapemerger2.a().size() - 1);
        int[] aint = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        voxelshapemerger.a((i, j, k) -> {
            boolean[] aboolean = new boolean[]{false};
            boolean flag = voxelshapemerger1.a((l, i1, j1) -> {
                boolean[] aboolean1 = new boolean[]{false};
                boolean flag1 = voxelshapemerger2.a((k1, l1, i2) -> {
                    boolean flag2 = operatorboolean.apply(voxelshapediscrete.c(i, l, k1), voxelshapediscrete1.c(j, i1, l1));

                    if (flag2) {
                        voxelshapebitset.d.set(voxelshapebitset.a(k, j1, i2));
                        aint[2] = Math.min(aint[2], i2);
                        aint[5] = Math.max(aint[5], i2);
                        aboolean1[0] = true;
                    }

                    return true;
                });

                if (aboolean1[0]) {
                    aint[1] = Math.min(aint[1], j1);
                    aint[4] = Math.max(aint[4], j1);
                    aboolean[0] = true;
                }

                return flag1;
            });

            if (aboolean[0]) {
                aint[0] = Math.min(aint[0], k);
                aint[3] = Math.max(aint[3], k);
            }

            return flag;
        });
        voxelshapebitset.e = aint[0];
        voxelshapebitset.f = aint[1];
        voxelshapebitset.g = aint[2];
        voxelshapebitset.h = aint[3] + 1;
        voxelshapebitset.i = aint[4] + 1;
        voxelshapebitset.j = aint[5] + 1;
        return voxelshapebitset;
    }
}
