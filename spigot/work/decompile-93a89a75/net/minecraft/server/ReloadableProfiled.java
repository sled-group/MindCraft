package net.minecraft.server;

import com.google.common.base.Stopwatch;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReloadableProfiled extends Reloadable<ReloadableProfiled.a> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Stopwatch e = Stopwatch.createUnstarted();

    public ReloadableProfiled(IResourceManager iresourcemanager, List<IReloadListener> list, Executor executor, Executor executor1, CompletableFuture<Unit> completablefuture) {
        super(executor, executor1, iresourcemanager, list, (ireloadlistener_a, iresourcemanager1, ireloadlistener, executor2, executor3) -> {
            AtomicLong atomiclong = new AtomicLong();
            AtomicLong atomiclong1 = new AtomicLong();
            MethodProfiler methodprofiler = new MethodProfiler(SystemUtils.getMonotonicNanos(), () -> {
                return 0;
            });
            MethodProfiler methodprofiler1 = new MethodProfiler(SystemUtils.getMonotonicNanos(), () -> {
                return 0;
            });
            CompletableFuture<Void> completablefuture1 = ireloadlistener.a(ireloadlistener_a, iresourcemanager1, methodprofiler, methodprofiler1, (runnable) -> {
                executor2.execute(() -> {
                    long i = SystemUtils.getMonotonicNanos();

                    runnable.run();
                    atomiclong.addAndGet(SystemUtils.getMonotonicNanos() - i);
                });
            }, (runnable) -> {
                executor3.execute(() -> {
                    long i = SystemUtils.getMonotonicNanos();

                    runnable.run();
                    atomiclong1.addAndGet(SystemUtils.getMonotonicNanos() - i);
                });
            });

            return completablefuture1.thenApplyAsync((ovoid) -> {
                return new ReloadableProfiled.a(ireloadlistener.getClass().getSimpleName(), methodprofiler.d(), methodprofiler1.d(), atomiclong, atomiclong1);
            }, executor1);
        }, completablefuture);
        this.e.start();
        this.c.thenAcceptAsync(this::a, executor1);
    }

    private void a(List<ReloadableProfiled.a> list) {
        this.e.stop();
        int i = 0;

        ReloadableProfiled.LOGGER.info("Resource reload finished after " + this.e.elapsed(TimeUnit.MILLISECONDS) + " ms");

        int j;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); i += j) {
            ReloadableProfiled.a reloadableprofiled_a = (ReloadableProfiled.a) iterator.next();
            MethodProfilerResults methodprofilerresults = reloadableprofiled_a.b;
            MethodProfilerResults methodprofilerresults1 = reloadableprofiled_a.c;
            int k = (int) ((double) reloadableprofiled_a.d.get() / 1000000.0D);

            j = (int) ((double) reloadableprofiled_a.e.get() / 1000000.0D);
            int l = k + j;
            String s = reloadableprofiled_a.a;

            ReloadableProfiled.LOGGER.info(s + " took approximately " + l + " ms (" + k + " ms preparing, " + j + " ms applying)");
            String s1 = methodprofilerresults.e();

            if (s1.length() > 0) {
                ReloadableProfiled.LOGGER.debug(s + " preparations:\n" + s1);
            }

            String s2 = methodprofilerresults1.e();

            if (s2.length() > 0) {
                ReloadableProfiled.LOGGER.debug(s + " reload:\n" + s2);
            }

            ReloadableProfiled.LOGGER.info("----------");
        }

        ReloadableProfiled.LOGGER.info("Total blocking time: " + i + " ms");
    }

    public static class a {

        private final String a;
        private final MethodProfilerResults b;
        private final MethodProfilerResults c;
        private final AtomicLong d;
        private final AtomicLong e;

        private a(String s, MethodProfilerResults methodprofilerresults, MethodProfilerResults methodprofilerresults1, AtomicLong atomiclong, AtomicLong atomiclong1) {
            this.a = s;
            this.b = methodprofilerresults;
            this.c = methodprofilerresults1;
            this.d = atomiclong;
            this.e = atomiclong1;
        }
    }
}
