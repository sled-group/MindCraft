package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.lang3.ArrayUtils;

public class LootEntryAlternatives extends LootEntryChildrenAbstract {

    LootEntryAlternatives(LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition) {
        super(alootentryabstract, alootitemcondition);
    }

    @Override
    protected LootEntryChildren a(LootEntryChildren[] alootentrychildren) {
        switch (alootentrychildren.length) {
            case 0:
                return LootEntryAlternatives.a;
            case 1:
                return alootentrychildren[0];
            case 2:
                return alootentrychildren[0].b(alootentrychildren[1]);
            default:
                return (loottableinfo, consumer) -> {
                    LootEntryChildren[] alootentrychildren1 = alootentrychildren;
                    int i = alootentrychildren.length;

                    for (int j = 0; j < i; ++j) {
                        LootEntryChildren lootentrychildren = alootentrychildren1[j];

                        if (lootentrychildren.expand(loottableinfo, consumer)) {
                            return true;
                        }
                    }

                    return false;
                };
        }
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        super.a(lootcollector, function, set, lootcontextparameterset);

        for (int i = 0; i < this.c.length - 1; ++i) {
            if (ArrayUtils.isEmpty(this.c[i].d)) {
                lootcollector.a("Unreachable entry!");
            }
        }

    }

    public static LootEntryAlternatives.a a(LootEntryAbstract.a<?>... alootentryabstract_a) {
        return new LootEntryAlternatives.a(alootentryabstract_a);
    }

    public static class a extends LootEntryAbstract.a<LootEntryAlternatives.a> {

        private final List<LootEntryAbstract> a = Lists.newArrayList();

        public a(LootEntryAbstract.a<?>... alootentryabstract_a) {
            LootEntryAbstract.a[] alootentryabstract_a1 = alootentryabstract_a;
            int i = alootentryabstract_a.length;

            for (int j = 0; j < i; ++j) {
                LootEntryAbstract.a<?> lootentryabstract_a = alootentryabstract_a1[j];

                this.a.add(lootentryabstract_a.b());
            }

        }

        @Override
        protected LootEntryAlternatives.a d() {
            return this;
        }

        @Override
        public LootEntryAlternatives.a a(LootEntryAbstract.a<?> lootentryabstract_a) {
            this.a.add(lootentryabstract_a.b());
            return this;
        }

        @Override
        public LootEntryAbstract b() {
            return new LootEntryAlternatives((LootEntryAbstract[]) this.a.toArray(new LootEntryAbstract[0]), this.f());
        }
    }
}
