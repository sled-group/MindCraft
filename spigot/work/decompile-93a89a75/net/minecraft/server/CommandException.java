package net.minecraft.server;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class CommandException extends RuntimeException {

    private final IChatBaseComponent a;

    public CommandException(IChatBaseComponent ichatbasecomponent) {
        super(ichatbasecomponent.getText(), (Throwable) null, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES);
        this.a = ichatbasecomponent;
    }

    public IChatBaseComponent a() {
        return this.a;
    }
}
