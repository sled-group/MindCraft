package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockActionContext extends ItemActionContext {

    private final BlockPosition g;
    protected boolean a;

    public BlockActionContext(ItemActionContext itemactioncontext) {
        this(itemactioncontext.getWorld(), itemactioncontext.getEntity(), itemactioncontext.n(), itemactioncontext.getItemStack(), itemactioncontext.d);
    }

    protected BlockActionContext(World world, @Nullable EntityHuman entityhuman, EnumHand enumhand, ItemStack itemstack, MovingObjectPositionBlock movingobjectpositionblock) {
        super(world, entityhuman, enumhand, itemstack, movingobjectpositionblock);
        this.a = true;
        this.g = movingobjectpositionblock.getBlockPosition().shift(movingobjectpositionblock.getDirection());
        this.a = world.getType(movingobjectpositionblock.getBlockPosition()).a(this);
    }

    public static BlockActionContext a(BlockActionContext blockactioncontext, BlockPosition blockposition, EnumDirection enumdirection) {
        return new BlockActionContext(blockactioncontext.getWorld(), blockactioncontext.getEntity(), blockactioncontext.n(), blockactioncontext.getItemStack(), new MovingObjectPositionBlock(new Vec3D((double) blockposition.getX() + 0.5D + (double) enumdirection.getAdjacentX() * 0.5D, (double) blockposition.getY() + 0.5D + (double) enumdirection.getAdjacentY() * 0.5D, (double) blockposition.getZ() + 0.5D + (double) enumdirection.getAdjacentZ() * 0.5D), enumdirection, blockposition, false));
    }

    @Override
    public BlockPosition getClickPosition() {
        return this.a ? super.getClickPosition() : this.g;
    }

    public boolean b() {
        return this.a || this.getWorld().getType(this.getClickPosition()).a(this);
    }

    public boolean c() {
        return this.a;
    }

    public EnumDirection d() {
        return EnumDirection.a((Entity) this.b)[0];
    }

    public EnumDirection[] e() {
        EnumDirection[] aenumdirection = EnumDirection.a((Entity) this.b);

        if (this.a) {
            return aenumdirection;
        } else {
            EnumDirection enumdirection = this.getClickedFace();

            int i;

            for (i = 0; i < aenumdirection.length && aenumdirection[i] != enumdirection.opposite(); ++i) {
                ;
            }

            if (i > 0) {
                System.arraycopy(aenumdirection, 0, aenumdirection, 1, i);
                aenumdirection[0] = enumdirection.opposite();
            }

            return aenumdirection;
        }
    }
}
