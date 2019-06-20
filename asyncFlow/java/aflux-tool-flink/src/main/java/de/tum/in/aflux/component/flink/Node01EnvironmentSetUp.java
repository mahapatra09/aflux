package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.EnvironmentSetUpActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolSemanticsCondition;

public class Node01EnvironmentSetUp extends AbstractMainExecutor {

    public static final String NAME = "Begin Job";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node99ExecuteAndGenerateJob.class,
                    false,
                    false,
                    true)
    };

    public Node01EnvironmentSetUp() {
        super(Node01EnvironmentSetUp.NAME,
                EnvironmentSetUpActor.class.getCanonicalName(),
                Node01EnvironmentSetUp.class.getName(),
                0,
                1,
                NodeLaunchType.LAUNCHED_BY_SIGNAL,
                false,
                null,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
