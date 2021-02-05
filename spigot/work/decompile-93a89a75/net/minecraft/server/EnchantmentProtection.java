package net.minecraft.server;

public class EnchantmentProtection extends Enchantment {

    public final EnchantmentProtection.DamageType a;

    public EnchantmentProtection(Enchantment.Rarity enchantment_rarity, EnchantmentProtection.DamageType enchantmentprotection_damagetype, EnumItemSlot... aenumitemslot) {
        super(enchantment_rarity, EnchantmentSlotType.ARMOR, aenumitemslot);
        this.a = enchantmentprotection_damagetype;
        if (enchantmentprotection_damagetype == EnchantmentProtection.DamageType.FALL) {
            this.itemTarget = EnchantmentSlotType.ARMOR_FEET;
        }

    }

    @Override
    public int a(int i) {
        return this.a.b() + (i - 1) * this.a.c();
    }

    @Override
    public int b(int i) {
        return this.a(i) + this.a.c();
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int a(int i, DamageSource damagesource) {
        return damagesource.ignoresInvulnerability() ? 0 : (this.a == EnchantmentProtection.DamageType.ALL ? i : (this.a == EnchantmentProtection.DamageType.FIRE && damagesource.p() ? i * 2 : (this.a == EnchantmentProtection.DamageType.FALL && damagesource == DamageSource.FALL ? i * 3 : (this.a == EnchantmentProtection.DamageType.EXPLOSION && damagesource.isExplosion() ? i * 2 : (this.a == EnchantmentProtection.DamageType.PROJECTILE && damagesource.b() ? i * 2 : 0)))));
    }

    @Override
    public boolean a(Enchantment enchantment) {
        if (enchantment instanceof EnchantmentProtection) {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection) enchantment;

            return this.a == enchantmentprotection.a ? false : this.a == EnchantmentProtection.DamageType.FALL || enchantmentprotection.a == EnchantmentProtection.DamageType.FALL;
        } else {
            return super.a(enchantment);
        }
    }

    public static int a(EntityLiving entityliving, int i) {
        int j = EnchantmentManager.a(Enchantments.PROTECTION_FIRE, entityliving);

        if (j > 0) {
            i -= MathHelper.d((float) i * (float) j * 0.15F);
        }

        return i;
    }

    public static double a(EntityLiving entityliving, double d0) {
        int i = EnchantmentManager.a(Enchantments.PROTECTION_EXPLOSIONS, entityliving);

        if (i > 0) {
            d0 -= (double) MathHelper.floor(d0 * (double) ((float) i * 0.15F));
        }

        return d0;
    }

    public static enum DamageType {

        ALL("all", 1, 11), FIRE("fire", 10, 8), FALL("fall", 5, 6), EXPLOSION("explosion", 5, 8), PROJECTILE("projectile", 3, 6);

        private final String f;
        private final int g;
        private final int h;

        private DamageType(String s, int i, int j) {
            this.f = s;
            this.g = i;
            this.h = j;
        }

        public int b() {
            return this.g;
        }

        public int c() {
            return this.h;
        }
    }
}
