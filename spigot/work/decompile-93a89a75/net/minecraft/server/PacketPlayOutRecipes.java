package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PacketPlayOutRecipes implements Packet<PacketListenerPlayOut> {

    private PacketPlayOutRecipes.Action a;
    private List<MinecraftKey> b;
    private List<MinecraftKey> c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;

    public PacketPlayOutRecipes() {}

    public PacketPlayOutRecipes(PacketPlayOutRecipes.Action packetplayoutrecipes_action, Collection<MinecraftKey> collection, Collection<MinecraftKey> collection1, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
        this.a = packetplayoutrecipes_action;
        this.b = ImmutableList.copyOf(collection);
        this.c = ImmutableList.copyOf(collection1);
        this.d = flag;
        this.e = flag1;
        this.f = flag2;
        this.g = flag3;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayOutRecipes.Action) packetdataserializer.a(PacketPlayOutRecipes.Action.class);
        this.d = packetdataserializer.readBoolean();
        this.e = packetdataserializer.readBoolean();
        this.f = packetdataserializer.readBoolean();
        this.g = packetdataserializer.readBoolean();
        int i = packetdataserializer.i();

        this.b = Lists.newArrayList();

        int j;

        for (j = 0; j < i; ++j) {
            this.b.add(packetdataserializer.o());
        }

        if (this.a == PacketPlayOutRecipes.Action.INIT) {
            i = packetdataserializer.i();
            this.c = Lists.newArrayList();

            for (j = 0; j < i; ++j) {
                this.c.add(packetdataserializer.o());
            }
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.writeBoolean(this.e);
        packetdataserializer.writeBoolean(this.f);
        packetdataserializer.writeBoolean(this.g);
        packetdataserializer.d(this.b.size());
        Iterator iterator = this.b.iterator();

        MinecraftKey minecraftkey;

        while (iterator.hasNext()) {
            minecraftkey = (MinecraftKey) iterator.next();
            packetdataserializer.a(minecraftkey);
        }

        if (this.a == PacketPlayOutRecipes.Action.INIT) {
            packetdataserializer.d(this.c.size());
            iterator = this.c.iterator();

            while (iterator.hasNext()) {
                minecraftkey = (MinecraftKey) iterator.next();
                packetdataserializer.a(minecraftkey);
            }
        }

    }

    public static enum Action {

        INIT, ADD, REMOVE;

        private Action() {}
    }
}
