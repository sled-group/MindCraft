package net.minecraft.server;

import javax.annotation.Nullable;

public interface ICrossbow {

    void a(boolean flag);

    void a(EntityLiving entityliving, ItemStack itemstack, IProjectile iprojectile, float f);

    @Nullable
    EntityLiving getGoalTarget();
}
