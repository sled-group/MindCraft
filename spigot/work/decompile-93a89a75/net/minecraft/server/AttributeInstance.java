package net.minecraft.server;

import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;

public interface AttributeInstance {

    IAttribute getAttribute();

    double getBaseValue();

    void setValue(double d0);

    Collection<AttributeModifier> a(AttributeModifier.Operation attributemodifier_operation);

    Collection<AttributeModifier> getModifiers();

    boolean a(AttributeModifier attributemodifier);

    @Nullable
    AttributeModifier a(UUID uuid);

    void addModifier(AttributeModifier attributemodifier);

    void removeModifier(AttributeModifier attributemodifier);

    void b(UUID uuid);

    double getValue();
}
