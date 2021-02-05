package net.minecraft.server;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class MerchantRecipeList extends ArrayList<MerchantRecipe> {

    public MerchantRecipeList() {}

    public MerchantRecipeList(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getList("Recipes", 10);

        for (int i = 0; i < nbttaglist.size(); ++i) {
            this.add(new MerchantRecipe(nbttaglist.getCompound(i)));
        }

    }

    @Nullable
    public MerchantRecipe a(ItemStack itemstack, ItemStack itemstack1, int i) {
        if (i > 0 && i < this.size()) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            return merchantrecipe.a(itemstack, itemstack1) ? merchantrecipe : null;
        } else {
            for (int j = 0; j < this.size(); ++j) {
                MerchantRecipe merchantrecipe1 = (MerchantRecipe) this.get(j);

                if (merchantrecipe1.a(itemstack, itemstack1)) {
                    return merchantrecipe1;
                }
            }

            return null;
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte((byte) (this.size() & 255));

        for (int i = 0; i < this.size(); ++i) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            packetdataserializer.a(merchantrecipe.a());
            packetdataserializer.a(merchantrecipe.getSellingItem());
            ItemStack itemstack = merchantrecipe.getBuyItem2();

            packetdataserializer.writeBoolean(!itemstack.isEmpty());
            if (!itemstack.isEmpty()) {
                packetdataserializer.a(itemstack);
            }

            packetdataserializer.writeBoolean(merchantrecipe.isFullyUsed());
            packetdataserializer.writeInt(merchantrecipe.getUses());
            packetdataserializer.writeInt(merchantrecipe.getMaxUses());
            packetdataserializer.writeInt(merchantrecipe.getXp());
            packetdataserializer.writeInt(merchantrecipe.getSpecialPrice());
            packetdataserializer.writeFloat(merchantrecipe.getPriceMultiplier());
            packetdataserializer.writeInt(merchantrecipe.k());
        }

    }

    public static MerchantRecipeList b(PacketDataSerializer packetdataserializer) {
        MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
        int i = packetdataserializer.readByte() & 255;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = packetdataserializer.m();
            ItemStack itemstack1 = packetdataserializer.m();
            ItemStack itemstack2 = ItemStack.a;

            if (packetdataserializer.readBoolean()) {
                itemstack2 = packetdataserializer.m();
            }

            boolean flag = packetdataserializer.readBoolean();
            int k = packetdataserializer.readInt();
            int l = packetdataserializer.readInt();
            int i1 = packetdataserializer.readInt();
            int j1 = packetdataserializer.readInt();
            float f = packetdataserializer.readFloat();
            int k1 = packetdataserializer.readInt();
            MerchantRecipe merchantrecipe = new MerchantRecipe(itemstack, itemstack2, itemstack1, k, l, i1, f, k1);

            if (flag) {
                merchantrecipe.q();
            }

            merchantrecipe.setSpecialPrice(j1);
            merchantrecipelist.add(merchantrecipe);
        }

        return merchantrecipelist;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.size(); ++i) {
            MerchantRecipe merchantrecipe = (MerchantRecipe) this.get(i);

            nbttaglist.add(merchantrecipe.s());
        }

        nbttagcompound.set("Recipes", nbttaglist);
        return nbttagcompound;
    }
}
