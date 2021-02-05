package net.minecraft.server;

public interface OperatorBoolean {

    OperatorBoolean FALSE = (flag, flag1) -> {
        return false;
    };
    OperatorBoolean NOT_OR = (flag, flag1) -> {
        return !flag && !flag1;
    };
    OperatorBoolean ONLY_SECOND = (flag, flag1) -> {
        return flag1 && !flag;
    };
    OperatorBoolean NOT_FIRST = (flag, flag1) -> {
        return !flag;
    };
    OperatorBoolean ONLY_FIRST = (flag, flag1) -> {
        return flag && !flag1;
    };
    OperatorBoolean NOT_SECOND = (flag, flag1) -> {
        return !flag1;
    };
    OperatorBoolean NOT_SAME = (flag, flag1) -> {
        return flag != flag1;
    };
    OperatorBoolean NOT_AND = (flag, flag1) -> {
        return !flag || !flag1;
    };
    OperatorBoolean AND = (flag, flag1) -> {
        return flag && flag1;
    };
    OperatorBoolean SAME = (flag, flag1) -> {
        return flag == flag1;
    };
    OperatorBoolean SECOND = (flag, flag1) -> {
        return flag1;
    };
    OperatorBoolean CAUSES = (flag, flag1) -> {
        return !flag || flag1;
    };
    OperatorBoolean FIRST = (flag, flag1) -> {
        return flag;
    };
    OperatorBoolean CAUSED_BY = (flag, flag1) -> {
        return flag || !flag1;
    };
    OperatorBoolean OR = (flag, flag1) -> {
        return flag || flag1;
    };
    OperatorBoolean TRUE = (flag, flag1) -> {
        return true;
    };

    boolean apply(boolean flag, boolean flag1);
}
