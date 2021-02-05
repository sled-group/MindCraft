package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WeightedRandom {

    public static int a(List<? extends WeightedRandom.WeightedRandomChoice> list) {
        int i = 0;
        int j = 0;

        for (int k = list.size(); j < k; ++j) {
            WeightedRandom.WeightedRandomChoice weightedrandom_weightedrandomchoice = (WeightedRandom.WeightedRandomChoice) list.get(j);

            i += weightedrandom_weightedrandomchoice.a;
        }

        return i;
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(Random random, List<T> list, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);

            return a(list, j);
        }
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(List<T> list, int i) {
        int j = 0;

        for (int k = list.size(); j < k; ++j) {
            T t0 = (WeightedRandom.WeightedRandomChoice) list.get(j);

            i -= t0.a;
            if (i < 0) {
                return t0;
            }
        }

        return null;
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(Random random, List<T> list) {
        return a(random, list, a(list));
    }

    public static class WeightedRandomChoice {

        protected final int a;

        public WeightedRandomChoice(int i) {
            this.a = i;
        }
    }
}
