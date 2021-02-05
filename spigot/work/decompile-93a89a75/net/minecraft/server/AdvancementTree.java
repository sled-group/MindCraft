package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class AdvancementTree {

    private final Advancement a;
    private final AdvancementTree b;
    private final AdvancementTree c;
    private final int d;
    private final List<AdvancementTree> e = Lists.newArrayList();
    private AdvancementTree f;
    private AdvancementTree g;
    private int h;
    private float i;
    private float j;
    private float k;
    private float l;

    public AdvancementTree(Advancement advancement, @Nullable AdvancementTree advancementtree, @Nullable AdvancementTree advancementtree1, int i, int j) {
        if (advancement.c() == null) {
            throw new IllegalArgumentException("Can't position an invisible advancement!");
        } else {
            this.a = advancement;
            this.b = advancementtree;
            this.c = advancementtree1;
            this.d = i;
            this.f = this;
            this.h = j;
            this.i = -1.0F;
            AdvancementTree advancementtree2 = null;

            Advancement advancement1;

            for (Iterator iterator = advancement.e().iterator(); iterator.hasNext(); advancementtree2 = this.a(advancement1, advancementtree2)) {
                advancement1 = (Advancement) iterator.next();
            }

        }
    }

    @Nullable
    private AdvancementTree a(Advancement advancement, @Nullable AdvancementTree advancementtree) {
        Advancement advancement1;

        if (advancement.c() != null) {
            advancementtree = new AdvancementTree(advancement, this, advancementtree, this.e.size() + 1, this.h + 1);
            this.e.add(advancementtree);
        } else {
            for (Iterator iterator = advancement.e().iterator(); iterator.hasNext(); advancementtree = this.a(advancement1, advancementtree)) {
                advancement1 = (Advancement) iterator.next();
            }
        }

        return advancementtree;
    }

    private void a() {
        if (this.e.isEmpty()) {
            if (this.c != null) {
                this.i = this.c.i + 1.0F;
            } else {
                this.i = 0.0F;
            }

        } else {
            AdvancementTree advancementtree = null;

            AdvancementTree advancementtree1;

            for (Iterator iterator = this.e.iterator(); iterator.hasNext(); advancementtree = advancementtree1.a(advancementtree == null ? advancementtree1 : advancementtree)) {
                advancementtree1 = (AdvancementTree) iterator.next();
                advancementtree1.a();
            }

            this.b();
            float f = (((AdvancementTree) this.e.get(0)).i + ((AdvancementTree) this.e.get(this.e.size() - 1)).i) / 2.0F;

            if (this.c != null) {
                this.i = this.c.i + 1.0F;
                this.j = this.i - f;
            } else {
                this.i = f;
            }

        }
    }

    private float a(float f, int i, float f1) {
        this.i += f;
        this.h = i;
        if (this.i < f1) {
            f1 = this.i;
        }

        AdvancementTree advancementtree;

        for (Iterator iterator = this.e.iterator(); iterator.hasNext(); f1 = advancementtree.a(f + this.j, i + 1, f1)) {
            advancementtree = (AdvancementTree) iterator.next();
        }

        return f1;
    }

    private void a(float f) {
        this.i += f;
        Iterator iterator = this.e.iterator();

        while (iterator.hasNext()) {
            AdvancementTree advancementtree = (AdvancementTree) iterator.next();

            advancementtree.a(f);
        }

    }

    private void b() {
        float f = 0.0F;
        float f1 = 0.0F;

        for (int i = this.e.size() - 1; i >= 0; --i) {
            AdvancementTree advancementtree = (AdvancementTree) this.e.get(i);

            advancementtree.i += f;
            advancementtree.j += f;
            f1 += advancementtree.k;
            f += advancementtree.l + f1;
        }

    }

    @Nullable
    private AdvancementTree c() {
        return this.g != null ? this.g : (!this.e.isEmpty() ? (AdvancementTree) this.e.get(0) : null);
    }

    @Nullable
    private AdvancementTree d() {
        return this.g != null ? this.g : (!this.e.isEmpty() ? (AdvancementTree) this.e.get(this.e.size() - 1) : null);
    }

    private AdvancementTree a(AdvancementTree advancementtree) {
        if (this.c == null) {
            return advancementtree;
        } else {
            AdvancementTree advancementtree1 = this;
            AdvancementTree advancementtree2 = this;
            AdvancementTree advancementtree3 = this.c;
            AdvancementTree advancementtree4 = (AdvancementTree) this.b.e.get(0);
            float f = this.j;
            float f1 = this.j;
            float f2 = advancementtree3.j;

            float f3;

            for (f3 = advancementtree4.j; advancementtree3.d() != null && advancementtree1.c() != null; f1 += advancementtree2.j) {
                advancementtree3 = advancementtree3.d();
                advancementtree1 = advancementtree1.c();
                advancementtree4 = advancementtree4.c();
                advancementtree2 = advancementtree2.d();
                advancementtree2.f = this;
                float f4 = advancementtree3.i + f2 - (advancementtree1.i + f) + 1.0F;

                if (f4 > 0.0F) {
                    advancementtree3.a(this, advancementtree).a(this, f4);
                    f += f4;
                    f1 += f4;
                }

                f2 += advancementtree3.j;
                f += advancementtree1.j;
                f3 += advancementtree4.j;
            }

            if (advancementtree3.d() != null && advancementtree2.d() == null) {
                advancementtree2.g = advancementtree3.d();
                advancementtree2.j += f2 - f1;
            } else {
                if (advancementtree1.c() != null && advancementtree4.c() == null) {
                    advancementtree4.g = advancementtree1.c();
                    advancementtree4.j += f - f3;
                }

                advancementtree = this;
            }

            return advancementtree;
        }
    }

    private void a(AdvancementTree advancementtree, float f) {
        float f1 = (float) (advancementtree.d - this.d);

        if (f1 != 0.0F) {
            advancementtree.k -= f / f1;
            this.k += f / f1;
        }

        advancementtree.l += f;
        advancementtree.i += f;
        advancementtree.j += f;
    }

    private AdvancementTree a(AdvancementTree advancementtree, AdvancementTree advancementtree1) {
        return this.f != null && advancementtree.b.e.contains(this.f) ? this.f : advancementtree1;
    }

    private void e() {
        if (this.a.c() != null) {
            this.a.c().a((float) this.h, this.i);
        }

        if (!this.e.isEmpty()) {
            Iterator iterator = this.e.iterator();

            while (iterator.hasNext()) {
                AdvancementTree advancementtree = (AdvancementTree) iterator.next();

                advancementtree.e();
            }
        }

    }

    public static void a(Advancement advancement) {
        if (advancement.c() == null) {
            throw new IllegalArgumentException("Can't position children of an invisible root!");
        } else {
            AdvancementTree advancementtree = new AdvancementTree(advancement, (AdvancementTree) null, (AdvancementTree) null, 1, 0);

            advancementtree.a();
            float f = advancementtree.a(0.0F, 0, advancementtree.i);

            if (f < 0.0F) {
                advancementtree.a(-f);
            }

            advancementtree.e();
        }
    }
}
