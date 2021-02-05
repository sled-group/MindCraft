package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScheduleBuilder {

    private final Schedule a;
    private final List<ScheduleBuilder.a> b = Lists.newArrayList();

    public ScheduleBuilder(Schedule schedule) {
        this.a = schedule;
    }

    public ScheduleBuilder a(int i, Activity activity) {
        this.b.add(new ScheduleBuilder.a(i, activity));
        return this;
    }

    public Schedule a() {
        Set set = (Set) this.b.stream().map(ScheduleBuilder.a::b).collect(Collectors.toSet());
        Schedule schedule = this.a;

        this.a.getClass();
        set.forEach(schedule::a);
        this.b.forEach((schedulebuilder_a) -> {
            Activity activity = schedulebuilder_a.b();

            this.a.c(activity).forEach((scheduleactivity) -> {
                scheduleactivity.a(schedulebuilder_a.a(), 0.0F);
            });
            this.a.b(activity).a(schedulebuilder_a.a(), 1.0F);
        });
        return this.a;
    }

    static class a {

        private final int a;
        private final Activity b;

        public a(int i, Activity activity) {
            this.a = i;
            this.b = activity;
        }

        public int a() {
            return this.a;
        }

        public Activity b() {
            return this.b;
        }
    }
}
