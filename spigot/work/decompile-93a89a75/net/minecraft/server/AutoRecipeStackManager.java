package net.minecraft.server;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class AutoRecipeStackManager {

    public final Int2IntMap a = new Int2IntOpenHashMap();

    public AutoRecipeStackManager() {}

    public void a(ItemStack itemstack) {
        if (!itemstack.f() && !itemstack.hasEnchantments() && !itemstack.hasName()) {
            this.b(itemstack);
        }

    }

    public void b(ItemStack itemstack) {
        this.a(itemstack, 64);
    }

    public void a(ItemStack itemstack, int i) {
        if (!itemstack.isEmpty()) {
            int j = c(itemstack);
            int k = Math.min(i, itemstack.getCount());

            this.b(j, k);
        }

    }

    public static int c(ItemStack itemstack) {
        return IRegistry.ITEM.a((Object) itemstack.getItem());
    }

    private boolean b(int i) {
        return this.a.get(i) > 0;
    }

    private int a(int i, int j) {
        int k = this.a.get(i);

        if (k >= j) {
            this.a.put(i, k - j);
            return i;
        } else {
            return 0;
        }
    }

    private void b(int i, int j) {
        this.a.put(i, this.a.get(i) + j);
    }

    public boolean a(IRecipe<?> irecipe, @Nullable IntList intlist) {
        return this.a(irecipe, intlist, 1);
    }

    public boolean a(IRecipe<?> irecipe, @Nullable IntList intlist, int i) {
        return (new AutoRecipeStackManager.a(irecipe)).a(i, intlist);
    }

    public int b(IRecipe<?> irecipe, @Nullable IntList intlist) {
        return this.a(irecipe, Integer.MAX_VALUE, intlist);
    }

    public int a(IRecipe<?> irecipe, int i, @Nullable IntList intlist) {
        return (new AutoRecipeStackManager.a(irecipe)).b(i, intlist);
    }

    public static ItemStack a(int i) {
        return i == 0 ? ItemStack.a : new ItemStack(Item.getById(i));
    }

    public void a() {
        this.a.clear();
    }

    class a {

        private final IRecipe<?> b;
        private final List<RecipeItemStack> c = Lists.newArrayList();
        private final int d;
        private final int[] e;
        private final int f;
        private final BitSet g;
        private final IntList h = new IntArrayList();

        public a(IRecipe irecipe) {
            this.b = irecipe;
            this.c.addAll(irecipe.a());
            this.c.removeIf(RecipeItemStack::d);
            this.d = this.c.size();
            this.e = this.a();
            this.f = this.e.length;
            this.g = new BitSet(this.d + this.f + this.d + this.d * this.f);

            for (int i = 0; i < this.c.size(); ++i) {
                IntList intlist = ((RecipeItemStack) this.c.get(i)).b();

                for (int j = 0; j < this.f; ++j) {
                    if (intlist.contains(this.e[j])) {
                        this.g.set(this.d(true, j, i));
                    }
                }
            }

        }

        public boolean a(int i, @Nullable IntList intlist) {
            if (i <= 0) {
                return true;
            } else {
                int j;

                for (j = 0; this.a(i); ++j) {
                    AutoRecipeStackManager.this.a(this.e[this.h.getInt(0)], i);
                    int k = this.h.size() - 1;

                    this.c(this.h.getInt(k));

                    for (int l = 0; l < k; ++l) {
                        this.c((l & 1) == 0, this.h.get(l), this.h.get(l + 1));
                    }

                    this.h.clear();
                    this.g.clear(0, this.d + this.f);
                }

                boolean flag = j == this.d;
                boolean flag1 = flag && intlist != null;

                if (flag1) {
                    intlist.clear();
                }

                this.g.clear(0, this.d + this.f + this.d);
                int i1 = 0;
                List<RecipeItemStack> list = this.b.a();

                for (int j1 = 0; j1 < list.size(); ++j1) {
                    if (flag1 && ((RecipeItemStack) list.get(j1)).d()) {
                        intlist.add(0);
                    } else {
                        for (int k1 = 0; k1 < this.f; ++k1) {
                            if (this.b(false, i1, k1)) {
                                this.c(true, k1, i1);
                                AutoRecipeStackManager.this.b(this.e[k1], i);
                                if (flag1) {
                                    intlist.add(this.e[k1]);
                                }
                            }
                        }

                        ++i1;
                    }
                }

                return flag;
            }
        }

        private int[] a() {
            IntAVLTreeSet intavltreeset = new IntAVLTreeSet();
            Iterator iterator = this.c.iterator();

            while (iterator.hasNext()) {
                RecipeItemStack recipeitemstack = (RecipeItemStack) iterator.next();

                intavltreeset.addAll(recipeitemstack.b());
            }

            IntIterator intiterator = intavltreeset.iterator();

            while (intiterator.hasNext()) {
                if (!AutoRecipeStackManager.this.b(intiterator.nextInt())) {
                    intiterator.remove();
                }
            }

            return intavltreeset.toIntArray();
        }

        private boolean a(int i) {
            int j = this.f;

            for (int k = 0; k < j; ++k) {
                if (AutoRecipeStackManager.this.a.get(this.e[k]) >= i) {
                    this.a(false, k);

                    while (!this.h.isEmpty()) {
                        int l = this.h.size();
                        boolean flag = (l & 1) == 1;
                        int i1 = this.h.getInt(l - 1);

                        if (!flag && !this.b(i1)) {
                            break;
                        }

                        int j1 = flag ? this.d : j;

                        int k1;

                        for (k1 = 0; k1 < j1; ++k1) {
                            if (!this.b(flag, k1) && this.a(flag, i1, k1) && this.b(flag, i1, k1)) {
                                this.a(flag, k1);
                                break;
                            }
                        }

                        k1 = this.h.size();
                        if (k1 == l) {
                            this.h.removeInt(k1 - 1);
                        }
                    }

                    if (!this.h.isEmpty()) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean b(int i) {
            return this.g.get(this.d(i));
        }

        private void c(int i) {
            this.g.set(this.d(i));
        }

        private int d(int i) {
            return this.d + this.f + i;
        }

        private boolean a(boolean flag, int i, int j) {
            return this.g.get(this.d(flag, i, j));
        }

        private boolean b(boolean flag, int i, int j) {
            return flag != this.g.get(1 + this.d(flag, i, j));
        }

        private void c(boolean flag, int i, int j) {
            this.g.flip(1 + this.d(flag, i, j));
        }

        private int d(boolean flag, int i, int j) {
            int k = flag ? i * this.d + j : j * this.d + i;

            return this.d + this.f + this.d + 2 * k;
        }

        private void a(boolean flag, int i) {
            this.g.set(this.c(flag, i));
            this.h.add(i);
        }

        private boolean b(boolean flag, int i) {
            return this.g.get(this.c(flag, i));
        }

        private int c(boolean flag, int i) {
            return (flag ? 0 : this.d) + i;
        }

        public int b(int i, @Nullable IntList intlist) {
            int j = 0;
            int k = Math.min(i, this.b()) + 1;

            while (true) {
                while (true) {
                    int l = (j + k) / 2;

                    if (this.a(l, (IntList) null)) {
                        if (k - j <= 1) {
                            if (l > 0) {
                                this.a(l, intlist);
                            }

                            return l;
                        }

                        j = l;
                    } else {
                        k = l;
                    }
                }
            }
        }

        private int b() {
            int i = Integer.MAX_VALUE;
            Iterator iterator = this.c.iterator();

            while (iterator.hasNext()) {
                RecipeItemStack recipeitemstack = (RecipeItemStack) iterator.next();
                int j = 0;

                int k;

                for (IntListIterator intlistiterator = recipeitemstack.b().iterator(); intlistiterator.hasNext(); j = Math.max(j, AutoRecipeStackManager.this.a.get(k))) {
                    k = (Integer) intlistiterator.next();
                }

                if (i > 0) {
                    i = Math.min(i, j);
                }
            }

            return i;
        }
    }
}
