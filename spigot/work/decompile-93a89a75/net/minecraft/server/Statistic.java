package net.minecraft.server;

import java.util.Objects;
import javax.annotation.Nullable;

public class Statistic<T> extends IScoreboardCriteria {

    private final Counter o;
    private final T p;
    private final StatisticWrapper<T> q;

    protected Statistic(StatisticWrapper<T> statisticwrapper, T t0, Counter counter) {
        super(a(statisticwrapper, t0));
        this.q = statisticwrapper;
        this.o = counter;
        this.p = t0;
    }

    public static <T> String a(StatisticWrapper<T> statisticwrapper, T t0) {
        return a(IRegistry.STATS.getKey(statisticwrapper)) + ":" + a(statisticwrapper.getRegistry().getKey(t0));
    }

    private static <T> String a(@Nullable MinecraftKey minecraftkey) {
        return minecraftkey.toString().replace(':', '.');
    }

    public StatisticWrapper<T> getWrapper() {
        return this.q;
    }

    public T b() {
        return this.p;
    }

    public boolean equals(Object object) {
        return this == object || object instanceof Statistic && Objects.equals(this.getName(), ((Statistic) object).getName());
    }

    public int hashCode() {
        return this.getName().hashCode();
    }

    public String toString() {
        return "Stat{name=" + this.getName() + ", formatter=" + this.o + '}';
    }
}
