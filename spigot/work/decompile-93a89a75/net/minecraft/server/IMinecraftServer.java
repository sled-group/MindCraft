package net.minecraft.server;

public interface IMinecraftServer {

    DedicatedServerProperties getDedicatedServerProperties();

    String e_();

    int e();

    String f_();

    String getVersion();

    int getPlayerCount();

    int getMaxPlayers();

    String[] getPlayers();

    String getWorld();

    String getPlugins();

    String executeRemoteCommand(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void g(String s);

    void h(String s);
}
