package net.minecraft.server;

import javax.annotation.Nullable;

public class ItemActionContext {

    protected final EntityHuman b;
    protected final EnumHand c;
    protected final MovingObjectPositionBlock d;
    protected final World e;
    protected final ItemStack f;

    public ItemActionContext(EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        this(entityhuman.world, entityhuman, enumhand, entityhuman.b(enumhand), movingobjectpositionblock);
    }

    protected ItemActionContext(World world, @Nullable EntityHuman entityhuman, EnumHand enumhand, ItemStack itemstack, MovingObjectPositionBlock movingobjectpositionblock) {
        this.b = entityhuman;
        this.c = enumhand;
        this.d = movingobjectpositionblock;
        this.f = itemstack;
        this.e = world;
    }

    public BlockPosition getClickPosition() {
        return this.d.getBlockPosition();
    }

    public EnumDirection getClickedFace() {
        return this.d.getDirection();
    }

    public Vec3D j() {
        return this.d.getPos();
    }

    public boolean k() {
        return this.d.d();
    }

    public ItemStack getItemStack() {
        return this.f;
    }

    @Nullable
    public EntityHuman getEntity() {
        return this.b;
    }

    public EnumHand n() {
        return this.c;
    }

    public World getWorld() {
        return this.e;
    }

    public EnumDirection f() {
        return this.b == null ? EnumDirection.NORTH : this.b.getDirection();
    }

    public boolean isSneaking() {
        return this.b != null && this.b.isSneaking();
    }

    public float h() {
        return this.b == null ? 0.0F : this.b.yaw;
    }
}
