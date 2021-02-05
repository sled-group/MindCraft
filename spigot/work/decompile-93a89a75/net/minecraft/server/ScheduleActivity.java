package net.minecraft.server;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
import java.util.List;

public class ScheduleActivity {

    private final List<ActivityFrame> a = Lists.newArrayList();
    private int b;

    public ScheduleActivity() {}

    public ScheduleActivity a(int i, float f) {
        this.a.add(new ActivityFrame(i, f));
        this.b();
        return this;
    }

    private void b() {
        Int2ObjectSortedMap<ActivityFrame> int2objectsortedmap = new Int2ObjectAVLTreeMap();

        this.a.forEach((activityframe) -> {
            ActivityFrame activityframe1 = (ActivityFrame) int2objectsortedmap.put(activityframe.a(), activityframe);
        });
        this.a.clear();
        this.a.addAll(int2objectsortedmap.values());
        this.b = 0;
    }

    public float a(int i) {
        if (this.a.size() <= 0) {
            return 0.0F;
        } else {
            ActivityFrame activityframe = (ActivityFrame) this.a.get(this.b);
            ActivityFrame activityframe1 = (ActivityFrame) this.a.get(this.a.size() - 1);
            boolean flag = i < activityframe.a();
            int j = flag ? 0 : this.b;
            float f = flag ? activityframe1.b() : activityframe.b();

            for (int k = j; k < this.a.size(); ++k) {
                ActivityFrame activityframe2 = (ActivityFrame) this.a.get(k);

                if (activityframe2.a() > i) {
                    break;
                }

                this.b = k;
                f = activityframe2.b();
            }

            return f;
        }
    }
}
