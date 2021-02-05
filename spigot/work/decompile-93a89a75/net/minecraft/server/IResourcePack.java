package net.minecraft.server;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public interface IResourcePack extends Closeable {

    InputStream a(EnumResourcePackType enumresourcepacktype, MinecraftKey minecraftkey) throws IOException;

    Collection<MinecraftKey> a(EnumResourcePackType enumresourcepacktype, String s, int i, Predicate<String> predicate);

    boolean b(EnumResourcePackType enumresourcepacktype, MinecraftKey minecraftkey);

    Set<String> a(EnumResourcePackType enumresourcepacktype);

    @Nullable
    <T> T a(ResourcePackMetaParser<T> resourcepackmetaparser) throws IOException;

    String a();
}
