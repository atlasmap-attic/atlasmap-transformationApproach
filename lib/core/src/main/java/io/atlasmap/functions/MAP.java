/**
 * Copyright (C) 2019 Red Hat, Inc.
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
import io.atlasmap.expression.parser.ParseException;
import io.atlasmap.spi.FunctionParameter;

public class MAP extends BaseFunctionFactory {

    @Override
    public Expression create(List<Expression> args) throws ParseException {
        if (args.size() != 2) {
            throw new ParseException("The '" + getName() + "' function expects 2 arguments.");
        }
        Expression sourceExpression = args.get(0);
        Expression targetExpression = args.get(1);
        return (ctx) -> {
            // TODO: map source to target
            return null;
        };
    }

    @Override
    public String getName() {
        return "Map";
    }

    @Override
    public String description() {
        return "Maps the source argument to a target model field.";
    }

    @Override
    public FunctionParameter[] parameters() {
        return new FunctionParameter[] {
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Source";
                    }
                }, new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Target";
                    }
                }
        };
    }

}
