package net.minecraft.server;

import it.unimi.dsi.fastutil.longs.Long2ByteFunction;
import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;

public abstract class LightEngineGraph {

    private final int a;
    private final LongLinkedOpenHashSet[] b;
    private final Long2ByteFunction c;
    private int d;
    private volatile boolean e;

    protected LightEngineGraph(int i, final int j, final int k) {
        if (i >= 254) {
            throw new IllegalArgumentException("Level count must be < 254.");
        } else {
            this.a = i;
            this.b = new LongLinkedOpenHashSet[i];

            for (int l = 0; l < i; ++l) {
                this.b[l] = new LongLinkedOpenHashSet(j, 0.5F) {
                    protected void rehash(int i1) {
                        if (i1 > j) {
                            super.rehash(i1);
                        }

                    }
                };
            }

            this.c = new Long2ByteOpenHashMap(k, 0.5F) {
                protected void rehash(int i1) {
                    if (i1 > k) {
                        super.rehash(i1);
                    }

                }
            };
            this.c.defaultReturnValue((byte) -1);
            this.d = i;
        }
    }

    private int a(int i, int j) {
        int k = i;

        if (i > j) {
            k = j;
        }

        if (k > this.a - 1) {
            k = this.a - 1;
        }

        return k;
    }

    private void a(int i) {
        int j = this.d;

        this.d = i;

        for (int k = j + 1; k < i; ++k) {
            if (!this.b[k].isEmpty()) {
                this.d = k;
                break;
            }
        }

    }

    protected void e(long i) {
        int j = this.c.get(i) & 255;

        if (j != 255) {
            int k = this.c(i);
            int l = this.a(k, j);

            this.a(i, l, this.a, true);
            this.e = this.d < this.a;
        }
    }

    private void a(long i, int j, int k, boolean flag) {
        if (flag) {
            this.c.remove(i);
        }

        this.b[j].remove(i);
        if (this.b[j].isEmpty() && this.d == j) {
            this.a(k);
        }

    }

    private void a(long i, int j, int k) {
        this.c.put(i, (byte) j);
        this.b[k].add(i);
        if (this.d > k) {
            this.d = k;
        }

    }

    protected void f(long i) {
        this.a(i, i, this.a - 1, false);
    }

    protected void a(long i, long j, int k, boolean flag) {
        this.a(i, j, k, this.c(j), this.c.get(j) & 255, flag);
        this.e = this.d < this.a;
    }

    private void a(long i, long j, int k, int l, int i1, boolean flag) {
        if (!this.a(j)) {
            k = MathHelper.clamp(k, 0, this.a - 1);
            l = MathHelper.clamp(l, 0, this.a - 1);
            boolean flag1;

            if (i1 == 255) {
                flag1 = true;
                i1 = l;
            } else {
                flag1 = false;
            }

            int j1;

            if (flag) {
                j1 = Math.min(i1, k);
            } else {
                j1 = MathHelper.clamp(this.a(j, i, k), 0, this.a - 1);
            }

            int k1 = this.a(l, i1);

            if (l != j1) {
                int l1 = this.a(l, j1);

                if (k1 != l1 && !flag1) {
                    this.a(j, k1, l1, false);
                }

                this.a(j, j1, l1);
            } else if (!flag1) {
                this.a(j, k1, this.a, true);
            }

        }
    }

    protected final void b(long i, long j, int k, boolean flag) {
        int l = this.c.get(j) & 255;
        int i1 = MathHelper.clamp(this.b(i, j, k), 0, this.a - 1);

        if (flag) {
            this.a(i, j, i1, this.c(j), l, true);
        } else {
            boolean flag1;
            int j1;

            if (l == 255) {
                flag1 = true;
                j1 = MathHelper.clamp(this.c(j), 0, this.a - 1);
            } else {
                j1 = l;
                flag1 = false;
            }

            if (i1 == j1) {
                this.a(i, j, this.a - 1, flag1 ? j1 : this.c(j), l, false);
            }
        }

    }

    protected final boolean b() {
        return this.e;
    }

    protected final int b(int i) {
        if (this.d >= this.a) {
            return i;
        } else {
            while (this.d < this.a && i > 0) {
                --i;
                LongLinkedOpenHashSet longlinkedopenhashset = this.b[this.d];
                long j = longlinkedopenhashset.removeFirstLong();
                int k = MathHelper.clamp(this.c(j), 0, this.a - 1);

                if (longlinkedopenhashset.isEmpty()) {
                    this.a(this.a);
                }

                int l = this.c.remove(j) & 255;

                if (l < k) {
                    this.a(j, l);
                    this.a(j, l, true);
                } else if (l > k) {
                    this.a(j, l, this.a(this.a - 1, l));
                    this.a(j, this.a - 1);
                    this.a(j, k, false);
                }
            }

            this.e = this.d < this.a;
            return i;
        }
    }

    protected abstract boolean a(long i);

    protected abstract int a(long i, long j, int k);

    protected abstract void a(long i, int j, boolean flag);

    protected abstract int c(long i);

    protected abstract void a(long i, int j);

    protected abstract int b(long i, long j, int k);
}
