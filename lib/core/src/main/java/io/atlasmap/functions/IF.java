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
import io.atlasmap.expression.internal.BooleanExpression;
import io.atlasmap.expression.parser.ParseException;
import io.atlasmap.spi.FunctionParameter;
import io.atlasmap.v2.FieldType;

public class IF extends BaseFunctionFactory {

    @Override
    public Expression create(List<Expression> args) throws ParseException {
        if (args.size() != 3) {
            throw new ParseException("IF expects 3 arguments.");
        }
        BooleanExpression conditional = BooleanExpression.asBooleanExpression(args.get(0));
        Expression trueExpression = args.get(1);
        Expression falseExpression = args.get(2);
        return (ctx) -> {
            if (conditional.matches(ctx)) {
                return trueExpression.evaluate(ctx);
            } else {
                return falseExpression.evaluate(ctx);
            }
        };
    }

    @Override
    public String description() {
        return "Tests a condition and evaluates the 'then' expression if true or the 'else' expression if false.";
    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public FunctionParameter[] parameters() {
        return new FunctionParameter[] {
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Condition";
                    }

                    @Override
                    public FieldType type() {
                        return FieldType.BOOLEAN;
                    }
                },
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Then";
                    }
                },
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Else";
                    }
                }
        };
    }
}
