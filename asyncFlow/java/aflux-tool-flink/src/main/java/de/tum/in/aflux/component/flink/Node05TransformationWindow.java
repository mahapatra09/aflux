package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.TransformationWindowActor;
import de.tum.in.aflux.tools.core.*;

public class Node05TransformationWindow extends AbstractMainExecutor {

    public static final String NAME = "Window";

    public static final String PROPERTY_WINDOW_TYPE = "Window Type";
    public static final String PROPERTY_WINDOW_UNITS = "Window Units";
    public static final String PROPERTY_WINDOW_SIZE = "Window Size";
    public static final String PROPERTY_SEPARATOR = "Separator";
    public static final String PROPERTY_WINDOW_SLIDE = "Window Slide";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node04TransformationMap.class,
                    true,
                    true,
                    true),
            new ToolSemanticsCondition(
                    Node06TransformationWindowOperation.class,
                    false,
                    true,
                    true),
    };

    public static final ToolPropertyOption[] WINDOW_TYPES = {
            new ToolPropertyOption("Tumbling", "Tumbling"),
            new ToolPropertyOption("Sliding", "Sliding")
    };
    public static final ToolPropertyOption[] WINDOW_UNITS = {
            new ToolPropertyOption("Hours", "hours"),
            new ToolPropertyOption("Minutes", "minutes"),
            new ToolPropertyOption("Seconds", "seconds")
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_WINDOW_TYPE,
                    "Tumbling",
                    PropertyInputType.SELECT,
                    WINDOW_TYPES,
                    "Pick type of window",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_WINDOW_UNITS,
                    "minutes",
                    PropertyInputType.SELECT,
                    WINDOW_UNITS,
                    "Pick time units for window definition",
                    "",
                    false),
            new ToolProperty(PROPERTY_WINDOW_SIZE,
                    "5",
                    PropertyInputType.TEXT,
                    null,
                    "Enter size",
                    "",
                    false),
            new ToolProperty(PROPERTY_SEPARATOR,
                    "",
                    PropertyInputType.SEPARATOR,
                    null,
                    "",
                    "",
                    false),
            new ToolProperty(PROPERTY_WINDOW_SLIDE,
                    "1",
                    PropertyInputType.TEXT,
                    null,
                    "",
                    "",
                    false)
    };

    public Node05TransformationWindow() {
        super(Node05TransformationWindow.NAME,
                TransformationWindowActor.class.getCanonicalName(),
                Node05TransformationWindow.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
