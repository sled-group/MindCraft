package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.common.primitives.UnsignedLong;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomFunctionCallbackTimerQueue<T> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final CustomFunctionCallbackTimers<T> b;
    private final Queue<CustomFunctionCallbackTimerQueue.a<T>> c = new PriorityQueue(c());
    private UnsignedLong d;
    private final Map<String, CustomFunctionCallbackTimerQueue.a<T>> e;

    private static <T> Comparator<CustomFunctionCallbackTimerQueue.a<T>> c() {
        return (customfunctioncallbacktimerqueue_a, customfunctioncallbacktimerqueue_a1) -> {
            int i = Long.compare(customfunctioncallbacktimerqueue_a.a, customfunctioncallbacktimerqueue_a1.a);

            return i != 0 ? i : customfunctioncallbacktimerqueue_a.b.compareTo(customfunctioncallbacktimerqueue_a1.b);
        };
    }

    public CustomFunctionCallbackTimerQueue(CustomFunctionCallbackTimers<T> customfunctioncallbacktimers) {
        this.d = UnsignedLong.ZERO;
        this.e = Maps.newHashMap();
        this.b = customfunctioncallbacktimers;
    }

    public void a(T t0, long i) {
        while (true) {
            CustomFunctionCallbackTimerQueue.a<T> customfunctioncallbacktimerqueue_a = (CustomFunctionCallbackTimerQueue.a) this.c.peek();

            if (customfunctioncallbacktimerqueue_a == null || customfunctioncallbacktimerqueue_a.a > i) {
                return;
            }

            this.c.remove();
            this.e.remove(customfunctioncallbacktimerqueue_a.c);
            customfunctioncallbacktimerqueue_a.d.a(t0, this, i);
        }
    }

    private void c(String s, long i, CustomFunctionCallbackTimer<T> customfunctioncallbacktimer) {
        this.d = this.d.plus(UnsignedLong.ONE);
        CustomFunctionCallbackTimerQueue.a<T> customfunctioncallbacktimerqueue_a = new CustomFunctionCallbackTimerQueue.a<>(i, this.d, s, customfunctioncallbacktimer);

        this.e.put(s, customfunctioncallbacktimerqueue_a);
        this.c.add(customfunctioncallbacktimerqueue_a);
    }

    public boolean a(String s, long i, CustomFunctionCallbackTimer<T> customfunctioncallbacktimer) {
        if (this.e.containsKey(s)) {
            return false;
        } else {
            this.c(s, i, customfunctioncallbacktimer);
            return true;
        }
    }

    public void b(String s, long i, CustomFunctionCallbackTimer<T> customfunctioncallbacktimer) {
        CustomFunctionCallbackTimerQueue.a<T> customfunctioncallbacktimerqueue_a = (CustomFunctionCallbackTimerQueue.a) this.e.remove(s);

        if (customfunctioncallbacktimerqueue_a != null) {
            this.c.remove(customfunctioncallbacktimerqueue_a);
        }

        this.c(s, i, customfunctioncallbacktimer);
    }

    private void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Callback");
        CustomFunctionCallbackTimer<T> customfunctioncallbacktimer = this.b.a(nbttagcompound1);

        if (customfunctioncallbacktimer != null) {
            String s = nbttagcompound.getString("Name");
            long i = nbttagcompound.getLong("TriggerTime");

            this.a(s, i, customfunctioncallbacktimer);
        }

    }

    public void a(NBTTagList nbttaglist) {
        this.c.clear();
        this.e.clear();
        this.d = UnsignedLong.ZERO;
        if (!nbttaglist.isEmpty()) {
            if (nbttaglist.a_() != 10) {
                CustomFunctionCallbackTimerQueue.LOGGER.warn("Invalid format of events: " + nbttaglist);
            } else {
                Iterator iterator = nbttaglist.iterator();

                while (iterator.hasNext()) {
                    NBTBase nbtbase = (NBTBase) iterator.next();

                    this.a((NBTTagCompound) nbtbase);
                }

            }
        }
    }

    private NBTTagCompound a(CustomFunctionCallbackTimerQueue.a<T> customfunctioncallbacktimerqueue_a) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.setString("Name", customfunctioncallbacktimerqueue_a.c);
        nbttagcompound.setLong("TriggerTime", customfunctioncallbacktimerqueue_a.a);
        nbttagcompound.set("Callback", this.b.a(customfunctioncallbacktimerqueue_a.d));
        return nbttagcompound;
    }

    public NBTTagList b() {
        NBTTagList nbttaglist = new NBTTagList();

        this.c.stream().sorted(c()).map(this::a).forEach(nbttaglist::add);
        return nbttaglist;
    }

    public static class a<T> {

        public final long a;
        public final UnsignedLong b;
        public final String c;
        public final CustomFunctionCallbackTimer<T> d;

        private a(long i, UnsignedLong unsignedlong, String s, CustomFunctionCallbackTimer<T> customfunctioncallbacktimer) {
            this.a = i;
            this.b = unsignedlong;
            this.c = s;
            this.d = customfunctioncallbacktimer;
        }
    }
}
