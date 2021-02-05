package net.minecraft.server;

import java.util.function.Consumer;

public interface LootEntry {

    int a(float f);

    void a(Consumer<ItemStack> consumer, LootTableInfo loottableinfo);
}
