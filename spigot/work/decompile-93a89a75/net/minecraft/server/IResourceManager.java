package net.minecraft.server;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface IResourceManager {

    IResource a(MinecraftKey minecraftkey) throws IOException;

    List<IResource> c(MinecraftKey minecraftkey) throws IOException;

    Collection<MinecraftKey> a(String s, Predicate<String> predicate);
}
