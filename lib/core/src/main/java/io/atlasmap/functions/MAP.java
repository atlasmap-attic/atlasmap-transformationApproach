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
            throw new ParseException(getClass().getSimpleName() + " expects 2 arguments.");
        }
        Expression sourceExpression = args.get(1);
        Expression targetExpression = args.get(2);
        return (ctx) -> {
            // TODO map source to target
            return null;
        };
    }

    @Override
    public String getName() {
        return "Map";
    }

    @Override
    public String description() {
        return "Maps the source argument to a target model field";
    }

    @Override
    public FunctionParameter[] parameters() {
        return new FunctionParameter[] {
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Source";
                    }
                },
                new BaseFunctionParameter() {

                    @Override
                    public String name() {
                        return "Source";
                    }
                }
        };
    }
}
