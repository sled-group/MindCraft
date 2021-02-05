package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInClientCommand implements Packet<PacketListenerPlayIn> {

    private PacketPlayInClientCommand.EnumClientCommand a;

    public PacketPlayInClientCommand() {}

    public PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand packetplayinclientcommand_enumclientcommand) {
        this.a = packetplayinclientcommand_enumclientcommand;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayInClientCommand.EnumClientCommand) packetdataserializer.a(PacketPlayInClientCommand.EnumClientCommand.class);
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public PacketPlayInClientCommand.EnumClientCommand b() {
        return this.a;
    }

    public static enum EnumClientCommand {

        PERFORM_RESPAWN, REQUEST_STATS;

        private EnumClientCommand() {}
    }
}
