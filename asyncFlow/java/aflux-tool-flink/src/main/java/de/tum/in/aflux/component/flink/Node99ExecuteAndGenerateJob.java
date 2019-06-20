package de.tum.in.aflux.component.flink;

import de.tum.in.aflux.component.flink.actor.ExecuteAndGenerateJobActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolSemanticsCondition;

public class Node99ExecuteAndGenerateJob extends AbstractMainExecutor {

    public static final String NAME = "End Job";

    public static final ToolSemanticsCondition[] semanticsConditions = {
            new ToolSemanticsCondition(
                    Node01EnvironmentSetUp.class,
                    true,
                    false,
                    true),
    };

    public Node99ExecuteAndGenerateJob() {
        super(Node99ExecuteAndGenerateJob.NAME,
                ExecuteAndGenerateJobActor.class.getCanonicalName(),
                Node99ExecuteAndGenerateJob.class.getName(),
                1,
                0,
                NodeLaunchType.LAUNCHED_BY_DATA,
                false,
                null,
                semanticsConditions);
        this.setColor("#e65270");
    }

}
