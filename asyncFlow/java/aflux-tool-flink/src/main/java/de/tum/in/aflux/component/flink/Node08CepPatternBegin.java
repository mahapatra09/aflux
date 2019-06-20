package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.CepPatternSequenceBeginActor;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.tools.core.*;

public class Node08CepPatternBegin extends AbstractMainExecutor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String NAME = "CEP begin";

    public static final String PROPERTY_SKIP_STRATEGY = "Skip Strategy";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node03TransformationFilter.class,
                    true,
                    false,
                    false),
            new ToolSemanticsCondition(
                    Node02SmartSantanderDataSource.class,
                    true,
                    false,
                    true),
            new ToolSemanticsCondition(
                    Node09CepPatternAppend.class,
                    false,
                    true,
                    true),
            new ToolSemanticsCondition(
                    Node11CepPatternEnd.class,
                    false,
                    false,
                    true),
    };

    public static final ToolPropertyOption[] SKIP_STRATEGIES = {
            new ToolPropertyOption("NO_SKIP", API.getMethodName("AfterMatchSkipStrategy.noSkip")),
            new ToolPropertyOption("SKIP_PAST_LAST_EVENT", API.getMethodName("AfterMatchSkipStrategy.skipPastLastEvent"))
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_SKIP_STRATEGY,
                    API.getMethodName("AfterMatchSkipStrategy.noSkip"),
                    PropertyInputType.SELECT,
                    SKIP_STRATEGIES,
                    "Skip strategy",
                    "",
                    false)
    };

    public Node08CepPatternBegin() {
        super(Node08CepPatternBegin.NAME,
                CepPatternSequenceBeginActor.class.getCanonicalName(),
                Node08CepPatternBegin.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
