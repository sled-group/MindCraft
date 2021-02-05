package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AttributeMapServer extends AttributeMapBase {

    private final Set<AttributeInstance> e = Sets.newHashSet();
    protected final Map<String, AttributeInstance> d = new InsensitiveStringMap<>();

    public AttributeMapServer() {}

    @Override
    public AttributeModifiable a(IAttribute iattribute) {
        return (AttributeModifiable) super.a(iattribute);
    }

    @Override
    public AttributeModifiable a(String s) {
        AttributeInstance attributeinstance = super.a(s);

        if (attributeinstance == null) {
            attributeinstance = (AttributeInstance) this.d.get(s);
        }

        return (AttributeModifiable) attributeinstance;
    }

    @Override
    public AttributeInstance b(IAttribute iattribute) {
        AttributeInstance attributeinstance = super.b(iattribute);

        if (iattribute instanceof AttributeRanged && ((AttributeRanged) iattribute).g() != null) {
            this.d.put(((AttributeRanged) iattribute).g(), attributeinstance);
        }

        return attributeinstance;
    }

    @Override
    protected AttributeInstance c(IAttribute iattribute) {
        return new AttributeModifiable(this, iattribute);
    }

    @Override
    public void a(AttributeInstance attributeinstance) {
        if (attributeinstance.getAttribute().c()) {
            this.e.add(attributeinstance);
        }

        Iterator iterator = this.c.get(attributeinstance.getAttribute()).iterator();

        while (iterator.hasNext()) {
            IAttribute iattribute = (IAttribute) iterator.next();
            AttributeModifiable attributemodifiable = this.a(iattribute);

            if (attributemodifiable != null) {
                attributemodifiable.f();
            }
        }

    }

    public Set<AttributeInstance> getAttributes() {
        return this.e;
    }

    public Collection<AttributeInstance> c() {
        Set<AttributeInstance> set = Sets.newHashSet();
        Iterator iterator = this.a().iterator();

        while (iterator.hasNext()) {
            AttributeInstance attributeinstance = (AttributeInstance) iterator.next();

            if (attributeinstance.getAttribute().c()) {
                set.add(attributeinstance);
            }
        }

        return set;
    }
}
