package net.minecraft.server;

public class InstantMobEffect extends MobEffectList {

    public InstantMobEffect(MobEffectInfo mobeffectinfo, int i) {
        super(mobeffectinfo, i);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean a(int i, int j) {
        return i >= 1;
    }
}
