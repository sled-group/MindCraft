package net.minecraft.server;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Mailbox<Msg> extends AutoCloseable {

    String bf();

    void a(Msg msg);

    default void close() {}

    default <Source> CompletableFuture<Source> a(Function<? super Mailbox<Source>, ? extends Msg> function) {
        CompletableFuture<Source> completablefuture = new CompletableFuture();

        completablefuture.getClass();
        Msg msg = function.apply(a("ask future procesor handle", completablefuture::complete));

        this.a(msg);
        return completablefuture;
    }

    static <Msg> Mailbox<Msg> a(final String s, final Consumer<Msg> consumer) {
        return new Mailbox<Msg>() {
            @Override
            public String bf() {
                return s;
            }

            @Override
            public void a(Msg msg) {
                consumer.accept(msg);
            }

            public String toString() {
                return s;
            }
        };
    }
}
