/**
 * Copyright (C) 2017 Red Hat, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.functions;

import java.util.List;

import io.atlasmap.core.BaseFunctionFactory;
import io.atlasmap.core.BaseFunctionParameter;
import io.atlasmap.expression.Expression;
import io.atlasmap.expression.internal.ComparisonExpression;
import io.atlasmap.expression.parser.ParseException;
import io.atlasmap.spi.FunctionParameter;
import io.atlasmap.v2.FieldType;

public class LT extends BaseFunctionFactory {

    @Override
    public Expression create(List<Expression> args) throws ParseException {
        if (args.size() != 2) {
            throw new ParseException("The '" + getName() + "' function expects 2 arguments.");
        }
        return ComparisonExpression.createLessThan(args.get(0), args.get(1));
    }

    @Override
    public String description() {
        return "Returns true if argument 1 is less than argument 2, false otherwise.";
    }

    @Override
    public String getName() {
        return "Is less than?";
    }

    @Override
    public FunctionParameter[] parameters() {
        return new FunctionParameter[] {
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Argument 1";
                    }

                    @Override
                    public FieldType type() {
                        return FieldType.NUMBER;
                    }
                },
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Argument 2";
                    }

                    @Override
                    public FieldType type() {
                        return FieldType.NUMBER;
                    }
                }
        };
    }
}
