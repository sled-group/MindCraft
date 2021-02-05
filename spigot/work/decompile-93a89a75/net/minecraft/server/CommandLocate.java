package net.minecraft.server;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

public class CommandLocate {

    private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.locate.failed", new Object[0]));

    public static void a(com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
        com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandDispatcher.a("locate").requires((commandlistenerwrapper) -> {
            return commandlistenerwrapper.hasPermission(2);
        })).then(CommandDispatcher.a("Pillager_Outpost").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Pillager_Outpost");
        }))).then(CommandDispatcher.a("Mineshaft").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Mineshaft");
        }))).then(CommandDispatcher.a("Mansion").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Mansion");
        }))).then(CommandDispatcher.a("Igloo").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Igloo");
        }))).then(CommandDispatcher.a("Desert_Pyramid").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Desert_Pyramid");
        }))).then(CommandDispatcher.a("Jungle_Pyramid").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Jungle_Pyramid");
        }))).then(CommandDispatcher.a("Swamp_Hut").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Swamp_Hut");
        }))).then(CommandDispatcher.a("Stronghold").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Stronghold");
        }))).then(CommandDispatcher.a("Monument").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Monument");
        }))).then(CommandDispatcher.a("Fortress").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Fortress");
        }))).then(CommandDispatcher.a("EndCity").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "EndCity");
        }))).then(CommandDispatcher.a("Ocean_Ruin").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Ocean_Ruin");
        }))).then(CommandDispatcher.a("Buried_Treasure").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Buried_Treasure");
        }))).then(CommandDispatcher.a("Shipwreck").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Shipwreck");
        }))).then(CommandDispatcher.a("Village").executes((commandcontext) -> {
            return a((CommandListenerWrapper) commandcontext.getSource(), "Village");
        })));
    }

    private static int a(CommandListenerWrapper commandlistenerwrapper, String s) throws CommandSyntaxException {
        BlockPosition blockposition = new BlockPosition(commandlistenerwrapper.getPosition());
        BlockPosition blockposition1 = commandlistenerwrapper.getWorld().a(s, blockposition, 100, false);

        if (blockposition1 == null) {
            throw CommandLocate.a.create();
        } else {
            int i = MathHelper.d(a(blockposition.getX(), blockposition.getZ(), blockposition1.getX(), blockposition1.getZ()));
            IChatBaseComponent ichatbasecomponent = ChatComponentUtils.a((IChatBaseComponent) (new ChatMessage("chat.coordinates", new Object[]{blockposition1.getX(), "~", blockposition1.getZ()}))).a((chatmodifier) -> {
                chatmodifier.setColor(EnumChatFormat.GREEN).setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/tp @s " + blockposition1.getX() + " ~ " + blockposition1.getZ())).setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_TEXT, new ChatMessage("chat.coordinates.tooltip", new Object[0])));
            });

            commandlistenerwrapper.sendMessage(new ChatMessage("commands.locate.success", new Object[]{s, ichatbasecomponent, i}), false);
            return i;
        }
    }

    private static float a(int i, int j, int k, int l) {
        int i1 = k - i;
        int j1 = l - j;

        return MathHelper.c((float) (i1 * i1 + j1 * j1));
    }
}
