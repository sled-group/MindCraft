package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nullable;

public class ChatComponentUtils {

    public static IChatBaseComponent a(IChatBaseComponent ichatbasecomponent, ChatModifier chatmodifier) {
        return chatmodifier.g() ? ichatbasecomponent : (ichatbasecomponent.getChatModifier().g() ? ichatbasecomponent.setChatModifier(chatmodifier.clone()) : (new ChatComponentText("")).addSibling(ichatbasecomponent).setChatModifier(chatmodifier.clone()));
    }

    public static IChatBaseComponent filterForDisplay(@Nullable CommandListenerWrapper commandlistenerwrapper, IChatBaseComponent ichatbasecomponent, @Nullable Entity entity, int i) throws CommandSyntaxException {
        if (i > 100) {
            return ichatbasecomponent;
        } else {
            ++i;
            IChatBaseComponent ichatbasecomponent1 = ichatbasecomponent instanceof ChatComponentContextual ? ((ChatComponentContextual) ichatbasecomponent).a(commandlistenerwrapper, entity, i) : ichatbasecomponent.g();
            Iterator iterator = ichatbasecomponent.getSiblings().iterator();

            while (iterator.hasNext()) {
                IChatBaseComponent ichatbasecomponent2 = (IChatBaseComponent) iterator.next();

                ichatbasecomponent1.addSibling(filterForDisplay(commandlistenerwrapper, ichatbasecomponent2, entity, i));
            }

            return a(ichatbasecomponent1, ichatbasecomponent.getChatModifier());
        }
    }

    public static IChatBaseComponent a(GameProfile gameprofile) {
        return gameprofile.getName() != null ? new ChatComponentText(gameprofile.getName()) : (gameprofile.getId() != null ? new ChatComponentText(gameprofile.getId().toString()) : new ChatComponentText("(unknown)"));
    }

    public static IChatBaseComponent a(Collection<String> collection) {
        return a(collection, (s) -> {
            return (new ChatComponentText(s)).a(EnumChatFormat.GREEN);
        });
    }

    public static <T extends Comparable<T>> IChatBaseComponent a(Collection<T> collection, Function<T, IChatBaseComponent> function) {
        if (collection.isEmpty()) {
            return new ChatComponentText("");
        } else if (collection.size() == 1) {
            return (IChatBaseComponent) function.apply(collection.iterator().next());
        } else {
            List<T> list = Lists.newArrayList(collection);

            list.sort(Comparable::compareTo);
            return b(collection, function);
        }
    }

    public static <T> IChatBaseComponent b(Collection<T> collection, Function<T, IChatBaseComponent> function) {
        if (collection.isEmpty()) {
            return new ChatComponentText("");
        } else if (collection.size() == 1) {
            return (IChatBaseComponent) function.apply(collection.iterator().next());
        } else {
            ChatComponentText chatcomponenttext = new ChatComponentText("");
            boolean flag = true;

            for (Iterator iterator = collection.iterator(); iterator.hasNext(); flag = false) {
                T t0 = iterator.next();

                if (!flag) {
                    chatcomponenttext.addSibling((new ChatComponentText(", ")).a(EnumChatFormat.GRAY));
                }

                chatcomponenttext.addSibling((IChatBaseComponent) function.apply(t0));
            }

            return chatcomponenttext;
        }
    }

    public static IChatBaseComponent a(IChatBaseComponent ichatbasecomponent) {
        return (new ChatComponentText("[")).addSibling(ichatbasecomponent).a("]");
    }

    public static IChatBaseComponent a(Message message) {
        return (IChatBaseComponent) (message instanceof IChatBaseComponent ? (IChatBaseComponent) message : new ChatComponentText(message.getString()));
    }
}
