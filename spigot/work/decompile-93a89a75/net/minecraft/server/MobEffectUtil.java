package net.minecraft.server;

public final class MobEffectUtil {

    public static boolean a(EntityLiving entityliving) {
        return entityliving.hasEffect(MobEffects.FASTER_DIG) || entityliving.hasEffect(MobEffects.CONDUIT_POWER);
    }

    public static int b(EntityLiving entityliving) {
        int i = 0;
        int j = 0;

        if (entityliving.hasEffect(MobEffects.FASTER_DIG)) {
            i = entityliving.getEffect(MobEffects.FASTER_DIG).getAmplifier();
        }

        if (entityliving.hasEffect(MobEffects.CONDUIT_POWER)) {
            j = entityliving.getEffect(MobEffects.CONDUIT_POWER).getAmplifier();
        }

        return Math.max(i, j);
    }

    public static boolean c(EntityLiving entityliving) {
        return entityliving.hasEffect(MobEffects.WATER_BREATHING) || entityliving.hasEffect(MobEffects.CONDUIT_POWER);
    }
}
