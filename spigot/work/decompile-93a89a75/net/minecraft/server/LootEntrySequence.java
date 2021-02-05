package net.minecraft.server;

import java.util.function.Consumer;

public class LootEntrySequence extends LootEntryChildrenAbstract {

    LootEntrySequence(LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition) {
        super(alootentryabstract, alootitemcondition);
    }

    @Override
    protected LootEntryChildren a(LootEntryChildren[] alootentrychildren) {
        switch (alootentrychildren.length) {
            case 0:
                return LootEntrySequence.b;
            case 1:
                return alootentrychildren[0];
            case 2:
                return alootentrychildren[0].a(alootentrychildren[1]);
            default:
                return (loottableinfo, consumer) -> {
                    LootEntryChildren[] alootentrychildren1 = alootentrychildren;
                    int i = alootentrychildren.length;

                    for (int j = 0; j < i; ++j) {
                        LootEntryChildren lootentrychildren = alootentrychildren1[j];

                        if (!lootentrychildren.expand(loottableinfo, consumer)) {
                            return false;
                        }
                    }

                    return true;
                };
        }
    }
}
