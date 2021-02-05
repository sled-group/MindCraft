package net.minecraft.server;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class ArgumentPredicateItemStack implements Predicate<ItemStack> {

    private static final Dynamic2CommandExceptionType a = new Dynamic2CommandExceptionType((object, object1) -> {
        return new ChatMessage("arguments.item.overstacked", new Object[]{object, object1});
    });
    private final Item b;
    @Nullable
    private final NBTTagCompound c;

    public ArgumentPredicateItemStack(Item item, @Nullable NBTTagCompound nbttagcompound) {
        this.b = item;
        this.c = nbttagcompound;
    }

    public Item a() {
        return this.b;
    }

    public boolean test(ItemStack itemstack) {
        return itemstack.getItem() == this.b && GameProfileSerializer.a(this.c, itemstack.getTag(), true);
    }

    public ItemStack a(int i, boolean flag) throws CommandSyntaxException {
        ItemStack itemstack = new ItemStack(this.b, i);

        if (this.c != null) {
            itemstack.setTag(this.c);
        }

        if (flag && i > itemstack.getMaxStackSize()) {
            throw ArgumentPredicateItemStack.a.create(IRegistry.ITEM.getKey(this.b), itemstack.getMaxStackSize());
        } else {
            return itemstack;
        }
    }

    public String c() {
        StringBuilder stringbuilder = new StringBuilder(IRegistry.ITEM.a((Object) this.b));

        if (this.c != null) {
            stringbuilder.append(this.c);
        }

        return stringbuilder.toString();
    }
}
