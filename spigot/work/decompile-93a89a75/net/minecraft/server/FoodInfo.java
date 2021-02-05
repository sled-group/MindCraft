package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class FoodInfo {

    private final int a;
    private final float b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final List<Pair<MobEffect, Float>> f;

    private FoodInfo(int i, float f, boolean flag, boolean flag1, boolean flag2, List<Pair<MobEffect, Float>> list) {
        this.a = i;
        this.b = f;
        this.c = flag;
        this.d = flag1;
        this.e = flag2;
        this.f = list;
    }

    public int getNutrition() {
        return this.a;
    }

    public float getSaturationModifier() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public List<Pair<MobEffect, Float>> f() {
        return this.f;
    }

    public static class a {

        private int a;
        private float b;
        private boolean c;
        private boolean d;
        private boolean e;
        private final List<Pair<MobEffect, Float>> f = Lists.newArrayList();

        public a() {}

        public FoodInfo.a a(int i) {
            this.a = i;
            return this;
        }

        public FoodInfo.a a(float f) {
            this.b = f;
            return this;
        }

        public FoodInfo.a a() {
            this.c = true;
            return this;
        }

        public FoodInfo.a b() {
            this.d = true;
            return this;
        }

        public FoodInfo.a c() {
            this.e = true;
            return this;
        }

        public FoodInfo.a a(MobEffect mobeffect, float f) {
            this.f.add(Pair.of(mobeffect, f));
            return this;
        }

        public FoodInfo d() {
            return new FoodInfo(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }
}
