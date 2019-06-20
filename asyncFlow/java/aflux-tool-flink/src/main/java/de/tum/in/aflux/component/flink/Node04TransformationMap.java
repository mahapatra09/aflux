package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.TransformationMapActor;
import de.tum.in.aflux.tools.core.*;

public class Node04TransformationMap extends AbstractMainExecutor {

    public static final String NAME = "Select";

    public static final String PROPERTY_ATTRIBUTE_TO_MAP = "Attribute to map";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node02SmartSantanderDataSource.class,
                    true,
                    false,
                    true),
    };

    public static final ToolPropertyOption[] SMARTSANTANDER_API_MODEL_ATTRIBUTES = {
            new ToolPropertyOption("Traffic Occupation", "Occupation"),
            new ToolPropertyOption("Traffic Charge", "Charge"),
            new ToolPropertyOption("Traffic Intensity", "Intensity"),
            new ToolPropertyOption("Noise Level", "Noise"),
            new ToolPropertyOption("Temperature", "Temperature"),
            new ToolPropertyOption("Light Intensity", "LightIntensity"),
            new ToolPropertyOption("Level of NO2", "LevelOfNO2"),
            new ToolPropertyOption("Level of CO", "LevelOfCO"),
            new ToolPropertyOption("Level of Ozone", "LevelOfOzone"),
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_ATTRIBUTE_TO_MAP,
                    "charge",
                    PropertyInputType.SELECT,
                    SMARTSANTANDER_API_MODEL_ATTRIBUTES,
                    "Pick attribute",
                    "",
                    false)
    };

    public Node04TransformationMap() {
        super(Node04TransformationMap.NAME,
                TransformationMapActor.class.getCanonicalName(),
                Node04TransformationMap.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
