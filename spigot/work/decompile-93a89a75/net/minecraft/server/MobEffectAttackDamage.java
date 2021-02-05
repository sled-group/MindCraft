package net.minecraft.server;

public class MobEffectAttackDamage extends MobEffectList {

    protected final double a;

    protected MobEffectAttackDamage(MobEffectInfo mobeffectinfo, int i, double d0) {
        super(mobeffectinfo, i);
        this.a = d0;
    }

    @Override
    public double a(int i, AttributeModifier attributemodifier) {
        return this.a * (double) (i + 1);
    }
}
