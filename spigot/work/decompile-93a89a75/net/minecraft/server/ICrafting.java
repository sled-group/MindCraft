package net.minecraft.server;

public interface ICrafting {

    void a(Container container, NonNullList<ItemStack> nonnulllist);

    void a(Container container, int i, ItemStack itemstack);

    void setContainerData(Container container, int i, int j);
}
