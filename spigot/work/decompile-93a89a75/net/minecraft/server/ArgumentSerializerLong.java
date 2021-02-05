package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.LongArgumentType;

public class ArgumentSerializerLong implements ArgumentSerializer<LongArgumentType> {

    public ArgumentSerializerLong() {}

    public void a(LongArgumentType longargumenttype, PacketDataSerializer packetdataserializer) {
        boolean flag = longargumenttype.getMinimum() != Long.MIN_VALUE;
        boolean flag1 = longargumenttype.getMaximum() != Long.MAX_VALUE;

        packetdataserializer.writeByte(ArgumentSerializers.a(flag, flag1));
        if (flag) {
            packetdataserializer.writeLong(longargumenttype.getMinimum());
        }

        if (flag1) {
            packetdataserializer.writeLong(longargumenttype.getMaximum());
        }

    }

    @Override
    public LongArgumentType b(PacketDataSerializer packetdataserializer) {
        byte b0 = packetdataserializer.readByte();
        long i = ArgumentSerializers.a(b0) ? packetdataserializer.readLong() : Long.MIN_VALUE;
        long j = ArgumentSerializers.b(b0) ? packetdataserializer.readLong() : Long.MAX_VALUE;

        return LongArgumentType.longArg(i, j);
    }

    public void a(LongArgumentType longargumenttype, JsonObject jsonobject) {
        if (longargumenttype.getMinimum() != Long.MIN_VALUE) {
            jsonobject.addProperty("min", longargumenttype.getMinimum());
        }

        if (longargumenttype.getMaximum() != Long.MAX_VALUE) {
            jsonobject.addProperty("max", longargumenttype.getMaximum());
        }

    }
}
