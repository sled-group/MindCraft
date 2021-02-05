package net.minecraft.server;

import com.mojang.datafixers.Dynamic;

public interface DefinedStructureRuleTestType extends DynamicDeserializer<DefinedStructureRuleTest> {

    DefinedStructureRuleTestType b = a("always_true", (dynamic) -> {
        return DefinedStructureTestTrue.a;
    });
    DefinedStructureRuleTestType c = a("block_match", DefinedStructureTestBlock::new);
    DefinedStructureRuleTestType d = a("blockstate_match", DefinedStructureTestBlockState::new);
    DefinedStructureRuleTestType e = a("tag_match", DefinedStructureTestTag::new);
    DefinedStructureRuleTestType f = a("random_block_match", DefinedStructureTestRandomBlock::new);
    DefinedStructureRuleTestType g = a("random_blockstate_match", DefinedStructureTestRandomBlockState::new);

    static DefinedStructureRuleTestType a(String s, DefinedStructureRuleTestType definedstructureruletesttype) {
        return (DefinedStructureRuleTestType) IRegistry.a(IRegistry.RULE_TEST, s, (Object) definedstructureruletesttype);
    }
}
