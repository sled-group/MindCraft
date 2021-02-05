package net.minecraft.server;

public abstract class VoxelShapeDiscrete {

    private static final EnumDirection.EnumAxis[] d = EnumDirection.EnumAxis.values();
    protected final int a;
    protected final int b;
    protected final int c;

    protected VoxelShapeDiscrete(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    public boolean a(EnumAxisCycle enumaxiscycle, int i, int j, int k) {
        return this.c(enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.X), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Y), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Z));
    }

    public boolean c(int i, int j, int k) {
        return i >= 0 && j >= 0 && k >= 0 ? (i < this.a && j < this.b && k < this.c ? this.b(i, j, k) : false) : false;
    }

    public boolean b(EnumAxisCycle enumaxiscycle, int i, int j, int k) {
        return this.b(enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.X), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Y), enumaxiscycle.a(i, j, k, EnumDirection.EnumAxis.Z));
    }

    public abstract boolean b(int i, int j, int k);

    public abstract void a(int i, int j, int k, boolean flag, boolean flag1);

    public boolean a() {
        EnumDirection.EnumAxis[] aenumdirection_enumaxis = VoxelShapeDiscrete.d;
        int i = aenumdirection_enumaxis.length;

        for (int j = 0; j < i; ++j) {
            EnumDirection.EnumAxis enumdirection_enumaxis = aenumdirection_enumaxis[j];

            if (this.a(enumdirection_enumaxis) >= this.b(enumdirection_enumaxis)) {
                return true;
            }
        }

        return false;
    }

    public abstract int a(EnumDirection.EnumAxis enumdirection_enumaxis);

    public abstract int b(EnumDirection.EnumAxis enumdirection_enumaxis);

    public int c(EnumDirection.EnumAxis enumdirection_enumaxis) {
        return enumdirection_enumaxis.a(this.a, this.b, this.c);
    }

    public int b() {
        return this.c(EnumDirection.EnumAxis.X);
    }

    public int c() {
        return this.c(EnumDirection.EnumAxis.Y);
    }

    public int d() {
        return this.c(EnumDirection.EnumAxis.Z);
    }

    protected boolean a(int i, int j, int k, int l) {
        for (int i1 = i; i1 < j; ++i1) {
            if (!this.c(k, l, i1)) {
                return false;
            }
        }

        return true;
    }

    protected void a(int i, int j, int k, int l, boolean flag) {
        for (int i1 = i; i1 < j; ++i1) {
            this.a(k, l, i1, false, flag);
        }

    }

    protected boolean a(int i, int j, int k, int l, int i1) {
        for (int j1 = i; j1 < j; ++j1) {
            if (!this.a(k, l, j1, i1)) {
                return false;
            }
        }

        return true;
    }

    public void b(VoxelShapeDiscrete.b voxelshapediscrete_b, boolean flag) {
        VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(this);

        for (int i = 0; i <= this.a; ++i) {
            for (int j = 0; j <= this.b; ++j) {
                int k = -1;

                for (int l = 0; l <= this.c; ++l) {
                    if (voxelshapebitset.c(i, j, l)) {
                        if (flag) {
                            if (k == -1) {
                                k = l;
                            }
                        } else {
                            voxelshapediscrete_b.consume(i, j, l, i + 1, j + 1, l + 1);
                        }
                    } else if (k != -1) {
                        int i1 = i;
                        int j1 = i;
                        int k1 = j;
                        int l1 = j;

                        voxelshapebitset.a(k, l, i, j, false);

                        while (voxelshapebitset.a(k, l, i1 - 1, k1)) {
                            voxelshapebitset.a(k, l, i1 - 1, k1, false);
                            --i1;
                        }

                        while (voxelshapebitset.a(k, l, j1 + 1, k1)) {
                            voxelshapebitset.a(k, l, j1 + 1, k1, false);
                            ++j1;
                        }

                        int i2;

                        while (voxelshapebitset.a(i1, j1 + 1, k, l, k1 - 1)) {
                            for (i2 = i1; i2 <= j1; ++i2) {
                                voxelshapebitset.a(k, l, i2, k1 - 1, false);
                            }

                            --k1;
                        }

                        while (voxelshapebitset.a(i1, j1 + 1, k, l, l1 + 1)) {
                            for (i2 = i1; i2 <= j1; ++i2) {
                                voxelshapebitset.a(k, l, i2, l1 + 1, false);
                            }

                            ++l1;
                        }

                        voxelshapediscrete_b.consume(i1, k1, k, j1 + 1, l1 + 1, l);
                        k = -1;
                    }
                }
            }
        }

    }

    public void a(VoxelShapeDiscrete.a voxelshapediscrete_a) {
        this.a(voxelshapediscrete_a, EnumAxisCycle.NONE);
        this.a(voxelshapediscrete_a, EnumAxisCycle.FORWARD);
        this.a(voxelshapediscrete_a, EnumAxisCycle.BACKWARD);
    }

    private void a(VoxelShapeDiscrete.a voxelshapediscrete_a, EnumAxisCycle enumaxiscycle) {
        EnumAxisCycle enumaxiscycle1 = enumaxiscycle.a();
        EnumDirection.EnumAxis enumdirection_enumaxis = enumaxiscycle1.a(EnumDirection.EnumAxis.Z);
        int i = this.c(enumaxiscycle1.a(EnumDirection.EnumAxis.X));
        int j = this.c(enumaxiscycle1.a(EnumDirection.EnumAxis.Y));
        int k = this.c(enumdirection_enumaxis);
        EnumDirection enumdirection = EnumDirection.a(enumdirection_enumaxis, EnumDirection.EnumAxisDirection.NEGATIVE);
        EnumDirection enumdirection1 = EnumDirection.a(enumdirection_enumaxis, EnumDirection.EnumAxisDirection.POSITIVE);

        for (int l = 0; l < i; ++l) {
            for (int i1 = 0; i1 < j; ++i1) {
                boolean flag = false;

                for (int j1 = 0; j1 <= k; ++j1) {
                    boolean flag1 = j1 != k && this.b(enumaxiscycle1, l, i1, j1);

                    if (!flag && flag1) {
                        voxelshapediscrete_a.consume(enumdirection, enumaxiscycle1.a(l, i1, j1, EnumDirection.EnumAxis.X), enumaxiscycle1.a(l, i1, j1, EnumDirection.EnumAxis.Y), enumaxiscycle1.a(l, i1, j1, EnumDirection.EnumAxis.Z));
                    }

                    if (flag && !flag1) {
                        voxelshapediscrete_a.consume(enumdirection1, enumaxiscycle1.a(l, i1, j1 - 1, EnumDirection.EnumAxis.X), enumaxiscycle1.a(l, i1, j1 - 1, EnumDirection.EnumAxis.Y), enumaxiscycle1.a(l, i1, j1 - 1, EnumDirection.EnumAxis.Z));
                    }

                    flag = flag1;
                }
            }
        }

    }

    public interface a {

        void consume(EnumDirection enumdirection, int i, int j, int k);
    }

    public interface b {

        void consume(int i, int j, int k, int l, int i1, int j1);
    }
}
