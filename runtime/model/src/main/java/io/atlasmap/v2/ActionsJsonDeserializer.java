/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.v2;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ActionsJsonDeserializer extends JsonDeserializer<Actions> {

    private static final String START_INDEX = "startIndex";
    private static final String END_INDEX = "endIndex";
    private static final String MATCH = "match";
    private static final String DATE_FORMAT = "dateFormat";
    private static final String CLASS_NAME = "className";
    private static final String METHOD_NAME = "methodName";
    private static final String FROM_UNIT = "fromUnit";
    private static final String TO_UNIT = "toUnit";

    @Override
    public Actions deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        Actions actions = null;
        if (jp != null && jp.isExpectedStartArrayToken()) {
            actions = new Actions();
            jp.nextToken();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                JsonToken jsonToken = jp.nextToken();

                if (jsonToken == JsonToken.END_ARRAY) {
                    break;
                }

                Action action = processActionJsonToken(jp);
                if (action != null) {
                    actions.getActions().add(action);
                }
            }
        } else {
            throw new IOException(
                    "Invalid JSON where array expected: " + (jp != null ? jp.getCurrentToken().asString() : null));
        }

        return actions;
    }

    protected Action processActionJsonToken(JsonParser jsonToken) throws IOException {

        if (jsonToken.getCurrentName() == null) {
            return null;
        }

        switch (jsonToken.getCurrentName()) {
            case "AbsoluteValue":
                return new AbsoluteValue();
            case "Add":
                return new Average();
            case "Average":
                return new Average();
            case "Camelize":
                return new Camelize();
            case "Capitalize":
                return new Capitalize();
            case "Ceiling":
                return new Ceiling();
            case "ConvertAreaUnit":
                return processConvertAreaUnitJsonToken(jsonToken);
            case "ConvertDistanceUnit":
                return processConvertDistanceUnitJsonToken(jsonToken);
            case "ConvertMassUnit":
                return processConvertMassUnitJsonToken(jsonToken);
            case "ConvertVolumeUnit":
                return processConvertVolumeUnitJsonToken(jsonToken);
            case "CurrentDate":
                return processCurrentDateJsonToken(jsonToken);
            case "CurrentDateTime":
                return processCurrentDateTimeJsonToken(jsonToken);
            case "CurrentTime":
                return processCurrentTimeJsonToken(jsonToken);
            case "CustomAction":
                return processCustomActionJsonToken(jsonToken);
            case "Divide":
                return new Divide();
            case "Floor":
                return new Floor();
            case "GenerateUUID":
                return new GenerateUUID();
            case "Maximum":
                return new Maximum();
            case "Minimum":
                return new Minimum();
            case "Multiply":
                return new Multiply();
            case "Lowercase":
                return new Lowercase();
            case "PadStringLeft":
                return processPadStringLeftJsonToken(jsonToken);
            case "PadStringRight":
                return processPadStringRightJsonToken(jsonToken);
            case "Replace":
                return processReplaceJsonToken(jsonToken);
            case "Round":
                return new Round();
            case "SeparateByDash":
                return new SeparateByDash();
            case "SeparateByUnderscore":
                return new SeparateByUnderscore();
            case "StringLength":
                return new StringLength();
            case "SubString":
                return processSubStringJsonToken(jsonToken);
            case "SubStringAfter":
                return processSubStringAfterJsonToken(jsonToken);
            case "SubStringBefore":
                return processSubStringBeforeJsonToken(jsonToken);
            case "Subtract":
                return new Subtract();
            case "Trim":
                return new Trim();
            case "TrimLeft":
                return new TrimLeft();
            case "TrimRight":
                return new TrimRight();
            case "Uppercase":
                return new Uppercase();
            default:
                // ref: https://github.com/atlasmap/atlasmap/issues/6
                // TODO: Logger not required in model module
                // logger.warn("Unsupported action named: " + jsonToken.getCurrentName());
        }

        return null;
    }

    protected Replace processReplaceJsonToken(JsonParser jsonToken) throws IOException {
        Replace replace = new Replace();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return replace;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
                case "oldString":
                    jsonToken.nextToken();
                    replace.setOldString(jsonToken.getValueAsString());
                    break;
                case "newString":
                    jsonToken.nextToken();
                    replace.setNewString(jsonToken.getValueAsString());
                    break;
                default:
                    break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return replace;
    }

    protected SubString processSubStringJsonToken(JsonParser jsonToken) throws IOException {
        SubString action = new SubString();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case START_INDEX:
                jsonToken.nextToken();
                action.setStartIndex(jsonToken.getIntValue());
                break;
            case END_INDEX:
                jsonToken.nextToken();
                action.setEndIndex(jsonToken.getIntValue());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected SubStringAfter processSubStringAfterJsonToken(JsonParser jsonToken) throws IOException {
        SubStringAfter action = new SubStringAfter();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case START_INDEX:
                jsonToken.nextToken();
                action.setStartIndex(jsonToken.getIntValue());
                break;
            case END_INDEX:
                jsonToken.nextToken();
                action.setEndIndex(jsonToken.getIntValue());
                break;
            case MATCH:
                jsonToken.nextToken();
                action.setMatch(jsonToken.getValueAsString());
                break;

            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected SubStringBefore processSubStringBeforeJsonToken(JsonParser jsonToken) throws IOException {
        SubStringBefore action = new SubStringBefore();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case START_INDEX:
                jsonToken.nextToken();
                action.setStartIndex(jsonToken.getIntValue());
                break;
            case END_INDEX:
                jsonToken.nextToken();
                action.setEndIndex(jsonToken.getIntValue());
                break;
            case MATCH:
                jsonToken.nextToken();
                action.setMatch(jsonToken.getValueAsString());
                break;

            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected PadStringLeft processPadStringLeftJsonToken(JsonParser jsonToken) throws IOException {
        PadStringLeft action = new PadStringLeft();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case "padCharacter":
                jsonToken.nextToken();
                action.setPadCharacter(jsonToken.getValueAsString());
                break;
            case "padCount":
                jsonToken.nextToken();
                action.setPadCount(jsonToken.getIntValue());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected PadStringRight processPadStringRightJsonToken(JsonParser jsonToken) throws IOException {
        PadStringRight action = new PadStringRight();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case "padCharacter":
                jsonToken.nextToken();
                action.setPadCharacter(jsonToken.getValueAsString());
                break;
            case "padCount":
                jsonToken.nextToken();
                action.setPadCount(jsonToken.getIntValue());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected CurrentDate processCurrentDateJsonToken(JsonParser jsonToken) throws IOException {
        CurrentDate action = new CurrentDate();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case DATE_FORMAT:
                jsonToken.nextToken();
                action.setDateFormat(jsonToken.getValueAsString());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected CurrentTime processCurrentTimeJsonToken(JsonParser jsonToken) throws IOException {
        CurrentTime action = new CurrentTime();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case DATE_FORMAT:
                jsonToken.nextToken();
                action.setDateFormat(jsonToken.getValueAsString());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected CurrentDateTime processCurrentDateTimeJsonToken(JsonParser jsonToken) throws IOException {
        CurrentDateTime action = new CurrentDateTime();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case DATE_FORMAT:
                jsonToken.nextToken();
                action.setDateFormat(jsonToken.getValueAsString());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected CustomAction processCustomActionJsonToken(JsonParser jsonToken) throws IOException {
        CustomAction action = new CustomAction();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case CLASS_NAME:
                jsonToken.nextToken();
                action.setClassName(jsonToken.getValueAsString());
                break;
            case METHOD_NAME:
                jsonToken.nextToken();
                action.setMethodName(jsonToken.getValueAsString());
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));
        return action;
    }

    protected ConvertVolumeUnit processConvertVolumeUnitJsonToken(JsonParser jsonToken) {
        ConvertVolumeUnit action = new ConvertVolumeUnit();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        return action;
    }

    protected ConvertMassUnit processConvertMassUnitJsonToken(JsonParser jsonToken) throws IOException {
        ConvertMassUnit action = new ConvertMassUnit();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case FROM_UNIT:
                jsonToken.nextToken();
                action.setFromUnit(MassUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            case TO_UNIT:
                jsonToken.nextToken();
                action.setToUnit(MassUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));

        return action;

    }

    protected ConvertDistanceUnit processConvertDistanceUnitJsonToken(JsonParser jsonToken) throws IOException {
        ConvertDistanceUnit action = new ConvertDistanceUnit();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case FROM_UNIT:
                jsonToken.nextToken();
                action.setFromUnit(DistanceUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            case TO_UNIT:
                jsonToken.nextToken();
                action.setToUnit(DistanceUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));

        return action;

    }

    protected ConvertAreaUnit processConvertAreaUnitJsonToken(JsonParser jsonToken) throws IOException {
        ConvertAreaUnit action = new ConvertAreaUnit();

        if (JsonToken.END_ARRAY.equals(jsonToken.currentToken())
                || JsonToken.END_OBJECT.equals(jsonToken.currentToken())) {
            return action;
        }

        JsonToken nextToken = null;
        do {
            if (JsonToken.START_OBJECT.equals(jsonToken.currentToken())) {
                jsonToken.nextToken();
            }
            switch (jsonToken.getCurrentName()) {
            case FROM_UNIT:
                jsonToken.nextToken();
                action.setFromUnit(AreaUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            case TO_UNIT:
                jsonToken.nextToken();
                action.setToUnit(AreaUnitType.fromValue(jsonToken.getValueAsString()));
                break;
            default:
                break;
            }

            nextToken = jsonToken.nextToken();
        } while (!JsonToken.END_ARRAY.equals(nextToken) && !JsonToken.END_OBJECT.equals(nextToken));

        return action;

    }

}