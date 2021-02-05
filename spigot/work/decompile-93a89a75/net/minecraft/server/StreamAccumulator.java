package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamAccumulator<T> {

    private final List<T> a = Lists.newArrayList();
    private final Spliterator<T> b;

    public StreamAccumulator(Stream<T> stream) {
        this.b = stream.spliterator();
    }

    public Stream<T> a() {
        return StreamSupport.stream(new AbstractSpliterator<T>(Long.MAX_VALUE, 0) {
            private int b;

            public boolean tryAdvance(Consumer<? super T> consumer) {
                while (true) {
                    if (this.b >= StreamAccumulator.this.a.size()) {
                        Spliterator spliterator = StreamAccumulator.this.b;
                        List list = StreamAccumulator.this.a;

                        list.getClass();
                        if (spliterator.tryAdvance(list::add)) {
                            continue;
                        }

                        return false;
                    }

                    consumer.accept(StreamAccumulator.this.a.get(this.b++));
                    return true;
                }
            }
        }, false);
    }
}
