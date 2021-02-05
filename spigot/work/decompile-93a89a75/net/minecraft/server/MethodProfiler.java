package net.minecraft.server;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.time.Duration;
import java.util.List;
import java.util.function.IntSupplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public class MethodProfiler implements GameProfilerFillerActive {

    private static final long a = Duration.ofMillis(100L).toNanos();
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<String> c = Lists.newArrayList();
    private final LongList d = new LongArrayList();
    private final Object2LongMap<String> e = new Object2LongOpenHashMap();
    private final Object2LongMap<String> f = new Object2LongOpenHashMap();
    private final IntSupplier g;
    private final long h;
    private final int i;
    private String j = "";
    private boolean k;

    public MethodProfiler(long i, IntSupplier intsupplier) {
        this.h = i;
        this.i = intsupplier.getAsInt();
        this.g = intsupplier;
    }

    @Override
    public void a() {
        if (this.k) {
            MethodProfiler.LOGGER.error("Profiler tick already started - missing endTick()?");
        } else {
            this.k = true;
            this.j = "";
            this.c.clear();
            this.enter("root");
        }
    }

    @Override
    public void b() {
        if (!this.k) {
            MethodProfiler.LOGGER.error("Profiler tick already ended - missing startTick()?");
        } else {
            this.exit();
            this.k = false;
            if (!this.j.isEmpty()) {
                MethodProfiler.LOGGER.error("Profiler tick ended before path was fully popped (remainder: '{}'). Mismatched push/pop?", new Supplier[]{() -> {
                            return MethodProfilerResults.b(this.j);
                        }});
            }

        }
    }

    @Override
    public void enter(String s) {
        if (!this.k) {
            MethodProfiler.LOGGER.error("Cannot push '{}' to profiler if profiler tick hasn't started - missing startTick()?", s);
        } else {
            if (!this.j.isEmpty()) {
                this.j = this.j + '\u001e';
            }

            this.j = this.j + s;
            this.c.add(this.j);
            this.d.add(SystemUtils.getMonotonicNanos());
        }
    }

    @Override
    public void a(java.util.function.Supplier<String> java_util_function_supplier) {
        this.enter((String) java_util_function_supplier.get());
    }

    @Override
    public void exit() {
        if (!this.k) {
            MethodProfiler.LOGGER.error("Cannot pop from profiler if profiler tick hasn't started - missing startTick()?");
        } else if (this.d.isEmpty()) {
            MethodProfiler.LOGGER.error("Tried to pop one too many times! Mismatched push() and pop()?");
        } else {
            long i = SystemUtils.getMonotonicNanos();
            long j = this.d.removeLong(this.d.size() - 1);

            this.c.remove(this.c.size() - 1);
            long k = i - j;

            this.e.put(this.j, this.e.getLong(this.j) + k);
            this.f.put(this.j, this.f.getLong(this.j) + 1L);
            if (k > MethodProfiler.a) {
                MethodProfiler.LOGGER.warn("Something's taking too long! '{}' took aprox {} ms", new Supplier[]{() -> {
                            return MethodProfilerResults.b(this.j);
                        }, () -> {
                            return (double) k / 1000000.0D;
                        }});
            }

            this.j = this.c.isEmpty() ? "" : (String) this.c.get(this.c.size() - 1);
        }
    }

    @Override
    public void exitEnter(String s) {
        this.exit();
        this.enter(s);
    }

    @Override
    public MethodProfilerResults d() {
        return new MethodProfilerResultsFilled(this.e, this.f, this.h, this.i, SystemUtils.getMonotonicNanos(), this.g.getAsInt());
    }
}
