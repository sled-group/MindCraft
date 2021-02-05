package net.minecraft.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoteControlSession extends RemoteConnectionThread {

    private static final Logger LOGGER = LogManager.getLogger();
    private boolean i;
    private Socket j;
    private final byte[] k = new byte[1460];
    private final String l;

    RemoteControlSession(IMinecraftServer iminecraftserver, String s, Socket socket) {
        super(iminecraftserver, "RCON Client");
        this.j = socket;

        try {
            this.j.setSoTimeout(0);
        } catch (Exception exception) {
            this.a = false;
        }

        this.l = s;
        this.b("Rcon connection from: " + socket.getInetAddress());
    }

    public void run() {
        while (true) {
            try {
                if (!this.a) {
                    return;
                }

                BufferedInputStream bufferedinputstream = new BufferedInputStream(this.j.getInputStream());
                int i = bufferedinputstream.read(this.k, 0, 1460);

                if (10 > i) {
                    return;
                }

                byte b0 = 0;
                int j = StatusChallengeUtils.b(this.k, 0, i);

                if (j == i - 4) {
                    int k = b0 + 4;
                    int l = StatusChallengeUtils.b(this.k, k, i);

                    k += 4;
                    int i1 = StatusChallengeUtils.a(this.k, k);

                    k += 4;
                    switch (i1) {
                        case 2:
                            if (this.i) {
                                String s = StatusChallengeUtils.a(this.k, k, i);

                                try {
                                    this.a(l, this.b.executeRemoteCommand(s));
                                } catch (Exception exception) {
                                    this.a(l, "Error executing: " + s + " (" + exception.getMessage() + ")");
                                }
                                continue;
                            }

                            this.f();
                            continue;
                        case 3:
                            String s1 = StatusChallengeUtils.a(this.k, k, i);
                            int j1 = k + s1.length();

                            if (!s1.isEmpty() && s1.equals(this.l)) {
                                this.i = true;
                                this.a(l, 2, "");
                                continue;
                            }

                            this.i = false;
                            this.f();
                            continue;
                        default:
                            this.a(l, String.format("Unknown request %s", Integer.toHexString(i1)));
                            continue;
                    }
                }
            } catch (SocketTimeoutException sockettimeoutexception) {
                return;
            } catch (IOException ioexception) {
                return;
            } catch (Exception exception1) {
                RemoteControlSession.LOGGER.error("Exception whilst parsing RCON input", exception1);
                return;
            } finally {
                this.g();
            }

            return;
        }
    }

    private void a(int i, int j, String s) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1248);
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        byte[] abyte = s.getBytes("UTF-8");

        dataoutputstream.writeInt(Integer.reverseBytes(abyte.length + 10));
        dataoutputstream.writeInt(Integer.reverseBytes(i));
        dataoutputstream.writeInt(Integer.reverseBytes(j));
        dataoutputstream.write(abyte);
        dataoutputstream.write(0);
        dataoutputstream.write(0);
        this.j.getOutputStream().write(bytearrayoutputstream.toByteArray());
    }

    private void f() throws IOException {
        this.a(-1, 2, "");
    }

    private void a(int i, String s) throws IOException {
        int j = s.length();

        do {
            int k = 4096 <= j ? 4096 : j;

            this.a(i, 0, s.substring(0, k));
            s = s.substring(k);
            j = s.length();
        } while (0 != j);

    }

    @Override
    public void b() {
        super.b();
        this.g();
    }

    private void g() {
        if (null != this.j) {
            try {
                this.j.close();
            } catch (IOException ioexception) {
                this.c("IO: " + ioexception.getMessage());
            }

            this.j = null;
        }
    }
}
