package net.minecraft.server;

import javax.annotation.concurrent.Immutable;

@Immutable
public class ChestLock {

    public static final ChestLock a = new ChestLock("");
    public final String key;

    public ChestLock(String s) {
        this.key = s;
    }

    public boolean a(ItemStack itemstack) {
        return this.key.isEmpty() || !itemstack.isEmpty() && itemstack.hasName() && this.key.equals(itemstack.getName().getString());
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (!this.key.isEmpty()) {
            nbttagcompound.setString("Lock", this.key);
        }

    }

    public static ChestLock b(NBTTagCompound nbttagcompound) {
        return nbttagcompound.hasKeyOfType("Lock", 8) ? new ChestLock(nbttagcompound.getString("Lock")) : ChestLock.a;
    }
}
