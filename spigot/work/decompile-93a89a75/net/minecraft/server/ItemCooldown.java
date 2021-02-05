package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ItemCooldown {

    public final Map<Item, ItemCooldown.Info> cooldowns = Maps.newHashMap();
    public int currentTick;

    public ItemCooldown() {}

    public boolean hasCooldown(Item item) {
        return this.a(item, 0.0F) > 0.0F;
    }

    public float a(Item item, float f) {
        ItemCooldown.Info itemcooldown_info = (ItemCooldown.Info) this.cooldowns.get(item);

        if (itemcooldown_info != null) {
            float f1 = (float) (itemcooldown_info.endTick - itemcooldown_info.b);
            float f2 = (float) itemcooldown_info.endTick - ((float) this.currentTick + f);

            return MathHelper.a(f2 / f1, 0.0F, 1.0F);
        } else {
            return 0.0F;
        }
    }

    public void a() {
        ++this.currentTick;
        if (!this.cooldowns.isEmpty()) {
            Iterator iterator = this.cooldowns.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<Item, ItemCooldown.Info> entry = (Entry) iterator.next();

                if (((ItemCooldown.Info) entry.getValue()).endTick <= this.currentTick) {
                    iterator.remove();
                    this.c((Item) entry.getKey());
                }
            }
        }

    }

    public void setCooldown(Item item, int i) {
        this.cooldowns.put(item, new ItemCooldown.Info(this.currentTick, this.currentTick + i));
        this.b(item, i);
    }

    protected void b(Item item, int i) {}

    protected void c(Item item) {}

    public class Info {

        private final int b;
        public final int endTick;

        private Info(int i, int j) {
            this.b = i;
            this.endTick = j;
        }
    }
}
