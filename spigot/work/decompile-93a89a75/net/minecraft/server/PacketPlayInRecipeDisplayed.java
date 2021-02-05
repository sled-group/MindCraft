package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInRecipeDisplayed implements Packet<PacketListenerPlayIn> {

    private PacketPlayInRecipeDisplayed.Status a;
    private MinecraftKey b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;

    public PacketPlayInRecipeDisplayed() {}

    public PacketPlayInRecipeDisplayed(IRecipe<?> irecipe) {
        this.a = PacketPlayInRecipeDisplayed.Status.SHOWN;
        this.b = irecipe.getKey();
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayInRecipeDisplayed.Status) packetdataserializer.a(PacketPlayInRecipeDisplayed.Status.class);
        if (this.a == PacketPlayInRecipeDisplayed.Status.SHOWN) {
            this.b = packetdataserializer.o();
        } else if (this.a == PacketPlayInRecipeDisplayed.Status.SETTINGS) {
            this.c = packetdataserializer.readBoolean();
            this.d = packetdataserializer.readBoolean();
            this.e = packetdataserializer.readBoolean();
            this.f = packetdataserializer.readBoolean();
            this.g = packetdataserializer.readBoolean();
            this.h = packetdataserializer.readBoolean();
            this.i = packetdataserializer.readBoolean();
            this.j = packetdataserializer.readBoolean();
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        if (this.a == PacketPlayInRecipeDisplayed.Status.SHOWN) {
            packetdataserializer.a(this.b);
        } else if (this.a == PacketPlayInRecipeDisplayed.Status.SETTINGS) {
            packetdataserializer.writeBoolean(this.c);
            packetdataserializer.writeBoolean(this.d);
            packetdataserializer.writeBoolean(this.e);
            packetdataserializer.writeBoolean(this.f);
            packetdataserializer.writeBoolean(this.g);
            packetdataserializer.writeBoolean(this.h);
            packetdataserializer.writeBoolean(this.i);
            packetdataserializer.writeBoolean(this.j);
        }

    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public PacketPlayInRecipeDisplayed.Status b() {
        return this.a;
    }

    public MinecraftKey c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public boolean h() {
        return this.g;
    }

    public boolean i() {
        return this.h;
    }

    public boolean j() {
        return this.i;
    }

    public boolean k() {
        return this.j;
    }

    public static enum Status {

        SHOWN, SETTINGS;

        private Status() {}
    }
}
