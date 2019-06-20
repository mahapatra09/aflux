package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.TransformationWindowOperationActor;
import de.tum.in.aflux.tools.core.*;

import static de.tum.in.aflux.component.flink.Node05TransformationWindow.PROPERTY_SEPARATOR;

public class Node06TransformationWindowOperation extends AbstractMainExecutor {

    public static final String NAME = "Window Operation";

    public static final String PROPERTY_WINDOW_OPERATION_TYPE = "Window Operation";
    public static final String PROPERTY_WINDOW_AGGREGATE_OPERATION = "Window Aggregate Operation";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node05TransformationWindow.class,
                    true,
                    true,
                    true),
    };

    public static final ToolPropertyOption[] WINDOW_OPERATIONS = {
            new ToolPropertyOption("Aggregate", "aggregate"),
            new ToolPropertyOption("Sum", "sum"),
            new ToolPropertyOption("Min", "max"),
            new ToolPropertyOption("Max", "min")
    };
    public static final ToolPropertyOption[] WINDOW_AGGREGATE_OPERATIONS = {
            new ToolPropertyOption("Average", "average")
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_WINDOW_OPERATION_TYPE,
                    "aggregate",
                    PropertyInputType.SELECT,
                    WINDOW_OPERATIONS,
                    "Pick the operation you would like to apply to the windowed data",
                    "",
                    false),
            new ToolProperty(PROPERTY_SEPARATOR,
                    "Optional",
                    PropertyInputType.SEPARATOR,
                    null,
                    "",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_WINDOW_AGGREGATE_OPERATION,
                    "average",
                    PropertyInputType.SELECT,
                    WINDOW_AGGREGATE_OPERATIONS,
                    "Pick the type of aggregation",
                    "",
                    false)
    };

    public Node06TransformationWindowOperation() {
        super(Node06TransformationWindowOperation.NAME,
                TransformationWindowOperationActor.class.getCanonicalName(),
                Node06TransformationWindowOperation.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
