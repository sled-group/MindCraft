package net.minecraft.server;

import java.util.Vector;
import javax.swing.JList;

public class PlayerListBox extends JList<String> {

    private final MinecraftServer a;
    private int b;

    public PlayerListBox(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        minecraftserver.b(this::tick);
    }

    public void tick() {
        if (this.b++ % 20 == 0) {
            Vector<String> vector = new Vector();

            for (int i = 0; i < this.a.getPlayerList().getPlayers().size(); ++i) {
                vector.add(((EntityPlayer) this.a.getPlayerList().getPlayers().get(i)).getProfile().getName());
            }

            this.setListData(vector);
        }

    }
}
