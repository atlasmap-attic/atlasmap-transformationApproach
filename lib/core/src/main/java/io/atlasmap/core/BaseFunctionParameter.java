package io.atlasmap.core;

import io.atlasmap.spi.FunctionParameter;
import io.atlasmap.v2.FieldType;

public abstract class BaseFunctionParameter implements FunctionParameter {

    @Override
    public FieldType type() {
        return FieldType.ANY;
    }

    @Override
    public boolean optional() {
        return false;
    }

    @Override
    public boolean repeatable() {
        return false;
    }

}
