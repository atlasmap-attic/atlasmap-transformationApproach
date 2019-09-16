package io.atlasmap.spi;

import io.atlasmap.v2.FieldType;

public interface FunctionParameter {

    String name();

    FieldType type();

    boolean optional();

    boolean repeatable();
}
