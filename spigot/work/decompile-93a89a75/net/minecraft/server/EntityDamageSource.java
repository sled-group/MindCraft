package net.minecraft.server;

import javax.annotation.Nullable;

public class EntityDamageSource extends DamageSource {

    @Nullable
    protected final Entity x;
    private boolean y;

    public EntityDamageSource(String s, @Nullable Entity entity) {
        super(s);
        this.x = entity;
    }

    public EntityDamageSource x() {
        this.y = true;
        return this;
    }

    public boolean y() {
        return this.y;
    }

    @Nullable
    @Override
    public Entity getEntity() {
        return this.x;
    }

    @Override
    public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
        ItemStack itemstack = this.x instanceof EntityLiving ? ((EntityLiving) this.x).getItemInMainHand() : ItemStack.a;
        String s = "death.attack." + this.translationIndex;

        return !itemstack.isEmpty() && itemstack.hasName() ? new ChatMessage(s + ".item", new Object[]{entityliving.getScoreboardDisplayName(), this.x.getScoreboardDisplayName(), itemstack.B()}) : new ChatMessage(s, new Object[]{entityliving.getScoreboardDisplayName(), this.x.getScoreboardDisplayName()});
    }

    @Override
    public boolean s() {
        return this.x != null && this.x instanceof EntityLiving && !(this.x instanceof EntityHuman);
    }

    @Nullable
    @Override
    public Vec3D w() {
        return new Vec3D(this.x.locX, this.x.locY, this.x.locZ);
    }
}
