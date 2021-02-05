package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DedicatedPlayerList extends PlayerList {

    private static final Logger LOGGER = LogManager.getLogger();

    public DedicatedPlayerList(DedicatedServer dedicatedserver) {
        super(dedicatedserver, dedicatedserver.getDedicatedServerProperties().maxPlayers);
        DedicatedServerProperties dedicatedserverproperties = dedicatedserver.getDedicatedServerProperties();

        this.a(dedicatedserverproperties.viewDistance);
        super.setHasWhitelist((Boolean) dedicatedserverproperties.whiteList.get());
        if (!dedicatedserver.isEmbeddedServer()) {
            this.getProfileBans().a(true);
            this.getIPBans().a(true);
        }

        this.z();
        this.x();
        this.y();
        this.w();
        this.A();
        this.C();
        this.B();
        if (!this.getWhitelist().c().exists()) {
            this.D();
        }

    }

    @Override
    public void setHasWhitelist(boolean flag) {
        super.setHasWhitelist(flag);
        this.getServer().setHasWhitelist(flag);
    }

    @Override
    public void addOp(GameProfile gameprofile) {
        super.addOp(gameprofile);
        this.B();
    }

    @Override
    public void removeOp(GameProfile gameprofile) {
        super.removeOp(gameprofile);
        this.B();
    }

    @Override
    public void reloadWhitelist() {
        this.C();
    }

    private void w() {
        try {
            this.getIPBans().save();
        } catch (IOException ioexception) {
            DedicatedPlayerList.LOGGER.warn("Failed to save ip banlist: ", ioexception);
        }

    }

    private void x() {
        try {
            this.getProfileBans().save();
        } catch (IOException ioexception) {
            DedicatedPlayerList.LOGGER.warn("Failed to save user banlist: ", ioexception);
        }

    }

    private void y() {
        try {
            this.getIPBans().load();
        } catch (IOException ioexception) {
            DedicatedPlayerList.LOGGER.warn("Failed to load ip banlist: ", ioexception);
        }

    }

    private void z() {
        try {
            this.getProfileBans().load();
        } catch (IOException ioexception) {
            DedicatedPlayerList.LOGGER.warn("Failed to load user banlist: ", ioexception);
        }

    }

    private void A() {
        try {
            this.getOPs().load();
        } catch (Exception exception) {
            DedicatedPlayerList.LOGGER.warn("Failed to load operators list: ", exception);
        }

    }

    private void B() {
        try {
            this.getOPs().save();
        } catch (Exception exception) {
            DedicatedPlayerList.LOGGER.warn("Failed to save operators list: ", exception);
        }

    }

    private void C() {
        try {
            this.getWhitelist().load();
        } catch (Exception exception) {
            DedicatedPlayerList.LOGGER.warn("Failed to load white-list: ", exception);
        }

    }

    private void D() {
        try {
            this.getWhitelist().save();
        } catch (Exception exception) {
            DedicatedPlayerList.LOGGER.warn("Failed to save white-list: ", exception);
        }

    }

    @Override
    public boolean isWhitelisted(GameProfile gameprofile) {
        return !this.getHasWhitelist() || this.isOp(gameprofile) || this.getWhitelist().isWhitelisted(gameprofile);
    }

    @Override
    public DedicatedServer getServer() {
        return (DedicatedServer) super.getServer();
    }

    @Override
    public boolean f(GameProfile gameprofile) {
        return this.getOPs().b(gameprofile);
    }
}
