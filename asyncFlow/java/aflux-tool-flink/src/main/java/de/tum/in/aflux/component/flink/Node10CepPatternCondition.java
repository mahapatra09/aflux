package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.CepPatternConditionActor;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.tools.core.*;

public class Node10CepPatternCondition extends AbstractMainExecutor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String NAME = "CEP add condition";

    public static final String PROPERTY_CONDITION_TYPE = "Condition Type";
    public static final String PROPERTY_ATTRIBUTE_TO_FILTER = "Attribute to filter";
    public static final String PROPERTY_CONDITION_OPERAND = "Condition Operand";
    public static final String PROPERTY_CONDITION_VALUE = "Condition Value";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node08CepPatternBegin.class,
                    true,
                    false,
                    true),
            new ToolSemanticsCondition(
                    Node09CepPatternAppend.class,
                    true,
                    false,
                    true),
    };

    public static final ToolPropertyOption[] CONDITION_TYPES = {
            new ToolPropertyOption("AND", API.getMethodName("Pattern.where")),
            new ToolPropertyOption("OR", API.getMethodName("Pattern.or"))
    };

    public static final ToolPropertyOption[] CONDITION_OPERANDS = {
            new ToolPropertyOption("greater than", ">"),
            new ToolPropertyOption("greater or equal than", ">="),
            new ToolPropertyOption("less or equal than", "<="),
            new ToolPropertyOption("less than", "<"),
            new ToolPropertyOption("equals", "=="),
            new ToolPropertyOption("not equals", "!=")
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_CONDITION_TYPE,
                    API.getMethodName("Pattern.where"),
                    PropertyInputType.SELECT,
                    CONDITION_TYPES,
                    "Type of condition",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_ATTRIBUTE_TO_FILTER,
                    "Charge",
                    PropertyInputType.SELECT,
                    Node04TransformationMap.SMARTSANTANDER_API_MODEL_ATTRIBUTES,
                    "Pick attribute",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_CONDITION_OPERAND,
                    ">=",
                    PropertyInputType.SELECT,
                    CONDITION_OPERANDS,
                    "Pick operand",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_CONDITION_VALUE,
                    "50",
                    PropertyInputType.TEXT,
                    null,
                    "Enter value",
                    "",
                    false)
    };

    public Node10CepPatternCondition() {
        super(Node10CepPatternCondition.NAME,
                CepPatternConditionActor.class.getCanonicalName(),
                Node10CepPatternCondition.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
