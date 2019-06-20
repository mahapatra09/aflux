package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.CepPatternSequenceEndActor;
import de.tum.in.aflux.tools.core.*;

public class Node11CepPatternEnd extends AbstractMainExecutor {

    public static final String NAME = "CEP end";

    public static final String PROPERTY_PATTERN_NAME = "Pattern name";
    public static final String PROPERTY_ELEMENT_NUMBER = "Element no.";
    public static final String PROPERTY_ALARM_MESSAGE = "Message";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node08CepPatternBegin.class,
                    true,
                    false,
                    true),
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_PATTERN_NAME,
                    "myPattern",
                    PropertyInputType.TEXT,
                    null,
                    "Name of the pattern to select",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_ELEMENT_NUMBER,
                    "0",
                    PropertyInputType.TEXT,
                    null,
                    "Element of the pattern to select",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_ALARM_MESSAGE,
                    "Alarm!! Event -> ",
                    PropertyInputType.TEXT,
                    null,
                    "Message for the alarm (Event data is added automatically)",
                    "",
                    false)
    };

    public Node11CepPatternEnd() {
        super(Node11CepPatternEnd.NAME,
                CepPatternSequenceEndActor.class.getCanonicalName(),
                Node11CepPatternEnd.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
