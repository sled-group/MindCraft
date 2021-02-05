package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;

public class AttributeModifiable implements AttributeInstance {

    private final AttributeMapBase a;
    private final IAttribute b;
    private final Map<AttributeModifier.Operation, Set<AttributeModifier>> c = Maps.newEnumMap(AttributeModifier.Operation.class);
    private final Map<String, Set<AttributeModifier>> d = Maps.newHashMap();
    private final Map<UUID, AttributeModifier> e = Maps.newHashMap();
    private double f;
    private boolean g = true;
    private double h;

    public AttributeModifiable(AttributeMapBase attributemapbase, IAttribute iattribute) {
        this.a = attributemapbase;
        this.b = iattribute;
        this.f = iattribute.getDefault();
        AttributeModifier.Operation[] aattributemodifier_operation = AttributeModifier.Operation.values();
        int i = aattributemodifier_operation.length;

        for (int j = 0; j < i; ++j) {
            AttributeModifier.Operation attributemodifier_operation = aattributemodifier_operation[j];

            this.c.put(attributemodifier_operation, Sets.newHashSet());
        }

    }

    @Override
    public IAttribute getAttribute() {
        return this.b;
    }

    @Override
    public double getBaseValue() {
        return this.f;
    }

    @Override
    public void setValue(double d0) {
        if (d0 != this.getBaseValue()) {
            this.f = d0;
            this.f();
        }
    }

    @Override
    public Collection<AttributeModifier> a(AttributeModifier.Operation attributemodifier_operation) {
        return (Collection) this.c.get(attributemodifier_operation);
    }

    @Override
    public Collection<AttributeModifier> getModifiers() {
        Set<AttributeModifier> set = Sets.newHashSet();
        AttributeModifier.Operation[] aattributemodifier_operation = AttributeModifier.Operation.values();
        int i = aattributemodifier_operation.length;

        for (int j = 0; j < i; ++j) {
            AttributeModifier.Operation attributemodifier_operation = aattributemodifier_operation[j];

            set.addAll(this.a(attributemodifier_operation));
        }

        return set;
    }

    @Nullable
    @Override
    public AttributeModifier a(UUID uuid) {
        return (AttributeModifier) this.e.get(uuid);
    }

    @Override
    public boolean a(AttributeModifier attributemodifier) {
        return this.e.get(attributemodifier.getUniqueId()) != null;
    }

    @Override
    public void addModifier(AttributeModifier attributemodifier) {
        if (this.a(attributemodifier.getUniqueId()) != null) {
            throw new IllegalArgumentException("Modifier is already applied on this attribute!");
        } else {
            Set<AttributeModifier> set = (Set) this.d.computeIfAbsent(attributemodifier.getName(), (s) -> {
                return Sets.newHashSet();
            });

            ((Set) this.c.get(attributemodifier.getOperation())).add(attributemodifier);
            set.add(attributemodifier);
            this.e.put(attributemodifier.getUniqueId(), attributemodifier);
            this.f();
        }
    }

    protected void f() {
        this.g = true;
        this.a.a((AttributeInstance) this);
    }

    @Override
    public void removeModifier(AttributeModifier attributemodifier) {
        AttributeModifier.Operation[] aattributemodifier_operation = AttributeModifier.Operation.values();
        int i = aattributemodifier_operation.length;

        for (int j = 0; j < i; ++j) {
            AttributeModifier.Operation attributemodifier_operation = aattributemodifier_operation[j];

            ((Set) this.c.get(attributemodifier_operation)).remove(attributemodifier);
        }

        Set<AttributeModifier> set = (Set) this.d.get(attributemodifier.getName());

        if (set != null) {
            set.remove(attributemodifier);
            if (set.isEmpty()) {
                this.d.remove(attributemodifier.getName());
            }
        }

        this.e.remove(attributemodifier.getUniqueId());
        this.f();
    }

    @Override
    public void b(UUID uuid) {
        AttributeModifier attributemodifier = this.a(uuid);

        if (attributemodifier != null) {
            this.removeModifier(attributemodifier);
        }

    }

    @Override
    public double getValue() {
        if (this.g) {
            this.h = this.g();
            this.g = false;
        }

        return this.h;
    }

    private double g() {
        double d0 = this.getBaseValue();

        AttributeModifier attributemodifier;

        for (Iterator iterator = this.b(AttributeModifier.Operation.ADDITION).iterator(); iterator.hasNext(); d0 += attributemodifier.getAmount()) {
            attributemodifier = (AttributeModifier) iterator.next();
        }

        double d1 = d0;

        AttributeModifier attributemodifier1;
        Iterator iterator1;

        for (iterator1 = this.b(AttributeModifier.Operation.MULTIPLY_BASE).iterator(); iterator1.hasNext(); d1 += d0 * attributemodifier1.getAmount()) {
            attributemodifier1 = (AttributeModifier) iterator1.next();
        }

        for (iterator1 = this.b(AttributeModifier.Operation.MULTIPLY_TOTAL).iterator(); iterator1.hasNext(); d1 *= 1.0D + attributemodifier1.getAmount()) {
            attributemodifier1 = (AttributeModifier) iterator1.next();
        }

        return this.b.a(d1);
    }

    private Collection<AttributeModifier> b(AttributeModifier.Operation attributemodifier_operation) {
        Set<AttributeModifier> set = Sets.newHashSet(this.a(attributemodifier_operation));

        for (IAttribute iattribute = this.b.d(); iattribute != null; iattribute = iattribute.d()) {
            AttributeInstance attributeinstance = this.a.a(iattribute);

            if (attributeinstance != null) {
                set.addAll(attributeinstance.a(attributemodifier_operation));
            }
        }

        return set;
    }
}
