package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.OutputResultActor;
import de.tum.in.aflux.tools.core.*;

public class Node07OutputResult extends AbstractMainExecutor {

    public static final String NAME = "Output Result";
    public static final String PROPERTY_OUTPUT_TYPE = "Output Type";
    public static final String PROPERTY_KAFKA_TOPIC = "Kafka Topic";
    public static final String PROPERTY_KAFKA_ADDRESS = "Kafka Address";
    public static final String PROPERTY_KAFKA_LOG_IDENTIFIER = "Log identifier";
    public static final String PROPERTY_CSV_FILENAME = "CSV Filename";

    public static final ToolPropertyOption[] OUTPUT_TYPES = {
            new ToolPropertyOption("Plain Text", "console"),
            new ToolPropertyOption("Kafka", "kafka"),
            new ToolPropertyOption("CSV", "csv")
    };

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node01EnvironmentSetUp.class,
                    true,
                    false,
                    true),
            new ToolSemanticsCondition(
                    Node02SmartSantanderDataSource.class,
                    true,
                    false,
                    true),
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_OUTPUT_TYPE,
                    "kafka",
                    PropertyInputType.SELECT,
                    OUTPUT_TYPES,
                    "Choose the type of output",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_KAFKA_TOPIC,
                    "wiki-result",
                    PropertyInputType.TEXT,
                    null,
                    "Kafka topic to be used",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_KAFKA_ADDRESS,
                    "localhost:9092",
                    PropertyInputType.TEXT,
                    null,
                    "Kafka address to be used",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_KAFKA_LOG_IDENTIFIER,
                    "output",
                    PropertyInputType.TEXT,
                    null,
                    "Identifier for the logs in Kafka",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_CSV_FILENAME,
                    "output.csv",
                    PropertyInputType.TEXT,
                    null,
                    "Filename for the output CSV",
                    "",
                    false)
    };

    public Node07OutputResult() {
        super(Node07OutputResult.NAME,
                OutputResultActor.class.getCanonicalName(),
                Node07OutputResult.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
