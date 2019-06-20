package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.CepPatternAppendActor;
import de.tum.in.aflux.component.flink.api.FlinkApiMapper;
import de.tum.in.aflux.tools.core.*;

public class Node09CepPatternAppend extends AbstractMainExecutor {

    public static final FlinkApiMapper API = FlinkApiMapper.getInstance();

    public static final String NAME = "CEP new patt.";

    public static final String PROPERTY_PATTERN_NAME = "Pattern Name";
    public static final String PROPERTY_PATTERN_CONTIGUITY = "Contiguity";
    public static final String PROPERTY_PATTERN_REPEAT_MIN = "Min. Repetitions";
    public static final String PROPERTY_PATTERN_REPEAT_MAX = "Max. Repetitions";
    public static final String PROPERTY_PATTERN_QUANTIFIER_OPTIONAL = "Optional";
    public static final String PROPERTY_PATTERN_QUANTIFIER_GREEDY = "Greedy";
    public static final String PROPERTY_PATTERN_QUANTIFIER_CONSECUTIVE = "Consecutive";
    public static final String PROPERTY_PATTERN_WITHIN = "Within";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node08CepPatternBegin.class,
                    true,
                    false,
                    true),
            new ToolSemanticsCondition(
                    Node10CepPatternCondition.class,
                    false,
                    true,
                    true),
    };

    public static final ToolPropertyOption[] PATTERN_CONTIGUITY_OPTIONS = {
            new ToolPropertyOption("strict", API.getMethodName("Pattern.next")),
            new ToolPropertyOption("strict negative", API.getMethodName("Pattern.notNext")),
            new ToolPropertyOption("relaxed", API.getMethodName("Pattern.followedBy")),
            new ToolPropertyOption("relaxed negative", API.getMethodName("Pattern.notFollowedBy"))
    };

    public static ToolProperty[] properties = {
            new ToolProperty(
                    PROPERTY_PATTERN_NAME,
                    "myPattern",
                    PropertyInputType.TEXT,
                    null,
                    "Name for the pattern that will be created",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_CONTIGUITY,
                    API.getMethodName("Pattern.next"),
                    PropertyInputType.SELECT,
                    PATTERN_CONTIGUITY_OPTIONS,
                    "Choose contiguity",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_REPEAT_MIN,
                    "1",
                    PropertyInputType.TEXT,
                    null,
                    "Choose minimum number of repetitions",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_REPEAT_MAX,
                    "-1",
                    PropertyInputType.TEXT,
                    null,
                    "Choose maximum number of repetitions (-1 for infinite)",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_QUANTIFIER_CONSECUTIVE,
                    "false",
                    PropertyInputType.CHECKBOX,
                    null,
                    "Consecutive repetitions?",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_QUANTIFIER_OPTIONAL,
                    "false",
                    PropertyInputType.CHECKBOX,
                    null,
                    "Optional pattern?",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_QUANTIFIER_GREEDY,
                    "false",
                    PropertyInputType.CHECKBOX,
                    null,
                    "Greedy pattern?",
                    "",
                    false),
            new ToolProperty(
                    PROPERTY_PATTERN_WITHIN,
                    "60",
                    PropertyInputType.TEXT,
                    null,
                    "Time to match pattern (seconds) (-1 for infinite)",
                    "",
                    false)
    };

    public Node09CepPatternAppend() {
        super(Node09CepPatternAppend.NAME,
                CepPatternAppendActor.class.getCanonicalName(),
                Node09CepPatternAppend.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                properties,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
