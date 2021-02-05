package net.minecraft.server;

import com.mojang.datafixers.Dynamic;

public interface DefinedStructureStructureProcessorType extends DynamicDeserializer<DefinedStructureProcessor> {

    DefinedStructureStructureProcessorType b = a("block_ignore", DefinedStructureProcessorBlockIgnore::new);
    DefinedStructureStructureProcessorType c = a("block_rot", DefinedStructureProcessorRotation::new);
    DefinedStructureStructureProcessorType d = a("gravity", DefinedStructureProcessorGravity::new);
    DefinedStructureStructureProcessorType e = a("jigsaw_replacement", (dynamic) -> {
        return DefinedStructureProcessorJigsawReplacement.a;
    });
    DefinedStructureStructureProcessorType f = a("rule", DefinedStructureProcessorRule::new);
    DefinedStructureStructureProcessorType g = a("nop", (dynamic) -> {
        return DefinedStructureProcessorNop.a;
    });

    static DefinedStructureStructureProcessorType a(String s, DefinedStructureStructureProcessorType definedstructurestructureprocessortype) {
        return (DefinedStructureStructureProcessorType) IRegistry.a(IRegistry.STRUCTURE_PROCESSOR, s, (Object) definedstructurestructureprocessortype);
    }
}
