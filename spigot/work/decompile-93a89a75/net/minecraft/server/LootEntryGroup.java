package net.minecraft.server;

import java.util.function.Consumer;

public class LootEntryGroup extends LootEntryChildrenAbstract {

    LootEntryGroup(LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition) {
        super(alootentryabstract, alootitemcondition);
    }

    @Override
    protected LootEntryChildren a(LootEntryChildren[] alootentrychildren) {
        switch (alootentrychildren.length) {
            case 0:
                return LootEntryGroup.b;
            case 1:
                return alootentrychildren[0];
            case 2:
                LootEntryChildren lootentrychildren = alootentrychildren[0];
                LootEntryChildren lootentrychildren1 = alootentrychildren[1];

                return (loottableinfo, consumer) -> {
                    lootentrychildren.expand(loottableinfo, consumer);
                    lootentrychildren1.expand(loottableinfo, consumer);
                    return true;
                };
            default:
                return (loottableinfo, consumer) -> {
                    LootEntryChildren[] alootentrychildren1 = alootentrychildren;
                    int i = alootentrychildren.length;

                    for (int j = 0; j < i; ++j) {
                        LootEntryChildren lootentrychildren2 = alootentrychildren1[j];

                        lootentrychildren2.expand(loottableinfo, consumer);
                    }

                    return true;
                };
        }
    }
}
