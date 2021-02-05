package net.minecraft.server;

import java.io.IOException;
import java.util.Collection;

public class PacketPlayOutMap implements Packet<PacketListenerPlayOut> {

    private int a;
    private byte b;
    private boolean c;
    private boolean d;
    private MapIcon[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private byte[] j;

    public PacketPlayOutMap() {}

    public PacketPlayOutMap(int i, byte b0, boolean flag, boolean flag1, Collection<MapIcon> collection, byte[] abyte, int j, int k, int l, int i1) {
        this.a = i;
        this.b = b0;
        this.c = flag;
        this.d = flag1;
        this.e = (MapIcon[]) collection.toArray(new MapIcon[collection.size()]);
        this.f = j;
        this.g = k;
        this.h = l;
        this.i = i1;
        this.j = new byte[l * i1];

        for (int j1 = 0; j1 < l; ++j1) {
            for (int k1 = 0; k1 < i1; ++k1) {
                this.j[j1 + k1 * l] = abyte[j + j1 + (k + k1) * 128];
            }
        }

    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.i();
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readBoolean();
        this.d = packetdataserializer.readBoolean();
        this.e = new MapIcon[packetdataserializer.i()];

        for (int i = 0; i < this.e.length; ++i) {
            MapIcon.Type mapicon_type = (MapIcon.Type) packetdataserializer.a(MapIcon.Type.class);

            this.e[i] = new MapIcon(mapicon_type, packetdataserializer.readByte(), packetdataserializer.readByte(), (byte) (packetdataserializer.readByte() & 15), packetdataserializer.readBoolean() ? packetdataserializer.h() : null);
        }

        this.h = packetdataserializer.readUnsignedByte();
        if (this.h > 0) {
            this.i = packetdataserializer.readUnsignedByte();
            this.f = packetdataserializer.readUnsignedByte();
            this.g = packetdataserializer.readUnsignedByte();
            this.j = packetdataserializer.a();
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.d(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeBoolean(this.c);
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.d(this.e.length);
        MapIcon[] amapicon = this.e;
        int i = amapicon.length;

        for (int j = 0; j < i; ++j) {
            MapIcon mapicon = amapicon[j];

            packetdataserializer.a((Enum) mapicon.getType());
            packetdataserializer.writeByte(mapicon.getX());
            packetdataserializer.writeByte(mapicon.getY());
            packetdataserializer.writeByte(mapicon.getRotation() & 15);
            if (mapicon.getName() != null) {
                packetdataserializer.writeBoolean(true);
                packetdataserializer.a(mapicon.getName());
            } else {
                packetdataserializer.writeBoolean(false);
            }
        }

        packetdataserializer.writeByte(this.h);
        if (this.h > 0) {
            packetdataserializer.writeByte(this.i);
            packetdataserializer.writeByte(this.f);
            packetdataserializer.writeByte(this.g);
            packetdataserializer.a(this.j);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
