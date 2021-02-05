package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class WeightedList<U> {

    private final List<WeightedList<U>.a<? extends U>> a;
    private final Random b;

    public WeightedList() {
        this(new Random());
    }

    public WeightedList(Random random) {
        this.a = Lists.newArrayList();
        this.b = random;
    }

    public void a(U u0, int i) {
        this.a.add(new WeightedList.a<>(u0, i));
    }

    public void a() {
        this.a.forEach((weightedlist_a) -> {
            weightedlist_a.a(this.b.nextFloat());
        });
        this.a.sort(Comparator.comparingDouble(WeightedList.a::a));
    }

    public Stream<? extends U> b() {
        return this.a.stream().map(WeightedList.a::b);
    }

    public String toString() {
        return "WeightedList[" + this.a + "]";
    }

    class a<T> {

        private final T b;
        private final int c;
        private double d;

        private a(Object object, int i) {
            this.c = i;
            this.b = object;
        }

        public double a() {
            return this.d;
        }

        public void a(float f) {
            this.d = -Math.pow((double) f, (double) (1.0F / (float) this.c));
        }

        public T b() {
            return this.b;
        }

        public String toString() {
            return "" + this.c + ":" + this.b;
        }
    }
}
