package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;

public interface AdvancementRequirements {

    AdvancementRequirements AND = (collection) -> {
        String[][] astring = new String[collection.size()][];
        int i = 0;

        String s;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); astring[i++] = new String[]{s}) {
            s = (String) iterator.next();
        }

        return astring;
    };
    AdvancementRequirements OR = (collection) -> {
        return new String[][]{(String[]) collection.toArray(new String[0])};
    };

    String[][] createRequirements(Collection<String> collection);
}
