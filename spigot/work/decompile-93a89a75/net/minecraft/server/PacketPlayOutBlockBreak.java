package net.minecraft.server;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketPlayOutBlockBreak implements Packet<PacketListenerPlayOut> {

    private static final Logger LOGGER = LogManager.getLogger();
    private BlockPosition c;
    private IBlockData d;
    PacketPlayInBlockDig.EnumPlayerDigType a;
    private boolean e;

    public PacketPlayOutBlockBreak() {}

    public PacketPlayOutBlockBreak(BlockPosition blockposition, IBlockData iblockdata, PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype, boolean flag) {
        this.c = blockposition.immutableCopy();
        this.d = iblockdata;
        this.a = packetplayinblockdig_enumplayerdigtype;
        this.e = flag;
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.c = packetdataserializer.e();
        this.d = (IBlockData) Block.REGISTRY_ID.fromId(packetdataserializer.i());
        this.a = (PacketPlayInBlockDig.EnumPlayerDigType) packetdataserializer.a(PacketPlayInBlockDig.EnumPlayerDigType.class);
        this.e = packetdataserializer.readBoolean();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.c);
        packetdataserializer.d(Block.getCombinedId(this.d));
        packetdataserializer.a((Enum) this.a);
        packetdataserializer.writeBoolean(this.e);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
