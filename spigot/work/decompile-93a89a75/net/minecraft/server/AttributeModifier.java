package net.minecraft.server;

import io.netty.util.internal.ThreadLocalRandom;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class AttributeModifier {

    private final double a;
    private final AttributeModifier.Operation b;
    private final Supplier<String> c;
    private final UUID d;
    private boolean e;

    public AttributeModifier(String s, double d0, AttributeModifier.Operation attributemodifier_operation) {
        this(MathHelper.a((Random) ThreadLocalRandom.current()), () -> {
            return s;
        }, d0, attributemodifier_operation);
    }

    public AttributeModifier(UUID uuid, String s, double d0, AttributeModifier.Operation attributemodifier_operation) {
        this(uuid, () -> {
            return s;
        }, d0, attributemodifier_operation);
    }

    public AttributeModifier(UUID uuid, Supplier<String> supplier, double d0, AttributeModifier.Operation attributemodifier_operation) {
        this.e = true;
        this.d = uuid;
        this.c = supplier;
        this.a = d0;
        this.b = attributemodifier_operation;
    }

    public UUID getUniqueId() {
        return this.d;
    }

    public String getName() {
        return (String) this.c.get();
    }

    public AttributeModifier.Operation getOperation() {
        return this.b;
    }

    public double getAmount() {
        return this.a;
    }

    public boolean e() {
        return this.e;
    }

    public AttributeModifier a(boolean flag) {
        this.e = flag;
        return this;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            AttributeModifier attributemodifier = (AttributeModifier) object;

            return Objects.equals(this.d, attributemodifier.d);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.d != null ? this.d.hashCode() : 0;
    }

    public String toString() {
        return "AttributeModifier{amount=" + this.a + ", operation=" + this.b + ", name='" + (String) this.c.get() + '\'' + ", id=" + this.d + ", serialize=" + this.e + '}';
    }

    public static enum Operation {

        ADDITION(0), MULTIPLY_BASE(1), MULTIPLY_TOTAL(2);

        private static final AttributeModifier.Operation[] d = new AttributeModifier.Operation[]{AttributeModifier.Operation.ADDITION, AttributeModifier.Operation.MULTIPLY_BASE, AttributeModifier.Operation.MULTIPLY_TOTAL};
        private final int e;

        private Operation(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public static AttributeModifier.Operation a(int i) {
            if (i >= 0 && i < AttributeModifier.Operation.d.length) {
                return AttributeModifier.Operation.d[i];
            } else {
                throw new IllegalArgumentException("No operation with value " + i);
            }
        }
    }
}
