package net.minecraft.server;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class PacketPlayOutPlayerInfo implements Packet<PacketListenerPlayOut> {

    private PacketPlayOutPlayerInfo.EnumPlayerInfoAction a;
    private final List<PacketPlayOutPlayerInfo.PlayerInfoData> b = Lists.newArrayList();

    public PacketPlayOutPlayerInfo() {}

    public PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction packetplayoutplayerinfo_enumplayerinfoaction, EntityPlayer... aentityplayer) {
        this.a = packetplayoutplayerinfo_enumplayerinfoaction;
        EntityPlayer[] aentityplayer1 = aentityplayer;
        int i = aentityplayer.length;

        for (int j = 0; j < i; ++j) {
            EntityPlayer entityplayer = aentityplayer1[j];

            this.b.add(new PacketPlayOutPlayerInfo.PlayerInfoData(entityplayer.getProfile(), entityplayer.ping, entityplayer.playerInteractManager.getGameMode(), entityplayer.getPlayerListName()));
        }

    }

    public PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction packetplayoutplayerinfo_enumplayerinfoaction, Iterable<EntityPlayer> iterable) {
        this.a = packetplayoutplayerinfo_enumplayerinfoaction;
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            this.b.add(new PacketPlayOutPlayerInfo.PlayerInfoData(entityplayer.getProfile(), entityplayer.ping, entityplayer.playerInteractManager.getGameMode(), entityplayer.getPlayerListName()));
        }

    }

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayOutPlayerInfo.EnumPlayerInfoAction) packetdataserializer.a(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.class);
        int i = packetdataserializer.i();

        for (int j = 0; j < i; ++j) {
            GameProfile gameprofile = null;
            int k = 0;
            EnumGamemode enumgamemode = null;
            IChatBaseComponent ichatbasecomponent = null;

            switch (this.a) {
                case ADD_PLAYER:
                    gameprofile = new GameProfile(packetdataserializer.k(), packetdataserializer.e(16));
                    int l = packetdataserializer.i();

                    for (int i1 = 0; i1 < l; ++i1) {
                        String s = packetdataserializer.e(32767);
                        String s1 = packetdataserializer.e(32767);

                        if (packetdataserializer.readBoolean()) {
                            gameprofile.getProperties().put(s, new Property(s, s1, packetdataserializer.e(32767)));
                        } else {
                            gameprofile.getProperties().put(s, new Property(s, s1));
                        }
                    }

                    enumgamemode = EnumGamemode.getById(packetdataserializer.i());
                    k = packetdataserializer.i();
                    if (packetdataserializer.readBoolean()) {
                        ichatbasecomponent = packetdataserializer.h();
                    }
                    break;
                case UPDATE_GAME_MODE:
                    gameprofile = new GameProfile(packetdataserializer.k(), (String) null);
                    enumgamemode = EnumGamemode.getById(packetdataserializer.i());
                    break;
                case UPDATE_LATENCY:
                    gameprofile = new GameProfile(packetdataserializer.k(), (String) null);
                    k = packetdataserializer.i();
                    break;
                case UPDATE_DISPLAY_NAME:
                    gameprofile = new GameProfile(packetdataserializer.k(), (String) null);
                    if (packetdataserializer.readBoolean()) {
                        ichatbasecomponent = packetdataserializer.h();
                    }
                    break;
                case REMOVE_PLAYER:
                    gameprofile = new GameProfile(packetdataserializer.k(), (String) null);
            }

            this.b.add(new PacketPlayOutPlayerInfo.PlayerInfoData(gameprofile, k, enumgamemode, ichatbasecomponent));
        }

    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
        packetdataserializer.d(this.b.size());
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            PacketPlayOutPlayerInfo.PlayerInfoData packetplayoutplayerinfo_playerinfodata = (PacketPlayOutPlayerInfo.PlayerInfoData) iterator.next();

            switch (this.a) {
                case ADD_PLAYER:
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getId());
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getName());
                    packetdataserializer.d(packetplayoutplayerinfo_playerinfodata.a().getProperties().size());
                    Iterator iterator1 = packetplayoutplayerinfo_playerinfodata.a().getProperties().values().iterator();

                    while (iterator1.hasNext()) {
                        Property property = (Property) iterator1.next();

                        packetdataserializer.a(property.getName());
                        packetdataserializer.a(property.getValue());
                        if (property.hasSignature()) {
                            packetdataserializer.writeBoolean(true);
                            packetdataserializer.a(property.getSignature());
                        } else {
                            packetdataserializer.writeBoolean(false);
                        }
                    }

                    packetdataserializer.d(packetplayoutplayerinfo_playerinfodata.c().getId());
                    packetdataserializer.d(packetplayoutplayerinfo_playerinfodata.b());
                    if (packetplayoutplayerinfo_playerinfodata.d() == null) {
                        packetdataserializer.writeBoolean(false);
                    } else {
                        packetdataserializer.writeBoolean(true);
                        packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.d());
                    }
                    break;
                case UPDATE_GAME_MODE:
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getId());
                    packetdataserializer.d(packetplayoutplayerinfo_playerinfodata.c().getId());
                    break;
                case UPDATE_LATENCY:
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getId());
                    packetdataserializer.d(packetplayoutplayerinfo_playerinfodata.b());
                    break;
                case UPDATE_DISPLAY_NAME:
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getId());
                    if (packetplayoutplayerinfo_playerinfodata.d() == null) {
                        packetdataserializer.writeBoolean(false);
                    } else {
                        packetdataserializer.writeBoolean(true);
                        packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.d());
                    }
                    break;
                case REMOVE_PLAYER:
                    packetdataserializer.a(packetplayoutplayerinfo_playerinfodata.a().getId());
            }
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("action", this.a).add("entries", this.b).toString();
    }

    public class PlayerInfoData {

        private final int b;
        private final EnumGamemode c;
        private final GameProfile d;
        private final IChatBaseComponent e;

        public PlayerInfoData(GameProfile gameprofile, int i, EnumGamemode enumgamemode, @Nullable IChatBaseComponent ichatbasecomponent) {
            this.d = gameprofile;
            this.b = i;
            this.c = enumgamemode;
            this.e = ichatbasecomponent;
        }

        public GameProfile a() {
            return this.d;
        }

        public int b() {
            return this.b;
        }

        public EnumGamemode c() {
            return this.c;
        }

        @Nullable
        public IChatBaseComponent d() {
            return this.e;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("latency", this.b).add("gameMode", this.c).add("profile", this.d).add("displayName", this.e == null ? null : IChatBaseComponent.ChatSerializer.a(this.e)).toString();
        }
    }

    public static enum EnumPlayerInfoAction {

        ADD_PLAYER, UPDATE_GAME_MODE, UPDATE_LATENCY, UPDATE_DISPLAY_NAME, REMOVE_PLAYER;

        private EnumPlayerInfoAction() {}
    }
}
