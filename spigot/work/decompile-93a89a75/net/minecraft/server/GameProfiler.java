package net.minecraft.server;

import java.time.Duration;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameProfiler implements GameProfilerFiller {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final long b = Duration.ofMillis(300L).toNanos();
    private final IntSupplier c;
    private final GameProfiler.b d = new GameProfiler.b();
    private final GameProfiler.b e = new GameProfiler.b();

    public GameProfiler(IntSupplier intsupplier) {
        this.c = intsupplier;
    }

    public GameProfiler.a d() {
        return this.d;
    }

    @Override
    public void a() {
        this.d.a.a();
        this.e.a.a();
    }

    @Override
    public void b() {
        this.d.a.b();
        this.e.a.b();
    }

    @Override
    public void enter(String s) {
        this.d.a.enter(s);
        this.e.a.enter(s);
    }

    @Override
    public void a(Supplier<String> supplier) {
        this.d.a.a(supplier);
        this.e.a.a(supplier);
    }

    @Override
    public void exit() {
        this.d.a.exit();
        this.e.a.exit();
    }

    @Override
    public void exitEnter(String s) {
        this.d.a.exitEnter(s);
        this.e.a.exitEnter(s);
    }

    class b implements GameProfiler.a {

        protected GameProfilerFillerActive a;

        private b() {
            this.a = GameProfilerDisabled.a;
        }

        @Override
        public boolean a() {
            return this.a != GameProfilerDisabled.a;
        }

        @Override
        public MethodProfilerResults b() {
            MethodProfilerResults methodprofilerresults = this.a.d();

            this.a = GameProfilerDisabled.a;
            return methodprofilerresults;
        }

        @Override
        public void d() {
            if (this.a == GameProfilerDisabled.a) {
                this.a = new MethodProfiler(SystemUtils.getMonotonicNanos(), GameProfiler.this.c);
            }

        }
    }

    public interface a {

        boolean a();

        MethodProfilerResults b();

        void d();
    }
}
