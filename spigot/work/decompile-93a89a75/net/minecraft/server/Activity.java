package net.minecraft.server;

public class Activity {

    public static final Activity CORE = a("core");
    public static final Activity IDLE = a("idle");
    public static final Activity WORK = a("work");
    public static final Activity PLAY = a("play");
    public static final Activity REST = a("rest");
    public static final Activity MEET = a("meet");
    public static final Activity PANIC = a("panic");
    public static final Activity RAID = a("raid");
    public static final Activity PRE_RAID = a("pre_raid");
    public static final Activity HIDE = a("hide");
    private final String k;

    private Activity(String s) {
        this.k = s;
    }

    public String a() {
        return this.k;
    }

    private static Activity a(String s) {
        return (Activity) IRegistry.a(IRegistry.ACTIVITY, s, (Object) (new Activity(s)));
    }

    public String toString() {
        return this.a();
    }
}
