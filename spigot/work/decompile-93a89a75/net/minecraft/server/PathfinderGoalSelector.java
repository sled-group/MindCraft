package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathfinderGoalSelector {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final PathfinderGoalWrapped b = new PathfinderGoalWrapped(Integer.MAX_VALUE, new PathfinderGoal() {
        @Override
        public boolean a() {
            return false;
        }
    }) {
        @Override
        public boolean g() {
            return false;
        }
    };
    private final Map<PathfinderGoal.Type, PathfinderGoalWrapped> c = new EnumMap(PathfinderGoal.Type.class);
    private final Set<PathfinderGoalWrapped> d = Sets.newLinkedHashSet();
    private final GameProfilerFiller e;
    private final EnumSet<PathfinderGoal.Type> f = EnumSet.noneOf(PathfinderGoal.Type.class);
    private int g = 3;

    public PathfinderGoalSelector(GameProfilerFiller gameprofilerfiller) {
        this.e = gameprofilerfiller;
    }

    public void a(int i, PathfinderGoal pathfindergoal) {
        this.d.add(new PathfinderGoalWrapped(i, pathfindergoal));
    }

    public void a(PathfinderGoal pathfindergoal) {
        this.d.stream().filter((pathfindergoalwrapped) -> {
            return pathfindergoalwrapped.j() == pathfindergoal;
        }).filter(PathfinderGoalWrapped::g).forEach(PathfinderGoalWrapped::d);
        this.d.removeIf((pathfindergoalwrapped) -> {
            return pathfindergoalwrapped.j() == pathfindergoal;
        });
    }

    public void doTick() {
        this.e.enter("goalCleanup");
        this.c().filter((pathfindergoalwrapped) -> {
            boolean flag;

            if (pathfindergoalwrapped.g()) {
                Stream stream = pathfindergoalwrapped.i().stream();
                EnumSet enumset = this.f;

                this.f.getClass();
                if (!stream.anyMatch(enumset::contains) && pathfindergoalwrapped.b()) {
                    flag = false;
                    return flag;
                }
            }

            flag = true;
            return flag;
        }).forEach(PathfinderGoal::d);
        this.c.forEach((pathfindergoal_type, pathfindergoalwrapped) -> {
            if (!pathfindergoalwrapped.g()) {
                this.c.remove(pathfindergoal_type);
            }

        });
        this.e.exit();
        this.e.enter("goalUpdate");
        this.d.stream().filter((pathfindergoalwrapped) -> {
            return !pathfindergoalwrapped.g();
        }).filter((pathfindergoalwrapped) -> {
            Stream stream = pathfindergoalwrapped.i().stream();
            EnumSet enumset = this.f;

            this.f.getClass();
            return stream.noneMatch(enumset::contains);
        }).filter((pathfindergoalwrapped) -> {
            return pathfindergoalwrapped.i().stream().allMatch((pathfindergoal_type) -> {
                return ((PathfinderGoalWrapped) this.c.getOrDefault(pathfindergoal_type, PathfinderGoalSelector.b)).a(pathfindergoalwrapped);
            });
        }).filter(PathfinderGoalWrapped::a).forEach((pathfindergoalwrapped) -> {
            pathfindergoalwrapped.i().forEach((pathfindergoal_type) -> {
                PathfinderGoalWrapped pathfindergoalwrapped1 = (PathfinderGoalWrapped) this.c.getOrDefault(pathfindergoal_type, PathfinderGoalSelector.b);

                pathfindergoalwrapped1.d();
                this.c.put(pathfindergoal_type, pathfindergoalwrapped);
            });
            pathfindergoalwrapped.c();
        });
        this.e.exit();
        this.e.enter("goalTick");
        this.c().forEach(PathfinderGoalWrapped::e);
        this.e.exit();
    }

    public Stream<PathfinderGoalWrapped> c() {
        return this.d.stream().filter(PathfinderGoalWrapped::g);
    }

    public void a(PathfinderGoal.Type pathfindergoal_type) {
        this.f.add(pathfindergoal_type);
    }

    public void b(PathfinderGoal.Type pathfindergoal_type) {
        this.f.remove(pathfindergoal_type);
    }

    public void a(PathfinderGoal.Type pathfindergoal_type, boolean flag) {
        if (flag) {
            this.b(pathfindergoal_type);
        } else {
            this.a(pathfindergoal_type);
        }

    }
}
