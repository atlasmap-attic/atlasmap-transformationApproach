package io.atlasmap.functions;

import java.util.List;

import io.atlasmap.core.BaseFunctionFactory;
import io.atlasmap.expression.Expression;
import io.atlasmap.expression.parser.ParseException;

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
}
