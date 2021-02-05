package net.minecraft.server;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface CommandDataAccessor {

    void a(NBTTagCompound nbttagcompound) throws CommandSyntaxException;

    NBTTagCompound a() throws CommandSyntaxException;

    IChatBaseComponent b();

    IChatBaseComponent a(NBTBase nbtbase);

    IChatBaseComponent a(ArgumentNBTKey.h argumentnbtkey_h, double d0, int i);
}
