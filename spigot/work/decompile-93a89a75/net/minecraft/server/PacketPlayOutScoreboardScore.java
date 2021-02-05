package net.minecraft.server;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;

public class PacketPlayOutScoreboardScore implements Packet<PacketListenerPlayOut> {

    private String a = "";
    @Nullable
    private String b;
    private int c;
    private ScoreboardServer.Action d;

    public PacketPlayOutScoreboardScore() {}

    public PacketPlayOutScoreboardScore(ScoreboardServer.Action scoreboardserver_action, @Nullable String s, String s1, int i) {
        if (scoreboardserver_action != ScoreboardServer.Action.REMOVE && s == null) {
            throw new IllegalArgumentException("Need an objective name");
        } else {
            this.a = s1;
            this.b = s;
            this.c = i;
            this.d = scoreboardserver_action;
        }
    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e(40);
        this.d = (ScoreboardServer.Action) packetdataserializer.a(ScoreboardServer.Action.class);
        String s = packetdataserializer.e(16);

        this.b = Objects.equals(s, "") ? null : s;
        if (this.d != ScoreboardServer.Action.REMOVE) {
            this.c = packetdataserializer.i();
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.d);
        packetdataserializer.a(this.b == null ? "" : this.b);
        if (this.d != ScoreboardServer.Action.REMOVE) {
            packetdataserializer.d(this.c);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }
}
