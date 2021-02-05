package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PacketPlayOutRecipeUpdate implements Packet<PacketListenerPlayOut> {

    private List<IRecipe<?>> a;

    public PacketPlayOutRecipeUpdate() {}

    public PacketPlayOutRecipeUpdate(Collection<IRecipe<?>> collection) {
        this.a = Lists.newArrayList(collection);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = Lists.newArrayList();
        int i = packetdataserializer.i();

        for (int j = 0; j < i; ++j) {
            this.a.add(c(packetdataserializer));
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a.size());
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            IRecipe<?> irecipe = (IRecipe) iterator.next();

            a(irecipe, packetdataserializer);
        }

    }

    public static IRecipe<?> c(PacketDataSerializer packetdataserializer) {
        MinecraftKey minecraftkey = packetdataserializer.o();
        MinecraftKey minecraftkey1 = packetdataserializer.o();

        return ((RecipeSerializer) IRegistry.RECIPE_SERIALIZER.getOptional(minecraftkey).orElseThrow(() -> {
            return new IllegalArgumentException("Unknown recipe serializer " + minecraftkey);
        })).a(minecraftkey1, packetdataserializer);
    }

    public static <T extends IRecipe<?>> void a(T t0, PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(IRegistry.RECIPE_SERIALIZER.getKey(t0.getRecipeSerializer()));
        packetdataserializer.a(t0.getKey());
        t0.getRecipeSerializer().a(packetdataserializer, t0);
    }
}
