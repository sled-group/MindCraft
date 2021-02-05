package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import javax.annotation.Nullable;

public class PotionRegistry {

    private final String a;
    private final ImmutableList<MobEffect> b;

    public static PotionRegistry a(String s) {
        return (PotionRegistry) IRegistry.POTION.get(MinecraftKey.a(s));
    }

    public PotionRegistry(MobEffect... amobeffect) {
        this((String) null, amobeffect);
    }

    public PotionRegistry(@Nullable String s, MobEffect... amobeffect) {
        this.a = s;
        this.b = ImmutableList.copyOf(amobeffect);
    }

    public String b(String s) {
        return s + (this.a == null ? IRegistry.POTION.getKey(this).getKey() : this.a);
    }

    public List<MobEffect> a() {
        return this.b;
    }

    public boolean b() {
        if (!this.b.isEmpty()) {
            UnmodifiableIterator unmodifiableiterator = this.b.iterator();

            while (unmodifiableiterator.hasNext()) {
                MobEffect mobeffect = (MobEffect) unmodifiableiterator.next();

                if (mobeffect.getMobEffect().isInstant()) {
                    return true;
                }
            }
        }

        return false;
    }
}
