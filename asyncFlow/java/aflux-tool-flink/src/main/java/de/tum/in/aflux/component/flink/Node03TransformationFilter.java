package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.TransformationFilterActor;
import de.tum.in.aflux.tools.core.*;

public class Node03TransformationFilter extends AbstractMainExecutor {

    public static final String NAME = "GPS filter";
    public static final String PROPERTY_FILTERING_LOCATION = "Filtering location";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node02SmartSantanderDataSource.class,
                    true,
                    true,
                    true),
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_FILTERING_LOCATION,
                    "43.4611843213236,-3.8074053793457097,1000", // serialized as "<lat>,<lng>,<rad>"
                    PropertyInputType.LOCATION_PICKER,
                    null,
                    "Enter location to filter and radius",
                    "",
                    false)
    };

    public Node03TransformationFilter() {
        super(Node03TransformationFilter.NAME,
                TransformationFilterActor.class.getCanonicalName(),
                Node03TransformationFilter.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
